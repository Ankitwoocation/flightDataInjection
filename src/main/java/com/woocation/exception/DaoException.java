package com.woocation.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class DaoException.
 */
public class DaoException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8735536706373616898L;

	/**
	 * Instantiates a new dao exception.
	 */
	public DaoException() {
		super();
	}

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param enableSuppression
	 *            the enable suppression
	 * @param writableStackTrace
	 *            the writable stack trace
	 */
	public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param message
	 *            the message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}

}
