/**
 * 
 */
package net.xingws.sample.webservice.exception;

/**
 * @author benxing
 *
 */
public class XingwsSerializationException extends XingwsSampleException {

	private static final long serialVersionUID = 8461760700663177598L;

	public XingwsSerializationException() {
	}

	/**
	 * @param message
	 */
	public XingwsSerializationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public XingwsSerializationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public XingwsSerializationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public XingwsSerializationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
