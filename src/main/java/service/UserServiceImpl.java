package service;

import java.util.List;

import dao.UserDao;
import dao.UserDaoDatabaseImpl;
import exception.SystemException;
import model.UserPojo;

public class UserServiceImpl implements UserService {

		UserDao userDao;
		
		public UserServiceImpl() {
			
			userDao = new UserDaoDatabaseImpl();
		}
		
		public UserPojo addUser(UserPojo userPojo) {
			return userDao.addUser(userPojo);
		}
		
		public List<UserPojo> getAllUsers(){
			return userDao.getAllUsers();
		}
		
		public UserPojo getOneUser(int userId) {
			return userDao.getOneUser(userId);
		}
}
