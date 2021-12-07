package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.specialty.Specialty;

public class SpecialtyDAO extends DatabaseConnection{

    /**
     * 
     * @return
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
     * 
     * @param specialtyToBeSaved
     * @return
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
			preparedStatement.setString(2, "Descrição");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
    
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
