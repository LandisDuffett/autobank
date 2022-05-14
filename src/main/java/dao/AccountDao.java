package dao;

import java.util.List;

import model.AccountPojo;

public interface AccountDao {

	AccountPojo addAccount(AccountPojo accountPojo);

	List<AccountPojo> getAllAccounts();

	AccountPojo getOneAccount(int accountNumber);

}
