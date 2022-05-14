package service;

import java.util.List;

import dao.AccountUsersDao;
import dao.AccountUsersDaoDatabaseImpl;
import model.AccountUsersPojo;

public class AccountUsersServiceImpl implements AccountUsersService {

	AccountUsersDao accountUsersDao;

	public AccountUsersServiceImpl() {
		
		accountUsersDao = new AccountUsersDaoDatabaseImpl();
	}

	public AccountUsersPojo addAccountUsers(AccountUsersPojo accountUsersPojo) {
		return accountUsersDao.addAccountUsers(accountUsersPojo);
	}
	
	public List<AccountUsersPojo> getAllAccountUsers() {
		return accountUsersDao.getAllAccountUsers();
	}
	
	public List<AccountUsersPojo> getTheAccountUsersForOneId(int userId) {
		return accountUsersDao.getTheAccountUsersForOneId(userId);
	}
	
	public List<AccountUsersPojo> getTheAccountUsersForOneAccNo(int accNo) {
		return accountUsersDao.getTheAccountUsersForOneAccNo(accNo);
	}
	
}
