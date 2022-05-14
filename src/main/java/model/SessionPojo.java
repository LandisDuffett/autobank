package model;

public class SessionPojo {
	private int sessionNumber;
	private int userId;
	private String loginTime;
	private String logoutTime;
	
	public SessionPojo() {

	}

	public SessionPojo(int sessionNumber, int userId, String loginTime, String logoutTime) {
		super();
		this.sessionNumber = sessionNumber;
		this.userId = userId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	
}
