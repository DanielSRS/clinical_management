package com.clinical.management.controller;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.SchedulingDAO;
import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.specialty.Specialty;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AutoAtendimentoController {
    
    @FXML private VBox contentContainer;

    public void initialize() {
        SchedulingDAO sch = new SchedulingDAO();
        SpecialtyDAO espDAO = new SpecialtyDAO();

        List<Scheduling> agendamentos =  sch.getScheduling();
        List<Specialty> especialidades = espDAO.getSpecialtys();

        Iterator<Scheduling> it = agendamentos.iterator();

        while (it.hasNext()) {
            Scheduling aux = it.next();
            String dia = aux.getDay().get(Calendar.DAY_OF_MONTH) + "";
            int mes = aux.getDay().get(Calendar.MONTH) + 1;
            String hora = aux.getHour().get(Calendar.HOUR_OF_DAY) + "";
            String minutos = aux.getHour().get(Calendar.MINUTE) + "";
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
            String s = "Data: " + dia + "/ " + mes + " - hora: " + hora + ":" + minutos
                        + " - Especialidade: " + especialidade + " - Dr. " + medico;

            Label l = new Label();
            l.setText(s);

            contentContainer.getChildren().add(l);
        }
    }
}
