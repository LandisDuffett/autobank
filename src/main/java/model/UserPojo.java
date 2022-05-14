package model;

public class UserPojo {

	private int userId;
	private String userName;
	private String userPassword;
	private String userType;
	private String userFirstName;
	private String userLastName;
	private int userPin;

	public UserPojo() {

	}

	public UserPojo(int userId, String userName, String userPassword, String userType, String userFirstName,
			String userLastName, int userPin) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userPin = userPin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public int getUserPin() {
		return userPin;
	}

	public void setUserPin(int userPin) {
		this.userPin = userPin;
	}
	
	
}
