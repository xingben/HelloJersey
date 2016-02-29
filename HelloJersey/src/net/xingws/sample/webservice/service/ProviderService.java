/**
 * 
 */
package net.xingws.sample.webservice.service;

import net.xingws.sample.webservice.data.Contact;
import net.xingws.sample.webservice.data.Provider;
import net.xingws.sample.webservice.data.Providers;
import net.xingws.sample.webservice.exception.XingwsSampleException;

/**
 * @author benxing
 *
 */
public interface ProviderService {
	/**
	 * @param contact
	 * @return
	 * @throws XingwsSampleException
	 */
	Provider createProvider(Contact contact) throws XingwsSampleException;
	
	/**
	 * @param provider
	 * @return
	 * @throws XingwsSampleException
	 */
	Provider updateProvider(Provider provider) throws XingwsSampleException;
	
	/**
	 * @param id
	 * @return
	 * @throws XingwsSampleException
	 */
	Provider getProvider(String id) throws XingwsSampleException;
	
	/**
	 * @return
	 * @throws XingwsSampleException
	 */
	Providers getProviders(long start, long size) throws XingwsSampleException;
}
