package service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import exception.NoAccountException;
import exception.SystemException;
import model.AccountPojo;
import model.UserPojo;

public interface UserService {

	UserPojo addUser(UserPojo userPojo) throws SystemException, SQLException;
	
	UserPojo logIn(UserPojo userPojo)throws NoAccountException, SystemException;
	
	UserPojo logOut(UserPojo userPojo);
	
	UserPojo changePassword(UserPojo userPojo);
	
	UserPojo recoverPassword(UserPojo userPojo, AccountPojo accountPojo);
	
	boolean removeUserAccount(UserPojo userPojo) throws SQLException;
}
