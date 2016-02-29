/**
 * 
 */
package net.xingws.sample.webservice.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import net.xingws.sample.webservice.exception.XingwsSampleProviderNotExistException;

/**
 * @author benxing
 *
 */
public class EntityNotExistExceptionMapper implements ExceptionMapper<XingwsSampleProviderNotExistException> {


	@Override
	public Response toResponse(XingwsSampleProviderNotExistException ex) {
		return Response.status(404).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}
}
