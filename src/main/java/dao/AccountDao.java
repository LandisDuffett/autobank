package dao;

import java.sql.SQLException;
import java.util.List;

import exception.BalanceNotEmptyException;
import exception.SystemException;
import model.AccountPojo;
import model.UserPojo;

public interface AccountDao {

	boolean addAccount(AccountPojo accountPojo);

	List<AccountPojo> getAllAccounts();

	AccountPojo getOneAccount(int accountNumber);
	
	boolean closeBankAccount(AccountPojo accountPojo, UserPojo userPojo) throws SystemException, SQLException, BalanceNotEmptyException;

	AccountPojo linkToAccount(AccountPojo accountPojo, UserPojo userPojo) throws SQLException, SystemException;
}
