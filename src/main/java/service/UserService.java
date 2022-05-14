package service;

import java.util.List;

import model.UserPojo;

public interface UserService {

	UserPojo addUser(UserPojo userPojo);
	
	List<UserPojo> getAllUsers();
	
	UserPojo getOneUser(int userId);
}
