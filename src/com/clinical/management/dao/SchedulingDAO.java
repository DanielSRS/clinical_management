package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

/**
 * Classe respons�vel pela busca no banco de dados especificamente na tabela agedamento
 *
 */
public class SchedulingDAO extends DatabaseConnection {

	/**
	 * Salva dado do agendamento no banco de dados
	 * 
	 * @param SchedulingToBeSaved
	 * @return true se o agendamento foi salvo na base de dados, do contrario, false
	 */

	public boolean saveScheduling(Scheduling schedulingToBeSaved) {
		conectar();
		String sql = "INSERT INTO scheduling (day, hour, doctor_id, specialty_id, status, user_id) VALUES (?, ?, ?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setLong(1, schedulingToBeSaved.getDay().getTimeInMillis());
			preparedStatement.setLong(2, schedulingToBeSaved.getHour().getTimeInMillis());
			preparedStatement.setInt(3, schedulingToBeSaved.getDoctor().getId());
			preparedStatement.setInt(4, schedulingToBeSaved.getSpecialty());
			preparedStatement.setString(5, schedulingToBeSaved.getStatus().toString());
			if (schedulingToBeSaved.getPatient() != null) {
				preparedStatement.setInt(6, schedulingToBeSaved.getPatient().getID());
			} else {
				preparedStatement.setNull(6, Types.INTEGER);
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}

	/**
	 * Atualiza agendamento
	 * @param schedulingToBeSaved
	 * @return
	 */
	public boolean updateScheduling(Scheduling schedulingToBeSaved) {
		conectar();
		String sql = "UPDATE scheduling SET status = ?, user_id = ? WHERE id = ? "; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setString(1, schedulingToBeSaved.getStatus().toString());
			if (schedulingToBeSaved.getPatient() != null) {
				preparedStatement.setInt(2, schedulingToBeSaved.getPatient().getID());
			} else {
				preparedStatement.setNull(2, Types.INTEGER);
			}
			preparedStatement.setInt(3, schedulingToBeSaved.getId());
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
	 */
	public List<Scheduling> getScheduling() {
		List<Scheduling> schedulingList = new ArrayList<>();

		conectar();

		String sql = "SELECT scheduling.user_id AS pacienteID, scheduling.id AS sheduling_id, day, hour, doctor_id, scheduling.status AS sch_status, scheduling.user_id AS sch_user_id, "
				+ " sub_specialty, doctor.id AS idDoMedico, cpf, users.name AS users_name, password, doctor.specialty_id AS doc_specialty_id, specialty.name AS specialty_name FROM scheduling "
				+ "INNER JOIN doctor ON scheduling.doctor_id = doctor.id INNER JOIN users ON doctor.user_id = users.id "
				+ "INNER JOIN specialty ON doctor.specialty_id = specialty.id ORDER BY day";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("sheduling_id");

				Integer pacienteID = result.getInt("pacienteID");
				
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
				int specID = result.getInt("doc_specialty_id");

				Specialty specialty_field = new Specialty(specialty, "", specID);
				

				int idDoMedico = result.getInt("doctor_id");
				Doctor doctor = new Doctor(name, cpf, password, specialty_field.getID());
				doctor.setId(idDoMedico);

				Scheduling aux = new Scheduling(calendar_day, calendar_hour, doctor, specialty_field.getID());
				aux.changeStatus(result.getString("sch_status"));
				aux.setId(id);
				aux.setpacienteID(pacienteID);
				schedulingList.add(aux);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao recuperar Agendamentos");
			return null;
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
	
	public Scheduling getByID(int scheduling_id) {
		Scheduling aux = null;

		conectar();

		String sql = "SELECT scheduling.id AS sheduling_id, day, hour, doctor_id, status, user_id, "
				+ " sub_specialty, cpf, users.name AS users_name, password, specialty.name AS specialty_name FROM scheduling WHERE id = ? "
				+ "INNER JOIN doctor ON scheduling.doctor_id = doctor.id " + "INNER JOIN users ON doctor.id = users.id "
				+ "INNER JOIN specialty ON doctor.specialty_id = specialty.id";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			preparedStatement.setInt(1, scheduling_id);
			result = preparedStatement.executeQuery();

			if (result.next()) {
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

				aux = new Scheduling(calendar_day, calendar_hour, doctor, specialty_field.getID());
				aux.setId(id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar Agendamentos");
			desconectar();
			return null;
		}

		desconectar();
		return aux;
	}

}
