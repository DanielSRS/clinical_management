package com.clinical.management.model.calendar;

import java.util.ArrayList;
import java.util.List;

public class All_calendar {

	private List<Calendar_doctor> listCalendarDoctors = new ArrayList<>();
	
	public All_calendar() {
		
	}

	public List<Calendar_doctor> getListCalendarDoctors() {
		return listCalendarDoctors;
	}

	public void setListCalendarDoctors(List<Calendar_doctor> listCalendarDoctors) {
		this.listCalendarDoctors = listCalendarDoctors;
	}
	
	public List<Calendar_doctor> recoverCalendarDoctor() {
		return this.listCalendarDoctors;
	}
}
