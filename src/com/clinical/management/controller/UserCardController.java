package com.clinical.management.controller;

import com.clinical.management.model.users.OrderTypes;
import com.clinical.management.model.users.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserCardController {
    //private User user;

    @FXML private Label userName;

    @FXML private Button admin;
    
    @FXML private Button paci;

    @FXML private Button doctor;

    @FXML private Button rece;
    
    /**
     * Define o usuário
     * @param user
     * @see com.clinical.management.model.users.User
     */
    public void setUser(User user) {
        //this.user = user;
        this.userName.setText(user.getName());

        if (user.getTypes() == OrderTypes.DOCTOR) {
            doctor.setVisible(true);
            doctor.setMaxWidth(300);
        }

        if (user.getTypes() == OrderTypes.ADMIN) {
            admin.setVisible(true);
            admin.setMaxWidth(300);
        }

        if (user.getTypes() == OrderTypes.PATIENT) {
            paci.setVisible(true);
            paci.setMaxWidth(300);
        }

        if (user.getTypes() == OrderTypes.RECEPTIONIST) {
            rece.setVisible(true);
            rece.setMaxWidth(300);
        }
    }
}
