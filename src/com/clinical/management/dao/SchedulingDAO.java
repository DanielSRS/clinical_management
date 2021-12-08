package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

public class SchedulingDAO extends DatabaseConnection {

	/**
	 * Salva dado do agendamento no banco de dados
	 * 
	 * @param SchedulingToBeSaved
	 * @return true se o agendamento foi salvo na base de dados, do contrario, false
	 */

	public boolean saveScheduling(Scheduling schedulingToBeSaved) {
		conectar();
		String sql = "INSERT INTO scheduling (" + "day," + "hour," + "doctor_id," + "specialty_id," + "status,"
				+ "user_id)" + "VALUES (?, ?, ?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interrogação
															// será substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setLong(1, schedulingToBeSaved.getDay().getTimeInMillis());
			preparedStatement.setLong(2, schedulingToBeSaved.getHour().getTimeInMillis());
			preparedStatement.setInt(3, schedulingToBeSaved.getDoctor().getID());
			preparedStatement.setString(4, schedulingToBeSaved.getSpecialty().getName());
			preparedStatement.setString(5, schedulingToBeSaved.getStatus().toString());
			preparedStatement.setInt(6, schedulingToBeSaved.getPatient().getID());
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
	public List<Scheduling> getScheduling() {
		List<Scheduling> schedulingList = new ArrayList<>();

		conectar();

		String sql = "SELECT id, day, hour, doctor_id, specialty_id, status, user_id, specialty_id,"
				+ " sub_specialty, cpf, name, password, specialty.name AS specialty FROM scheduling"
				+ "INNER JOIN doctor ON scheduling.doctor_id = doctor.id " + "INNER JOIN users ON doctor.id = users.id "
				+ "INNER JOIN specialty_name ON doctor.specialty_id = specialty.id";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("id");
				
				Long day = result.getLong("day");
				Calendar calendar_day = Calendar.getInstance();
				calendar_day.setTimeInMillis(day);

				Long hour = result.getLong("hour");
				Calendar calendar_hour = Calendar.getInstance();
				calendar_hour.setTimeInMillis(hour);

				String name = result.getString("name");
				String cpf = result.getString("cpf");
				String password = result.getString("password");

				String specialty = result.getString("specialty_name");

				Specialty specialty_field = new Specialty(specialty);

				Doctor doctor = new Doctor(name, cpf, specialty_field, password);

				Scheduling aux = new Scheduling(calendar_day, calendar_hour, doctor, specialty_field);
				schedulingList.add(aux);
				aux.setId(id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar Agendamentos");
		}

		desconectar();
		return schedulingList;
	}
	
	public Boolean removeScheduling(Scheduling schedulingToBeRemoved) {
    	conectar();
		String sql = "DELETE FROM scheduling WHERE id = ?";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, schedulingToBeRemoved.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			desconectar();
			return false;
		}
		desconectar();
		return true;
    }

}
