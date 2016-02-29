/**
 * 
 */
package net.xingws.sample.webservice.exception;

/**
 * @author benxing
 *
 */
public class XingwsSampleException extends Exception {

	private static final long serialVersionUID = 1870365501378258380L;

	public XingwsSampleException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public XingwsSampleException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public XingwsSampleException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public XingwsSampleException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public XingwsSampleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
