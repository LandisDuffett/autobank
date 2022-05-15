package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public List<TransactionPojo> getTransactionsForOneAccNo(int accNo) {

		List<TransactionPojo> theTransactionsForOneAccNo = new ArrayList<TransactionPojo>();

		Connection conn = null;

		// TransactionPojo transactionPojo = null;
		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM transactions WHERE account_number=" + accNo;

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				TransactionPojo transactionPojo = new TransactionPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getString(6), resultSet.getInt(7), resultSet.getInt(8));

				theTransactionsForOneAccNo.add(transactionPojo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theTransactionsForOneAccNo;

	}
}
