package com.clinical.management.model.consultation;

import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.medicalRecord.MedicalRecord;

public class Query {

	private Scheduling scheduling;
	private MedicalRecord medicalRecord;
	private Integer id;

	public Query() {
		this.id = null;
	}

	public Query(Integer id) {
		this.id = id;
	}

	public Scheduling getScheduling() {
		return scheduling;
	}

	public void setScheduling(Scheduling scheduling) {
		this.scheduling = scheduling;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}
