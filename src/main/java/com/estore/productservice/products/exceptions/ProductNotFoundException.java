package com.estore.productservice.products.exceptions;

public class ProductNotFoundException extends RuntimeException {

	private String message;

	public ProductNotFoundException(String message) {
		super();
		this.message = message;
	}

	public ProductNotFoundException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	
	
}
