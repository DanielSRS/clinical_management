package com.clinical.management.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.UserDAO;
import com.clinical.management.model.users.User;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * Controler da pagina Main
 */
public class UsersPageController {

	@FXML VBox usersContainer;

	@FXML public void addUser() {
		FXMLLoader addUserModal = new FXMLLoader(getClass().getResource("../view/pages/AddUserModal.fxml"));
        try {
			Parent modal = addUserModal.load();
			modal.prefHeight(usersContainer.getScene().getHeight());
			modal.maxHeight(usersContainer.getScene().getHeight());
			AddUserModalController modelController = addUserModal.getController();
			modelController.addController(this);
			StackNavigator.addModal(modal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void initialize() {
		renderUsers();
	}

	/**
	 * Renderiza as informações dos usuários
	 */
	public void renderUsers() {
		UserDAO usersDB = new UserDAO();
		List<User> users =  usersDB.getUsers(); // Obtem todos os usuários
		Collections.reverse(users);
		Iterator<User> it = users.iterator();
		this.usersContainer.getChildren().clear(); // Apaga qualquer dado preexistente
		while (it.hasNext()) {
			User aux = it.next();

			FXMLLoader userCard = new FXMLLoader(getClass().getResource("../view/pages/UserCard.fxml"));
			try {
				Parent card = userCard.load();
				UserCardController cont = userCard.getController();
				cont.setUser(aux);
				this.usersContainer.getChildren().add(card);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
