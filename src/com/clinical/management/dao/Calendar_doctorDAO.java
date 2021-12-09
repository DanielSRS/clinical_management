package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.clinical.management.model.calendar.Calendar_doctor;
import com.clinical.management.model.calendar.Scheduling;

public class Calendar_doctorDAO extends DatabaseConnection {

	/**
	 * Salva dados da agenda do m�dico no banco de dados
	 * 
	 * @param Calendar_doctorToBeSaved
	 * @return true se a agenda do m�dico foi salvo na base de dados, do contrario, false
	 */

	public boolean saveCalendar_doctor(Calendar_doctor calendar_doctorToBeSaved) {
		conectar();
		String sql = "INSERT INTO scheduling (" + "day," + "hour," + "doctor_id," + "specialty_id," + "status,"
				+ "user_id)" + "VALUES (?, ?, ?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			/*preparedStatement.setLong(1, schedulingToBeSaved.getDay().getTimeInMillis());
			preparedStatement.setLong(2, schedulingToBeSaved.getHour().getTimeInMillis());
			preparedStatement.setInt(3, schedulingToBeSaved.getDoctor().getID());
			preparedStatement.setInt(4, schedulingToBeSaved.getSpecialty());
			preparedStatement.setString(5, schedulingToBeSaved.getStatus().toString());
			preparedStatement.setInt(6, schedulingToBeSaved.getPatient().getID());*/
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
}
