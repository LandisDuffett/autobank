package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.BalanceBelowZeroException;
import exception.EmptyListException;
import exception.OnlyOneAccountException;
import exception.SystemException;
import model.AccountPojo;
import model.AccountUsersPojo;
import model.TransactionPojo;

public class TransactionDaoDatabaseImpl implements TransactionDao {

	public TransactionPojo addTransaction(TransactionPojo transactionPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO transactions(account_number, transaction_type, transaction_amount, updated_balance, time, target_accno, target_routno) VALUES('"
					+ transactionPojo.getAccountNumber() + "', '" + transactionPojo.getTransactionType() + "', '"
					+ transactionPojo.getTransactionAmount() + "', '" + transactionPojo.getUpdatedBalance() + "', '"
					+ transactionPojo.getTargetAccNo() + "', '" + + transactionPojo.getTargetRoutNo() + "', '" + transactionPojo.getTime() + "')";

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return transactionPojo;
	}

	public List<TransactionPojo> getAllTransactions() {

		List<TransactionPojo> allTransactions = new ArrayList<TransactionPojo>();

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM transactions";

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				TransactionPojo transactionPojo = new TransactionPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
				allTransactions.add(transactionPojo);
			}
			/*
			 * if (counter == 0) { throw new EmptyListException(); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allTransactions;
	}

	public TransactionPojo getOneTransaction(int transactionNumber) {
		Connection conn = null;
		TransactionPojo transactionPojo = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM transactions WHERE transaction_number=" + transactionNumber;
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				transactionPojo = new TransactionPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionPojo;

	}

	public List<TransactionPojo> getTransactionsForOneAccNo(TransactionPojo transactionPojo)throws EmptyListException, SystemException {

		List<TransactionPojo> theTransactionsForOneAccNo = new ArrayList<TransactionPojo>();

		Connection conn = null;

		// TransactionPojo transactionPojo = null;
		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();
			
			String query2 = "SELECT * FROM transactions INNER JOIN accountUsers on transactions.account_number = accountUsers.account_number WHERE accountUsers.account_number ="+transactionPojo.getAccountNumber()+" AND accountUsers.user_id=(SELECT user_id FROM sessions WHERE session_number =(SELECT MAX(session_number) FROM sessions))";

			ResultSet resultSet2 = stmt.executeQuery(query2);
			
			if(!resultSet2.next()) {
				throw new EmptyListException();
			}
			
			String query = "SELECT transaction_number, transactions.account_number, transaction_type, ROUND(CAST(transaction_amount AS numeric),2), ROUND(CAST(updated_balance AS numeric),2), time, target_accno, target_routno FROM transactions INNER JOIN accountUsers on transactions.account_number = accountUsers.account_number WHERE accountUsers.account_number ="+transactionPojo.getAccountNumber()+" AND accountUsers.user_id=(SELECT user_id FROM sessions WHERE session_number =(SELECT MAX(session_number) FROM sessions))";

			ResultSet resultSet = stmt.executeQuery(query);
			
			int counter = 0;

			while (resultSet.next()) {
				counter++;

				TransactionPojo listTransactionPojo = new TransactionPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));

				theTransactionsForOneAccNo.add(listTransactionPojo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theTransactionsForOneAccNo;

	}
	
	public TransactionPojo makeDeposit(TransactionPojo transactionPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			
			String query = "INSERT INTO transactions(account_number, transaction_type, transaction_amount, updated_balance, time, target_accno, target_routno) VALUES((SELECT accounts.account_number FROM accounts INNER JOIN accountUsers ON accounts.account_number = accountUsers.account_number INNER JOIN users ON accountUsers.user_id=users.user_id WHERE accountUsers.account_number="
					+ transactionPojo.getAccountNumber()
					+ " AND users.user_id=(SELECT user_id FROM sessions WHERE session_number = (SELECT MAX(session_number) FROM sessions))), 'deposit', ABS("
					+ transactionPojo.getTransactionAmount()
					+ "), (SELECT account_balance FROM accounts WHERE accounts.account_number="
					+ transactionPojo.getAccountNumber() + ") + ABS(" + transactionPojo.getTransactionAmount() + "), '"
					+ transactionPojo.getTime() + "', " + transactionPojo.getTargetAccNo() + ", "
					+ transactionPojo.getTargetRoutNo() + ")";
			int rowsAffected = stmt.executeUpdate(query);
			if (rowsAffected == 1) {
				String query2 = "UPDATE accounts SET account_balance = (account_balance +ABS("
						+ transactionPojo.getTransactionAmount() + ")) WHERE account_number ="
						+ transactionPojo.getAccountNumber() + "";
				int rowsAffected2 = stmt.executeUpdate(query2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactionPojo;
	}
	
	public TransactionPojo makeWithdrawal(TransactionPojo transactionPojo)throws BalanceBelowZeroException {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			
			String query = "INSERT INTO transactions(account_number, transaction_type, transaction_amount, updated_balance, time, target_accno, target_routno) VALUES((SELECT accounts.account_number FROM accounts INNER JOIN accountUsers ON accounts.account_number = accountUsers.account_number INNER JOIN users ON accountUsers.user_id=users.user_id WHERE accountUsers.account_number="
					+ transactionPojo.getAccountNumber()
					+ " AND users.user_id=(SELECT user_id FROM sessions WHERE session_number = (SELECT MAX(session_number) FROM sessions))), 'withdrawal', (0-ABS("
					+ transactionPojo.getTransactionAmount()
					+ ")), (SELECT account_balance FROM accounts WHERE accounts.account_number="
					+ transactionPojo.getAccountNumber() + ") + (0-ABS(" + transactionPojo.getTransactionAmount() + ")), '"
					+ transactionPojo.getTime() + "', " + transactionPojo.getTargetAccNo() + ", "
					+ transactionPojo.getTargetRoutNo() + ")";
			String query2 = "SELECT account_balance FROM accounts WHERE account_number="+transactionPojo.getAccountNumber()+"";
			ResultSet resultSet = stmt.executeQuery(query2);
			resultSet.next();
			double check = transactionPojo.getTransactionAmount();
			if(check < 0) 
				check = (-2 * check) / 2;
			if(resultSet.getDouble(1) - check < 0) {
				throw new BalanceBelowZeroException();
			} 
			int rowsAffected = stmt.executeUpdate(query);
			if (rowsAffected == 1) {
				String query3 = "UPDATE accounts SET account_balance = (account_balance + (0-ABS("
						+ transactionPojo.getTransactionAmount() + "))) WHERE account_number ="
						+ transactionPojo.getAccountNumber() + "";
				int rowsAffected3 = stmt.executeUpdate(query3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactionPojo;
	}
	
	public TransactionPojo viewBalance(TransactionPojo transactionPojo) {
		
		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();
			
			String query = "SELECT ROUND(CAST(accounts.account_balance AS numeric), 2) FROM accounts INNER JOIN accountUsers ON accounts.account_number=accountUsers.account_number INNER JOIN users ON accountUsers.user_id = users.user_id WHERE accounts.account_number ="+transactionPojo.getAccountNumber()+" AND users.user_id = (SELECT user_id FROM sessions WHERE session_number=(SELECT MAX(session_number) FROM sessions))";
			ResultSet resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				transactionPojo.setUpdatedBalance(resultSet.getDouble(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactionPojo;
		}
	
	public TransactionPojo transferFunds (TransactionPojo transactionPojo)throws OnlyOneAccountException, SystemException, BalanceBelowZeroException {
		
		Connection conn;

		try {
			conn = DBUtil.makeConnection();
			
			Statement stmt = conn.createStatement();
			
			TransactionPojo depositTransactionPojo = new TransactionPojo();
			
			TransactionPojo withdrawalTransactionPojo = new TransactionPojo();
			
			String query = "SELECT accounts FROM accountUsers INNER JOIN accounts ON accountUsers.account_number = accounts.account_number INNER JOIN users ON users.user_id = accountUsers.user_id WHERE users.user_id = (SELECT user_id FROM sessions WHERE session_number = (SELECT MAX(session_number) FROM sessions))";

			ResultSet resultSet = stmt.executeQuery(query);

			if(!resultSet.next()) {
				throw new OnlyOneAccountException();
			} else {
				depositTransactionPojo.setAccountNumber(transactionPojo.getTargetRoutNo());
				depositTransactionPojo.setTransactionType("deposit");
				depositTransactionPojo.setTransactionAmount(transactionPojo.getTransactionAmount());
				//quick call using view balance method to get updated_balance value for deposit object
				TransactionPojo messengerPojo = new TransactionPojo();
				messengerPojo.setAccountNumber(transactionPojo.getTargetRoutNo());
				messengerPojo = viewBalance(messengerPojo);
				depositTransactionPojo.setUpdatedBalance(messengerPojo.getUpdatedBalance());
				depositTransactionPojo.setTime(new Date().toString());
				depositTransactionPojo.setTargetAccNo(100);
				depositTransactionPojo.setTargetRoutNo(200);
				
				withdrawalTransactionPojo.setAccountNumber(transactionPojo.getTargetAccNo());
				withdrawalTransactionPojo.setTransactionType("withdrawal");
				withdrawalTransactionPojo.setTransactionAmount(transactionPojo.getTransactionAmount());
				//quick call using view balance method to get updated_balance value for withdrawal object
				TransactionPojo messengerPojo2 = new TransactionPojo();
				messengerPojo.setAccountNumber(transactionPojo.getTargetAccNo());
				messengerPojo2 = viewBalance(messengerPojo2);
				withdrawalTransactionPojo.setUpdatedBalance(messengerPojo2.getUpdatedBalance());
				withdrawalTransactionPojo.setTime(new Date().toString());
				withdrawalTransactionPojo.setTargetAccNo(100);
				withdrawalTransactionPojo.setTargetRoutNo(200);
				
				makeWithdrawal(withdrawalTransactionPojo);
				makeDeposit(depositTransactionPojo);
			}
		} catch (SQLException e) {
			throw new SystemException();
	}
		return transactionPojo;
		}
}
