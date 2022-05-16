package dao;

import java.util.List;

import exception.BalanceBelowZeroException;
import exception.EmptyListException;
import exception.OnlyOneAccountException;
import exception.SystemException;
import model.TransactionPojo;

public interface TransactionDao {

	TransactionPojo addTransaction (TransactionPojo transactionPojo);
	
	List<TransactionPojo> getAllTransactions();
	
	TransactionPojo getOneTransaction(int transactionNumber);
	
	List<TransactionPojo> getTransactionsForOneAccNo(TransactionPojo transactionPojo)throws EmptyListException, SystemException;
	
	TransactionPojo makeDeposit(TransactionPojo transactionPojo);
	
	TransactionPojo makeWithdrawal(TransactionPojo transactionPojo)throws BalanceBelowZeroException;
	
	TransactionPojo viewBalance(TransactionPojo transactionPojo);
	
	TransactionPojo transferFunds (TransactionPojo transactionPojo)throws OnlyOneAccountException, SystemException, BalanceBelowZeroException;
}
