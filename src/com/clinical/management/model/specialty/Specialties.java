package com.clinical.management.model.specialty;

import java.util.ArrayList;
import java.util.List;

public class Specialties {
	
	private List<Specialty> listSpecialities = new ArrayList<>();
	
	public Specialties() {
		
	}

	public List<Specialty> getListSpecialities() {
		return listSpecialities;
	}

	public void setListSpecialities(List<Specialty> listSpecialities) {
		this.listSpecialities = listSpecialities;
	}
	
	public void list_specialties() {
		for(Specialty i: listSpecialities) {
			System.out.println(i);
		}
	}
	
	public boolean remove_specialties(String name) {
		for(int i = 0; i < listSpecialities.size(); i++) {
			if(listSpecialities.get(i).getName().equals(name)) {
				listSpecialities.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean add_specialties(String name) {
		Specialty s = new Specialty(name, "");
		for(Specialty i: listSpecialities) {
			if(i.equals(s)) {
				return false;
			}
		}
		
		listSpecialities.add(s);
		return true;
	}
	
	

}
