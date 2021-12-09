package com.clinical.management.model.doctor;

import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.OrderTypes;
import com.clinical.management.model.users.User;

/**
 * Classe responsável pela ligação do usuário médico, onde terá a especialidade,
 * sub-especialidade e id do médico.
 *
 */
public class Doctor extends User {
	private Integer specialty;
	private Integer sub_specialty;
	private int id;

	/**
	 * @param name
	 * @param cpf
	 * @param password
	 * @param specialty Construtor da classe médico
	 */
	public Doctor(String name, String cpf, String password, Integer specialty) {
		super(name, cpf, OrderTypes.DOCTOR, password);
		this.specialty = specialty;
	}

	/**
	 * @param name
	 * @param cpf
	 * @param password
	 * @param specialty
	 * @param sub_specialty Segundo construtor para a classe médico
	 */
	public Doctor(String name, String cpf, String password, Integer specialty, Integer sub_specialty) {
		super(name, cpf, OrderTypes.DOCTOR, password);
		this.specialty = specialty;
		this.sub_specialty = sub_specialty;
	}

	/**
	 * @return specialty pega a especialidade
	 */
	public Integer getSpecialty() {
		return specialty;
	}

	/**
	 * @return sub_specialty pega a sub-especialidade
	 */
	public Integer getSub_specialty() {
		return sub_specialty;
	}

	/**
	 * @param specialty seta o id da classe especialidade
	 */
	public void define_specialty(Specialty specialty) {
		this.specialty = specialty.getID();
	}

	/**
	 * @param sub_specialty seta o id da classe sub-especialidade
	 */
	public void define_sub_specialty(Specialty sub_specialty) {
		this.sub_specialty = sub_specialty.getID();
	}

	/**
	 * @return id
	 * pega o id da classe doutor
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * seta o valor do id da classe doutor
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return boolean
	 * Se a especialidade e sub-especialidade do médico forem nulas, o médico pode ser removido
	 */
	public boolean can_be_removed() {
		if (this.specialty == null && this.sub_specialty == null) {
			return true;
		}
		return false;
	}

}
