/**
 * 
 */
package net.xingws.sample.webservice.serializer;

import net.xingws.sample.webservice.exception.XingwsSerializationException;

/**
 * @author benxing
 *
 */
public interface Serializer {
	/**
	 * @param payload
	 * @param t
	 * @return
	 * @throws XingwsSerializationException
	 */
	<T> T deserialize(String payload, Class<T> t) throws XingwsSerializationException;

	/**
	 * @param obj
	 * @return
	 * @throws XingwsSerializationException
	 */
	String serialize(Object obj)  throws XingwsSerializationException ;
}
