package com.clinical.management.model.consultation;

import java.util.List;

import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.medicalRecord.MedicalRecord;
import com.clinical.management.model.medicalRecord.MedicalRecords;

public class Query {
	
	private Scheduling scheduling;
	private MedicalRecords medicalRecords;
	
	public Query() {
		
	}

	public Scheduling getScheduling() {
		return scheduling;
	}

	public void setScheduling(Scheduling scheduling) {
		this.scheduling = scheduling;
	}

	public MedicalRecords getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecord(MedicalRecords medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
	
	public List<MedicalRecord> consultRecords(String name) {
		for(int i = 0; i < this.medicalRecords.patientRecords().size(); i++) {
			if(name.equals(this.medicalRecords.patientRecords().get(i).getPatient().getUser().getName())) {
				return this.medicalRecords.patientRecords();
			}
		}
		
		return null;
	}
}
