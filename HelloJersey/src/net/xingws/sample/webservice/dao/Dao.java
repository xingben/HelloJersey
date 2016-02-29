/**
 * 
 */
package net.xingws.sample.webservice.dao;

import java.util.List;

import net.xingws.sample.webservice.data.Provider;
import net.xingws.sample.webservice.exception.XingwsSampleException;

/**
 * @author benxing
 *
 */
public interface Dao {
	/**
	 * Validate provider exists
	 * @param id
	 * @return
	 */
	boolean providerExists(String id);
	
	/**
	 * @param email
	 * @return
	 */
	boolean contactExists(String email);
	
	/**
	 * @param provider
	 */
	void updateProvider(Provider provider) throws XingwsSampleException;
	
	/**
	 * @param id
	 * @return
	 */
	Provider getProvider(String id) throws XingwsSampleException;
	
	/**
	 * @return
	 */
	List<Provider> getProviders(long start, long size) throws XingwsSampleException;
}
