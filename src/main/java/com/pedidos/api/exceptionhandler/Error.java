package com.pedidos.api.exceptionhandler;

public class Error {

	private String userMessage;
	private String devMessage;

	public Error(String userMessage, String devMessage) {
		this.userMessage = userMessage;
		this.devMessage = devMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public String getDevMessage() {
		return devMessage;
	}

}
