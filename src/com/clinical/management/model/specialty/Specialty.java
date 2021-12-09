package com.clinical.management.model.specialty;

public class Specialty {
	private String name;
	private int id;
	private String description;

	public Specialty(String name) {
		this.name = name;
		this.description = "";
	}
	
	public Specialty(String name, String description, int id) {
		this.name = name;
		this.id = id;
		this.description = description;
	}
	
	public Specialty(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	
	
	

}
