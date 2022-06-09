package service;

import java.util.List;

import exception.BalanceBelowZeroException;
import exception.EmptyListException;
import exception.OnlyOneAccountException;
import exception.SystemException;
import model.TransactionPojo;

public interface TransactionService {
	
	List<TransactionPojo> getTransactionsForOneAccNo(TransactionPojo transactionPojo)throws EmptyListException, SystemException;
	
	TransactionPojo makeDeposit(TransactionPojo transactionPojo);
	
	TransactionPojo makeWithdrawal(TransactionPojo transactionPojo)throws BalanceBelowZeroException;
	
	TransactionPojo viewBalance(TransactionPojo transactionPojo);
	
	TransactionPojo transferFunds (TransactionPojo transactionPojo)throws OnlyOneAccountException, SystemException, BalanceBelowZeroException;
}
