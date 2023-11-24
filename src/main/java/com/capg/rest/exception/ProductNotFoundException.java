package com.capg.rest.exception;

public class ProductNotFoundException extends RuntimeException {
	private String message;

	public ProductNotFoundException() {

	}

	public ProductNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ProductNotFoundException [message=" + message + "]";
	}

}
