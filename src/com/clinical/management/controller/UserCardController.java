package com.clinical.management.controller;

import com.clinical.management.model.users.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserCardController {
    //private User user;

    @FXML private Label userName;
    
    /**
     * Define o usuário
     * @param user
     * @see com.clinical.management.model.users.User
     */
    public void setUser(User user) {
        //this.user = user;
        this.userName.setText(user.getName());
    }
}
