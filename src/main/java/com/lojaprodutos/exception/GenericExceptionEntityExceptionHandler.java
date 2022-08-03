package com.lojaprodutos.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GenericExceptionEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<AppException> handleConflict(Exception ex, WebRequest request) {
		return this.handleException(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<AppException> handleException(Exception ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.BAD_REQUEST.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(new AppException(ex, status), headers, status);
	}
	
	@ExceptionHandler(value = { EmptyResultDataAccessException.class, NotFoundException.class, NoSuchElementException.class })
	protected ResponseEntity<AppException> handleNotFound(Exception ex, WebRequest request) {
		return this.handleException(ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
}
