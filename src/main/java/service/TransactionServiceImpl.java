package service;

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
}
