package com.clinical.management.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.clinical.management.model.users.OrderTypes;
import com.clinical.management.model.users.User;

public class UserDAO extends DatabaseConnection {
	
	/**
	 * Salva dado usuário no banco de dados
	 * @param userToBeSaved
	 * @return true se usuário foi salvo na base de dados, do contrario, false
	 */
	public Integer saveUser(User userToBeSaved) {
		Integer idDOUsuarioCriado = null;
		conectar();
		String sql = "INSERT INTO users ("
				+ "cpf,"
				+ "name,"
				+ "password,"
				+ "status)"
				+ "VALUES (?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setString(1, userToBeSaved.getCpf());
			preparedStatement.setString(2, userToBeSaved.getName());
			preparedStatement.setString(3, userToBeSaved.getPassword());
			preparedStatement.setString(4, userToBeSaved.getTypes().toString());
			preparedStatement.executeUpdate();
			ResultSet res = preparedStatement.getGeneratedKeys();

			if(res.next()) {
				idDOUsuarioCriado = res.getInt(1);
			}
		} catch (SQLException e) {
			desconectar();
			return idDOUsuarioCriado;
		}
		desconectar();
		return idDOUsuarioCriado;
	}

	/**
	 * Obtem todos os usuários salvos na base de dados
	 * @return Objeto List do tipo User com as informações dos usuários
	 * @see com.clinical.management.model.users.User
	 */
	public List<User> getUsers() {
		List<User> userList = new ArrayList<>();

		conectar();

		String sql = "SELECT id, name, cpf, status, password FROM users";

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
				String status = result.getString("status");

				OrderTypes ot = OrderTypes.PATIENT;

				if (status.equals(OrderTypes.ADMIN.toString())) {
					ot = OrderTypes.ADMIN;
				}

				if (status.equals(OrderTypes.DOCTOR.toString())) {
					ot = OrderTypes.DOCTOR;
				}

				if (status.equals(OrderTypes.RECEPTIONIST.toString())) {
					ot = OrderTypes.RECEPTIONIST;
				}

				User aux = new User(name, cpf, id, password);
				aux.setTypes(ot);
				userList.add(aux);
				System.out.println("User: " + name + ", cpf: " + cpf + ", id: " + id);
			}
		} catch(SQLException e) {
			System.out.println("Erro ao recuperar usuários");
		}
		
		desconectar();
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
		
		desconectar();
		return user;
	}

	public User getUserByID(int userID) {
		User user =  null;

		conectar();

		String sql = "SELECT id, name, cpf, password FROM users WHERE id = ? ";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = criarPreparedStatement(sql);
			preparedStatement.setInt(1, userID);
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
		
		desconectar();
		return user;
	}

	/**
	 * @return user
	 * retorna o usu�rio logado
	 */
	public User getLoggedUser() {

		User user =  null;
		Integer logged_user = null;

		conectar();

		String sql = "SELECT id, logged_user FROM settings WHERE entity = ?";
		String sqlUser = "SELECT id, name, cpf, password FROM users WHERE id = ?";

		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		// Obter id do usuario logado
		try {
			preparedStatement = criarPreparedStatement(sql);
			preparedStatement.setString(1, "system");
			result = preparedStatement.executeQuery();

			if (result.next()) {
				logged_user = result.getInt("logged_user");
			}

			if (logged_user == null) {
				desconectar();
				return null;
			}
		} catch(SQLException e) {
			System.out.println("Erro ao recuperar usuários");
		}

		// Obter usuario
		try {
			preparedStatement = criarPreparedStatement(sqlUser);
			preparedStatement.setInt(1, logged_user);
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
			desconectar();
			return user;
		}
		
		desconectar();
		return user;
	}

	/**
	 * @param userToBeSaved
	 * @return boolean
	 * salva o login do usu�rio
	 */
	public boolean saveLoggedUser(User userToBeSaved) {
		conectar();
		int result = -1;
		String sql = "UPDATE settings SET logged_user = ? WHERE entity = ?";
		String sql2 = "INSERT INTO settings (logged_user, entity) VALUES (?, ?);";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setInt(1, userToBeSaved.getID());
			preparedStatement.setString(2, "system");
			result = preparedStatement.executeUpdate();

			if (result == 0) {
				preparedStatement = criarPreparedStatement(sql2, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, userToBeSaved.getID());
				preparedStatement.setString(2, "system");
				result = preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}

	/**
	 * @return boolean
	 * remove o login do usu�rio
	 */
	public boolean removeLoggedUser() {
		conectar();
		String sql = "UPDATE settings SET logged_user = ? WHERE id = 1;";
		
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setNull(1, Types.INTEGER);;
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			desconectar();
			return false;
		}
		desconectar();
		return true;
	}
}
