package com.pedidos.api.builder;

import com.pedidos.api.model.Client;

public class ClientBuilder {

	private Client client;

	private ClientBuilder() {

	}

	public static ClientBuilder oneClient() {
		ClientBuilder builder = new ClientBuilder();
		initializeDefaultData(builder);
		return builder;

	}

	private static void initializeDefaultData(ClientBuilder builder) {
		builder.client = new Client();
		Client client = builder.client;
		client.setName("Darth Vader");
	}

	public Client now() {
		return client;
	}

}
