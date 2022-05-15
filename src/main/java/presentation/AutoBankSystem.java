package presentation;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import exception.SystemException;
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

		// USER METHODS

		UserService userService = new UserServiceImpl();

		UserPojo newUserPojo = new UserPojo();
		UserPojo returnedUserPojo = null;
		AccountPojo newAccountPojo = new AccountPojo(); 

		/*
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
		
		// get all users
		/*
		List<UserPojo> everyUser;
		everyUser = userService.getAllUsers();
		everyUser.forEach((item) -> System.out.println(item.getUserId() + "\t" + item.getUserName() + "\t"
				+ item.getUserPassword() + "\t\t\t" + item.getUserType() + "\t" + item.getUserFirstName() + "\t"
				+ item.getUserLastName() + "\t" + item.getUserPin()));
		*/
		
		// get one user
		/*
		System.out.println("Enter user id");
		int userid = scan.nextInt();
		UserPojo getUserPojo = null;
		getUserPojo = userService.getOneUser(userid);
		System.out.println("User ID:" + getUserPojo.getUserId());
		*/
		
		//log in
		
		System.out.println("Enter user name");
		newUserPojo.setUserName(scan.nextLine());
		System.out.println("Enter password");
		newUserPojo.setUserPassword(scan.nextLine());
		returnedUserPojo = userService.logIn(newUserPojo);
		System.out.println("User id: " + returnedUserPojo.getUserLastName());
		
		
		//log out
		/*
		System.out.println("Log out and close application:");
		String input = scan.nextLine();
		if(input != null) {
			System.out.println("You are logged out...Goodbye.");
			userService.logOut();
		}
		*/
		//change password
		/*
		System.out.println("enter new password");
		newUserPojo.setUserPassword(scan.nextLine());
		System.out.println("enter PIN");
		newUserPojo.setUserPin(scan.nextInt());
		returnedUserPojo = userService.changePassword(newUserPojo);
		System.out.println("New password: " + returnedUserPojo.getUserPassword());
		*/
		
		//recover password
		/*
		System.out.println("enter PIN");
		newUserPojo.setUserPin(scan.nextInt());
		System.out.println("enter one account number");
		newAccountPojo.setAccountNumber(scan.nextInt());
		System.out.println("enter Access Code for that account");
		newAccountPojo.setAccessCode(scan.nextInt());
		returnedUserPojo = userService.recoverPassword(newUserPojo, newAccountPojo);
		System.out.println("Your password is: " + returnedUserPojo.getUserPassword());
		*/
		
		//remove user account
		/*
		System.out.println("Enter PIN");
		newUserPojo.setUserPin(scan.nextInt());
		try {
			boolean success = userService.removeUserAccount(newUserPojo);
			if(success) {
				System.out.println("success");
			} else {
				System.out.println("fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// ACCOUNT METHODS
		
		 AccountService accountService = new AccountServiceImpl();
		  
		 AccountPojo returnedAccountPojo = null;
		 
		 //create new bankaccount
		 
		 System.out.println("Enter new account type(checking or savings)");
		 //use switch statement to validate choice: 1 = checking, 2 = savings, else=not a valid choice, choose again
		 newAccountPojo.setAccountType(scan.nextLine());
		 System.out.println("Enter new acount access code");
		 newAccountPojo.setAccessCode(scan.nextInt()); 
		 boolean creation = accountService.addAccount(newAccountPojo);
		 System.out.println("Acount creation was: " + creation);
		
		
		// get all accounts
		/*
		List<AccountPojo> everyAccount;
		everyAccount = accountService.getAllAccounts();
		everyAccount.forEach((item) -> System.out.println(item.getAccountNumber() + "\t" + item.getAccountType() + "\t"
				+ item.getAccountBalance() + "\t" + item.getAccessCode()));
		*/
		
		// get one account
		 /*
		System.out.println("Enter account number");
		int accountnumber = scan.nextInt();
		AccountPojo getAccountPojo = null;
		getAccountPojo = accountService.getOneAccount(accountnumber);
		System.out.println("Account Number:" + getAccountPojo.getAccountNumber());
		 */
		
		// ACCOUNTUSERS METHODS
		
		AccountUsersService accountUsersService = new AccountUsersServiceImpl();
		
		AccountUsersPojo newAccountUsersPojo = new AccountUsersPojo();
		AccountUsersPojo returnedAccountUsersPojo = null;
		/*
		System.out.println("Enter new acountUser account number");
		newAccountUsersPojo.setAccountNumber(scan.nextInt());
		System.out.println("Enter new acountUser user id");
		newAccountUsersPojo.setUserId(scan.nextInt()); returnedAccountUsersPojo =
		accountUsersService.addAccountUsers(newAccountUsersPojo);
		System.out.println("Your account number is: " +
		returnedAccountUsersPojo.getAccountNumber());
		*/
		
		// get all accountUsers
		/*
		List<AccountUsersPojo> everyAccountUsers;
		everyAccountUsers = accountUsersService.getAllAccountUsers();
		everyAccountUsers.forEach((item) -> System.out.println(item.getAccountNumber() + "\t" + item.getUserId() + "\t"));
		*/
		
		// get one account by user id
		/*
		System.out.println("Enter user id");
		int userid = scan.nextInt();
		List<AccountUsersPojo> theAccountUsersById;
		theAccountUsersById = accountUsersService.getTheAccountUsersForOneId(userid);
		theAccountUsersById.forEach((item) -> System.out.println(item.getAccountNumber() + "\t" + item.getUserId() + "\t"));
		*/
		
		// get one account by account number
		/*
		System.out.println("Enter account number");
		int accno = scan.nextInt();
		List<AccountUsersPojo> theAccountUsersByAccNo;
		theAccountUsersByAccNo = accountUsersService.getTheAccountUsersForOneAccNo(accno);
		theAccountUsersByAccNo.forEach((item) -> System.out.println(item.getAccountNumber() + "\t" + item.getUserId() + "\t"));
		*/
		
		// TRANSACTION METHODS
		
		TransactionService transactionService = new TransactionServiceImpl();
		 
		TransactionPojo newTransactionPojo = new TransactionPojo(); TransactionPojo
		returnedTransactionPojo = null;
		
		/*
		System.out.println("Enter new account number");
		newTransactionPojo.setAccountNumber(scan.nextInt());
		System.out.println("Enter new time"); newTransactionPojo.setTime(new
		Date().toString()); System.out.println("Enter new transaction type");
		scan.nextLine(); newTransactionPojo.setTransactionType(scan.nextLine());
		System.out.println("Enter new transaction amount");
		newTransactionPojo.setTransactionAmount(scan.nextDouble());
		System.out.println("Enter new updated balance");
		newTransactionPojo.setUpdatedBalance(scan.nextDouble());
		returnedTransactionPojo =
		transactionService.addTransaction(newTransactionPojo);
		System.out.println("Your account number is: " +
		returnedTransactionPojo.getAccountNumber());
		*/
		
		// get all transactions
		/*
		List<TransactionPojo> everyTransaction;
		everyTransaction = transactionService.getAllTransactions();
		everyTransaction.forEach((item) -> System.out.println(item.getTransactionNumber() + "\t" + item.getAccountNumber() + "\t"
				+ item.getTime() + "\t" + item.getTransactionType() + "\t" + item.getTransactionAmount() + + item.getUpdatedBalance() + "\t"));
		*/
		
		// get one transaction
		/*
		System.out.println("Enter transaction number");
		int transactionNumber = scan.nextInt();
		TransactionPojo getTransactionPojo = null;
		getTransactionPojo = transactionService.getOneTransaction(transactionNumber);
		System.out.println("Transaction Number:" + getTransactionPojo.getTransactionNumber());
		*/
		
		// get one transaction by user
		/*
		System.out.println("Enter account number");
		int accno = scan.nextInt();
		List<TransactionPojo> theTransactionsByAccNo;
		theTransactionsByAccNo = transactionService.getTransactionsForOneAccNo(accno);
		theTransactionsByAccNo.forEach((item) -> System.out.println(item.getTransactionNumber() + "\t" + item.getAccountNumber() + "\t"
				+ item.getTime() + "\t" + item.getTransactionType() + "\t" + item.getTransactionAmount() + "\t" + item.getUpdatedBalance() + "\t" + item.getTargetAccNo()+"\t"+item.getTargetRoutNo()));
		*/

		// SESSION METHODS
		SessionService sessionService = new SessionServiceImpl();
		
		SessionPojo newSessionPojo = new SessionPojo(); 
		SessionPojo returnedSessionPojo = null;
		/*
		System.out.println("Enter new userId");
		newSessionPojo.setUserId(scan.nextInt());
		System.out.println("Enter new login time"); newSessionPojo.setLoginTime(new
		Date().toString()); System.out.println("Enter new logout time");
		newSessionPojo.setLogoutTime(new Date().toString()); returnedSessionPojo =
		sessionService.addSession(newSessionPojo);
		System.out.println("Your user id is: " + returnedSessionPojo.getUserId());
		*/
		
		//get all sessions
		/*
		List<SessionPojo> everySession;
		everySession = sessionService.getAllSessions();
		everySession.forEach((item) -> System.out.println(item.getSessionNumber() + "\t" + item.getUserId() + "\t"
				+ item.getLoginTime() + "\t" + item.getLogoutTime()));
		*/
		
		/*
		//get all sessions for one user
		System.out.println("Enter user id");
		int userid = scan.nextInt();
		List<SessionPojo> theSessionsByUser;
		theSessionsByUser = sessionService.getSessionsForOneUser(userid);
		theSessionsByUser.forEach((item) -> System.out.println(item.getSessionNumber() + "\t" + item.getUserId() + "\t"
				+ item.getLoginTime() + "\t" + item.getLogoutTime()));	
		*/	 
	}
	
}
