package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.AccountUsersPojo;

public class AccountUsersDaoDatabaseImpl implements AccountUsersDao {
	
	public AccountUsersPojo addAccountUsers(AccountUsersPojo accountUsersPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO accountUsers(account_number, user_id) VALUES('"
					
					+ accountUsersPojo.getAccountNumber() + "', '" + accountUsersPojo.getUserId() + "')";

			int rowsAffected = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return accountUsersPojo;
	}
	
	
	public List<AccountUsersPojo> getAllAccountUsers() {

		List<AccountUsersPojo> allAccountUsers = new ArrayList<AccountUsersPojo>();

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM accountUsers";

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				AccountUsersPojo accountUsersPojo = new AccountUsersPojo(resultSet.getInt(1), resultSet.getInt(2));

				allAccountUsers.add(accountUsersPojo);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return allAccountUsers;
	}

	
	public List<AccountUsersPojo> getTheAccountUsersForOneId(int userId) {

		List<AccountUsersPojo> theAccountUsersForOneId = new ArrayList<AccountUsersPojo>();

		Connection conn = null;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM accountUsers WHERE user_id=" + userId;

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				AccountUsersPojo accountUsersPojo = new AccountUsersPojo(resultSet.getInt(1), resultSet.getInt(2));

				theAccountUsersForOneId.add(accountUsersPojo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theAccountUsersForOneId;

	}
	
	
	public List<AccountUsersPojo> getTheAccountUsersForOneAccNo(int accNo) {

		List<AccountUsersPojo> theAccountUsersForOneAccNo = new ArrayList<AccountUsersPojo>();

		Connection conn = null;

		try {

			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM accountUsers WHERE account_number=" + accNo;

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				AccountUsersPojo accountUsersPojo = new AccountUsersPojo(resultSet.getInt(1), resultSet.getInt(2));

				theAccountUsersForOneAccNo.add(accountUsersPojo);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return theAccountUsersForOneAccNo;

	}
}
