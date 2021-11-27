package com.clinical.management.model.patient;

import com.clinical.management.model.consultation.Queries;
import com.clinical.management.model.medicalRecord.MedicalRecords;
import com.clinical.management.model.users.User;

public class Patient {
	private User user;
	private Queries queries;
	private MedicalRecords medicalRecords;
	
	public Patient() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Queries getQueries() {
		return queries;
	}

	public void setQueries(Queries queries) {
		this.queries = queries;
	}

	public MedicalRecords getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(MedicalRecords medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
	
	
}
