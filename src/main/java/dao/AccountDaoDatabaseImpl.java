package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.AccountPojo;
import model.TransactionPojo;
import model.UserPojo;

public class AccountDaoDatabaseImpl implements AccountDao {

	public boolean addAccount(AccountPojo accountPojo) {

		Connection conn;
		boolean success = false;
		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();
			
			//query checks if there are any existing accounts of the type that the user would now like to create: users can only have one of each account type
			//String query = "SELECT * FROM accounts INNER JOIN accountUsers ON accountUsers.account_number = accounts.account_number WHERE accountUsers.user_id=(SELECT user_id FROM sessions WHERE session_number = (SELECT MAX (session_number) FROM sessions)) AND accounts.account_type='"+accountPojo.getAccountType()+"'";
			String query = "SELECT * FROM accounts INNER JOIN accountUsers ON accountUsers.account_number = accounts.account_number WHERE accountUsers.user_id=(SELECT user_id FROM sessions WHERE session_number = (SELECT MAX (session_number) FROM sessions))  AND accounts.account_type='"+accountPojo.getAccountType()+"'";
			ResultSet resultSet = stmt.executeQuery(query);
			boolean res = resultSet.next();
			if(!res) {
				String query2 = "INSERT INTO accounts(account_type, account_balance, access_code) VALUES('"+ accountPojo.getAccountType() + "', 0, '" + accountPojo.getAccessCode() + "')";
				int rowsAffected = stmt.executeUpdate(query2);
				if(rowsAffected == 1) {
					success = true;
					String query3 = "INSERT INTO accountUsers(account_number, user_id) VALUES((SELECT account_number FROM accounts WHERE account_number = (SELECT MAX (account_number) FROM accounts)),(SELECT user_id FROM sessions WHERE session_number = (SELECT MAX (session_number) FROM sessions)))";
					int rowsAffected2 = stmt.executeUpdate(query3);
				}
				
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;
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
