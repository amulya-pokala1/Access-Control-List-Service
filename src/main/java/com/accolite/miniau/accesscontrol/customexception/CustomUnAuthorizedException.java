package com.accolite.miniau.accesscontrol.customexception;

public class CustomUnAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomUnAuthorizedException(String msg) {
		super(msg);
	}
}
