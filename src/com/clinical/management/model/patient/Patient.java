package com.clinical.management.model.patient;

import com.clinical.management.model.users.User;

/**
 * Essa classe é responsável pelo paciente
 *
 */
public class Patient {
	private User user;
	
	public Patient() {
		
	}

	/**
	 * @return user
	 * pega o usuário
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 * seta o usuário
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
