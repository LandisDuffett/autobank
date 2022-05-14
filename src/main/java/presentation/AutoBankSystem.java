package presentation;


import java.util.Date;
import java.util.Scanner;

import model.AccountPojo;
import model.AccountUsersPojo;
import model.SessionPojo;
import model.TransactionPojo;
import model.UserPojo;
import service.AccountService;
import service.AccountServiceImpl;
import service.AccountUsersService;
import service.AccountUsersServiceImpl;
import service.SessionService;
import service.SessionServiceImpl;
import service.TransactionService;
import service.TransactionServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class AutoBankSystem {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		//USER METHODS
		/*
		UserService userService = new UserServiceImpl();

		UserPojo newUserPojo = new UserPojo();
		UserPojo returnedUserPojo = null;
		
		//create new useraccount
		System.out.println("Enter new user name");
		newUserPojo.setUserName(scan.nextLine());
		System.out.println("Enter new password");
		newUserPojo.setUserPassword(scan.nextLine());
		System.out.println("Enter new user first name");
		newUserPojo.setUserFirstName(scan.nextLine());
		System.out.println("Enter new user last name");
		newUserPojo.setUserLastName(scan.nextLine());
		System.out.println("Enter new PIN");
		newUserPojo.setUserPin(scan.nextInt());
		returnedUserPojo = userService.addUser(newUserPojo);
		System.out.println("Welcome, " + returnedUserPojo.getUserFirstName());
		*/
		
		//ACCOUNT METHODS
		/*
		AccountService accountService = new AccountServiceImpl();
		
		AccountPojo newAccountPojo = new AccountPojo();
		AccountPojo returnedAccountPojo = null;
		
		//create new bankaccount
		System.out.println("Enter new account type(checking or savings)");
		newAccountPojo.setAccountType(scan.nextLine());
		System.out.println("Enter new acount access code");
		newAccountPojo.setAccessCode(scan.nextInt());
		returnedAccountPojo = accountService.addAccount(newAccountPojo);
		System.out.println("Your account type is: " + returnedAccountPojo.getAccountType());
		*/
		
		//ACCOUNTUSERS METHODS
		/*
		AccountUsersService accountUsersService = new AccountUsersServiceImpl();
		
		AccountUsersPojo newAccountUsersPojo = new AccountUsersPojo();
		AccountUsersPojo returnedAccountUsersPojo = null;
		
		System.out.println("Enter new acountUser account number");
		newAccountUsersPojo.setAccountNumber(scan.nextInt());
		System.out.println("Enter new acountUser user id");
		newAccountUsersPojo.setUserId(scan.nextInt());
		returnedAccountUsersPojo = accountUsersService.addAccountUsers(newAccountUsersPojo);
		System.out.println("Your account number is: " + returnedAccountUsersPojo.getAccountNumber());
		*/
		
		//TRANSACTION METHODS
		/*
		TransactionService TransactionService = new TransactionServiceImpl();
		
		TransactionPojo newTransactionPojo = new TransactionPojo();
		TransactionPojo returnedTransactionPojo = null;
		
		System.out.println("Enter new account number");
		newTransactionPojo.setAccountNumber(scan.nextInt());
		System.out.println("Enter new time");
		newTransactionPojo.setTime(new Date().toString());
		System.out.println("Enter new transaction type");
		scan.nextLine();
		newTransactionPojo.setTransactionType(scan.nextLine());
		System.out.println("Enter new transaction amount");
		newTransactionPojo.setTransactionAmount(scan.nextDouble());
		System.out.println("Enter new updated balance");
		newTransactionPojo.setUpdatedBalance(scan.nextDouble());
		returnedTransactionPojo = TransactionService.addTransaction(newTransactionPojo);
		System.out.println("Your account number is: " + returnedTransactionPojo.getAccountNumber());
		*/
		
		//SESSION METHODS
		SessionService SessionService = new SessionServiceImpl();
		
		SessionPojo newSessionPojo = new SessionPojo();
		SessionPojo returnedSessionPojo = null;
		
		System.out.println("Enter new userId");
		newSessionPojo.setUserId(scan.nextInt());
		System.out.println("Enter new login time");
		newSessionPojo.setLoginTime(new Date().toString());
		System.out.println("Enter new logout time");
		newSessionPojo.setLogoutTime(new Date().toString());
		returnedSessionPojo = SessionService.addSession(newSessionPojo);
		System.out.println("Your user id is: " + returnedSessionPojo.getUserId());
		
	}
}
