package service;

import java.util.List;

import model.TransactionPojo;

public interface TransactionService {

	TransactionPojo addTransaction(TransactionPojo transactionPojo);
	
	List<TransactionPojo> getAllTransactions();
	
	TransactionPojo getOneTransaction(int transactionNumber);
	
	List<TransactionPojo> getTransactionsForOneAccNo(int accNo);
}
