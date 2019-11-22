package ua.nure.chekhunov.practice10.db;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class Connector {

	public static Connection getConnection() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/practice10");
			return ds.getConnection();
		} catch (NamingException | SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
