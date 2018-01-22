package com.pedidos.api.model;

public enum Profitability {

	GREAT("Ótima"), GOOD("Boa"), BAD("Ruim");

	private String name;

	private Profitability(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
