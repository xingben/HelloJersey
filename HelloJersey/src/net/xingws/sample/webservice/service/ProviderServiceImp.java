/**
 * 
 */
package net.xingws.sample.webservice.service;

import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.xingws.sample.webservice.dao.Dao;
import net.xingws.sample.webservice.data.Contact;
import net.xingws.sample.webservice.data.Provider;
import net.xingws.sample.webservice.data.Providers;
import net.xingws.sample.webservice.exception.XingwsSampleException;
import net.xingws.sample.webservice.exception.XingwsSampleInternalException;
import net.xingws.sample.webservice.exception.XingwsSampleProviderExistException;
import net.xingws.sample.webservice.exception.XingwsSampleProviderNotExistException;

/**
 * @author benxing
 *
 */
public class ProviderServiceImp implements ProviderService {
	private static Logger logger = LoggerFactory.getLogger(ProviderServiceImp.class);

	private Dao dao;

	@Inject
	public ProviderServiceImp(Dao dao) {
		this.dao = dao;
	}

	@Override
	public Provider createProvider(Contact contact) throws XingwsSampleException {
		Provider provider = new Provider();
		String id = UUID.randomUUID().toString();
		provider.setId(id);
		provider.setContact(contact);

		if (dao.contactExists(contact.getEmail())) {
			logger.error(contact.getEmail() + " exists");
			throw new XingwsSampleProviderExistException(contact.getEmail() + " exists");
		}
		
		try {
			this.dao.updateProvider(provider);
		} catch (Exception ex) {
			logger.error("Service exception in Dao", ex);
			throw new XingwsSampleInternalException(ex);
		}

		return provider;
	}

	@Override
	public Provider updateProvider(Provider provider) throws XingwsSampleException {

		if (!dao.providerExists(provider.getId())) {
			logger.error(provider.getId() + " does not exist");
			throw new XingwsSampleProviderNotExistException(provider.getId() + " does not exist");
		}
		
		try {
			this.dao.updateProvider(provider);
		} catch (Exception ex) {
			logger.error("Service exception in Dao", ex);
			throw new XingwsSampleInternalException(ex);
		}

		return provider;
	}

	@Override
	public Provider getProvider(String id) throws XingwsSampleException {
		Provider provider = null;

		if (!dao.providerExists(id)) {
			logger.error(id + " does not exist");
			throw new XingwsSampleProviderNotExistException(id + " does not exist");
		}
		
		try {
			provider = this.dao.getProvider(id);
		} catch (Exception ex) {
			logger.error("Service exception in Dao", ex);
			throw new XingwsSampleInternalException(ex);
		}

		return provider;
	}

	@Override
	public Providers getProviders(long start, long size) throws XingwsSampleException {
		Providers providers = null;

		try {
			providers = this.dao.getProviders(start, size);
		} catch (Exception ex) {
			logger.error("Service exception in Dao", ex);
			throw new XingwsSampleInternalException(ex);
		}
		
		return providers;
	}
}
