package service;

import dao.UserDao;
import dao.UserDaoDatabaseImpl;
import model.UserPojo;

public class UserServiceImpl implements UserService {

		UserDao userDao;
		
		public UserServiceImpl() {
			
			userDao = new UserDaoDatabaseImpl();
		}
		
		public UserPojo addUser(UserPojo userPojo) {
			return userDao.addUser(userPojo);
		}
}
