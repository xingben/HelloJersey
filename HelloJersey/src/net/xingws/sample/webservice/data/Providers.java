/**
 * 
 */
package net.xingws.sample.webservice.data;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.xingws.sample.webservice.reponse.LinkAdapter;

/**
 * @author benxing
 *
 */
@XmlRootElement
public class Providers {
	private List<Provider> providers;
	private long totalProvider;
	private List<Link> links;
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
	@XmlElementRef(name="providers")
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
	@XmlElement	
	public final void setTotalProvider(long totalProvider) {
		this.totalProvider = totalProvider;
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
	
	///@XmlTransient
	public void buildLinks(UriBuilder builder, MediaType type, long start, long size) {
		this.links = new ArrayList<Link>();
		long begin = 0;
		
		if(start > 0) {//build previous
			begin = (start - size)>=0 ? (start - size) : 0;
			URI uri = builder.clone().build(begin, size);
			Link link = Link.fromUri(uri).
					rel("previous").type(type.toString()).build();
			this.links.add(link);
		}
		
		if(start + size < this.totalProvider) {
			begin = start + size;
			Link link = Link.fromUri(builder.clone().build(begin, size)).
					rel("next").type(type.toString()).build();
			this.links.add(link);			
		}
		
	}

}
