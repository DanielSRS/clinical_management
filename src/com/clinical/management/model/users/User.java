package com.clinical.management.model.users;

public class User {

	private String name;
	private String cpf;
	private OrderTypes types;
	
	public User(String name, String cpf, OrderTypes types) {
		this.name = name;
		this.cpf = cpf;
		this.types = types;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public OrderTypes getTypes() {
		return types;
	}

	public void setTypes(OrderTypes types) {
		this.types = types;
	}
	
	
	
	
}
