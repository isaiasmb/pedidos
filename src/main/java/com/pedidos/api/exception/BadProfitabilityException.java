package com.pedidos.api.exception;

public class BadProfitabilityException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BadProfitabilityException(String messageError) {
		super(messageError);
	}

}
