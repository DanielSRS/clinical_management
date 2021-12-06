package com.clinical.management.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.UserDAO;
import com.clinical.management.model.users.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Controler da pagina Main
 */
public class MainPageController {
	
	@FXML private Label centerLebel;

	@FXML VBox usersContainer;
		
	/**
	 * Desloga o usuario atual
	 */
	@FXML private void signout() {
		this.auth.signout();
	}
	
	/**
	 * Controller de authenticação
	 */
	private AuthenticationController auth;
	
	/**
	 * Define o controller de authenticação
	 * @param auth Controller de authenticação
	 */
	public void setAuth(AuthenticationController auth) {
		this.auth = auth;
		printAllData();
	}
	
	/**
	 * Exibe o nome do do usuário na Label no centro da tela
	 */
	public void printAllData() {
		if (this.auth.getCurrentUser() == null){
			return;
		}
		centerLebel.setText("\n\nUser: " + this.auth.getCurrentUser().getName());
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
		Iterator<User> it = users.iterator();
		this.usersContainer.getChildren().clear(); // Apaga qualquer dado preexistente
		while (it.hasNext()) {
			User aux = it.next();
			//Label lb = new Label();
			//String data = "Nome: " + aux.getName() + ", CPF: " + aux.getCpf() + ", ID: " + aux.getID();
			//lb.setText(data);
			//this.usersContainer.getChildren().add(lb);

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