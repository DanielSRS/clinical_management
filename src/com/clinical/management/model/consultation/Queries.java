package com.clinical.management.model.consultation;

import java.util.ArrayList;
import java.util.List;

public class Queries {
	private List<Query> listQuery = new ArrayList<>();

	public Queries() {
	}

	public List<Query> getListQuery() {
		return listQuery;
	}

	public void setListQuery(List<Query> listQuery) {
		this.listQuery = listQuery;
	}
	
	public List<Query> patientQuery(String name){
		List<Query> temp = new ArrayList<>();
		for(int i = 0; i < this.listQuery.size(); i++) {
			if(name.equals(this.listQuery.get(i).getScheduling().getPatient().getName()) ) {
				temp.add(this.listQuery.get(i));
			}
		}
		
		return temp;
	}
	
	public List<Query> doctorQuery(String name){
		List<Query> temp = new ArrayList<>();
		for(int i = 0; i < this.listQuery.size(); i++) {
			if(name.equals(this.listQuery.get(i).getScheduling().getDoctor().getName()) ) {
				temp.add(this.listQuery.get(i));
			}
		}
		
		return temp;
	}
	
	
	
}
