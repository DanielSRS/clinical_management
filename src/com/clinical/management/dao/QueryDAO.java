package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.clinical.management.model.consultation.Query;

//import com.clinical.management.model.consultation.Query;

public class QueryDAO extends DatabaseConnection {
	
	/**
	 * Salva dado da consulta no banco de dados
	 * 
	 * @param QueryToBeSaved
	 * @return true se a consulta foi salvo na base de dados, do contrario, false
	 */

	public boolean saveQuery(Query QueryToBeSaved) {
		conectar();
		String sql = "INSERT INTO query (" + "scheduling_id," + "medicalRecords_id" + "date" + "user_id)"
				+ "VALUES (?, ?, ?, ?)"; // Ele prepara a query pra executar. onde tem a interroga��o
															// ser� substituido abaixo.

		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, QueryToBeSaved.getScheduling());
			preparedStatement.setInt(2, QueryToBeSaved.getMedicalRecord());
			preparedStatement.setLong(2, QueryToBeSaved.getDate().getTimeInMillis());
			preparedStatement.setInt(2, QueryToBeSaved.getUser());
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
	public List<Query> getQuery() {
		List<Query> queryList = new ArrayList<>();

		conectar();

		String sql = "SELECT id, scheduling_id, medicalRecords_id, date, user_id FROM query";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				Integer id = result.getInt("id");
				
				Integer scheduling_id = result.getInt("sheduling_id");
				
				Integer medicalRecords_id = result.getInt("medicalRecords_id");
				
				Long date = result.getLong("date");
				Calendar calendar_date = Calendar.getInstance();
				calendar_date.setTimeInMillis(date);
				
				Integer user_id = result.getInt("user_id");


				Query aux = new Query(scheduling_id, medicalRecords_id, calendar_date, user_id);
				queryList.add(aux);
				aux.setId(id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao recuperar Consultas");
		}

		desconectar();
		return queryList;
	}
	
}
