package com.clinical.management.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
			try {
				// Cria diretório caso não exista
				Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/db/"));
			} catch (IOException e) {
				//
			}

			// Caminho para o banco de dados
			String pathToDB = System.getProperty("user.dir") + "\\db\\cmdb.db";
			String url = "jdbc:sqlite:" + pathToDB;

			// Cria a tabela de usuários caso não exista
			String ussersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
                + "	cpf VARCHAR(11) NOT NULL UNIQUE,\n"
				+ " name VARCHAR(255) NOT NULL,\n"
				+ " password VARCHAR(50) NOT NULL\n"
                + ");";
			
			// Cria banco de dados caso não exista e cria conexão
			Connection con = DriverManager.getConnection(url);
			Statement prep = con.createStatement();

			// Cria as tabelas
			prep.executeUpdate(ussersTable);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

}