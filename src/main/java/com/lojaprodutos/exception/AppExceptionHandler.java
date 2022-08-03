package com.lojaprodutos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = { AppRequestException.class })
	public ResponseEntity<Object> handleAppRequestExceptionnHandler(AppRequestException appRequestException) {

		AppException appException = AppException.builder().message(appRequestException.getMessage())
				.statusCode(HttpStatus.BAD_REQUEST.value()).build();

		return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
	}

}
