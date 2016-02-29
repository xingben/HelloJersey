/**
 * 
 */
package net.xingws.sample.webservice.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import net.xingws.sample.webservice.exception.XingwsSampleInternalException;

/**
 * @author benxing
 *
 */
public class InternalExceptionMapper implements ExceptionMapper<XingwsSampleInternalException> {

	@Override
	public Response toResponse(XingwsSampleInternalException ex) {
		return Response.status(500).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}

}
