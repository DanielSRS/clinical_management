package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.clinical.management.model.calendar.Scheduling;
import com.clinical.management.model.doctor.Doctor;

public class DoctorDAO extends DatabaseConnection {
	
	/**
	 * Salva dado dos m�dicos no banco de dados
	 * 
	 * @param DoctorToBeSaved
	 * @return true se o m�dico foi salvo na base de dados, do contrario, false
	 */

	public boolean saveDoctor(Doctor DoctorToBeSaved) {
		conectar();
		String sql = "INSERT INTO doctor (" + "specialty_id," + "sub_specialty)"
				+ "VALUES (?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, DoctorToBeSaved.getSpecialty());
			preparedStatement.setInt(2, DoctorToBeSaved.getSub_specialty());
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
	 * @return Objeto List do tipo consulta com as informações dos agendamentos
	 * @see com.clinical.management.model.users.Scheduling
	 */
	public List<Doctor> getDoctor() {
		List<Doctor> doctorList = new ArrayList<>();

		conectar();

		String sql = "SELECT doctor.id AS doctor_id, specialty_id, sub_specialty, user_id, users.id AS users_id, cpf, name, password"
				+ "FROM doctor INNER JOIN users ON doctor.user_id = users_id";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer doctor_id = result.getInt("doctor_id");
				
				Integer specialty_id = result.getInt("specialty_id");
				
				Integer sub_specialty = result.getInt("sub_specialty");

				String cpf = result.getString("cpf");
				String name = result.getString("name");
				String password = result.getString("password");


				Doctor aux = new Doctor(name, cpf, password, specialty_id, sub_specialty);
				doctorList.add(aux);
				aux.setId(doctor_id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar Consultas");
		}

		desconectar();
		return doctorList;
	}
	
	public Boolean removeDoctor(Doctor doctorToBeRemoved) {
    	conectar();
		String sql = "DELETE FROM Doctor WHERE id = ?";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, doctorToBeRemoved.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			desconectar();
			return false;
		}
		desconectar();
		return true;
    }
	
	public Integer saveDoctor(Doctor DoctorToBeSaved, int user_ID) {
		Integer doctor_ID = null;
		conectar();
		String sql = "INSERT INTO doctor (specialty_id, sub_specialty, user_id) VALUES (?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, DoctorToBeSaved.getSpecialty());
			preparedStatement.setInt(2, DoctorToBeSaved.getSub_specialty());
			preparedStatement.setInt(3, user_ID);
			preparedStatement.executeUpdate();
			ResultSet res = preparedStatement.getGeneratedKeys();
			if (res.next()) {
				doctor_ID = res.getInt(1);
			}
		} catch (SQLException e) {
			desconectar();
			return doctor_ID;
		}
		desconectar();
		return doctor_ID;
	}
	
	
}
