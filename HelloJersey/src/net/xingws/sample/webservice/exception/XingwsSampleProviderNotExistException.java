/**
 * 
 */
package net.xingws.sample.webservice.exception;

/**
 * @author benxing
 *
 */
public class XingwsSampleProviderNotExistException extends XingwsSampleException {

	private static final long serialVersionUID = 6298068479221694457L;

	public XingwsSampleProviderNotExistException() {
	}

	/**
	 * @param message
	 */
	public XingwsSampleProviderNotExistException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public XingwsSampleProviderNotExistException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public XingwsSampleProviderNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public XingwsSampleProviderNotExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
