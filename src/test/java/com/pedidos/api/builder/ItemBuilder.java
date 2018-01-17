package com.pedidos.api.builder;

import com.pedidos.api.model.Item;
import com.pedidos.api.model.Product;

public class ItemBuilder {

	private Item item;

	private ItemBuilder() {
	}

	public static ItemBuilder oneItem(Product product) {
		ItemBuilder builder = new ItemBuilder();
		initializeDefaultData(builder, product);
		return builder;
	}

	private static void initializeDefaultData(ItemBuilder builder, Product product) {
		builder.item = new Item();
		Item item = builder.item;
		item.setAmount(2);
		item.setUnitPrice(50000);
		item.setProduct(product);
	}

	public Item now() {
		return item;
	}

}
