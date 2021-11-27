package com.clinical.management.model.calendar;

import java.util.Calendar;

import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.User;

public class Scheduling {

	private Calendar day;
	private Calendar hour;
	private Doctor doctor;
	private Specialty specialty;
	private OrderStatus status;
	private User patient;

	public Scheduling(Calendar day, Calendar hour, Doctor doctor, Specialty specialty) {

		this.day = day;
		this.hour = hour;
		this.doctor = doctor;
		this.specialty = specialty;
		this.status = OrderStatus.AVAILABLE;
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

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
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
	
	public boolean mark(User patient) {
		if(this.status == OrderStatus.AVAILABLE) {
			this.patient = patient;
			this.status = OrderStatus.MARKED;
			return true;
		}
		
		return false;
	}
	
	public boolean blocked() {
		if(this.status == OrderStatus.AVAILABLE) {
			this.status = OrderStatus.BLOCKED;
			return true;
		}
		
		return false;
	}
	
	public boolean markOff() {
		if(this.status == OrderStatus.MARKED) {
			this.status = OrderStatus.AVAILABLE;
			return true;
		}
		
		return false;
	}
	

}
