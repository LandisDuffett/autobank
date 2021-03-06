package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.BalanceNotEmptyException;
import exception.SystemException;
import model.AccountPojo;
import model.UserPojo;

public class AccountDaoDatabaseImpl implements AccountDao {

	public boolean addAccount(AccountPojo accountPojo) {

		Connection conn;
		
		boolean success = false;
		
		try {
			
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM accounts INNER JOIN accountUsers ON accountUsers.account_number = accounts.account_number WHERE accountUsers.user_id=(SELECT user_id FROM sessions WHERE session_number = (SELECT MAX (session_number) FROM sessions))  AND accounts.account_type='"
					
					+ accountPojo.getAccountType() + "'";
			
			ResultSet resultSet = stmt.executeQuery(query);
			
			boolean res = resultSet.next();
			
			if (!res) {
				
				String query2 = "INSERT INTO accounts(account_type, account_balance, access_code) VALUES('"
						
						+ accountPojo.getAccountType() + "', 0, '" + accountPojo.getAccessCode() + "')";
				
				int rowsAffected = stmt.executeUpdate(query2);
				
				if (rowsAffected == 1) {
					
					success = true;
					
					String query3 = "INSERT INTO accountUsers(account_number, user_id) VALUES((SELECT account_number FROM accounts WHERE account_number = (SELECT MAX (account_number) FROM accounts)),(SELECT user_id FROM sessions WHERE session_number = (SELECT MAX (session_number) FROM sessions)))";
					
					int rowsAffected2 = stmt.executeUpdate(query3);
					
				}

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return success;
	}
	
	
	public List<AccountPojo> getAllAccounts() {

		List<AccountPojo> allAccounts = new ArrayList<AccountPojo>();

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			UserPojo newUserPojo = new UserPojo();

			Statement stmt = conn.createStatement();

			String query = "SELECT accounts.account_number, accounts.account_type, ROUND(CAST(accounts.account_balance AS numeric),2), accounts.access_code "
					+ "FROM accounts INNER JOIN accountUsers ON accountUsers.account_number = accounts.account_number "
					+ "INNER JOIN sessions ON sessions.user_id = accountUsers.user_id WHERE session_number = (SELECT MAX(session_number) FROM sessions)";

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				AccountPojo accountPojo = new AccountPojo(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getDouble(3),

						resultSet.getInt(4));

				allAccounts.add(accountPojo);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return allAccounts;
	}
	
	
	public boolean closeBankAccount(AccountPojo accountPojo, UserPojo userPojo)
			throws SystemException, BalanceNotEmptyException, SQLException {
		
		Connection conn = null;
		
		boolean success = false;
		
		try {
			
			conn = DBUtil.makeConnection();
			
			Statement stmt = conn.createStatement();
			
			String query = "SELECT users.user_id, accounts.account_number, accounts.account_type, accounts.account_balance FROM accounts "
					
					+ "INNER JOIN accountUsers on accounts.account_number = accountUsers.account_number INNER JOIN users ON users.user_id = "
					
					+ "accountUsers.user_id AND users.user_pin ="
					
					+ userPojo.getUserPin()
					
					+ "WHERE accountUsers.user_id=(SELECT user_id FROM sessions WHERE session_number=(SELECT MAX (session_number) FROM sessions)) "
					
					+ "AND accounts.account_number="
					
					+ accountPojo.getAccountNumber() + "";
			
			ResultSet resultSet = stmt.executeQuery(query);
			
			if (resultSet.next()) {
								
				if (resultSet.getDouble(4) != 0) {
					
					throw new BalanceNotEmptyException();
					
				}
				
				String query2 = "INSERT INTO inactive_accounts(account_number, account_type) VALUES("
						
						+ resultSet.getInt(2) + ", '" + resultSet.getString(3) + "')";
				
				String query3 = "INSERT INTO inactive_accountusers(account_number, user_id) VALUES("
						
						+ resultSet.getInt(2) + ", " + resultSet.getInt(1) + ")";
				
				String query4 = "DELETE FROM accounts WHERE account_number=" + resultSet.getInt(2) + "";
				
				int rowsAffected2 = stmt.executeUpdate(query2);
				
				int rowsAffected3 = stmt.executeUpdate(query3);
				
				int rowsAffected4 = stmt.executeUpdate(query4);
							
				if (rowsAffected2 == 1 && rowsAffected3 == 1 && rowsAffected4 == 1) {
					
					success = true;

				}
				
			}
			
		} catch (SQLException e) {
			
			throw new SQLException();
			
		}

		return success;
	}
	
	//allows a user to join an existing bank account as a joint account holder; user needs own login credentials and PIN 
	//as well as the access code given to the original account owner at time of account creation
	public AccountPojo linkToAccount(AccountPojo accountPojo, UserPojo userPojo) throws SQLException, SystemException {

		Connection conn = null;
		
		boolean success = false;
		
		AccountPojo returnedAccountPojo = new AccountPojo();
		
		try {
			
			conn = DBUtil.makeConnection();
			
			Statement stmt = conn.createStatement();
			
			String query = "INSERT INTO accountUsers(account_number, user_id) VALUES((SELECT account_number FROM accounts WHERE access_code ="
					
					+ accountPojo.getAccessCode()
					
					+ "), (SELECT user_id FROM users WHERE user_pin = (SELECT user_pin FROM users WHERE user_id = (SELECT user_id FROM sessions WHERE session_number = (SELECT MAX (session_number) FROM sessions))) AND user_pin="
					
					+ userPojo.getUserPin() + "))";
			
			int rowsAffected = stmt.executeUpdate(query);
			
			if (rowsAffected == 1) {
				
				String query2 = "SELECT * FROM accounts WHERE account_number=" + accountPojo.getAccountNumber() + "";
				
				ResultSet resultSet = stmt.executeQuery(query2);
				
				resultSet.next();
				
				returnedAccountPojo.setAccountNumber(resultSet.getInt(1));
				
				returnedAccountPojo.setAccountType(resultSet.getString(2));
				
				returnedAccountPojo.setAccessCode(resultSet.getInt(4));
				
			}
			
		} catch (SQLException e) {
			
			throw new SQLException();
			
		}
		
		return returnedAccountPojo;
	}
	
}
