package service;

import java.sql.SQLException;
import java.util.List;

import dao.UserDao;
import dao.UserDaoDatabaseImpl;
import exception.SystemException;
import model.AccountPojo;
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
		
		public UserPojo logIn(UserPojo userPojo) {
			return userDao.logIn(userPojo);
		}
		
		public void logOut() {
			
		}
		
		public UserPojo changePassword(UserPojo userPojo) {
			return userDao.changePassword(userPojo);
		}
		
		public UserPojo recoverPassword(UserPojo userPojo, AccountPojo accountPojo) {
			return userDao.recoverPassword(userPojo, accountPojo);
		}
		
		public boolean removeUserAccount(UserPojo userPojo) throws SQLException {
			return userDao.removeUserAccount(userPojo);
		}
}
