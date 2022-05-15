package dao;

import java.sql.SQLException;
import java.util.List;

import model.AccountPojo;
import model.UserPojo;

public interface UserDao {

	UserPojo addUser(UserPojo userPojo);
	
	List<UserPojo> getAllUsers();
	
	UserPojo getOneUser(int userId);
	
	UserPojo logIn(UserPojo userPojo);
	
	void logOut();
	
	UserPojo changePassword(UserPojo userPojo);
	
	UserPojo recoverPassword(UserPojo userPojo, AccountPojo accountPojo);
	
	boolean removeUserAccount(UserPojo userPojo) throws SQLException;
}
