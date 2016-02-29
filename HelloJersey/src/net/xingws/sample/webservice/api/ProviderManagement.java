/**
 * 
 */
package net.xingws.sample.webservice.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import net.xingws.sample.webservice.data.Contact;
import net.xingws.sample.webservice.data.Provider;
import net.xingws.sample.webservice.exception.XingwsSampleException;
import net.xingws.sample.webservice.service.ProviderService;
/**
 * @author benxing
 *
 */
@Path("/ProviderManagement")
public class ProviderManagement {

	private ProviderService service;
	
	@Inject
	public ProviderManagement(ProviderService service) {
		this.service = service;
	}
	
	@GET
	@Path("/provider/{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getProvider(@Context HttpHeaders headers,
			@PathParam("id") String id) throws XingwsSampleException {
		MediaType type = headers.getAcceptableMediaTypes().get(0);
		
		CacheControl cc = new CacheControl();
		cc.setPrivate(false);
		cc.setMaxAge(60);
		cc.setNoStore(true);
		
		Provider provider = service.getProvider(id);
		
		
		Response.ResponseBuilder builder = Response.ok(provider, type);
		
		return builder.build();
	}
	
	@POST
	@Path("/provider/create")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createProvider(@Context HttpHeaders headers,
			Contact contact)  throws XingwsSampleException {
		MediaType type = headers.getAcceptableMediaTypes().get(0);
		Provider provider = service.createProvider(contact);
		
		Response.ResponseBuilder builder = Response.ok(provider, type);
		
		return builder.build();
	}
	
	@PUT
	@Path("/provider/update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateProvider(@Context HttpHeaders headers,
			Provider provider)  throws XingwsSampleException {
		MediaType type = headers.getAcceptableMediaTypes().get(0);
		service.updateProvider(provider);
		
		Response.ResponseBuilder builder = Response.ok(provider, type);
		
		return builder.build();
	}
	
}
