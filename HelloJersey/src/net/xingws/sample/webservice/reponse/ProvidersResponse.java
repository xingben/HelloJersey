/**
 * 
 */
package net.xingws.sample.webservice.reponse;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.xingws.sample.webservice.data.Provider;

/**
 * @author benxing
 *
 */
public class ProvidersResponse {

	@XmlElement
	private List<Provider> providers;
	

	@XmlElement(name = "link")
	@XmlJavaTypeAdapter(LinkAdapter.class) 
	private List<Link> links;
	/**
	 * 
	 */
	public ProvidersResponse() {
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
