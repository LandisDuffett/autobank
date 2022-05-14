package dao;

import java.util.List;

import model.TransactionPojo;

public interface TransactionDao {

	TransactionPojo addTransaction (TransactionPojo transactionPojo);
	
	List<TransactionPojo> getAllTransactions();
	
	TransactionPojo getOneTransaction(int transactionNumber);
	
	List<TransactionPojo> getTransactionsForOneAccNo(int userId);
}
