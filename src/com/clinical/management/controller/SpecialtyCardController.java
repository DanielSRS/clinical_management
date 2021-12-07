package com.clinical.management.controller;

import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.model.specialty.Specialty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SpecialtyCardController {
    private Specialty specialty;
    
    private SpecialtyPageController specialtyListingPageController;

    @FXML private Label specialtyName;
    
    @FXML private void deleteSpecialty() {
    	SpecialtyDAO spd = new SpecialtyDAO();
    	spd.removeSpecialty(specialty);
    	
    	if (specialtyListingPageController != null) {
    		specialtyListingPageController.renderSpecialty();
    	}
    }
    
    /**
     * Define o usuário
     * @param user
     * @see com.clinical.management.model.users.User
     */
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
        this.specialtyName.setText(specialty.getName());
    }
    
    /**
     * Define o controller da pagina principal
     */
    public void addController(SpecialtyPageController controller) {
        this.specialtyListingPageController = controller;
    }
}
