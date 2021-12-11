package com.clinical.management.controller;

import java.util.List;

import com.clinical.management.dao.SchedulingDAO;
import com.clinical.management.dao.UserDAO;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.users.User;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MarcarConsultaModalController {

    @FXML AutoAtendimentoController ctr;

    @FXML Button saveButton;

    @FXML AnchorPane senhaCont;

    @FXML VBox authCntainer;

    @FXML TextField senhaField;

    @FXML TextField cpfField;

    @FXML ComboBox<Scheduling> horarioCombo;

    @FXML TextField especialidadeField;

    @FXML TextField dataField;

    @FXML Label doctorSpecialty;

    @FXML Label doctorName;

    @FXML private void onClick() {
        System.out.println("clicado");
    }
    
    /**
     * fecha o modal
     */
    @FXML private void closeModal() {
        System.out.println("Close modal");
        StackNavigator.removeModal();
    }

    /**
     * Salva o agendamento
     */
    @FXML private void save() {
        UserDAO usd = new UserDAO();
        System.out.println("Save");
        System.out.println("User: " + cpfField.getText() + ", password: " + this.senhaField.getText());
        User patiente = usd.getUserByNameAndPassword(cpfField.getText(), this.senhaField.getText());

        /*if (AuthenticationController.usuarioLogado() != null && false) {
            Scheduling agendamento = this.horarioCombo.getValue();

            if (agendamento != null) {
                agendamento.mark(AuthenticationController.usuarioLogado());

                SchedulingDAO schDAO = new SchedulingDAO();
                schDAO.updateScheduling(agendamento);

                closeModal();

                System.out.println("Useeer: " + agendamento.getPatient().getName());

                if (this.ctr != null) {
                    ctr.renderAgain();
                }
            }
            return;
        }*/

        if (patiente == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Não foi possivel marcar consulta");
            alert.setHeaderText("Parece que você não tem um cadastro, compareça na recepção");

            alert.showAndWait();

        } else {
            Scheduling agendamento = this.horarioCombo.getValue();

            if (agendamento != null) {
                agendamento.mark(patiente);

                SchedulingDAO schDAO = new SchedulingDAO();
                schDAO.updateScheduling(agendamento);

                closeModal();

                System.out.println("Useeer: " + patiente.getName());

                if (this.ctr != null) {
                    ctr.removeUnavaliable();
                }
            }
            return;
        }
    }

    public void setScheduling(List<Scheduling> sch) {
        this.horarioCombo.getItems().addAll(sch);
        this.dataField.setText(sch.get(0).getDataString());

        this.horarioCombo.setValue(sch.get(0));
    }

    /**
     * Define o nome do medico
     * @param doc
     */
    public void setDoctorName(String doc) {
        this.doctorName.setText(doc);
    }

    /**
     * Define o define o texto do campo de especialidade do medico
     * @param doc
     */
    public void setDoctorSpecialty(String doc) {
        this.doctorSpecialty.setText(doc);
        this.especialidadeField.setText(doc);
    }

    public void initialize() {
        if (AuthenticationController.usuarioLogado() != null) {
            //this.cpfField.setText(AuthenticationController.usuarioLogado().getCpf());
            //this.saveButton.setText("Salvar");
        }
        //this.authCntainer.getChildren().remove(this.senhaCont);
    }

    public void setCtr(AutoAtendimentoController c) {
        this.ctr = c;
    }
}
