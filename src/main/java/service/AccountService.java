package service;

import java.util.List;

import model.AccountPojo;

public interface AccountService {
	
	boolean addAccount(AccountPojo accountPojo);
	
	List<AccountPojo> getAllAccounts();
	
	AccountPojo getOneAccount(int accountNumber);
}
