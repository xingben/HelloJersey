/**
 * 
 */
package net.xingws.sample.webservice.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.xingws.sample.webservice.data.Alert;
import net.xingws.sample.webservice.data.Contact;
import net.xingws.sample.webservice.data.Provider;
import net.xingws.sample.webservice.data.Providers;
import net.xingws.sample.webservice.exception.XingwsSampleException;
import net.xingws.sample.webservice.interceptor.Compress;
import net.xingws.sample.webservice.kafka.AlertingKafkaProducer;
import net.xingws.sample.webservice.kafka.AlertingKafkaProducerPool;
import net.xingws.sample.webservice.reponse.ProvidersResponse;
import net.xingws.sample.webservice.service.ProviderService;
/**
 * @author benxing
 *
 */
@Path("/ProviderManagement")
public class ProviderManagement {

	private ProviderService service;
	private AlertingKafkaProducerPool pool = AlertingKafkaProducerPool.getInstance();
	ObjectMapper mapper = new ObjectMapper();
	
	@Inject
	public ProviderManagement(ProviderService service) {
		this.service = service;
	}
	
	@GET
	@Path("/provider/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})	
	//@Produces(MediaType.APPLICATION_XML)		
	public Response getProvider(@PathParam("id") String id,
			@DefaultValue("json") @QueryParam("format") String format) throws XingwsSampleException {
		MediaType type = MediaType.APPLICATION_JSON_TYPE;
		
		if(format.equalsIgnoreCase("xml")) {
			type = MediaType.APPLICATION_XML_TYPE;
		}
		
		CacheControl cc = new CacheControl();
		cc.setPrivate(false);
		cc.setMaxAge(60);
		cc.setNoStore(true);
		
		Provider provider = service.getProvider(id);
		
		Response.ResponseBuilder builder = Response.status(200).entity(provider).type(type).cacheControl(cc);
		
		return builder.build();
	}
	
	@POST
	@Path("/create/provider")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createProvider(Contact contact,
			@DefaultValue("json") @QueryParam("format") String format)  throws XingwsSampleException {
		MediaType type = MediaType.APPLICATION_JSON_TYPE;
		
		if(format.equalsIgnoreCase("xml")) {
			type = MediaType.APPLICATION_XML_TYPE;
		}
		Provider provider = service.createProvider(contact);
		
		Response.ResponseBuilder builder = Response.ok(provider, type);
		
		return builder.build();
	}
	
	@POST
	@Path("/create/alert")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createAlert(Alert alert)  throws XingwsSampleException {
		
		try {
			AlertingKafkaProducer producer = pool.getPool().borrowObject();
			producer.send(alert.getName()+"_"+alert.getServer(), mapper.writeValueAsString(alert), "Alerting");
			pool.getPool().returnObject(producer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new XingwsSampleException(e);
		}
		
		Response.ResponseBuilder builder = Response.ok();
		
		return builder.build();
	}
	
	@PUT
	@Path("/update/provider")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateProvider(Provider provider, 
			@DefaultValue("json") @QueryParam("format") String format)  throws XingwsSampleException {
		MediaType type = MediaType.APPLICATION_JSON_TYPE;
		
		if(format.equalsIgnoreCase("xml")) {
			type = MediaType.APPLICATION_XML_TYPE;
		}
		service.updateProvider(provider);
		
		Response.ResponseBuilder builder = Response.ok(provider, type);
		
		return builder.build();
	}
	
	@GET
	@Path("/providers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Compress
	public Response getProviders(@Context UriInfo uriInfo, @DefaultValue("0") @QueryParam("start") long start,
			@DefaultValue("5") @QueryParam("size") long size,
			@DefaultValue("json") @QueryParam("format") String format)  throws XingwsSampleException {
		
		if(start < 0 ) start = 0;
		if(size <= 0) size = 5;
		
		MediaType type = MediaType.APPLICATION_JSON_TYPE;
		
		if(format.equalsIgnoreCase("xml")) {
			type = MediaType.APPLICATION_XML_TYPE;
		}
		Providers providers = service.getProviders(start, size);
		
		UriBuilder uribuilder = uriInfo.getBaseUriBuilder();
		uribuilder.path("ProviderManagement/providers");
		uribuilder.queryParam("start","{start}");
		uribuilder.queryParam("size","{size}");
		
//		ProvidersResponse response = new ProvidersResponse();
//		response.setProviders(providers);
		providers.buildLinks(uribuilder, type, start, size);
		
		Response.ResponseBuilder builder = Response.ok(providers, type);
		
		return builder.build();
	}
	
	@GET
	@Path("/query/providers")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response queryProviders(@Context UriInfo uriInfo, 
			@DefaultValue("0") @QueryParam("start") long start,
			@DefaultValue("5") @QueryParam("size") long size,
			@DefaultValue("json") @QueryParam("format") String format)  throws XingwsSampleException {
		
		if(start < 0 ) start = 0;
		if(size <= 0) size = 5;
		
		MediaType type = MediaType.APPLICATION_JSON_TYPE;
		
		if(format.equalsIgnoreCase("xml")) {
			type = MediaType.APPLICATION_XML_TYPE;
		}
		Providers providers = service.getProviders(start, size);
		
		Response response = Response.created(uriInfo.getAbsolutePath())
				.status(Status.OK).entity(new GenericEntity<List<Provider>>(providers.getProviders()){}).type(type).build();

		return response;
	}
}
