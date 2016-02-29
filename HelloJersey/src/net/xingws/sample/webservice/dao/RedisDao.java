/**
 * 
 */
package net.xingws.sample.webservice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import net.xingws.sample.webservice.data.Provider;
import net.xingws.sample.webservice.exception.XingwsSampleException;
import net.xingws.sample.webservice.serializer.Serializer;
import net.xingws.sample.webservice.util.RedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author benxing
 *
 */
@Singleton
public class RedisDao implements Dao {
	private Serializer serializer;
	private RedisPool redispool;
	private String providersKey;
	private String mapKey;

	@Inject
	public RedisDao(RedisPool pool, 
			Serializer serializer, 
			@Named("providers key") String providersKey,
			@Named("providers map keys") String mapKey) {
		this.serializer = serializer;
		this.redispool = pool;
		this.providersKey = providersKey;
		this.mapKey = mapKey;
	}

	@Override
	public boolean providerExists(String id) {
		Jedis jedis = this.redispool.getRedisPool().getResource();
		boolean exists = false;

		try {
			String key = jedis.hget(this.mapKey, id);
			if (key != null) {
				exists = jedis.exists(key);
			}
		} finally {
			this.close(jedis);
		}

		return exists;
	}

	@Override
	public boolean contactExists(String email) {
		Jedis jedis = this.redispool.getRedisPool().getResource();
		boolean exists = false;

		try {
			exists = jedis.exists(email.toLowerCase());

		} finally {
			this.close(jedis);
		}

		return exists;
	}

	@Override
	public void updateProvider(Provider provider) throws XingwsSampleException{
		Jedis jedis = this.redispool.getRedisPool().getResource();
		
		try {
			String id = provider.getId();
			String email = provider.getContact().getEmail().toLowerCase();
			Transaction tranction = jedis.multi();
			tranction.hset(this.mapKey, id, email);
			tranction.zadd(this.providersKey, 0.0, email);
			tranction.set(email, this.serializer.serialize(provider));
			tranction.exec();
			
			
		} finally {
			this.close(jedis);
		}
	}

	@Override
	public Provider getProvider(String id) throws XingwsSampleException{
		Jedis jedis = this.redispool.getRedisPool().getResource();
		Provider provider = null;
		
		try {
			String email = jedis.hget(this.providersKey, id);
			
			if(email != null) {
				provider = this.serializer.deserialize(jedis.get(email), Provider.class);
			}
		} finally {
			this.close(jedis);
		}		
		return provider;
	}

	@Override
	public List<Provider> getProviders(long start, long size) throws XingwsSampleException{
		Jedis jedis = this.redispool.getRedisPool().getResource();
		List<Provider> providers = new ArrayList<Provider>();
		List<String> values = null;
		
		try {
			long count = jedis.zcard(providersKey);
			if(start < 0) start = 0;
			long end = start + size - 1;
			if(end >= count) end = -1; 

			Set<String> sets = jedis.zrange(providersKey, start, end);
			String[] keys = sets.toArray(new String[sets.size()]);
			values = jedis.mget(keys);
		} finally {
			this.close(jedis);
		}
		
		if(values != null) {
			for(String value : values) {
				providers.add(this.serializer.deserialize(value, Provider.class));
			}
		}
		
		return providers;
	}

	private void close(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
}
