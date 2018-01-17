package com.pedidos.api.model;

public enum Profitability {

	GREAT(0, "Ótimo"), GOOD(1, "Bom"), BAD(2, "Ruim");

	private Integer id;
	private String name;

	private Profitability(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
