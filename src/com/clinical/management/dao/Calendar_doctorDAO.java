package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.calendar.Calendar_doctor;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

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

		conectar();

		String sql = "SELECT calendar_doctor.id AS calendar_doctor_id, sunday_id, monday_id, tuesday_id, wednesday_id, thursday_id, "
				+ " friday_id, saturday_id, doctor_id FROM calendar_doctor ON ";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("sheduling_id");
				
				Long day = result.getLong("day");
				Calendar calendar_day = Calendar.getInstance();
				calendar_day.setTimeInMillis(day);

				Long hour = result.getLong("hour");
				Calendar calendar_hour = Calendar.getInstance();
				calendar_hour.setTimeInMillis(hour);

				String name = result.getString("users_name");
				String cpf = result.getString("cpf");
				String password = result.getString("password");

				String specialty = result.getString("specialty_name");

				Specialty specialty_field = new Specialty(specialty);
				

				Doctor doctor = new Doctor(name, cpf, password, specialty_field.getID());

				Scheduling aux = new Scheduling(calendar_day, calendar_hour, doctor, specialty_field.getID());
				calendar_doctorList.add(aux);
				aux.setId(id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar Agendamentos");
		}

		desconectar();
		return calendar_doctorList;
	}
}
