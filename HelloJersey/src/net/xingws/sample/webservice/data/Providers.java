/**
 * 
 */
package net.xingws.sample.webservice.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author benxing
 *
 */
@XmlRootElement
public class Providers {
	@XmlElement
	private List<Provider> providers;
	@XmlElement
	private long totalProvider;
	
	public Providers() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the providers
	 */
	public final List<Provider> getProviders() {
		return providers;
	}

	/**
	 * @param providers the providers to set
	 */
	public final void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	/**
	 * @return the totalProvider
	 */
	public final long getTotalProvider() {
		return totalProvider;
	}

	/**
	 * @param totalProvider the totalProvider to set
	 */
	public final void setTotalProvider(long totalProvider) {
		this.totalProvider = totalProvider;
	}

}
