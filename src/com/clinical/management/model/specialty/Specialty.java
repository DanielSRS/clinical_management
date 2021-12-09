package com.clinical.management.model.specialty;

/**
 * Essa classe é responsável pelas especialidades e sub-especialidades do médico
 *
 */
public class Specialty {
	private String name;
	private int id;
	private String description;

	/**
	 * @param name
	 * construtor da classe especialidade
	 */
	public Specialty(String name) {
		this.name = name;
		this.description = "";
	}
	
	/**
	 * @param name
	 * @param description
	 * @param id
	 * Segundo construtor da classe especialidade
	 */
	public Specialty(String name, String description, int id) {
		this.name = name;
		this.id = id;
		this.description = description;
	}
	
	/**
	 * @param name
	 * @param description
	 * Terceiro construtor da classe especialidade
	 */
	public Specialty(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * @return name
	 * pega o nome da especialidade e sub-especialidade
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * seta o nome da especialidade e sub-especialidade
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return id
	 * pega o id da especialidade e sub-especialidade
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * @return description
	 * pega a descrição da especialidade e sub-especialidade
	 */
	public String getDescription() {
		return this.description;
	}
	
	
	
	

}
