package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.UserPojo;

public class UserDaoDatabaseImpl implements UserDao {

	public UserPojo addUser(UserPojo userPojo) {
		
		Connection conn;
		
			try {
				conn = DBUtil.makeConnection();
				
				Statement stmt = conn.createStatement();
				
				String query = "INSERT INTO users(user_name, user_password, user_firstName, user_lastName, user_pin) VALUES('"+userPojo.getUserName()+"', '"+userPojo.getUserPassword()+"', '"+userPojo.getUserFirstName()+"', '"+userPojo.getUserLastName()+"', '"+userPojo.getUserPin()+"')";

				int rowsAffected = stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return userPojo;
	}
}
