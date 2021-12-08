package com.clinical.management.controller;

import java.util.List;

import com.clinical.management.dao.SchedulingDAO;
import com.clinical.management.model.calendar.Scheduling;

public class SchedulingController {
	

	public void listScheduling() {
		SchedulingDAO sc = new SchedulingDAO();
		List<Scheduling> aux = sc.getScheduling();
		for(int i = 0; i < aux.size(); i++) {
			System.out.println(aux.get(i).getDoctor().getName());
		}
	}
	
	
	
}
