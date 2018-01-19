/*
 * 
 */
package com.accolite.miniau.accesscontrol.customexception;

/**
 * The Class CustomUnAuthorizedException.
 */
public class CustomUnAuthorizedException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new custom un authorized exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public CustomUnAuthorizedException(String msg) {
		super(msg);
	}
}
