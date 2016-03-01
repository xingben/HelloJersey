/**
 * 
 */
package net.xingws.sample.webservice.serializer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.xingws.sample.webservice.exception.XingwsSerializationException;

/**
 * @author benxing
 *
 */
public class JacksonSerializer implements Serializer {
	private static Logger logger = LoggerFactory.getLogger(JacksonSerializer.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public <T> T deserialize(String payload, Class<T> t) throws XingwsSerializationException {
		T ret = null;
		
		if(payload != null && !payload.isEmpty()) {
			try {
				ret = mapper.readValue(payload, t);
			} catch (IOException e) {
				logger.error("Failed to deserilize object:\n" + payload, e);
				throw new XingwsSerializationException(e);
			}
		}
		
		return ret;
	}

	@Override
	public String serialize(Object obj) throws XingwsSerializationException  {
		String ret = null;
		
		if(obj != null) {
			try {
				ret = this.mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				logger.error("Failed to serilize object:", e);
				throw new XingwsSerializationException(e);
			}
		}
		
		return ret;
	}
}
