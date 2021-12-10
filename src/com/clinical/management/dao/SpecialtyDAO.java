package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.specialty.Specialty;

/**
 * Classe respons�vel pela busca no banco de dados especificamente na tabela especialidades
 *
 */
public class SpecialtyDAO extends DatabaseConnection{

    /**
     * 
     * @return specialtyList
     * retorna uma lista com os dados buscados na tabela especialidades
     */
    public List<Specialty> getSpecialtys() {
		List<Specialty> specialtyList = new ArrayList<>();

		conectar();

		String sql = "SELECT id, name, description FROM specialty ORDER BY id";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				System.out.println("Specialty: " + name);
				Specialty aux = new Specialty(name, description, id);
				specialtyList.add(aux);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao recuperar especialidades");
		}
		
		desconectar();
		return specialtyList;
	}

	/**
	 * Obtem uma especialidade dado seu id
	 * @param idDaEspecialidade int com o id da especialidade
	 * @return Objeto Specialty com as informações da especialidade
	 * @see com.clinical.management.model.specialty.Specialty
	 */
    public Specialty getSpecialtyByID(int idDaEspecialidade) {
		Specialty specialty = null;

		conectar();

		String sql = "SELECT id, name, description FROM specialty WHERE id = ?";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			preparedStatement.setInt(1, idDaEspecialidade);
			result = preparedStatement.executeQuery();

			if (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				System.out.println("Specialty: " + name);
				specialty = new Specialty(name, description, id);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao recuperar especialidades");
			desconectar();
			return specialty;
		}
		
		desconectar();
		return specialty;
	}


    /**
     * 
     * @param specialtyToBeSaved
     * @return boolean
     * se salvo com sucesso no banco de dados retornar true, do contr�rio retorna false
     */
    public boolean saveSpecialty(Specialty specialtyToBeSaved) {
		conectar();
		String sql = "INSERT INTO specialty ("
				+ "name,"
				+ "description)"
				+ "VALUES (?, ?)";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setString(1, specialtyToBeSaved.getName());
			preparedStatement.setString(2, specialtyToBeSaved.getDescription());
			preparedStatement.executeUpdate();
			ResultSet res = preparedStatement.getGeneratedKeys();

			if (res.next()) {
				int id = res.getInt(1);
				System.out.println("ID gerado: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
    
    /**
     * @param specialtyToBeRemoved
     * @return boolean
     * m�todo respons�vel por remover especialidade do banco de dados
     */
    public Boolean removeSpecialty(Specialty specialtyToBeRemoved) {
    	conectar();
		String sql = "DELETE FROM specialty WHERE id = ?";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, specialtyToBeRemoved.getID());
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
