package com.pedidos.api.exception;

public class InvalidMultipleException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidMultipleException(String messageError) {
		super(messageError);
	}

}
