package com.pedidos.api.exception;

public class ProductNotFound extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ProductNotFound(String errorMessage) {
		super(errorMessage);
	}

}
