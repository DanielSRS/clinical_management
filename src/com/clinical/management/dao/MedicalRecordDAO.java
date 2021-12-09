package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.medicalRecord.MedicalRecord;

/**
 * Classe responsável pela busca no banco de dados especificamente na tabela prontuário médico
 *
 */
public class MedicalRecordDAO extends DatabaseConnection {

	/**
	 * Salva dado do protuï¿½rio no banco de dados
	 * @param userToBeSaved
	 * @return true se o protuï¿½rio foi salvo na base de dados, do contrario, false
	 */
	public boolean saveMedicalRecord(MedicalRecord medicalRecordToBeSaved) {
		conectar();
		String sql = "INSERT INTO medical_record ("
				+ "user_id,"
				+ "anamnesis,"
				+ "physical_exam,"
				+ "hypotheses,"
				+ "diagnoses,"
				+ "treatments)"
				
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, medicalRecordToBeSaved.getId());
			preparedStatement.setString(2, medicalRecordToBeSaved.getAnamnesis());
			preparedStatement.setString(3, medicalRecordToBeSaved.getPhysicalExams());
			preparedStatement.setString(3, medicalRecordToBeSaved.getHypotheses());
			preparedStatement.setString(3, medicalRecordToBeSaved.getDiagnoses());
			preparedStatement.setString(3, medicalRecordToBeSaved.getTreatments());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
	
	/**
	 * Obtem todos  os protuï¿½rios salvos na base de dados
	 * 
	 * @return Objeto List do tipo protuï¿½rio com as informaÃ§Ãµes dos protuï¿½rios
	 */
	public List<MedicalRecord> getMedicalRecord() {
		List<MedicalRecord> medicalRecordList = new ArrayList<>();

		conectar();

		String sql = "SELECT id, user_id, anamnesis, physical_exam, hypotheses, diagnoses, treatments FROM medical_record";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("id");
				
				Integer user_id = result.getInt("user_id");
				
				String anamnesis = result.getString("anamnesis");
				String physical_exam = result.getString("physical_exam");
				String hypotheses = result.getString("hypotheses");
				String diagnoses = result.getString("diagnoses");
				String treatments = result.getString("treatments");		
				

				MedicalRecord aux = new MedicalRecord(user_id, anamnesis, physical_exam, hypotheses, diagnoses, treatments);
				medicalRecordList.add(aux);
				aux.setId(id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar Consultas");
		}

		desconectar();
		return medicalRecordList;
	}

}
