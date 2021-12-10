package com.clinical.management.model.users;

public class User {

	private Integer id;
	private String name;
	private String cpf;
	private OrderTypes types;
	private String password;
	
	/**
	 * 
	 * @param name
	 * @param cpf
	 * @param types
	 * @param password
	 */
	public User(String name, String cpf, OrderTypes types, String password) {
		this.name = name;
		this.cpf = cpf;
		this.types = types;
		this.password = password;
		this.id = null;
	}

	/**
	 * Cria novo usuário
	 * @param name String com nome
	 * @param cpf String com cpf
	 * @param id int com id
	 * @param password String com senha
	 */
	public User(String name, String cpf, int id, String password) {
		this.name = name;
		this.cpf = cpf;
		this.types = null;
		this.password = password;
		this.id = id;
	}

	/**
	 * Obtem nome do usuário
	 * @return String com nome do usuário
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do usuário
	 * @param name String com nome do usuário
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtem o cpf do usuário
	 * @returnString com cpf do usuário
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Define o cpf do usuário
	 * @param cpf String com cpf do usuário
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public OrderTypes getTypes() {
		return types;
	}

	public void setTypes(OrderTypes types) {
		this.types = types;
	}
	
	/**
	 * Obtem a senha do usuário
	 * @return String com senha do usuário
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Obtem o id do usuário
	 * @return int com id do usuário
	 */
	public Integer getID() {
		return this.id;
	}

	/**
	 * @param id
	 * seta o id do usu�rio
	 */
	public void setUserID(Integer id) {
		this.id = id;
	}

}
