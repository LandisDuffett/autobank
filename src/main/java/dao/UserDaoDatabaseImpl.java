package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.SystemException;
import model.UserPojo;

public class UserDaoDatabaseImpl implements UserDao {

	public UserPojo addUser(UserPojo userPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO users(user_name, user_password, user_firstName, user_lastName, user_pin) VALUES('"
					+ userPojo.getUserName() + "', '" + userPojo.getUserPassword() + "', '"
					+ userPojo.getUserFirstName() + "', '" + userPojo.getUserLastName() + "', '" + userPojo.getUserPin()
					+ "')";

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userPojo;
	}

	public List<UserPojo> getAllUsers() {

		List<UserPojo> allUsers = new ArrayList<UserPojo>();

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM users";

			ResultSet resultSet = stmt.executeQuery(query);

			int counter = 0;

			while (resultSet.next()) {
				counter++;

				UserPojo userPojo = new UserPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));

				allUsers.add(userPojo);
			}
			/*
			 * if (counter == 0) { throw new EmptyListException(); }
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allUsers;
	}

	public UserPojo getOneUser(int userId) {
		Connection conn = null;
		UserPojo userPojo = null;
		try {
			conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM users WHERE user_id=" + userId;
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				userPojo = new UserPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userPojo;

	}
}
