package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.consultation.Query;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

public class QueryDAO extends DatabaseConnection {
	
	/**
	 * Salva dado da consulta no banco de dados
	 * 
	 * @param QueryToBeSaved
	 * @return true se a consulta foi salvo na base de dados, do contrario, false
	 */

	public boolean saveQuery(Query QueryToBeSaved) {
		conectar();
		String sql = "INSERT INTO query (" + "scheduling_id," + "medicalRecords_id)"
				+ "VALUES (?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, QueryToBeSaved.getScheduling().getId());
			preparedStatement.setInt(2, QueryToBeSaved.getMedicalRecord().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
	
	/**
	 * Obtem todos  as consulta salvos na base de dados
	 * 
	 * @return Objeto List do tipo agendamento com as informações dos agendamentos
	 * @see com.clinical.management.model.users.Scheduling
	 */
	public List<Scheduling> getScheduling() {
		List<Scheduling> schedulingList = new ArrayList<>();

		conectar();

		String sql = "SELECT scheduling.id AS sheduling_id, day, hour, doctor_id, status, user_id, "
				+ " sub_specialty, cpf, users.name AS users_name, password, specialty.name AS specialty_name FROM scheduling "
				+ "INNER JOIN doctor ON scheduling.doctor_id = doctor.id " + "INNER JOIN users ON doctor.id = users.id "
				+ "INNER JOIN specialty ON doctor.specialty_id = specialty.id";

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
	
}
