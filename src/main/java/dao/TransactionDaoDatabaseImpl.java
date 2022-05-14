package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.TransactionPojo;

public class TransactionDaoDatabaseImpl implements TransactionDao {

	public TransactionPojo addTransaction(TransactionPojo transactionPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO transactions(account_number, transaction_type, transaction_amount, updated_balance, time) VALUES('"
					+ transactionPojo.getAccountNumber() + "', '" + transactionPojo.getTransactionType() + "', '" + transactionPojo.getTransactionAmount() + "', '" + transactionPojo.getUpdatedBalance()+ "', '" + transactionPojo.getTime() + "')";

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return transactionPojo;
	}
}
