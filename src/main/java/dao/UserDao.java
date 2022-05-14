package dao;

import java.util.List;

import model.UserPojo;

public interface UserDao {

	UserPojo addUser(UserPojo userPojo);
	
	List<UserPojo> getAllUsers();
	
	UserPojo getOneUser(int userId);
}
