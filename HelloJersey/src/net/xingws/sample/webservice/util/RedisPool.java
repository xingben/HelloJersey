/**
 * 
 */
package net.xingws.sample.webservice.util;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author benxing
 *
 */
@Singleton
public class RedisPool {
	private JedisPool pool;
	
	@Inject
	public RedisPool(@Named("redis host")String host, 
			@Named("redis port")int port, 
			@Named("redis max connection") int maxConnection) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setBlockWhenExhausted(false);
		config.setMaxTotal(maxConnection);
		this.pool = new JedisPool(config, host, port);
	}
	
	public JedisPool getRedisPool() {
		return this.pool;
	}
}
