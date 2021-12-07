package com.clinical.management.controller;

import com.clinical.management.dao.UserDAO;
import com.clinical.management.model.users.User;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddUserModalController {

    private MainPageController userListingPageController;

    @FXML private TextField nameField;

    @FXML private TextField cpfField;

    @FXML private TextField passwordField;

    /**
     * Cria um novo usuário
     */
    @FXML private void createUser() {
        String name = nameField.getText();
        String cpf = cpfField.getText();
        String password = passwordField.getText();

        // Não faz nada se algum campo estive vazio
        if (name.equals("") || cpf.equals("") || password.equals("")){
            return;
        }

        User newUser = new User(name, cpf, null, password); // cria usuário
        UserDAO dbUser = new UserDAO();
        dbUser.saveUser(newUser);  // adiciona no banco de dados

        if (userListingPageController != null) {
            userListingPageController.renderUsers();  // recarrega a listagem de usuários
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
    public void addController(MainPageController controller) {
        this.userListingPageController = controller;
    }
}
