package com.pedidos.api.builder;

import com.pedidos.api.model.Product;

public class ProductBuilder {
	
	private Product product;
	private ProductBuilder() {}
	
	public static ProductBuilder oneProduct() {
		ProductBuilder builder = new ProductBuilder();
		initializeDefaultData(builder);
		return builder;
	}

	private static void initializeDefaultData(ProductBuilder builder) {
		builder.product = new Product();
		Product product = builder.product;
		product.setMultiple(2);
		product.setName("Millenium Falcon");
		product.setUnitPrice(550000);		
	}
	
	public Product now() {
		return product;
	}
}
