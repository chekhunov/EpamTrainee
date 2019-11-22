package ua.nure.chekhunov.practice10.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.chekhunov.practice10.db.entities.User;
import ua.nure.chekhunov.practice10.exception.LoginException;


public class UserDAO extends AbstractDAO {

	private static final String SELECT_USER_BY_LOGIN = "select * from users where login = ? and password = ?;";

	private static final String UPDATE_USER_NAME = "update users set name = ? where login = ?;";

	public UserDAO(Connection connection) {
		super(connection);
	}

	public User findUserByLogin(String login, String password) {
		PreparedStatement statement = null;
		try {
			User user = new User();
			statement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet resSet = statement.executeQuery();
			if (resSet.next()) {
				user.setId(resSet.getInt("id"));
				user.setLogin(resSet.getString("login"));
				user.setPassword(resSet.getString("password"));
				user.setName(resSet.getString("name"));
				user.setRoleId(resSet.getInt("role_id"));
				return user;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			throw new LoginException("User has not been finded!", e);
		} finally {
			close(statement);
		}
	}

	public boolean updateUserName(String login, String newName) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_USER_NAME);
			statement.setString(1, newName);
			statement.setString(2, login);
			return statement.execute();
		} catch (SQLException e) {
			throw new LoginException("user has not been finded!", e);
		} finally {
			close(statement);
		}
	}

}
