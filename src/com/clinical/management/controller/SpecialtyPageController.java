package com.clinical.management.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

/**
 * Controler da pagina Specialty
 */
public class SpecialtyPageController {
	
	@FXML private Label centerLebel;

	@FXML VBox specialtysContainer;

	@FXML ToggleButton users;

	@FXML ToggleButton medicalCare;

	@FXML ToggleButton selfService;

	@FXML ToggleButton schedule;

	@FXML ToggleButton specialty;

	//private ToggleGroup sideMenuGroup;
		
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

	@FXML public void addSpecialty() {
		FXMLLoader addSpecialtyModal = new FXMLLoader(getClass().getResource("../view/pages/AddSpecialtyModal.fxml"));
        try {
			Parent modal = addSpecialtyModal.load();
			modal.prefHeight(centerLebel.getScene().getHeight());
			modal.maxHeight(centerLebel.getScene().getHeight());
			AddSpecialtyModalController modalController = addSpecialtyModal.getController();
			modalController.addController(this);
			StackNavigator.addModal(modal);
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
		renderSpecialty();
	}

	/**
	 * Renderiza as informações dos usuários
	 */
	public void renderSpecialty() {
		SpecialtyDAO specialtyDB = new SpecialtyDAO();
		List<Specialty> specialtys =  specialtyDB.getSpecialtys(); // Obtem todos os usuários
		Collections.reverse(specialtys);
		Iterator<Specialty> it = specialtys.iterator();
		this.specialtysContainer.getChildren().clear(); // Apaga qualquer dado preexistente
		while (it.hasNext()) {
			Specialty aux = it.next();

			FXMLLoader specialtyCard = new FXMLLoader(getClass().getResource("../view/pages/SpecialtyCard.fxml"));
			try {
				Parent card = specialtyCard.load();
				SpecialtyCardController cont = specialtyCard.getController();
				cont.setSpecialty(aux);
				cont.addController(this);
				this.specialtysContainer.getChildren().add(card);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}