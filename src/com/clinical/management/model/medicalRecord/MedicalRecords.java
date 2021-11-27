package com.clinical.management.model.medicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecords {
	private List<MedicalRecord> medicalRecords = new ArrayList<>();

	public MedicalRecords() {
		super();
	}

	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
	public List<MedicalRecord> patientRecords(){
		return this.medicalRecords;
	}
}
