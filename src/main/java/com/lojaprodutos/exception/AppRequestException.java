package com.lojaprodutos.exception;

public class AppRequestException extends RuntimeException {

	public AppRequestException(String message) {
		super(message);
	}

	public AppRequestException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
