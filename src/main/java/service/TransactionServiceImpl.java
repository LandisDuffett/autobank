package service;

import java.util.List;

import dao.TransactionDao;
import dao.TransactionDaoDatabaseImpl;
import model.TransactionPojo;

public class TransactionServiceImpl implements TransactionService {

	TransactionDao transactionDao;
	
	public TransactionServiceImpl() {
		
		transactionDao = new TransactionDaoDatabaseImpl();
	}
	
	public TransactionPojo addTransaction(TransactionPojo transactionPojo) {
		return transactionDao.addTransaction(transactionPojo);
	}
	
	public List<TransactionPojo> getAllTransactions() {
		return transactionDao.getAllTransactions();
	}
	
	public TransactionPojo getOneTransaction(int transactionNumber) {
		return transactionDao.getOneTransaction(transactionNumber);
	}
	
	public List<TransactionPojo> getTransactionsForOneAccNo(int accNo) {
		return transactionDao.getTransactionsForOneAccNo(accNo);
	}
}
