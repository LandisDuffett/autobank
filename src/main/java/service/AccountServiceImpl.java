package service;

import java.util.List;

import dao.AccountDao;
import dao.AccountDaoDatabaseImpl;
import model.AccountPojo;
import model.UserPojo;

public class AccountServiceImpl implements AccountService {
	
	AccountDao accountDao;
	
	public AccountServiceImpl() {
		
		accountDao = new AccountDaoDatabaseImpl();
	}
	
	public boolean addAccount(AccountPojo accountPojo) {
		return accountDao.addAccount(accountPojo);
	}
	
	public List<AccountPojo> getAllAccounts(){
		return accountDao.getAllAccounts();
	}
	
	public AccountPojo getOneAccount(int accountNumber) {
		return accountDao.getOneAccount(accountNumber);
	}
}
