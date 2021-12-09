package com.clinical.management.model.consultation;

import java.util.Calendar;

import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.users.User;

public class Query {

	private Integer scheduling;
	private Integer medicalRecord;
	private Calendar date;
	private Integer user;
	private Integer id;

	public Query(int id) {
		this.id = id;
	}

	public Query(int scheduling, int medicalRecord, Calendar date, int user) {
		this.scheduling = scheduling;
		this.medicalRecord = medicalRecord;
		this.date = date;
		this.user = user;
	}

	public Integer getScheduling() {
		return scheduling;
	}

	public void setScheduling(Scheduling scheduling) {
		this.scheduling = scheduling.getId();
		this.date = scheduling.getDay();
		this.user = scheduling.getPatient().getID();
	}

	public Integer getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(Integer medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
