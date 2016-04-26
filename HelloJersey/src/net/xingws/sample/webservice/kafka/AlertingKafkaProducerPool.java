/**
 * 
 */
package net.xingws.sample.webservice.kafka;

import java.util.Properties;

import org.apache.commons.pool2.impl.GenericObjectPool;


/**
 * @author bxing
 *
 */
public class AlertingKafkaProducerPool {
	private static AlertingKafkaProducerPool instance = null;
	private GenericObjectPool<AlertingKafkaProducer> pool;
	
	private AlertingKafkaProducerPool() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		pool = new GenericObjectPool<AlertingKafkaProducer>(new AlertingKafkaProducerFactory(properties));
		
		pool.setMaxTotal(20);
		pool.setMaxIdle(20);
		
	}
	
	public static synchronized AlertingKafkaProducerPool getInstance() {
		if(instance == null) {
			instance = new AlertingKafkaProducerPool();
		}
		
		return instance;
	}
	
	public GenericObjectPool<AlertingKafkaProducer> getPool() {
		return this.pool;
	}
}
