package com.clinical.management.model.calendar;

import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.specialty.Specialty;

public class AttendanceCalendar {

	private List<Scheduling> specialtySchedule = new ArrayList<>();

	public AttendanceCalendar() {
	}

	public List<Scheduling> getSpecialtySchedule() {
		return specialtySchedule;
	}

	public void setSpecialtySchedule(List<Scheduling> specialtySchedule) {
		this.specialtySchedule = specialtySchedule;
	}
	
	public List<Scheduling> schedulePerspecialty() {
		return this.specialtySchedule;
	}
	
	
	
	
}
