package com.clinical.management.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controler da pagina Main
 */
public class MainPageController {

	/**
	 * Controller de authenticação
	 */
	private AuthenticationController auth;

	private Parent usersPage;

	private Parent specialtyPage;

	private ToggleGroup sideMenuGroup;

	private ToggleButton atual;

	@FXML StackPane drawerContent;
	
	@FXML private Label centerLebel;

	@FXML VBox usersContainer;

	@FXML ToggleButton users;

	@FXML ToggleButton medicalCare;

	@FXML ToggleButton selfService;

	@FXML ToggleButton schedule;

	@FXML ToggleButton specialty;

	@FXML private void goTO() {
		ToggleButton tb = (ToggleButton) sideMenuGroup.getSelectedToggle();
		if (tb == null) {
			atual.setSelected(true);
			return;
		}
		atual = tb;
		if (tb.equals(users)) {
			System.out.println("users");
			loadUsersPage();
		} else if(tb.equals(medicalCare)) {
			System.out.println("medicalCare");
		} else if(tb.equals(selfService)) {
			System.out.println("selfService");
		} else if(tb.equals(schedule)) {
			System.out.println("schedule");
			loadSchedulePage();
		} else if(tb.equals(specialty)) {
			System.out.println("specialty");
			loadSpecialtyPage();
		}
	}
		
	/**
	 * Desloga o usuario atual
	 */
	@FXML private void signout() {
		this.auth.signout();
	}
	
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
		this.sideMenuGroup = new ToggleGroup();
		this.users.setToggleGroup(sideMenuGroup);
		this.medicalCare.setToggleGroup(sideMenuGroup);
		this.selfService.setToggleGroup(sideMenuGroup);
		this.schedule.setToggleGroup(sideMenuGroup);
		this.specialty.setToggleGroup(sideMenuGroup);
		this.users.setSelected(true);

		this.atual = this.users;

		this.drawerContent.getChildren().add(new StackPane());
		loadUsersPage();
	}

	private void loadSpecialtyPage() {
		this.drawerContent.getChildren().clear();
		if (specialtyPage != null) {
			this.drawerContent.getChildren().add(specialtyPage);
			return;
		}
		FXMLLoader addUserModal = new FXMLLoader(getClass().getResource("../view/pages/SpecialtyPage.fxml"));
        try {
			specialtyPage = addUserModal.load();
			this.drawerContent.getChildren().add(specialtyPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadSchedulePage() {
		this.drawerContent.getChildren().clear();
	}

	private void loadUsersPage() {
		this.drawerContent.getChildren().clear();
		if (usersPage != null) {
			this.drawerContent.getChildren().add(usersPage);
			return;
		}
		FXMLLoader addUserModal = new FXMLLoader(getClass().getResource("../view/pages/UsersPage.fxml"));
        try {
			this.usersPage = addUserModal.load();
			this.drawerContent.getChildren().add(usersPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Renderiza as informações dos usuários
	 */
	public void renderUsers() {
		/*UserDAO usersDB = new UserDAO();
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
		}*/
	}

}