package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.users.User;

public class UserDAO extends DatabaseConnection {
	
	/**
	 * Salva dado usuário no banco de dados
	 * @param userToBeSaved
	 * @return true se usuário foi salvo na base de dados, do contrario, false
	 */
	public boolean saveUser(User userToBeSaved) {
		conectar();
		String sql = "INSERT INTO users ("
				+ "cpf,"
				+ "name,"
				+ "password)"
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setString(1, userToBeSaved.getCpf());
			preparedStatement.setString(2, userToBeSaved.getName());
			preparedStatement.setString(3, userToBeSaved.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		desconectar();
		return true;
	}

	/**
	 * Obtem todos os usuários salvos na base de dados
	 * @return Objeto List do tipo User com as informações dos usuários
	 * @see com.clinical.management.model.users.User
	 */
	public List<User> getUsers() {
		List<User> userList = new ArrayList<>();

		conectar();

		String sql = "SELECT id, name, cpf, password FROM users";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				String name = result.getString("name");
				int id = result.getInt("id");
				String cpf = result.getString("cpf");
				String password = result.getString("password");
				User aux = new User(name, cpf, id, password);
				userList.add(aux);
				System.out.println("User: " + name + ", cpf: " + cpf + ", id: " + id);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao recuperar usuários");
		}
		
		return userList;
	}

	/**
	 * Obtem usuário dados CPF e Senha
	 * @return User
	 * @see com.clinical.management.model.users.User
	 */
	public User getUserByNameAndPassword(String uName, String uPassword) {

		User user =  null;

		conectar();

		String sql = "SELECT id, name, cpf, password FROM users WHERE name = ? and password = ?";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			preparedStatement.setString(1, uName);
			preparedStatement.setString(2, uPassword);
			result = preparedStatement.executeQuery();

			if (result.next()) {
				String name = result.getString("name");
				int id = result.getInt("id");
				String cpf = result.getString("cpf");
				String password = result.getString("password");
				user = new User(name, cpf, id, password);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao recuperar usuários");
		}
		
		return user;
	}
}
