/*
 * 
 */
package com.accolite.miniau.accesscontrol.advice;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;
import com.accolite.miniau.accesscontrol.customexception.CustomUnAuthorizedException;

// TODO: Auto-generated Javadoc
/**
 * The Class DefinedExceptionHandler.
 */
@RestControllerAdvice
public class DefinedExceptionHandler {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(DefinedExceptionHandler.class);

	/**
	 * Not found.
	 *
	 * @param e the e
	 * @return the string
	 */
	@ExceptionHandler(CustomNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound(CustomNotFoundException e) {
		logger.warn("Invalid URL PATH", e);
		return e.getMessage();
	}

	/**
	 * Bad request.
	 *
	 * @param e the e
	 * @return the string
	 */
	@ExceptionHandler(CustomBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badRequest(CustomBadRequestException e) {
		logger.warn("Bad Request ", e);
		return e.getMessage();
	}

	/**
	 * Un authorized.
	 *
	 * @param e the e
	 * @return the string
	 */
	@ExceptionHandler(CustomUnAuthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String unAuthorized(CustomUnAuthorizedException e) {
		logger.warn("UnAuthorized user ", e);
		return e.getMessage();
	}
}
