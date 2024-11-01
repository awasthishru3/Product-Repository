package com.estore.productservice.products.exceptions;

public class ProductAlreadyPresentException extends RuntimeException{
	private String message;

	public ProductAlreadyPresentException(String message) {
		super();
		this.message = message;
	}

	public ProductAlreadyPresentException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	
	
}
