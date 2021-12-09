package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.calendar.Calendar_doctor;
import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

public class Calendar_doctorDAO extends DatabaseConnection {

	/**
	 * Salva dados da agenda do médico no banco de dados
	 * 
	 * @param Calendar_doctorToBeSaved
	 * @return true se a agenda do médico foi salvo na base de dados, do contrario, false
	 */

	public boolean saveCalendar_doctor(Calendar_doctor calendar_doctorToBeSaved) {
		conectar();
		String sql = "INSERT INTO calendar_doctor (" + "sunday_id," + "monday_id," + "tuesday_id," + "wednesday_id," + "thursday_id,"
				+ "friday_id," + "saturday_id," + "doctor_id)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interrogação
															// será substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, calendar_doctorToBeSaved.getSunday().get);
			preparedStatement.setInt(2, calendar_doctorToBeSaved.getMonday().getId());
			preparedStatement.setInt(3, calendar_doctorToBeSaved.getTuesday().getId());
			preparedStatement.setInt(4, calendar_doctorToBeSaved.getWednesday().getId());
			preparedStatement.setInt(5, calendar_doctorToBeSaved.getThursday().getId());
			preparedStatement.setInt(6, calendar_doctorToBeSaved.getFriday().getId());
			preparedStatement.setInt(1, calendar_doctorToBeSaved.getSaturday().getId());
			preparedStatement.setInt(1, calendar_doctorToBeSaved.getDoctor());
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
	 * @return Objeto List do tipo agendamento com as informaÃ§Ãµes dos agendamentos
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
