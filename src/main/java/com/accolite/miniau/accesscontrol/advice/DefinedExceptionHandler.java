package com.accolite.miniau.accesscontrol.advice;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.accolite.miniau.accesscontrol.customexception.CustomBadRequestException;
import com.accolite.miniau.accesscontrol.customexception.CustomNotFoundException;

@RestControllerAdvice
public class DefinedExceptionHandler {

	private static final Logger logger = Logger.getLogger(DefinedExceptionHandler.class);

	@ExceptionHandler(CustomNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound(Exception e) {
		logger.warn("Invalid URL PATH", e);
		return e.toString();
	}

	@ExceptionHandler(CustomBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badRequest(CustomBadRequestException e) {
		logger.warn("Bad Request ",e);
		return e.toString();
	}
}
