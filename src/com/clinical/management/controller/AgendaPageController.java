package com.clinical.management.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.clinical.management.dao.Calendar_doctorDAO;
import com.clinical.management.model.calendar.Calendar_doctor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AgendaPageController {

    @FXML private VBox contentContainer;
    
    public void initialize() {
        Calendar_doctorDAO calDAO = new Calendar_doctorDAO();
        List<Calendar_doctor> calList = calDAO.getCalendar_doctor();
        Iterator<Calendar_doctor> it = calList.iterator();

        contentContainer.getChildren().clear();

        while(it.hasNext()) {
            Calendar_doctor aux = it.next();

            Label lb = new Label();
            String f = "ID do calendario: " + aux.getId() + ", ID do medico: " + aux.getDocID();
            String g = "";
			if (aux.getMonday() != null) g = "\n" + aux.getMonday().getStart_service().get(Calendar.HOUR_OF_DAY);
            //System.out.println(f + g);

            //contentContainer.getChildren().add(lb);

            FXMLLoader calCard = new FXMLLoader(getClass().getResource("../view/pages/DoctorCalendarCard.fxml"));
			try {
				Parent card = calCard.load();
				DoctorCalendarCardController cont = calCard.getController();
				cont.setAgenda(aux);

                contentContainer.getChildren().add(card);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    public void renderUsers() {
		/*UserDAO usersDB = new UserDAO();
		List<User> users =  usersDB.getUsers(); // Obtem todos os usuários
		Collections.reverse(users);
		Iterator<User> it = users.iterator();
		this.usersContainer.getChildren().clear(); // Apaga qualquer dado preexistente
		while (it.hasNext()) {
			User aux = it.next();
			//Label lb = new Label();
			//String data = "Nome: " + aux.getName() + ", CPF: " + aux.getCpf() + ", ID: " + aux.getID();
			//lb.setText(data);
			//this.usersContainer.getChildren().add(lb);

			FXMLLoader userCard = new FXMLLoader(getClass().getResource("../view/pages/UserCard.fxml"));
			try {
				Parent card = userCard.load();
				UserCardController cont = userCard.getController();
				cont.setUser(aux);
				this.usersContainer.getChildren().add(card);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
}
