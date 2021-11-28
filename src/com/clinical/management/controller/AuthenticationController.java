package com.clinical.management.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.model.users.OrderTypes;
import com.clinical.management.model.users.User;
import com.clinical.management.model.users.Users;
import com.clinical.management.util.UserListener;

public class AuthenticationController {
	
	private User currentLoggedUser;
	private Users users = new Users();
	private List<UserListener> listners;

	public AuthenticationController() {
		User newUser = new User("admin", "12345678900", OrderTypes.ADMIN, "admin");
		this.users.addUser(newUser);
		this.listners = new ArrayList<>();
	}
	
	public boolean signin(String cpf, String password) {
		User user = users.getUserByCPF(cpf);
		if (user != null && user.getPassword().equals(password)) {
			currentLoggedUser = user;
			System.out.println(user.getName() + " esta logado!!");
			loggedUserChanged();
			return true;
		}
		return false;
	}
	
	public boolean signout() {
		if (currentLoggedUser != null) {
			System.out.println(currentLoggedUser.getName() + " esta deslogado!!");
			currentLoggedUser = null;
			loggedUserChanged();
			return true;
		}
		loggedUserChanged();
		return false;
	}
	
	public User getCurrentUser() {
		return this.currentLoggedUser;
	}
	
	public void addListner(UserListener listner) {
		this.listners.add(listner);
	}
	
	public void loggedUserChanged() {
		Iterator<UserListener> it = this.listners.iterator();
		while (it.hasNext()) {
			it.next().loggedUserChanged();
		}
	}

}