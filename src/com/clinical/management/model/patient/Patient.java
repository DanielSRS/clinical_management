package com.clinical.management.model.patient;

import com.clinical.management.model.consultation.Queries;
import com.clinical.management.model.users.User;

public class Patient {
	private User user;
	
	public Patient() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
