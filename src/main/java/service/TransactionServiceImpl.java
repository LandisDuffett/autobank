package service;

import java.util.List;

import dao.TransactionDao;
import dao.TransactionDaoDatabaseImpl;
import exception.BalanceBelowZeroException;
import exception.EmptyListException;
import exception.OnlyOneAccountException;
import exception.SystemException;
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
	
	public List<TransactionPojo> getTransactionsForOneAccNo(TransactionPojo transactionPojo)throws EmptyListException, SystemException{
		return transactionDao.getTransactionsForOneAccNo(transactionPojo);
	}
	
	public TransactionPojo makeDeposit(TransactionPojo transactionPojo) {
		return transactionDao.makeDeposit(transactionPojo);
	}
	
	public TransactionPojo makeWithdrawal(TransactionPojo transactionPojo) throws BalanceBelowZeroException {
		return transactionDao.makeWithdrawal(transactionPojo);
	}
	
	public TransactionPojo viewBalance(TransactionPojo transactionPojo) {
		return transactionDao.viewBalance(transactionPojo);
	}
	
	public TransactionPojo transferFunds (TransactionPojo transactionPojo)throws OnlyOneAccountException, SystemException, BalanceBelowZeroException {
		return transactionDao.transferFunds(transactionPojo);
	}
}
