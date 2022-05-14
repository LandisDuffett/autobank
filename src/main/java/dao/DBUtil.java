package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// singleton design pattern
	static Connection conn;
		
	static {
		try {
			//step 1
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Connection makeConnection() throws SQLException {
		//step 2
		
		String connectionUrl = "jdbc:postgresql://database-2.cjo1lsd4zbsb.us-west-2.rds.amazonaws.com:5432/autobank";
		String userName = "postgres";
		String password = "zilpah987";
		
		if(conn == null) {
			conn = DriverManager.getConnection(connectionUrl, userName, password);	
		}
		return conn;
	}
	

}
