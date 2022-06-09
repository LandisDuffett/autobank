package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import exception.NoAccountException;
import exception.SystemException;
import model.AccountPojo;
import model.SessionPojo;
import model.UserPojo;
import service.SessionService;
import service.SessionServiceImpl;

public class UserDaoDatabaseImpl implements UserDao {

	public UserPojo addUser(UserPojo userPojo) throws SystemException, SQLException {

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
			
			throw new SQLException();
			
		} finally {
			
			try {
				
				logIn(userPojo);
				
			} catch (Exception e) {
				
				System.out.println("could not add");
			}
		}

		return userPojo;
	}

	

	public UserPojo logIn(UserPojo userPojo) throws NoAccountException, SystemException {
		
		Connection conn = null;
		
		SessionService sessionService = new SessionServiceImpl();
		
		SessionPojo sessionPojo = new SessionPojo();
		
		try {
			
			conn = DBUtil.makeConnection();
			
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM users WHERE user_name='" + userPojo.getUserName() + "' AND user_password='"
					
					+ userPojo.getUserPassword() + "'";
			
			ResultSet resultSet = stmt.executeQuery(query);
						
			if (resultSet.next() && resultSet.getInt(1) > 0) {
				
				userPojo = new UserPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						
						resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
				
				sessionPojo.setUserId(userPojo.getUserId());
				
				sessionPojo.setLoginTime(new Date().toString());
				
				sessionService.addSession(sessionPojo);

			} else {
				throw new SystemException();
			}

		} catch (SQLException e) {

			throw new NullPointerException();
		}
		return userPojo;
	}

	public UserPojo logOut(UserPojo userPojo) {
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.makeConnection();
			
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM sessions WHERE session_number = (SELECT MAX(session_number) from sessions)";
			
			ResultSet resultSet = stmt.executeQuery(query);

			resultSet.next();
		
			String query2 = "UPDATE sessions SET logout_time='" + new Date().toString() + "' WHERE user_id="
					
					+ resultSet.getInt(2) + " AND session_number='" + resultSet.getInt(1) + "'";
			
			stmt.executeUpdate(query2);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		DBUtil.closeConnection();
		
		return userPojo;
	}
	

	public UserPojo changePassword(UserPojo userPojo) {
		
		Connection conn;
		
		SessionService sessionService = new SessionServiceImpl();
		
		SessionPojo sessionPojo = new SessionPojo();
		
		try {
			
			conn = DBUtil.makeConnection();
			
			Statement stmt = conn.createStatement();
			
			String query = "SELECT user_pin FROM users WHERE user_id=(SELECT user_id FROM sessions WHERE session_number =(SELECT MAX (session_number) FROM sessions))";
		
			ResultSet resultSet = stmt.executeQuery(query);
			
			if (resultSet.next()) {
			
				String query2 = "UPDATE users SET user_password='" + userPojo.getUserPassword()
				
						+ "' WHERE user_id=(SELECT user_id FROM sessions WHERE session_number=(SELECT MAX (session_number) FROM sessions)) AND user_pin="
						
						+ resultSet.getInt(1) + "";
			
				stmt.executeUpdate(query2);
			}
			
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
			
			/*
			String query = "SELECT accountUsers.user_id, accounts.access_code FROM accountUsers INNER JOIN accounts ON accountUsers.account_number = accounts.account_number WHERE accounts.account_number ="
					
					+ accountPojo.getAccountNumber() + " AND accounts.access_code =" + accountPojo.getAccessCode()
					
					+ " AND user_id = (SELECT user_id FROM users WHERE user_name = 'william' AND user_pin ="
					
					+ userPojo.getUserPin() + ")";
					*/
			
			String query = "SELECT user_password FROM users INNER JOIN accountUsers ON accountUsers.user_id = users.user_id INNER JOIN accounts ON accountUsers.account_number = accounts.account_number WHERE accounts.account_number = " + accountPojo.getAccountNumber() + "AND accounts.access_code = " + accountPojo.getAccessCode() + " AND users.user_id = (SELECT user_id FROM users WHERE user_name ='" + userPojo.getUserName() + "' AND user_pin =" + userPojo.getUserPin() + ")";
			
			ResultSet resultSet = stmt.executeQuery(query);
						
			if(resultSet.next()) {
				
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
			
			//logOut(userPojo);
			
			String query = "SELECT * FROM users WHERE user_pin=" + userPojo.getUserPin()
			
					+ " AND user_id=(SELECT user_id FROM sessions WHERE session_number=(SELECT MAX (session_number) FROM sessions))";
			
			ResultSet resultSet = stmt.executeQuery(query);
			
			if (resultSet.next()) {
				
				conn.setAutoCommit(false);
				
				String query2 = "INSERT INTO inactive_users(user_id, user_name, user_password, user_type, user_firstname, user_lastname, user_pin) VALUES("
						
						+ resultSet.getInt(1) + ", '" + resultSet.getString(2) + "', '" + resultSet.getString(3)
						
						+ "', '" + resultSet.getString(4) + "', '" + resultSet.getString(5) + "', '"
						
						+ resultSet.getString(6) + "', " + resultSet.getInt(7) + ")";
				
				String query3 = "DELETE FROM users WHERE user_id=" + resultSet.getInt(1) + "";
				
				int rowsAffected2 = stmt.executeUpdate(query2);
				
				//System.out.println("inserted into inactive users");
				
				int rowsAffected3 = stmt.executeUpdate(query3);
				
				conn.commit();
				
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
