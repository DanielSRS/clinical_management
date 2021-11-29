package com.clinical.management.model.users;

public class User {

	private String name;
	private String cpf;
	private OrderTypes types;
	private String password;
	
	public User(String name, String cpf, OrderTypes types, String password) {
		this.name = name;
		this.cpf = cpf;
		this.types = types;
		this.password = password;
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
	
	public String getPassword() {
		return this.password;
	}

}
