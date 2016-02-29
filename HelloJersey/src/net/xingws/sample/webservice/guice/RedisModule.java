/**
 * 
 */
package net.xingws.sample.webservice.guice;

import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;

import net.xingws.sample.webservice.serializer.JacksonSerializer;
import net.xingws.sample.webservice.serializer.Serializer;
import net.xingws.sample.webservice.util.RedisPool;

/**
 * @author benxing
 *
 */
public class RedisModule extends ServletModule {

	
	 @Override protected void configureServlets() {
		 this.bind(String.class).annotatedWith(Names.named("redis host")).toInstance("localhost");
		 this.bind(Integer.class).annotatedWith(Names.named("redis port")).toInstance(6379);
		 this.bind(Integer.class).annotatedWith(Names.named("redis max connection")).toInstance(8);
		 
		 this.bind(String.class).annotatedWith(Names.named("providers key")).toInstance("provider:sorted:set");
		 this.bind(String.class).annotatedWith(Names.named("providers map keys")).toInstance("provider:email:map");
		 
		 this.bind(Serializer.class).to(JacksonSerializer.class).in(Singleton.class);
		 this.bind(RedisPool.class).in(Singleton.class);
    }

}
//@Inject
//public RedisDao(RedisPool pool, 
//		Serializer serializer, 
//		@Named("providers key") String providersKey,
//		@Named("providers map keys") String mapKey) {