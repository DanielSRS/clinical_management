package com.clinical.management.model.calendar;

import java.util.Calendar;

import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.User;

public class Scheduling {

	private Calendar day;
	private Calendar hour;
	private Doctor doctor;
	private Integer specialty;
	private OrderStatus status;
	private User patient;
	private Integer id;

	public Scheduling(Calendar day, Calendar hour, Doctor doctor, Integer specialty) {

		this.day = day;
		this.hour = hour;
		this.doctor = doctor;
		this.specialty = specialty;
		this.status = OrderStatus.AVAILABLE;
		this.id = null;
	}

	public Calendar getDay() {
		return day;
	}

	public void setDay(Calendar day) {
		this.day = day;
	}

	public Calendar getHour() {
		return hour;
	}

	public void setHour(Calendar hour) {
		this.hour = hour;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Integer getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty.getID();
	}

	public OrderStatus getStatus() {
		return status;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean mark(User patient) {
		if (this.status == OrderStatus.AVAILABLE) {
			this.patient = patient;
			this.status = OrderStatus.MARKED;
			return true;
		}

		return false;
	}

	public boolean blocked() {
		if (this.status == OrderStatus.AVAILABLE) {
			this.status = OrderStatus.BLOCKED;
			return true;
		}

		return false;
	}

	public boolean markOff() {
		if (this.status == OrderStatus.MARKED) {
			this.status = OrderStatus.AVAILABLE;
			return true;
		}

		return false;
	}

}
