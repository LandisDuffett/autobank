package model;

import java.sql.Date;

public class TransactionPojo {
	private int transactionNumber;
	private int accountNumber;
	private String time;
	private String transactionType;
	private double transactionAmount;
	private double updatedBalance;
	
	public TransactionPojo() {

	}

	public TransactionPojo(int transactionNumber, int accountNumber, String time, String transactionType, double transactionAmount, double updatedBalance) {
		super();
		this.transactionNumber = transactionNumber;
		this.accountNumber = accountNumber;
		this.time = time;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.updatedBalance = updatedBalance;
	}

	public int getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public double getUpdatedBalance() {
		return updatedBalance;
	}

	public void setUpdatedBalance(double updatedBalance) {
		this.updatedBalance = updatedBalance;
	}
	
	
}
