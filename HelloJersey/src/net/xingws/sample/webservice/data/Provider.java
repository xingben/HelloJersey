/**
 * 
 */
package net.xingws.sample.webservice.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author benxing
 *
 */
@XmlRootElement(name="provider")
public class Provider {
	private String id;
	private Contact contact;
	
	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@XmlElement
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the contact
	 */
	public final Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	@XmlElement
	public final void setContact(Contact contact) {
		this.contact = contact;
	}
}
