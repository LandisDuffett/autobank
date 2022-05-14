package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountUsersPojo;
	}
}
