package service;

import dao.AccountDao;
import dao.AccountDaoDatabaseImpl;
import model.AccountPojo;

public class AccountServiceImpl implements AccountService {
	
	AccountDao accountDao;
	
	public AccountServiceImpl() {
		
		accountDao = new AccountDaoDatabaseImpl();
	}
	
	public AccountPojo addAccount(AccountPojo accountPojo) {
		return accountDao.addAccount(accountPojo);
	}
}
