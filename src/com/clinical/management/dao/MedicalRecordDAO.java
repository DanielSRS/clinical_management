package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.medicalRecord.MedicalRecord;

/**
 * Classe respons�vel pela busca no banco de dados especificamente na tabela prontu�rio m�dico
 *
 */
public class MedicalRecordDAO extends DatabaseConnection {

	/**
	 * Salva dado do protu�rio no banco de dados
	 * @param userToBeSaved
	 * @return true se o protu�rio foi salvo na base de dados, do contrario, false
	 */
	public Integer saveMedicalRecord(MedicalRecord medicalRecordToBeSaved) {
		Integer idDoProntuario = null;
		conectar();
		String sql = "INSERT INTO medical_record ("
				+ " user_id,"
				+ " anamnesis,"
				+ " physical_exam,"
				+ " hypotheses,"
				+ " diagnoses,"
				+ " treatments) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, medicalRecordToBeSaved.getPatient());
			preparedStatement.setString(2, medicalRecordToBeSaved.getAnamnesis());
			preparedStatement.setString(3, medicalRecordToBeSaved.getPhysicalExams());
			preparedStatement.setString(4, medicalRecordToBeSaved.getHypotheses());
			preparedStatement.setString(5, medicalRecordToBeSaved.getDiagnoses());
			preparedStatement.setString(6, medicalRecordToBeSaved.getTreatments());
			if (preparedStatement.executeUpdate() == 1) {
				System.out.println("salvo");
			} else {
				System.out.println("Não salvo");
			}

			ResultSet res = preparedStatement.getGeneratedKeys();

			while(res.next()){
				idDoProntuario = res.getInt(1);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			desconectar();
			return idDoProntuario;
		}
		desconectar();
		return idDoProntuario;
	}
	
	/**
	 * Obtem todos  os protu�rios salvos na base de dados
	 * 
	 * @return Objeto List do tipo protu�rio com as informações dos protu�rios
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

	public List<MedicalRecord> getMedicalRecordByID(int schID) {
		List<MedicalRecord> medicalRecordList = new ArrayList<>();

		conectar();

		String sql = "SELECT id, user_id, anamnesis, physical_exam, hypotheses, diagnoses, treatments FROM medical_record WHERE id = ?";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			preparedStatement.setInt(1, schID);
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
