package com.clinical.management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controler da pagina Main
 */
public class MainPageController {
	
	@FXML private Label centerLebel;
		
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
		centerLebel.setText("\n\nUser: " + this.auth.getCurrentUser().getName());
	}

}