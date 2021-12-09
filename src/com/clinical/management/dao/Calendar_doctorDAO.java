package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.clinical.management.model.calendar.Calendar_doctor;

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
			preparedStatement.setInt(1, calendar_doctorToBeSaved.get);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
}
