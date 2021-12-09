package com.clinical.management.model.patient;

import com.clinical.management.model.users.User;

/**
 * Essa classe � respons�vel pelo paciente
 *
 */
public class Patient {
	private User user;
	
	public Patient() {
		
	}

	/**
	 * @return user
	 * pega o usu�rio
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 * seta o usu�rio
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
