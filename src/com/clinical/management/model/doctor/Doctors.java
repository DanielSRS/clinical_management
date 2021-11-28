package com.clinical.management.model.doctor;

import java.util.ArrayList;
import java.util.List;

public class Doctors{
	
	private List<Doctor> listDoctors = new ArrayList<>();

	public Doctors() {
	}

	public List<Doctor> getListDoctors() {
		return listDoctors;
	}

	public void setListDoctors(List<Doctor> listDoctors) {
		this.listDoctors = listDoctors;
	}
	
	public boolean addDoctors(Doctor d) {
		for(Doctor j: this.listDoctors) {
			if(j.getName().equals(d.getName()) || j.getCpf().equals(d.getCpf())) {
				return false;
			}
		}
		
		this.listDoctors.add(d);
		return true;
	}
	
	public boolean removeDoctors(Doctor d) {
		for(int j = 0; j < this.listDoctors.size(); j++) {
			if(this.listDoctors.get(j).getName().equals(d.getName()) || this.listDoctors.get(j).getCpf().equals(d.getCpf())) {
				this.listDoctors.remove(j);
				return true;
			}
		}
		
		return false;
	}
	
	public void listDoctors() {
		for(Doctor j: this.listDoctors) {
			System.out.println(j.getName());
		}
	}
}
