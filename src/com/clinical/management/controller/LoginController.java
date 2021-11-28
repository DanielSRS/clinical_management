package com.clinical.management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	
	
	@FXML
	private TextField password;
	
	@FXML
	private TextField cpf;
	
	@FXML
	private Button signinButton;
	
	@FXML private Button theme;
	
	
	
	
	public void initialize() {
		System.out.println("Login page loaded");
	}
	

}
