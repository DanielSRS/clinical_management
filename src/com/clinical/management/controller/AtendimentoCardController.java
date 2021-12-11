package com.clinical.management.controller;

import java.io.IOException;

import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class AtendimentoCardController {

    private Scheduling s;

    @FXML private Label informationLAbel;

    @FXML private void onClick() {
        FXMLLoader calCard = new FXMLLoader(getClass().getResource("../view/pages/ConsultaModal.fxml"));
        try {
            Parent card = calCard.load();
            ConsultaModalController cont = calCard.getController();
            cont.setSch(s);
            
            //contentContainer.getChildren().add(card);
            StackNavigator.addModal(card);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setInformation(String s) {
        this.informationLAbel.setText(s);
    }

    public void sche(Scheduling g) {
        this.s = g;
    }
}
