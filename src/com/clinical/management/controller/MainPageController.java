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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * Controler da pagina Main
 */
public class MainPageController {
	
	@FXML private Label centerLebel;

	@FXML VBox usersContainer;

	@FXML ToggleButton users;

	@FXML ToggleButton medicalCare;

	@FXML ToggleButton selfService;

	@FXML ToggleButton schedule;

	@FXML ToggleButton specialty;

	private ToggleGroup sideMenuGroup;
		
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

	@FXML public void addUser() {
		FXMLLoader addUserModal = new FXMLLoader(getClass().getResource("../view/pages/AddUserModal.fxml"));
        try {
			Parent modal = addUserModal.load();
			modal.prefHeight(centerLebel.getScene().getHeight());
			modal.maxHeight(centerLebel.getScene().getHeight());
			AddUserModalController modelController = addUserModal.getController();
			modelController.addController(this);
			StackNavigator.addModal(modal);
			System.out.println("foi");
			//loginPageController.setAuth(auth);
			//stackNavigator.setInitialPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		/*this.sideMenuGroup = new ToggleGroup();
		this.users.setToggleGroup(sideMenuGroup);
		this.medicalCare.setToggleGroup(sideMenuGroup);
		this.selfService.setToggleGroup(sideMenuGroup);
		this.schedule.setToggleGroup(sideMenuGroup);
		this.specialty.setToggleGroup(sideMenuGroup);*/
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