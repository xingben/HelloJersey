/**
 * 
 */
package net.xingws.sample.webservice.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import net.xingws.sample.webservice.exception.XingwsSampleProviderExistException;

/**
 * @author benxing
 *
 */
public class EntityExistExceptionMapper implements ExceptionMapper<XingwsSampleProviderExistException> {

	@Override
	public Response toResponse(XingwsSampleProviderExistException ex) {
		return Response.status(489).entity(ex.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}
}
