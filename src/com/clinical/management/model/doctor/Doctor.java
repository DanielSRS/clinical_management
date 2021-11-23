package com.clinical.management.model.doctor;

import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.User;

public class Doctor extends User {
	private Specialty specialty;
	private Specialty sub_specialty;

	public Doctor(String name, String cpf) {
		super(name, cpf);
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public Specialty getSub_specialty() {
		return sub_specialty;
	}

	public void define_specialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public void define_sub_specialty(Specialty sub_specialty) {
		this.sub_specialty = sub_specialty;
	}

	public boolean can_be_removed() {
		if (this.specialty == null && this.sub_specialty == null) {
			return true;
		}
		return false;
	}

}
