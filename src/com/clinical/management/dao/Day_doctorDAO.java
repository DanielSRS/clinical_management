package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.calendar.Day_doctor;
import com.clinical.management.model.doctor.Doctor;
import com.clinical.management.model.specialty.Specialty;

public class Day_doctorDAO extends DatabaseConnection {

	/**
	 * Salva dado do dia do m�dico no banco de dados
	 * 
	 * @param Day_doctorToBeSaved
	 * @return true se o dia do m�dico foi salvo na base de dados, do contrario,
	 *         false
	 */

	public boolean saveDay_doctor(Day_doctor day_doctorToBeSaved) {
		conectar();
		String sql = "INSERT INTO day_doctor (" + "start_service," + "end_service" + "doctor_id" + "duration_service)"
				+ "VALUES (?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
											// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setLong(1, day_doctorToBeSaved.getStart_service().getTimeInMillis());
			preparedStatement.setLong(2, day_doctorToBeSaved.getEnd_service().getTimeInMillis());
			preparedStatement.setInt(3, day_doctorToBeSaved.getDoctor().getId());
			preparedStatement.setInt(4, day_doctorToBeSaved.getDuration_service());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}

	public Integer saveDay_doctor(Day_doctor day_doctorToBeSaved, int id) {
		Integer novoID = null;
		conectar();
		String sql = "INSERT INTO day_doctor (start_service, end_service, doctor_id, duration_service)"
				+ "VALUES (?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
											// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setLong(1, day_doctorToBeSaved.getStart_service().getTimeInMillis());
			preparedStatement.setLong(2, day_doctorToBeSaved.getEnd_service().getTimeInMillis());
			preparedStatement.setInt(3, day_doctorToBeSaved.getDoctor().getId());
			preparedStatement.setInt(4, day_doctorToBeSaved.getDuration_service());

			preparedStatement.executeUpdate();
			ResultSet res = preparedStatement.getGeneratedKeys();
			if (res.next()) {
				novoID = res.getInt(1);
			}
		} catch (SQLException e) {
			desconectar();
			return novoID;
		}
		desconectar();
		return novoID;
	}

	/**
	 * Obtem todos os dias do m�dico salvos na base de dados
	 * 
	 * @return Objeto List do tipo day_doctor com as informações dos dias do
	 *         m�dico
	 * @see com.clinical.management.model.users.User
	 */
	public List<Day_doctor> getDay_doctor() {
		List<Day_doctor> day_doctorList = new ArrayList<>();

		conectar();

		String sql = "SELECT day_doctor.id AS day_doctor_id, start_service, end_service, doctor_id, duration_service,"
				+ " doctor.id AS doctors_id FROM day_doctor, specialty_id, sub_specialty, user_id, users.id AS users_id, cpf, users.name AS users_name, status, password "
				+ "INNER JOIN doctor ON day_doctor.doctor_id = doctors_id"
				+ "INNER JOIN users ON doctor.user_id = users.users_id";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {

				Long start_service = result.getLong("start_service");
				Calendar calendar_start_service = Calendar.getInstance();
				calendar_start_service.setTimeInMillis(start_service);

				Long end_service = result.getLong("end_service");
				Calendar calendar_end_service = Calendar.getInstance();
				calendar_end_service.setTimeInMillis(end_service);

				int duration_service = result.getInt("duration_service");

				String specialty_id = result.getString("specialty_id");
				Specialty specialty_field = new Specialty(specialty_id);

				String sub_specialty = result.getString("sub_specialty");
				Specialty specialty_fieldSub = new Specialty(sub_specialty);

				String cpf = result.getString("cpf");
				String users_name = result.getString("users_name");

				String password = result.getString("password");

				Doctor doctor = new Doctor(users_name, cpf, password, specialty_field.getID(),
						specialty_fieldSub.getID());

				Day_doctor aux = new Day_doctor(calendar_start_service.get(Calendar.HOUR_OF_DAY),
						calendar_end_service.get(Calendar.HOUR_OF_DAY), doctor, duration_service);
				day_doctorList.add(aux);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar dia do m�dico");
		}

		desconectar();
		return day_doctorList;
	}
	
	public Boolean removeDay_doctor(Day_doctor day_doctorToBeRemoved) {
    	conectar();
		String sql = "DELETE FROM day_doctor WHERE id = ?";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, day_doctorToBeRemoved.getId());
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
