package service;

import java.sql.SQLException;
import java.util.List;

import dao.AccountDao;
import dao.AccountDaoDatabaseImpl;
import exception.BalanceNotEmptyException;
import exception.SystemException;
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
	
	public boolean closeBankAccount(AccountPojo accountPojo, UserPojo userPojo) throws SystemException, SQLException, BalanceNotEmptyException {
		return accountDao.closeBankAccount(accountPojo, userPojo);
	}
	
	public AccountPojo linkToAccount(AccountPojo accountPojo, UserPojo userPojo) throws SQLException, SystemException {
		return accountDao.linkToAccount(accountPojo, userPojo);
	}
}
