package com.organization.onlineshopping.exception.handler;



import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.organization.onlineshopping.exception.ApiError;
import com.organization.onlineshopping.exception.InternalException;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice
public class InternalExceptionHandler extends ResponseEntityExceptionHandler {
	
	public InternalExceptionHandler() {
		super();
	}
	
	@ExceptionHandler({InternalException.class})
	protected ResponseEntity<ApiError> handleoutcomeCode(Exception e, WebRequest request){
		ApiError obj = new ApiError();
		InternalException re = (InternalException) e;
		obj.setOutcomeCode(re.getOutcomeCode());
		obj.setOutcomeMessage(re.getOutcomeMessage());
		obj.setInternalMessage(re.getInternalMessage());
		log.error("Internal Exception - OutcomeCode: " + re.getOutcomeCode() + " Message: " + re.getOutcomeMessage());
		return new ResponseEntity(obj, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(IOException.class)
	public final ResponseEntity<Object> handleIOException(IOException ex, final WebRequest request) {

		logger.error("handleIOException - Catching: " + ex.getClass().getSimpleName());
		return handleExceptionInternal(ex, message2(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);

	}
	
	@ExceptionHandler(JWTDecodeException.class)
	public final ResponseEntity<Object> handleJWTDecodeException(JWTDecodeException ex, final WebRequest request) {

		logger.error("JWTDecodeException - Catching: " + ex.getClass().getSimpleName());
		return handleExceptionInternal(ex, message2(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);

	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public final ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex, final WebRequest request) {

		logger.error("TokenExpiredException - Catching: " + ex.getClass().getSimpleName());
		return handleExceptionInternal(ex, message2(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);

	}
	
	@ExceptionHandler(JWTVerificationException.class)
	public final ResponseEntity<Object> handleJWTVerificationException(JWTVerificationException ex, final WebRequest request) {

		logger.error("JWTVerificationException - Catching: " + ex.getClass().getSimpleName());
		return handleExceptionInternal(ex, message2(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);

	}
	
	private ApiError message2(final HttpStatus httpStatus, final Exception ex) {
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
		final String devMessage = ExceptionUtils.getRootCauseMessage(ex);

		return new ApiError(httpStatus.value(), message, devMessage);
	}

}