package ua.nure.chekhunov.practice10.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.chekhunov.practice10.db.entities.Role;
import ua.nure.chekhunov.practice10.exception.LoginException;

public class RoleDAO extends AbstractDAO {

	private static final String SELECT_ROLE_BY_ID = "select * from roles where id = ?;";

	public RoleDAO(Connection connection) {
		super(connection);
	}

	public Role findRoleById(int id) {
		PreparedStatement statement = null;
		try {
			Role role = new Role();
			statement = connection.prepareStatement(SELECT_ROLE_BY_ID);
			statement.setInt(1, id);
			ResultSet resSet = statement.executeQuery();
			if (resSet.next()) {
				role.setId(resSet.getInt("id"));
				role.setName(resSet.getString("name"));
				return role;
			} else {
				throw new SQLException();
			}
		} catch (SQLException e) {
			throw new LoginException("Role has not been finded!", e);
		} finally {
			close(statement);
		}
	}

}
