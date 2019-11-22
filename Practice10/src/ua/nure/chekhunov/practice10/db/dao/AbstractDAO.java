package ua.nure.chekhunov.practice10.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {

	protected Connection connection;

	public AbstractDAO(Connection connection) {
		this.connection = connection;
	}

	public void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
