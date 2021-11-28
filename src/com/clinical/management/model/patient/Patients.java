package com.clinical.management.model.patient;

import java.util.ArrayList;
import java.util.List;

public class Patients {
	
	private List<Patient> ListPatients = new ArrayList<>();
	
	public Patients() {
		
	}

	public List<Patient> getListPatients() {
		return ListPatients;
	}

	public void setListPatients(List<Patient> listPatients) {
		ListPatients = listPatients;
	}
	
	
}
