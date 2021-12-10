package com.clinical.management.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.UserDAO;
import com.clinical.management.model.users.OrderTypes;
import com.clinical.management.model.users.User;
import com.clinical.management.util.UserListener;

public class AuthenticationController {
	
	private User currentLoggedUser;
	private static User currentLoggedUserStatik;
	private UserDAO userDao = new UserDAO();
	private List<UserListener> listners;

	public AuthenticationController() {
		try {
			// Cria diretório caso não exista
			Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/db/"));
		} catch (IOException e) {
			//
		}
		Boolean thereIsTables = userDao.thereIsATable("settings");
		if (!thereIsTables) {
			userDao.createDatabase();
			User newUser = new User("admin", "12345671900", OrderTypes.ADMIN, "admin");
			User newUser2 = new User("admin2", "12345628900", OrderTypes.ADMIN, "admin");
			User newUser3 = new User("admin3", "12345638900", OrderTypes.ADMIN, "admin");
			User newUser4 = new User("admin4", "11345648900", OrderTypes.ADMIN, "admin");
			User newUser5 = new User("admin5", "13345658900", OrderTypes.ADMIN, "admin");
			User newUser6 = new User("admin6", "14345658900", OrderTypes.ADMIN, "admin");
			User newUser7 = new User("admin7", "15345658900", OrderTypes.ADMIN, "admin");
			User newUser8 = new User("admin8", "16345658900", OrderTypes.ADMIN, "admin");
			User newUser9 = new User("admin9", "12745658900", OrderTypes.ADMIN, "admin");
			User newUser10 = new User("admin10", "18345658900", OrderTypes.ADMIN, "admin");
			User newUser11 = new User("admin11", "19345658900", OrderTypes.ADMIN, "admin");
			
			userDao.saveUser(newUser);
			userDao.saveUser(newUser2);
			userDao.saveUser(newUser3);
			userDao.saveUser(newUser4);
			userDao.saveUser(newUser5);
			userDao.saveUser(newUser6);
			userDao.saveUser(newUser7);
			userDao.saveUser(newUser8);
			userDao.saveUser(newUser9);
			userDao.saveUser(newUser10);
			userDao.saveUser(newUser11);
		} else {
			User loggedUser = userDao.getLoggedUser();
			if (loggedUser == null) {
				System.out.println("Não há usuário logado");
			} else {
				System.out.println("Usuário logado: " + loggedUser.getName());
				this.currentLoggedUser = loggedUser;
				AuthenticationController.currentLoggedUserStatik = loggedUser;
			}
		}
		this.listners = new ArrayList<>();
	}
	
	public boolean signin(String cpf, String password) {
		User user = userDao.getUserByNameAndPassword(cpf, password);
		if (user != null) {
			currentLoggedUser = user;
			AuthenticationController.currentLoggedUserStatik = user;
			System.out.println(user.getName() + " esta logado!!");
			userDao.saveLoggedUser(user);
			loggedUserChanged();
			return true;
		}
		return false;
	}
	
	public boolean signout() {
		if (currentLoggedUser != null) {
			System.out.println(currentLoggedUser.getName() + " esta deslogado!!");
			currentLoggedUser = null;
			AuthenticationController.currentLoggedUserStatik =  null;
			userDao.removeLoggedUser();
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

	public static User usuarioLogado() {
		return currentLoggedUserStatik;
	}
}
