/**
 * 
 */
package net.xingws.sample.webservice.guice;

import java.util.Collections;
import java.util.List;

import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;
import com.squarespace.jersey2.guice.JerseyGuiceServletContextListener;

import net.xingws.sample.webservice.dao.Dao;
import net.xingws.sample.webservice.dao.RedisDao;
import net.xingws.sample.webservice.service.ProviderService;
import net.xingws.sample.webservice.service.ProviderServiceImp;

/**
 * @author benxing
 *
 */
public class ProviderContextListener extends JerseyGuiceServletContextListener {

	@Override
	protected List<? extends Module> modules() {
		// TODO Auto-generated method stub
		return Collections.singletonList(new ServletModule() {
			@Override
			protected void configureServlets() {
				this.install(new RedisModule());
				this.bind(Dao.class).to(RedisDao.class);
				this.bind(ProviderService.class).to(ProviderServiceImp.class);
			}
		});
	}

}
