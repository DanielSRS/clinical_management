package com.clinical.management.model.calendar;

import java.util.Calendar;

import com.clinical.management.model.doctor.Doctor;

public class Day_doctor {
	private Calendar Start_service, End_service;
	private Doctor doctor;
	private Integer duration_service;
	private Integer id;

	public Day_doctor(int start_service, int end_service, Doctor doctor, Integer duration_service) {
		this.doctor = doctor;
		this.duration_service = duration_service;
		Calendar d = Calendar.getInstance();
		d.set(2000, Calendar.DECEMBER, 01, start_service, 0);
		this.Start_service = d;
		Calendar b = Calendar.getInstance();
		b.set(2000, Calendar.DECEMBER, 01, end_service, 0);
		this.End_service = b;
		this.id = null;

	}

	public Calendar getStart_service() {
		return Start_service;
	}

	public void setStart_service(Calendar start_service) {
		Start_service = start_service;
	}

	public Calendar getEnd_service() {
		return End_service;
	}

	public void setEnd_service(Calendar end_service) {
		End_service = end_service;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Integer getDuration_service() {
		return duration_service;
	}

	public void setDuration_service(Integer duration_service) {
		this.duration_service = duration_service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
