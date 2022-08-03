package com.lojaprodutos.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AppException {

	@JsonProperty(value = "status_code")
	private Integer statusCode;
	
	private String message;

	public AppException(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public AppException(Exception exception) {
		super();
		if(exception instanceof AppRequestException)
			this.createAppException((AppRequestException) exception);
		else {
			this.statusCode = HttpStatus.BAD_REQUEST.value();
			this.message = exception.getMessage();
		}
	}
	
	public AppException(Exception exception, HttpStatus httpStatus) {
		super();
		if(exception instanceof AppRequestException)
			this.createAppException((AppRequestException) exception);
		else {
			this.statusCode = httpStatus.value();
			this.message = exception.getMessage();
		}
	}

	private void createAppException(AppRequestException exception) {
		this.statusCode = HttpStatus.BAD_REQUEST.value();
		this.message = exception.getMessage();
	}
}
