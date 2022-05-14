package service;

import java.util.List;

import model.AccountPojo;
import model.AccountUsersPojo;

public interface AccountUsersService {
	
	AccountUsersPojo addAccountUsers(AccountUsersPojo accountUsersPojo);
	
	List<AccountUsersPojo> getAllAccountUsers();
	
	List<AccountUsersPojo> getTheAccountUsersForOneId(int userId);
	
	List<AccountUsersPojo> getTheAccountUsersForOneAccNo(int accNo);

}
