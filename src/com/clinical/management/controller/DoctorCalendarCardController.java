package com.clinical.management.controller;

import java.util.Calendar;

import com.clinical.management.dao.DoctorDAO;
import com.clinical.management.model.calendar.Calendar_doctor;
import com.clinical.management.model.doctor.Doctor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DoctorCalendarCardController {
    
    private Calendar_doctor agendaDoMedico;

    private Doctor doctor;

    @FXML private Label doctorName;

    // Segunda
    @FXML private TextField segHI;
    @FXML private TextField segHF;
    @FXML private TextField segMI;
    @FXML private TextField segMF;

    // Terça
    @FXML private TextField terHI;
    @FXML private TextField terHF;
    @FXML private TextField terMI;
    @FXML private TextField terMF;

    // Quarta
    @FXML private TextField quaHI;
    @FXML private TextField quaHF;
    @FXML private TextField quaMI;
    @FXML private TextField quaMF;

    // Quinta
    @FXML private TextField quiHI;
    @FXML private TextField quiHF;
    @FXML private TextField quiMI;
    @FXML private TextField quiMF;

    // Sexta
    @FXML private TextField sexHI;
    @FXML private TextField sexHF;
    @FXML private TextField sexMI;
    @FXML private TextField sexMF;

    // Sabado
    @FXML private TextField sabHI;
    @FXML private TextField sabHF;
    @FXML private TextField sabMI;
    @FXML private TextField sabMF;

    // Domingo
    @FXML private TextField domHI;
    @FXML private TextField domHF;
    @FXML private TextField domMI;
    @FXML private TextField domMF;

    public void setAgenda(Calendar_doctor agenda) {
        DoctorDAO docDao = new DoctorDAO();
        this.agendaDoMedico = agenda;

        doctor = docDao.getDoctorByID(agenda.getDocID());

        this.doctorName.setText(doctor.getName());
        initSegunda();
        initTerca();
        initQua();
        initQui();
    }

    private void initSegunda() {
        if (agendaDoMedico.getMonday() == null) {
            return;
        }
        //TimeZone tz = agendaDoMedico.getMonday().getStart_service().getTimeZone();

        // Getting zone id
        //ZoneId zoneId = tz.toZoneId();

        // conversion
        //LocalDateTime localDateTime = LocalDateTime.ofInstant(agendaDoMedico.getMonday().getStart_service().toInstant(), zoneId);
        //System.out.println(localDateTime.toLocalTime());
        String hora = agendaDoMedico.getMonday().getStart_service().get(Calendar.HOUR_OF_DAY) + "";
        String horafin = agendaDoMedico.getMonday().getEnd_service().get(Calendar.HOUR_OF_DAY) + "";
        segHI.setText(hora);
        segHF.setText(horafin);
        //System.out.println("Hi: " + hora + ", HF: " + horafin);
    }

    private void initTerca() {
        if (agendaDoMedico.getTuesday() == null) {
            return;
        }
        String hora = agendaDoMedico.getTuesday().getStart_service().get(Calendar.HOUR_OF_DAY) + "";
        String horafin = agendaDoMedico.getTuesday().getEnd_service().get(Calendar.HOUR_OF_DAY) + "";
        terHI.setText(hora);
        terHF.setText(horafin);
        //System.out.println("Hi: " + hora + ", HF: " + horafin);
    }

    private void initQua() {
        if (agendaDoMedico.getWednesday() == null) {
            return;
        }
        String hora = agendaDoMedico.getWednesday().getStart_service().get(Calendar.HOUR_OF_DAY) + "";
        String horafin = agendaDoMedico.getWednesday().getEnd_service().get(Calendar.HOUR_OF_DAY) + "";
        quaHI.setText(hora);
        quaHF.setText(horafin);
        //System.out.println("Hi: " + hora + ", HF: " + horafin);
    }

    private void initQui() {
        if (agendaDoMedico.getThursday() == null) {
            return;
        }
        String hora = agendaDoMedico.getThursday().getStart_service().get(Calendar.HOUR_OF_DAY) + "";
        String horafin = agendaDoMedico.getThursday().getEnd_service().get(Calendar.HOUR_OF_DAY) + "";
        quiHI.setText(hora);
        quiHF.setText(horafin);
        //System.out.println("Hi: " + hora + ", HF: " + horafin);
    }

}
