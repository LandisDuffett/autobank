package model;

public class AccountUsersPojo {
	
	private int accountNumber;
	private int userId;
	
	public AccountUsersPojo() {

	}

	public AccountUsersPojo(int accountNumber,int userId) {
		super();
		this.accountNumber = accountNumber;
		this.userId = userId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
