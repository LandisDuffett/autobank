package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.SessionPojo;

public class SessionDaoDatabaseImpl implements SessionDao {

	public SessionPojo addSession(SessionPojo sessionPojo) {

		Connection conn;

		try {
			conn = DBUtil.makeConnection();

			Statement stmt = conn.createStatement();

			String query = "INSERT INTO sessions(user_id, login_time, logout_time) VALUES('"
					+sessionPojo.getUserId() + "', '"+ sessionPojo.getLoginTime()+"', '"+sessionPojo.getLogoutTime()+"')";

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sessionPojo;
	}
}
