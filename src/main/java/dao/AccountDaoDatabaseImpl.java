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
		
		System.out.println("hellofrom 59");

		try {
			conn = DBUtil.makeConnection();
			
			UserPojo newUserPojo = new UserPojo();

			Statement stmt = conn.createStatement();

			String query1 = "SELECT user_id FROM sessions WHERE session_number = (SELECT MAX(session_number) FROM sessions)";
			ResultSet resultSet1 = stmt.executeQuery(query1);
			resultSet1.next();
			String query = "SELECT * FROM accounts INNER JOIN accountUsers ON accountUsers.account_number = accounts.account_number WHERE accountUsers.user_id="+resultSet1.getInt(1)+"";
			ResultSet resultSet = stmt.executeQuery(query);
			//resultSet.next();
			//System.out.println("hel");
			//System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDouble(3) + " " +resultSet.getInt(4));
			int counter = 0;

			while (resultSet.next()) {
				counter++;

				AccountPojo accountPojo = new AccountPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),
						resultSet.getInt(4));
				System.out.println("hoo");
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
	
	public boolean closeBankAccount(AccountPojo accountPojo, UserPojo userPojo) throws SystemException, BalanceNotEmptyException, SQLException {
		Connection conn = null;
		boolean success = false;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			System.out.println("zero");
			String query = "SELECT users.user_id, users.user_pin, accounts.account_number, accounts.account_type, accounts.account_balance, accounts.access_code FROM accounts INNER JOIN accountUsers on accounts.account_number = accountUsers.account_number INNER JOIN users ON users.user_id = accountUsers.user_id AND users.user_pin ="+userPojo.getUserPin()+"WHERE accountUsers.user_id=(SELECT user_id FROM sessions WHERE session_number=(SELECT MAX (session_number) FROM sessions)) AND accounts.account_number="+accountPojo.getAccountNumber()+"";
			ResultSet resultSet = stmt.executeQuery(query);
			//resultSet.next();
			//System.out.println(resultSet.getDouble(5));
			if(resultSet.next()) {
				System.out.println("zoo");
				if (resultSet.getDouble(5) != 0) {
					System.out.println("woo");
					throw new BalanceNotEmptyException();
				}
				System.out.println("loo");
				String query2 = "INSERT INTO inactive_accounts(account_number, account_type, access_code) VALUES("+ resultSet.getInt(3) + ", '" + resultSet.getString(4) + "', " + resultSet.getInt(6) +")";
				System.out.println("boo");
				String query3 = "DELETE FROM accounts WHERE account_number=" + resultSet.getInt(3) + "";
				System.out.println("goo");
				String query4 = "DELETE FROM accountUsers WHERE account_number=" + resultSet.getInt(3) + " AND user_id=" + resultSet.getInt(1)+"";
				//conn.setAutoCommit(false);
				System.out.println("moo");
				int rowsAffected2 = stmt.executeUpdate(query2);
				System.out.println("three");
				int rowsAffected3 = stmt.executeUpdate(query3);
				System.out.println("four");
				int rowsAffected4 = stmt.executeUpdate(query4);
				System.out.println("five");
				//System.out.println(rowsAffected3);
				//System.out.println(rowsAffected4);
				//conn.commit();
				if (rowsAffected2 == 1 && rowsAffected3 == 1 && rowsAffected4 == 1) {
				//if (rowsAffected3 == 1 && rowsAffected4 == 1) {
					success = true;
					
				}
			}
		} catch (SQLException e) {
			//conn.rollback();
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
			System.out.println("goo");
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO accountUsers(account_number, user_id) VALUES((SELECT account_number FROM accounts WHERE access_code ="+accountPojo.getAccessCode()+"), (SELECT user_id FROM users WHERE user_pin = (SELECT user_pin FROM users WHERE user_id = (SELECT user_id FROM sessions WHERE session_number = (SELECT MAX (session_number) FROM sessions))) AND user_pin="+userPojo.getUserPin()+"))";
			int rowsAffected = stmt.executeUpdate(query);
			System.out.println("goo2");
			if(rowsAffected == 1) {
				String query2 = "SELECT * FROM accounts WHERE account_number="+accountPojo.getAccountNumber()+"";
				ResultSet resultSet = stmt.executeQuery(query2);
				resultSet.next();
				returnedAccountPojo.setAccountNumber(resultSet.getInt(1));
				returnedAccountPojo.setAccountType(resultSet.getString(2));
				System.out.println("goo3");
				returnedAccountPojo.setAccessCode(resultSet.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println("goo4");
			throw new SQLException();
		} 
		return returnedAccountPojo;
	}
	
}
