package dao;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import exception.NoAccountException;
import exception.SystemException;
import model.AccountPojo;
import model.UserPojo;

public interface UserDao {

	UserPojo addUser(UserPojo userPojo) throws SystemException, SQLException;
	
	List<UserPojo> getAllUsers();
	
	UserPojo getOneUser(int userId);
	
	UserPojo logIn(UserPojo userPojo)throws NoAccountException, SystemException;
	
	UserPojo logOut(UserPojo userPojo);
	
	UserPojo changePassword(UserPojo userPojo);
	
	UserPojo recoverPassword(UserPojo userPojo, AccountPojo accountPojo);
	
	boolean removeUserAccount(UserPojo userPojo) throws SQLException;
}
