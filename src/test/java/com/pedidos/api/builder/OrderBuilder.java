package com.pedidos.api.builder;

import java.util.List;

import com.pedidos.api.model.Client;
import com.pedidos.api.model.Item;
import com.pedidos.api.model.Order;

public class OrderBuilder {

	private Order order;

	private OrderBuilder() {
	}

	public static OrderBuilder oneOrder(Client client, List<Item> items) {
		OrderBuilder builder = new OrderBuilder();
		initializeDefaultData(builder, client, items);
		return builder;
	}

	private static void initializeDefaultData(OrderBuilder builder, Client client, List<Item> items) {
		builder.order = new Order();
		Order order = builder.order;
		order.setClient(client);
		order.setItems(items);
	}

	public Order now() {
		return order;
	}

}
