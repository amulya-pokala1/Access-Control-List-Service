package com.accolite.miniau.accesscontrol.customexception;

public class CustomBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomBadRequestException(String msg) {
		super(msg);
	}
}
