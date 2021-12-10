package com.clinical.management.controller;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class AgendamentoCardController {
    private Doctor doctor;

    private List<Scheduling> schedules;

    List<Specialty> especialidades;

    @FXML private Label doctorName;

    @FXML private Label doctorSpecialty;

    @FXML private HBox horariosContainer;

    public void setDoctor(Doctor doc) {
        this.doctor = doc;
        this.doctorName.setText(doc.getName());
        SpecialtyDAO spd = new SpecialtyDAO();
        Specialty especialidadeDoMedico = spd.getSpecialtyByID(doc.getSpecialty());

        if (especialidadeDoMedico != null) {
            this.doctorSpecialty.setText(especialidadeDoMedico.getName());
        }
    }

    public void setSchedules(List<Scheduling> sch) {
        this.schedules = sch;
        printAgendamentos(sch);
    }

    public void setSpecialty(String specialty) {
        this.doctorSpecialty.setText(specialty);
    }

    private void printAgendamentos(List<Scheduling> agend) {
        horariosContainer.getChildren().clear();
        Iterator<Scheduling> it = agend.iterator();
        while (it.hasNext()) {
            Scheduling aux = it.next();
            String dia = aux.getDay().get(Calendar.DAY_OF_MONTH) + "";
            int mes = aux.getDay().get(Calendar.MONTH) + 1;
            int hora = aux.getHour().get(Calendar.HOUR_OF_DAY);
            int minutos = aux.getHour().get(Calendar.MINUTE);
            String status = aux.getStatus().toString();
            String medico = aux.getDoctor().getName();
            String especialidade = "";
            Iterator<Specialty> espIT = especialidades.iterator();
            while(espIT.hasNext()) {
                Specialty auxEsp = espIT.next();
                System.out.println(auxEsp.getName() + " - " + auxEsp.getID() + " - " + aux.getDoctor().getSpecialty());
                if (auxEsp.getID() == aux.getDoctor().getSpecialty()) {
                    especialidade = auxEsp.getName();
                }
            }
            String s = "        Data: " + dia + "/ " + mes + " - hora: " + hora + ":" + minutos
                        + "Status: " + status + " - Especialidade: " + especialidade + " - Dr. " + medico;

            Label l = new Label();
            l.setText(s);

            String horaS = "";
            String minutosS = "";
            if (minutos < 10) {
                minutosS = "0" + minutos;
            } else {
                minutosS = minutos + "";
            }

            if (hora < 10) {
                horaS = "0" + hora;
            } else {
                horaS = hora + "";
            }
             

            Button h = new Button(horaS + ":" + minutosS);
            h.getStyleClass().add("scheduling-button");

            horariosContainer.getChildren().add(h);

            
        }
    }

    public void setEspecialidade(List<Specialty> esp) {
        this.especialidades = esp;
    }
}
