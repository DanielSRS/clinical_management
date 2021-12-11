package com.clinical.management.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.SchedulingDAO;
import com.clinical.management.dao.SpecialtyDAO;
import com.clinical.management.dao.UserDAO;
import com.clinical.management.model.calendar.OrderStatus;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.specialty.Specialty;
import com.clinical.management.model.users.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AtendimentoMedicoController {

    @FXML VBox contentContainer;

    SchedulingDAO schDAO = new SchedulingDAO();
    
    SpecialtyDAO espDAO = new SpecialtyDAO();

    List<Specialty> especialidades = espDAO.getSpecialtys();

    List<Scheduling> ag = schDAO.getScheduling();

    public void initialize() {
        Iterator<Scheduling> it = ag.iterator();

        while(it.hasNext()) {
            Scheduling aux = it.next();

            if(aux.getStatus() != OrderStatus.MARKED || aux.getDoctor().getId() != 1) {
                System.out.println("ag não disponivel com id: " + aux.getId());
                it.remove();
            }

        }

        printAgendamentos(ag);
    }

    private void printAgendamentos(List<Scheduling> agend) {
        Iterator<Scheduling> it = agend.iterator();
        while (it.hasNext()) {
            Scheduling aux = it.next();
            //String dia = aux.getDay().get(Calendar.DAY_OF_MONTH) + "";
            //int mes = aux.getDay().get(Calendar.MONTH) + 1;
            //String hora = aux.getHour().get(Calendar.HOUR_OF_DAY) + "";
            //String minutos = aux.getHour().get(Calendar.MINUTE) + "";
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
            Integer idDoaciente = aux.getpacienteID();
            String pname = ""; 
            if (idDoaciente  != null && idDoaciente != 0) {
                UserDAO ud = new UserDAO();
                User p = ud.getUserByID(idDoaciente);
                if (p != null) {
                    pname = p.getName();
                    aux.setPatient(p);
                }
            }
            String s = "Paciente: " + pname + " data: " + aux.getDataString() + " - hora: " + aux.toString()
                        + " Status: " + status + " - Especialidade: " + especialidade + " - Dr. " + medico;

            Label l = new Label();
            l.setText(s);

            FXMLLoader calCard = new FXMLLoader(getClass().getResource("../view/pages/AtendimentoCard.fxml"));
            try {
                Parent card = calCard.load();
                AtendimentoCardController cont = calCard.getController();
                cont.setInformation(s);
                cont.sche(aux);
                
                contentContainer.getChildren().add(card);
                //StackNavigator.addModal(card);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            

            
        }
    }
    
}
