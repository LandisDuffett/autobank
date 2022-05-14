package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.SessionPojo;

public class SessionDaoDatabaseImpl implements SessionDao {

	public SessionPojo addSession(SessionPojo sessionPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO sessions(user_id, login_time, logout_time) VALUES('"
					+sessionPojo.getUserId() + "', '"+ sessionPojo.getLoginTime()+"', '"+sessionPojo.getLogoutTime()+"')";

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sessionPojo;
	}
	
	public List<SessionPojo> getAllSessions() {

		List<SessionPojo> allSessions = new ArrayList<SessionPojo>();

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM sessions";

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				SessionPojo sessionPojo = new SessionPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
				allSessions.add(sessionPojo);
			}
			/*
			 * if (counter == 0) { throw new EmptyListException(); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allSessions;
	}
	
	public List<SessionPojo> getSessionsForOneUser(int userId) {

		List<SessionPojo> theSessionsForOneUser = new ArrayList<SessionPojo>();

		Connection conn = null;

		// TransactionPojo transactionPojo = null;
		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM sessions WHERE user_id=" + userId;

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				SessionPojo sessionPojo = new SessionPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));

				theSessionsForOneUser.add(sessionPojo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theSessionsForOneUser;

	}
}
