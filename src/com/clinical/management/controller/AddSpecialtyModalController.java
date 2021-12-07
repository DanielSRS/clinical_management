package com.clinical.management.controller;

import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddSpecialtyModalController {

    private SpecialtyPageController specialtyListingPageController;

    @FXML private TextField nameField;

    @FXML private TextField descriptionField;

    /**
     * Cria um novo usuário
     */
    @FXML private void createSpecialty() {
        String name = nameField.getText();
        String dascription = descriptionField.getText();

        // Não faz nada se algum campo estive vazio
        if (name.equals("") || dascription.equals("")){
            return;
        }

        Specialty newSpecialty = new Specialty(name, dascription);
        SpecialtyDAO dbSpecialty = new SpecialtyDAO();
        dbSpecialty.saveSpecialty(newSpecialty);  // adiciona no banco de dados

        if (specialtyListingPageController != null) {
            specialtyListingPageController.renderSpecialty();  // recarrega a listagem de usuários
        }

        closeModal(); //fecha o modal
    }
    
    /**
     * Fecha o modal
     */
    @FXML public void closeModal(){
        StackNavigator.removeModal();
    }

    /**
     * Define o controller da pagina principal
     */
    public void addController(SpecialtyPageController controller) {
        this.specialtyListingPageController = controller;
    }
}
