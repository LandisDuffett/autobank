package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.AccountPojo;
import model.UserPojo;

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
	
	public List<AccountPojo> getAllAccounts() {

		List<AccountPojo> allAccounts = new ArrayList<AccountPojo>();

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM accounts";

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				AccountPojo accountPojo = new AccountPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
						resultSet.getInt(4));
				allAccounts.add(accountPojo);
			}
			/*
			 * if (counter == 0) { throw new EmptyListException(); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allAccounts;
	}
	
	public AccountPojo getOneAccount(int accountNumber) {
		Connection conn = null;
		AccountPojo accountPojo = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM accounts WHERE account_number=" + accountNumber;
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				accountPojo = new AccountPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
						resultSet.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountPojo;

	}
}
