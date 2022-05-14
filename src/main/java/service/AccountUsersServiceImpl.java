package service;

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
}
