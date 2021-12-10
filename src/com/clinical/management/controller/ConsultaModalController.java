package com.clinical.management.controller;

import com.clinical.management.dao.MedicalRecordDAO;
import com.clinical.management.dao.QueryDAO;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.consultation.Query;
import com.clinical.management.model.medicalRecord.MedicalRecord;
import com.clinical.management.view.navigation.StackNavigator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ConsultaModalController {

    private Scheduling sch;

    QueryDAO qDAO = new QueryDAO();

    MedicalRecordDAO mrDAO = new MedicalRecordDAO();

    @FXML private Button saveButton;

    @FXML private TextArea an;

    @FXML private TextArea ex;

    @FXML private TextArea hi;

    @FXML private TextArea di;

    @FXML private TextArea tr;

    @FXML private void save() {
        this.saveButton.setDisable(true);
        System.out.println(an.getText());
        System.out.println(ex.getText());
        System.out.println(hi.getText());
        System.out.println(di.getText());
        System.out.println(tr.getText());

        int id = this.sch.getpacienteID();

        MedicalRecord mr = new MedicalRecord(id, an.getText() + "\n\n", 
                            ex.getText() + "\n\n", hi.getText() + "\n\n", 
                            di.getText() + "\n\n", tr.getText() + "\n\n");
    
        
        Integer idDoProntuario = mrDAO.saveMedicalRecord(mr);

        Query consulta = new Query(this.sch.getId(), idDoProntuario, this.sch.getDay(), this.sch.getpacienteID());

        

        qDAO.saveQuery(consulta);

        closeModal();
    }
    
    public void closeModal() {
        StackNavigator.removeModal();
    }

    public void setSch(Scheduling s) {
        this.sch = s;
        System.out.println("Id do agendamento: " + s.getId());
        Query consulta = qDAO.getQueryBySchedulingID(this.sch.getId());

        if (consulta != null) {
            System.out.println("Consulta recuperada");
            this.saveButton.setDisable(true);
            MedicalRecord mr = mrDAO.getMedicalRecordByID(consulta.getMedicalRecord());

            if (mr != null) {
                System.out.println("Prontuario recuperado");
                an.setText(mr.getAnamnesis());
                ex.setText(mr.getPhysicalExams());
                hi.setText(mr.getHypotheses());
                di.setText(mr.getDiagnoses());
                tr.setText(mr.getTreatments());
            }
        }
    }

    public void initialize() {
        
    }
}
