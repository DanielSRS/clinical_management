package com.clinical.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe respons�vel pela conex�o com o banco de dados
 *
 */
public class DatabaseConnection {
	
	/**
	 * Conexão com o banco de dados
	 */
	private Connection conexao;
	
	/**
	 * Cria uma conexão com o banco de dados
	 * @return
	 */
	public boolean conectar() {
		try {
			String pathToDB = System.getProperty("user.dir") + "\\db\\cmdb.db";
			String url = "jdbc:sqlite:" + pathToDB;
			
			this.conexao = DriverManager.getConnection(url);
			System.out.println("conectado com database!!");
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Desconecta o banco de dados
	 * @return
	 */
	public boolean desconectar() {
		try {
			if(!this.conexao.isClosed()) {
				this.conexao.close();
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Cria um PreparedStatement
	 * @param pSQL String sql a ser executada
	 * @param RETURN_GENERATED_KEYS retorno de key gerada
	 * @return PreparedStatement
	 * @see java.sql.PreparedStatement
	 * @see java.sql.Connection#prepareStatement(String, int)
	 */
	public PreparedStatement criarPreparedStatement(String pSQL, int RETURN_GENERATED_KEYS) {
		try {
			return this.conexao.prepareStatement(pSQL, RETURN_GENERATED_KEYS);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Cria um PreparedStatement
	 * @param pSQL String sql a ser executada
	 * @return PreparedStatement
	 * @see java.sql.PreparedStatement
	 */
	public PreparedStatement criarPreparedStatement(String pSQL) {
		try {
			return this.conexao.prepareStatement(pSQL);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Crias as tabelas nescessarias caso não existam
	 * @return true se não houver erros. false do contrario
	 */
	public boolean createDatabase() {
		try {
			// Caminho para o banco de dados
			String pathToDB = System.getProperty("user.dir") + "\\db\\cmdb.db";
			String url = "jdbc:sqlite:" + pathToDB;

			// Cria a tabela de usuários caso não exista
			String ussersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
                + "	cpf VARCHAR(11) NOT NULL UNIQUE,\n"
				+ " name VARCHAR(255) NOT NULL,\n"
                + "	status VARCHAR(255) NOT NULL, \n"
				+ " password VARCHAR(50) NOT NULL\n"
                + ");";

			// Cria a tabela de configurações caso não exista
			String settingsTable = "CREATE TABLE IF NOT EXISTS settings (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
                + "	logged_user INT REFERENCES users (id),\n"
				+ "	entity VARCHAR(6) NOT NULL\n"
                + ");";
			
			String medicalRecord = "CREATE TABLE IF NOT EXISTS medical_record (\n"
					+ "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
		            + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
				    + "user_id INT REFERENCES users (id) NOT NULL, \n"
				    + "anamnesis VARCHAR (8000) NOT NULL,\n"
				    + "physical_exam VARCHAR (8000) NOT NULL, \n"
				    + "hypotheses VARCHAR (8000) NOT NULL, \n"
				    + "diagnoses VARCHAR (8000) NOT NULL, \n"
				    + "treatments VARCHAR (8000) NOT NULL\n"
				    + ");";
			
			String specialty = "CREATE TABLE IF NOT EXISTS specialty (\n"
					+ "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
		            + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
					+ " name VARCHAR(50) UNIQUE NOT NULL, \n"
		            + " description VARCHAR(50)\n"
					+ ");";
			
			String doctor = "CREATE TABLE IF NOT EXISTS doctor (\n"
					+ "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
		            + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
		            + " specialty_id  VARCHAR (50) REFERENCES specialty (id) NOT NULL, \n"
		            + " sub_specialty VARCHAR (50) REFERENCES specialty (id), \n"
		            + "	user_id INT REFERENCES users (id) NOT NULL\n"
		            + ");";
			
			String scheduling = "CREATE TABLE IF NOT EXISTS scheduling (\n"
					+ "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
		            + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
		            + "day INTEGER NOT NULL, \n"
		            + "hour INTEGER NOT NULL, \n"
		            + "doctor_id INT REFERENCES doctor (id) NOT NULL, \n"
		            + "specialty_id INT REFERENCES users (id) NOT NULL, \n"
		            + "status STRING NOT NULL, \n"
		            + "user_id INT REFERENCES users (id) \n"
		            + ");";
			
			String query = "CREATE TABLE IF NOT EXISTS query (\n"
					+ "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
		            + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
		            + "scheduling_id INT REFERENCES scheduling (id) NOT NULL, \n"
		            + "medicalRecords_id INT REFERENCES medical_record (id) NOT NULL, \n"
		            + "date INTEGER NOT NULL, \n"
		            + "user_id INT REFERENCES users (id) NOT NULL\n"
		            + ");";
			
			String day_doctor = "CREATE TABLE IF NOT EXISTS day_doctor (\n"
					+ "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
		            + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
		            + "start_service INTEGER NOT NULL, \n"
		            + "end_service INTEGER NOT NULL, \n"
		            + "doctor_id INT REFERENCES users (id) NOT NULL, \n"
		            + "duration_service INT NOT NULL\n"
		            + ");";
			
			String calendar_doctor = "CREATE TABLE IF NOT EXISTS calendar_doctor (\n"
					+ "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
		            + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
					+ "sunday_id    INT REFERENCES day_doctor (id), \n"
				    + "monday_id    INT REFERENCES day_doctor (id), \n"
				    + "tuesday_id   INT REFERENCES day_doctor (id), \n"
				    + "wednesday_id INT REFERENCES day_doctor (id), \n"
				    + "thursday_id  INT REFERENCES day_doctor (id), \n"
				    + "friday_id    INT REFERENCES day_doctor (id), \n"
				    + "saturday_id  INT REFERENCES day_doctor (id), \n"
				    + "doctor_id	INT REFERENCES doctor (id) NOT NULL\n"
				    + ");";
			
			// Cria banco de dados caso não exista e cria conexão
			Connection con = DriverManager.getConnection(url);
			Statement prep = con.createStatement();

			// Cria as tabelas
			prep.executeUpdate(ussersTable);
			prep.executeUpdate(settingsTable);
			prep.executeUpdate(medicalRecord);
			prep.executeUpdate(specialty);
			prep.executeUpdate(doctor);
			prep.executeUpdate(scheduling);
			prep.executeUpdate(query);
			prep.executeUpdate(day_doctor);
			prep.executeUpdate(calendar_doctor);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Verifica se há uma tabela no banco de dados
	 * @param tableName Nome da tabela
	 * @return true se houver a tabela no banco, do contrario, false
	 */
	public Boolean thereIsATable(String tableName) {
		conectar();

		String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name = ? ";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			preparedStatement.setString(1, tableName);
			result = preparedStatement.executeQuery();

			if (result.next()) {
				desconectar();
		        return true;
			}
		} catch(SQLException e) {
			System.out.println("Erro ao recuperar usuários");
			desconectar();
			return false;
		}
		
		desconectar();
		return false;
	}

}
