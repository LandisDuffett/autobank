package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.AccountPojo;

public class AccountDaoDatabaseImpl implements AccountDao {

	public AccountPojo addAccount(AccountPojo accountPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO accounts(account_type, account_balance, access_code) VALUES('"
					+ accountPojo.getAccountType() + "', 0, '" + accountPojo.getAccessCode() + "')";

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountPojo;
	}
}
