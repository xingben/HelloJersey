/**
 * 
 */
package net.xingws.sample.webservice.reponse;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.xingws.sample.webservice.data.Provider;

/**
 * @author benxing
 *
 */
@XmlRootElement
public class ProviderResponse {
	@XmlElement
	private Provider provider;
	
	@XmlElement(name = "link")
	@XmlJavaTypeAdapter(LinkAdapter.class) 
	private List<Link> links;
	
	public ProviderResponse() {
	}

	/**
	 * @return the provider
	 */
	public final Provider getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public final void setProvider(Provider provider) {
		this.provider = provider;
	}

	/**
	 * @return the links
	 */
	public final List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public final void setLinks(List<Link> links) {
		this.links = links;
	}

}
