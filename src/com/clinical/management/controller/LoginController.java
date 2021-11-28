package com.clinical.management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	
	public Boolean isDarkMode = false;
	
	@FXML
	private TextField password;
	
	@FXML
	private TextField cpf;
	
	@FXML
	private Button signinButton;
	
	@FXML private Button theme;
	
	@FXML private void signout() {
		this.auth.signout();
	}
	
	@FXML private void themes() {
		System.out.println("Current theme dark: " + this.isDarkMode);
		if (this.isDarkMode) {
			this.theme.getScene().getRoot().getStyleClass().remove("dark");
			/*this.theme.getScene().getRoot().getStyleClass().add("decorated");
			Stage stage;
			Scene currentScene = this.theme.getScene();
	        stage=(Stage) this.theme.getScene().getWindow();
	        Stage newStage = new Stage();
	        newStage.setScene(currentScene);
	        stage.hide();
	        stage.close();
	        FXResizeHelper re = new FXResizeHelper(newStage, 0, 10);
	        newStage.initStyle(StageStyle.UNDECORATED);
	        newStage.show();*/
		} else {
			this.theme.getScene().getRoot().getStyleClass().add("dark");
			/*this.theme.getScene().getRoot().getStyleClass().remove("decorated");
			Stage stage;
			Scene currentScene = this.theme.getScene();
	        stage=(Stage) this.theme.getScene().getWindow();
	        Stage newStage = new Stage();
	        newStage.setScene(currentScene);
	        stage.hide();
	        stage.close();
	        FXResizeHelper re = new FXResizeHelper(newStage, 0, 10);
	        newStage.show();*/
		}
		this.isDarkMode = !this.isDarkMode;
		
	}
	
	private AuthenticationController auth;
	
	@FXML
	private void login() {
		this.auth.signin(this.cpf.getText(), this.password.getText());
	}
	
	public void initialize() {
		System.out.println("Login page loaded");
		String text2 = String.valueOf(Character.toChars(Integer.parseInt("2190", 16)));
		this.theme.setText(text2);
	}
	
	public void setAuth(AuthenticationController auth) {
		this.auth = auth;
	}

}
