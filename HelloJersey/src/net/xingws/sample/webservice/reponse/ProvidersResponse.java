/**
 * 
 */
package net.xingws.sample.webservice.reponse;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.xingws.sample.webservice.data.Providers;

/**
 * @author benxing
 *
 */
@XmlRootElement
public class ProvidersResponse {
	private Providers providers;
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
	public final Providers getProviders() {
		return providers;
	}
	/**
	 * @param providers the providers to set
	 */
	@XmlElement
	public final void setProviders(Providers providers) {
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
	@XmlElement(name = "link")
	@XmlJavaTypeAdapter(LinkAdapter.class) 
	public final void setLinks(List<Link> links) {
		this.links = links;
	}
	
	@XmlTransient
	public void buildLinks(UriBuilder builder, MediaType type, long start, long size) {
		this.links = new ArrayList<Link>();
		long begin = 0;
		
		if(start > 0) {//build previous
			begin = (start - size + 1)>=0 ? (start - size + 1) : 0;
			Link link = Link.fromUri(builder.clone().build(begin, size)).
					rel("previous").type(type.toString()).build();
			this.links.add(link);
		}
		
		if(start + size < this.providers.getTotalProvider()) {
			begin = start + size;
			Link link = Link.fromUri(builder.clone().build(begin, size)).
					rel("next").type(type.toString()).build();
			this.links.add(link);			
		}
		
	}

}
