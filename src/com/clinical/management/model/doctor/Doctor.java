package com.clinical.management.model.doctor;

import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.OrderTypes;
import com.clinical.management.model.users.User;

public class Doctor extends User {
	private Integer specialty;
	private Integer sub_specialty;
	private int id;

	public Doctor(String name, String cpf, String password, Integer specialty) {
		super(name, cpf, OrderTypes.DOCTOR, password);
		this.specialty = specialty;
	}
	
	public Doctor(String name, String cpf, String password, Integer specialty, Integer sub_specialty) {
		super(name, cpf, OrderTypes.DOCTOR, password);
		this.specialty = specialty;
		this.sub_specialty = sub_specialty;
	}

	public int getSpecialty() {
		return specialty;
	}

	public Integer getSub_specialty() {
		return sub_specialty;
	}

	public void define_specialty(Specialty specialty) {
		this.specialty = specialty.getID();
	}

	public void define_sub_specialty(Specialty sub_specialty) {
		this.sub_specialty = sub_specialty.getID();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean can_be_removed() {
		if (this.specialty == null && this.sub_specialty == null) {
			return true;
		}
		return false;
	}

}
