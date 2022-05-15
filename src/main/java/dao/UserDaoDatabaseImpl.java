package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.SystemException;
import model.AccountPojo;
import model.AccountUsersPojo;
import model.SessionPojo;
import model.UserPojo;
import service.AccountUsersService;
import service.SessionService;
import service.SessionServiceImpl;

public class UserDaoDatabaseImpl implements UserDao {

	public UserPojo addUser(UserPojo userPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO users(user_name, user_password, user_firstName, user_lastName, user_pin) VALUES('"
					+ userPojo.getUserName() + "', '" + userPojo.getUserPassword() + "', '"
					+ userPojo.getUserFirstName() + "', '" + userPojo.getUserLastName() + "', '" + userPojo.getUserPin()
					+ "')";

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userPojo;
	}

	public List<UserPojo> getAllUsers() {

		List<UserPojo> allUsers = new ArrayList<UserPojo>();

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM users";

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				UserPojo userPojo = new UserPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));

				allUsers.add(userPojo);
			}
			/*
			 * if (counter == 0) { throw new EmptyListException(); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allUsers;
	}

	public UserPojo getOneUser(int userId) {
		Connection conn = null;
		UserPojo userPojo = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM users WHERE user_id=" + userId;
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				userPojo = new UserPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPojo;

	}

	public UserPojo logIn(UserPojo userPojo) {
		Connection conn = null;
		SessionService sessionService = new SessionServiceImpl();
		SessionPojo sessionPojo = new SessionPojo();
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM users WHERE user_name='" + userPojo.getUserName() + "' AND user_password='"
					+ userPojo.getUserPassword() + "'";
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				userPojo = new UserPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
				sessionPojo.setUserId(userPojo.getUserId());
				sessionPojo.setLoginTime(new Date().toString());
				sessionService.addSession(sessionPojo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPojo;
	}

	public void logOut() {
		DBUtil.closeConnection();
	}

	public UserPojo changePassword(UserPojo userPojo) {
		Connection conn;
		SessionService sessionService = new SessionServiceImpl();
		SessionPojo sessionPojo = new SessionPojo();
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "UPDATE users SET user_password='" + userPojo.getUserPassword()
					+ "' WHERE user_id=(SELECT user_id FROM sessions WHERE session_number=(SELECT MAX (session_number) FROM sessions)) AND user_pin="
					+ userPojo.getUserPin() + "";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPojo;
	}

	public UserPojo recoverPassword(UserPojo userPojo, AccountPojo accountPojo) {
		Connection conn;
		SessionService sessionService = new SessionServiceImpl();
		SessionPojo sessionPojo = new SessionPojo();
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT user_password FROM users WHERE user_id=(SELECT accountUsers.user_id FROM accountUsers INNER JOIN accounts ON accounts.account_number = accountUsers.account_number WHERE accounts.access_code="
					+ accountPojo.getAccessCode() + " AND accounts.account_number=" + accountPojo.getAccountNumber()
					+ ") AND user_pin =" + userPojo.getUserPin() + "";
			// String query = "SELECT user_password FROM users WHERE user_id=(SELECT user_id
			// FROM sessions WHERE session_number=(SELECT MAX (session_number) FROM
			// sessions)) AND user_pin='"+userPojo.getUserPin()+"'";
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				userPojo.setUserPassword(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPojo;
	}

	
	public boolean removeUserAccount(UserPojo userPojo) throws SQLException {
		Connection conn = null;
		boolean success = false;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM users WHERE user_pin=" + userPojo.getUserPin()
					+ " AND user_id=(SELECT user_id FROM sessions WHERE session_number=(SELECT MAX (session_number) FROM sessions))";
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				String query2 = "INSERT INTO inactive_users(user_id, user_name, user_password, user_type, user_firstName, user_lastName, user_pin) VALUES("+ resultSet.getInt(1) + ", '" + resultSet.getString(2) + "', '" + resultSet.getString(3) +"', '"+ resultSet.getString(4) + "', '" + resultSet.getString(5) + "', '" + resultSet.getString(6) + "', '"+ resultSet.getInt(7) + "')";
				String query3 = "DELETE FROM users WHERE user_id=" + resultSet.getInt(1) + "";
				int rowsAffected2 = stmt.executeUpdate(query2);
				int rowsAffected3 = stmt.executeUpdate(query3);
				if (rowsAffected2 == 1 && rowsAffected3 == 1) {
					success = true;
				}
			}
		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException();
		}

		return success;
	}
	
}
