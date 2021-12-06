package com.clinical.management.controller;

import com.clinical.management.model.users.User;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

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

    public void initialize() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Editar projeto");
    }
}
