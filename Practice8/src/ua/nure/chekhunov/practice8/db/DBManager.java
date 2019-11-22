package ua.nure.chekhunov.practice8.db;

import ua.nure.chekhunov.practice8.db.entity.Team;
import ua.nure.chekhunov.practice8.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chekhunov
 *
 */
public class DBManager {
	private static DBManager instance;
	private static Connection connection;
	private static final Lock CONNECTION_LOCK = new ReentrantLock();

	private static final String FIND_ALL_USERS = "SELECT * FROM users";
	private static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
	private static final String INSERT_USER = "INSERT INTO users VALUES (DEFAULT ,?)";
	private static final String INSERT_TEAM = "INSERT INTO teams VALUES (DEFAULT ,?)";
	private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String FIND_TEAM_BY_LOGIN = "SELECT * FROM teams WHERE name=?";
	private static final String FIND_TEAMS_BY_USER_ID = "SELECT t.id, t.name FROM users_teams ut\n"
			+ "JOIN users u ON ut.user_id = u.id\n" + "JOIN teams t ON ut.team_id = t.id\n" + "WHERE u.id = ?";

	private static final String INSERT_USER_TO_TEAM = "INSERT INTO users_teams VALUES (?, ?)";
	private static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE name=?";
    private static final String SQL_UPDATE_TEAM = "UPDATE teams SET name=? WHERE id=?";
	
	public static Connection getConnection() {
	ResourceBundle resource = ResourceBundle.getBundle("app");
	try {
		return
				DriverManager.getConnection(resource.getString("connection.url"));	
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return null;
	}

	private DBManager() {
		connection = getConnection();
	}

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public boolean insertUser(User user) {
		PreparedStatement ps = null;
		ResultSet id = null;
		try {
			CONNECTION_LOCK.lock();
			ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getLogin());
			if (ps.executeUpdate() != 1) {
				return false;
			}
			id = ps.getGeneratedKeys();
			if (id.next()) {
				int idField = id.getInt(1);
				user.setId(idField);
			}
		} catch (Exception e) {
			System.out.println("insert user:" + e.getMessage());
			return false;
		} finally {
			close(id);
			close(ps);
			CONNECTION_LOCK.unlock();
		}
		return true;
	}

	public boolean insertTeam(Team team) {
		PreparedStatement ps = null;
		ResultSet id = null;
		try {
			CONNECTION_LOCK.lock();
			ps = connection.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, team.getName());
			if (ps.executeUpdate() != 1) {
				return false;
			}
			id = ps.getGeneratedKeys();
			if (id.next()) {
				int idField = id.getInt(1);
				team.setId(idField);
			}
		} catch (Exception e) {
			System.out.println("insert team:" + e.getMessage());
			return false;
		} finally {
			close(id);
			close(ps);
			CONNECTION_LOCK.unlock();
		}
		return true;
	}

	public List<User> findAllUsers() {
		Statement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		try {
			CONNECTION_LOCK.lock();
			ps = connection.createStatement();
			rs = ps.executeQuery(FIND_ALL_USERS);
			while (rs.next()) {
				User user = new User();
				users.add(user);
				user.setId(rs.getInt(1));
				user.setLogin(rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println("find all users:" + e.getMessage());
			return Collections.emptyList();
		} finally {
			close(rs);
			close(ps);
			CONNECTION_LOCK.unlock();
		}
		return users;
	}

	public List<Team> findAllTeams() {
		Statement ps = null;
		ResultSet rs = null;
		List<Team> teams = new ArrayList<>();
		try {
			CONNECTION_LOCK.lock();
			ps = connection.createStatement();
			rs = ps.executeQuery(FIND_ALL_TEAMS);
			while (rs.next()) {
				Team team = new Team();
				teams.add(team);
				team.setId(rs.getInt(1));
				team.setName(rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println("find all users:" + e.getMessage());
			return Collections.emptyList();
		} finally {
			close(rs);
			close(ps);
			CONNECTION_LOCK.unlock();
		}
		return teams;
	}

	public User getUser(String login) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			CONNECTION_LOCK.lock();
			ps = connection.prepareStatement(FIND_USER_BY_LOGIN);
			ps.setString(1, login);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
			}
		} catch (SQLException ex) {
			System.out.println("get User");
		} finally {
			close(rs);
			close(ps);
			CONNECTION_LOCK.unlock();
		}
		return user;
	}

	public Team getTeam(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Team team = null;
		try {
			CONNECTION_LOCK.lock();
			ps = connection.prepareStatement(FIND_TEAM_BY_LOGIN);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				team = new Team();
				team.setId(rs.getInt("id"));
				team.setName(rs.getString("name"));
			}
		} catch (SQLException ex) {
			System.out.println("get Team");
		} finally {
			close(rs);
			close(ps);
			CONNECTION_LOCK.unlock();
		}
		return team;
	}

	public List<Team> getUserTeams(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Team> teams = new ArrayList<>();
		try {
			CONNECTION_LOCK.lock();
			ps = connection.prepareStatement(FIND_TEAMS_BY_USER_ID);
			ps.setInt(1, user.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Team team = new Team();
				teams.add(team);
				team.setId(rs.getInt(1));
				team.setName(rs.getString(2));
			}
		} catch (Exception e) {
			System.out.println("get teams:" + e.getMessage());
			return Collections.emptyList();
		} finally {
			close(rs);
			close(ps);
			CONNECTION_LOCK.unlock();
		}
		return teams;
	}

	public boolean setTeamsForUser(User user, Team... teams) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			CONNECTION_LOCK.lock();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(INSERT_USER_TO_TEAM);
			for (Team t : teams) {
				ps.setInt(1, user.getId());
				ps.setInt(2, t.getId());
				ps.addBatch();
			}
			int[] usersTeams = ps.executeBatch();
			for (int i : usersTeams) {
				if (i != 1) {
					return false;
				}
			}
			connection.commit();
			return true;
		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("set teams");
		} finally {
			close(rs);
			close(ps);
			setAutocommit();
			CONNECTION_LOCK.unlock();
		}
		return false;
	}

	 public boolean deleteTeam(Team team) {
	        PreparedStatement ps = null;
	        ResultSet id = null;
	        try {
	            CONNECTION_LOCK.lock();
	            ps = connection.prepareStatement(SQL_DELETE_TEAM);
	            ps.setString(1, team.getName());
	            if (ps.executeUpdate() != 1) {
	                return false;
	            }
	        } catch (Exception e) {
	            System.out.println("Can't delete user:" + e.getMessage());
	            return false;
	        } finally {
	            close(id);
	            close(ps);
	            CONNECTION_LOCK.unlock();
	        }
	        return true;
	    }

	    public boolean updateTeam(Team team) {
	        PreparedStatement ps = null;
	        ResultSet id = null;
	        try {
	            CONNECTION_LOCK.lock();
	            ps = connection.prepareStatement(SQL_UPDATE_TEAM);
	            ps.setString(1, team.getName());
	            ps.setInt(2, team.getId());
	            if (ps.executeUpdate() != 1) {
	                return false;
	            }
	        } catch (Exception e) {
	            System.out.println("Can't update group:" + e.getMessage());
	            return false;
	        } finally {
	            close(id);
	            close(ps);
	            CONNECTION_LOCK.unlock();
	        }
	        return true;
	    }
	
	private static void setAutocommit() {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
