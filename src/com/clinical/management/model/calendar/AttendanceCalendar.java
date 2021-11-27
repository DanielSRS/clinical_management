package com.clinical.management.model.calendar;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Scheduling> specialtySchedule(String name){
		List<Scheduling> temp = new ArrayList<>();
		for(int i = 0; i < this.specialtySchedule.size(); i++) {
			if(name.equals(this.specialtySchedule.get(i).getSpecialty().getName()) ) {
				temp.add(this.specialtySchedule.get(i));
			}
		}
		
		return temp;
	}
	
	public List<Scheduling> listAllCalendar(){
		return this.specialtySchedule;
	}
	
	
	
}
