package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.calendar.Calendar_doctor;
import com.clinical.management.model.calendar.Day_doctor;

/**
 * Classe respons�vel pela busca no banco de dados especificamente na tabela agenda do m�dico
 *
 */
public class Calendar_doctorDAO extends DatabaseConnection {

	/**
	 * Salva dados da agenda do m�dico no banco de dados
	 * 
	 * @param Calendar_doctorToBeSaved
	 * @return true se a agenda do m�dico foi salvo na base de dados, do contrario, false
	 */

	public boolean saveCalendar_doctor(Calendar_doctor calendar_doctorToBeSaved) {
		conectar();
		String sql = "INSERT INTO calendar_doctor (" + "sunday_id," + "monday_id," + "tuesday_id," + "wednesday_id," + "thursday_id,"
				+ "friday_id," + "saturday_id," + "doctor_id)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			if (calendar_doctorToBeSaved.getSunday() != null) {
				preparedStatement.setInt(1, calendar_doctorToBeSaved.getSunday().getId());
			} else {
				preparedStatement.setNull(1, Types.INTEGER);
			}
			if (calendar_doctorToBeSaved.getMonday() != null) {
				preparedStatement.setInt(2, calendar_doctorToBeSaved.getMonday().getId());
			} else {
				preparedStatement.setNull(2, Types.INTEGER);
			}
			if (calendar_doctorToBeSaved.getTuesday() != null) {
				preparedStatement.setInt(3, calendar_doctorToBeSaved.getTuesday().getId());
			} else {
				preparedStatement.setNull(3, Types.INTEGER);
			}
			if (calendar_doctorToBeSaved.getWednesday() != null) {
				preparedStatement.setInt(4, calendar_doctorToBeSaved.getWednesday().getId());
			} else {
				preparedStatement.setNull(4, Types.INTEGER);
			}
			if (calendar_doctorToBeSaved.getThursday() != null) {
				preparedStatement.setInt(5, calendar_doctorToBeSaved.getThursday().getId());
			} else {
				preparedStatement.setNull(5, Types.INTEGER);
			}
			if (calendar_doctorToBeSaved.getFriday() != null) {
				preparedStatement.setInt(6, calendar_doctorToBeSaved.getFriday().getId());
			} else {
				preparedStatement.setNull(6, Types.INTEGER);
			}
			if (calendar_doctorToBeSaved.getSaturday() != null) {
				preparedStatement.setInt(7, calendar_doctorToBeSaved.getSaturday().getId());
			} else {
				preparedStatement.setNull(7, Types.INTEGER);
			}
			preparedStatement.setInt(8, calendar_doctorToBeSaved.getDocID());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
	
	/**
	 * Obtem todos os agendamentos salvos na base de dados
	 * 
	 * @return Objeto List do tipo agendamento com as informações dos agendamentos
	 * @see com.clinical.management.model.users.Scheduling
	 */
	public List<Calendar_doctor> getCalendar_doctor() {
		List<Calendar_doctor> calendar_doctorList = new ArrayList<>();
		Day_doctorDAO dayDAO = new Day_doctorDAO();

		conectar();

		String sql = "SELECT calendar_doctor.id AS calendar_doctor_id, sunday_id, monday_id, tuesday_id, wednesday_id, thursday_id, "
				+ " friday_id, saturday_id, doctor_id FROM calendar_doctor ";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer idDoCalendar = result.getInt("calendar_doctor_id");
				Integer sedundaID = result.getInt("monday_id");
				Integer tercaID =  result.getInt("tuesday_id");
				Integer quartaID = result.getInt("wednesday_id");
				Integer quintaID = result.getInt("thursday_id");
				Integer sextaId = result.getInt("friday_id");
				Integer sabadoID = result.getInt("saturday_id");
				Integer dommingoID = result.getInt("sunday_id");

				System.out.println("IDS: ");
				System.out.println(sedundaID);
				System.out.println(tercaID);
				System.out.println(quartaID);
				System.out.println(quintaID);
				System.out.println(sextaId);
				System.out.println(sabadoID);
				System.out.println(dommingoID);

				Day_doctor seg = null;
				Day_doctor ter = null;
				Day_doctor qua = null;
				Day_doctor qui = null;
				Day_doctor sex = null;
				Day_doctor sab = null;
				Day_doctor dom = null;


				if (sedundaID != 0) {
					seg = dayDAO.getDay_doctorByID(sedundaID);
				    //System.out.println("Segunda: " + seg.getStart_service().get(Calendar.HOUR_OF_DAY));
				}
				if (tercaID != 0) {
					ter = dayDAO.getDay_doctorByID(tercaID);
				    //System.out.println("Terça: " + ter.getStart_service().get(Calendar.HOUR_OF_DAY));
				}
				if (quartaID != 0) {
					qua = dayDAO.getDay_doctorByID(quartaID);
					//System.out.println("Quarta: " + qua.getStart_service().get(Calendar.HOUR_OF_DAY));
				}
				if (quintaID != 0) {
					qui = dayDAO.getDay_doctorByID(quintaID);
				    //System.out.println("Quinta: " + qui.getStart_service().get(Calendar.HOUR_OF_DAY));
				}
				if (sextaId != 0){
					sex = dayDAO.getDay_doctorByID(sextaId);
				    //System.out.println("Sexta: " + sex.getStart_service().get(Calendar.HOUR_OF_DAY));
				}
				if (sabadoID != 0) {
					sab = dayDAO.getDay_doctorByID(sabadoID);
				    //System.out.println("Sabado: " + sab.getStart_service().get(Calendar.HOUR_OF_DAY));
				}
				if (dommingoID != 0) {
					dom = dayDAO.getDay_doctorByID(dommingoID);
				    //System.out.println("Domingo: " + dom.getStart_service().get(Calendar.HOUR_OF_DAY));
				}

				Calendar_doctor cal = new Calendar_doctor();
				cal.setMonday(seg);
				cal.setTuesday(ter);
				cal.setWednesday(qua);
				cal.setThursday(qui);
				cal.setFriday(sex);
				cal.setSaturday(sab);
				cal.setSunday(dom);
				cal.setId(idDoCalendar);
				cal.setDocID(result.getInt("doctor_id"));

				calendar_doctorList.add(cal);
				
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar Agendamentos");
			e.printStackTrace();
			desconectar();
			return calendar_doctorList;
		}

		desconectar();
		return calendar_doctorList;
	}
}
