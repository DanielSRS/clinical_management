package com.clinical.management.controller;

import javafx.fxml.FXML;

public class MainPageController {
		
	@FXML private void signout() {
		this.auth.signout();
	}
	
	private AuthenticationController auth;
	
	public void setAuth(AuthenticationController auth) {
		this.auth = auth;
	}

}