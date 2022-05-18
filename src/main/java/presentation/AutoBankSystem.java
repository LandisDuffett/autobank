package presentation;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import exception.BalanceBelowZeroException;
import exception.BalanceNotEmptyException;
import exception.EmptyListException;
import exception.NoAccountException;
import exception.OnlyOneAccountException;
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

	public static void main(String[] args) throws SystemException {
		UserPojo newUserPojo = new UserPojo();
		UserPojo returnedUserPojo = null;
		AccountPojo newAccountPojo = new AccountPojo(); 
		AccountService accountService = new AccountServiceImpl();
		UserService userService = new UserServiceImpl();
		TransactionService transactionService = new TransactionServiceImpl();
		TransactionPojo newTransactionPojo = new TransactionPojo(); TransactionPojo
		returnedTransactionPojo = null;

		Scanner scan = new Scanner(System.in);
		char a = 'y';
		while (a == 'y') {
			int opt = 0;
			try {
			while(opt != 1 && opt != 2 && opt !=3) {
			System.out.println("Welcome to Bankbot!");
			System.out.println("Select one of the options below:");
			System.out.println("1. log in");
			System.out.println("2. sign up");
			System.out.println("3. forgot password");
			opt = scan.nextInt();
			}
			} catch (Exception e) {
				System.out.println("invalid input: input must be 1 or 2");
				System.exit(a);
			}
			switch (opt) {
			case 1:
				try {
					System.out.println("Enter user name:");
					scan.nextLine();
					newUserPojo.setUserName(scan.nextLine());
					System.out.println("Enter password:");
					newUserPojo.setUserPassword(scan.nextLine());
					returnedUserPojo = userService.logIn(newUserPojo);
					System.out.println("");
					System.out.println("Welcome back, " + returnedUserPojo.getUserFirstName() + "!");
				} catch (NoAccountException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.exit(a);
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.exit(a);
				}
				break;
			case 2:
				try {
					System.out.println("Enter new user name");
					scan.nextLine();
					newUserPojo.setUserName(scan.nextLine());
					System.out.println("Enter new password");
					newUserPojo.setUserPassword(scan.nextLine());
					System.out.println("Enter new user first name");
					newUserPojo.setUserFirstName(scan.nextLine());
					System.out.println("Enter new user last name");
					newUserPojo.setUserLastName(scan.nextLine());
					System.out.println("Enter new PIN"); 
					newUserPojo.setUserPin(scan.nextInt());
					if(newUserPojo.getUserName() == "" || newUserPojo.getUserPassword() == "" || newUserPojo.getUserFirstName() == "" || newUserPojo.getUserLastName() == "") {
						System.out.println("You must enter a value for all fields. Try again");
						System.exit(a);
					}
					returnedUserPojo = userService.addUser(newUserPojo);
					System.out.println("Welcome, " + returnedUserPojo.getUserFirstName());
					break;
				} catch (SQLException s) {
					System.out.println("Make sure your PIN is exactly four digits. Someone else might also already have that user name. Try again.");
					System.exit(a);
				} catch (SystemException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter PIN");
				newUserPojo.setUserPin(scan.nextInt());
				System.out.println("enter one account number");
				newAccountPojo.setAccountNumber(scan.nextInt());
				System.out.println("enter Access Code for that account");
				newAccountPojo.setAccessCode(scan.nextInt());
				returnedUserPojo = userService.recoverPassword(newUserPojo, newAccountPojo);
				System.out.println("Your password is: " + returnedUserPojo.getUserPassword());
				opt = 0;
						try {
							System.out.println("Enter user name:");
							scan.nextLine();
							newUserPojo.setUserName(scan.nextLine());
							System.out.println("Enter password:");
							newUserPojo.setUserPassword(scan.nextLine());
							returnedUserPojo = userService.logIn(newUserPojo);
							System.out.println("");
							System.out.println("Welcome back, " + returnedUserPojo.getUserFirstName() + "!");
						} catch (NoAccountException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							System.exit(a);
						} catch (SystemException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
							System.exit(a);
						}
					
					
						}
			break;
					}
			
						
					

			System.out.println("Welcome to Bankbot!");
			System.out.println("*****************************************************");
			System.out.println("1. Main menu");
			System.out.println("2. Log out");
			System.out.println("*****************************************************");
			System.out.println("Please enter an option:");
			a = 'n';
			int option = scan.nextInt();
			char b = 'y';
			while (b == 'y') {
				switch (option) {
				case 1:
					/*
					System.out.println("Enter user name:");
					scan.nextLine();
					newUserPojo.setUserName(scan.nextLine());
					System.out.println("Enter password:");
					newUserPojo.setUserPassword(scan.nextLine());
					try {
						returnedUserPojo = userService.logIn(newUserPojo);
						System.out.println("");
						System.out.println("Welcome back, "+returnedUserPojo.getUserFirstName()+"!");
					} catch (NoAccountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SystemException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					*/
					System.out.println("****************     MAIN MENU     ******************");
					System.out.println("1. Manage user account");
					System.out.println("2. Manage bank accounts");
					System.out.println("3. Manage funds");
					System.out.println("4. Log out");
					System.out.println("*****************************************************");
					System.out.println("Please enter an option:");
					int foo = scan.nextInt();
					switch (foo) {
					case 1:
						System.out.println("*********	     USER ACCOUNT MANAGEMENT	 *********");
						System.out.println("1. Change password");
						//System.out.println("2. Recover password");
						System.out.println("2. Remove user account");
						System.out.println("3. Return to main menu");
						System.out.println("4. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						int input = scan.nextInt();
						switch (input) {
						case 1:
							System.out.println("*****************************************************");
							System.out.println("Enter new password:");
							scan.nextLine();
							newUserPojo.setUserPassword(scan.nextLine());
							System.out.println("Enter your 4-digit PIN:");
							newUserPojo.setUserPin(scan.nextInt());
							if(newUserPojo.getUserPassword() == "") {
								System.out.println("You must enter a value for all fields. Try again");
								System.exit(a);
							}
							returnedUserPojo = userService.changePassword(newUserPojo);
							System.out.println("New password is: " + returnedUserPojo.getUserPassword());
							System.out.println("You will now be automatically logged out. Please log in again with your new password. Thank you.");
							/*System.out.println("Select 1 to continue your session (You will be taken to the main menu.");
							System.out.println("Select 2 to log out.");
							int wheretogo = scan.nextInt();
							switch (wheretogo) {
							case 1:
								a = 'n';
							case 2:
							*/
							
								userService.logOut(newUserPojo);
								String input2 = scan.nextLine();
								if(input2 != null) {
									System.out.println("You are logged out...Goodbye.");
									
									//System.exit(a);
								}
								System.exit(a);
							
							break;
							/*
						case 2:
							System.out.println("Enter PIN");
							newUserPojo.setUserPin(scan.nextInt());
							System.out.println("enter one account number");
							newAccountPojo.setAccountNumber(scan.nextInt());
							System.out.println("enter Access Code for that account");
							newAccountPojo.setAccessCode(scan.nextInt());
							returnedUserPojo = userService.recoverPassword(newUserPojo, newAccountPojo);
							System.out.println("Your password is: " + returnedUserPojo.getUserPassword());
							System.out.println("4. Return to main menu");
							System.out.println("5. Log out");
							System.out.println("*****************************************************");
							System.out.println("Please enter an option:");
							System.out.println("*****************************************************");
							a = 'n';
							break;
							*/
						case 2:
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
								System.out.println(e.getMessage());
								e.printStackTrace();
							}
							//a = 'n';
							break;
						case 3:
							System.out.println("return to main menu");
							a = 'n';
							break;
						case 4:
							//System.out.println("Log out and close application:");
							//String input = scan.nextLine();
							//if(input != null) {
							UserPojo newUserPojo2 = new UserPojo();
							userService.logOut(newUserPojo2);
							System.out.println("You are logged out...Goodbye.");
							System.exit(a);
							//}
							//a = 'n';
							//break;
						}
						break;
					case 2:
						System.out.println("manage bank accounts");
						System.out.println("*********	     BANK ACCOUNT MANAGEMENT	 *********");
						System.out.println("1. View all accounts");
						System.out.println("2. Open new bank account");
						System.out.println("3. Link to existing account");
						System.out.println("4. Close bank account");
						System.out.println("5. Return to main menu");
						System.out.println("6. Log out");
						System.out.println("*****************************************************");
						int input3 = scan.nextInt();
						switch (input3) {
						case 1:
							List<AccountPojo> everyAccount;
							everyAccount = accountService.getAllAccounts();
							everyAccount.forEach((item) -> System.out.println(item.getAccountNumber() +"\t\t"+ item.getAccountType()+ "\t\t" + item.getAccountBalance() +"\t\t" + item.getAccessCode()));
							System.out.println("");
							System.out.println("");
							a = 'n';
							break;
						case 2:
							 System.out.println("Enter 1 or 2 for new account type:");
							 System.out.println("1. checking");
							 System.out.println("2. savings");
							 //use switch statement to validate choice: 1 = checking, 2 = savings, else=not a valid choice, choose again
							 int choose = scan.nextInt();
							 switch(choose) {
							 case 1:
								 newAccountPojo.setAccountType("checking");
								 break;
							 case 2:
								 newAccountPojo.setAccountType("savings");
								 break;
							 }
							 System.out.println("Enter new acount access code:");
							 newAccountPojo.setAccessCode(scan.nextInt()); 
							 boolean creation = accountService.addAccount(newAccountPojo);
							 if(creation == true) {
								 System.out.println("account creation successful");
							 } else {
								 System.out.println("account creation failed");
							 }
							
							System.out.println("Enter any key to return to main menu.");
							String go = scan.nextLine();
							if (go != "") {
								a = 'n';
							}
							break;
						case 3:
							 System.out.println("Enter account to link to:");
							 newAccountPojo.setAccountNumber(scan.nextInt());
							 scan.nextLine();
							 System.out.println("Enter access code for this account:");
							 newAccountPojo.setAccessCode(scan.nextInt());
							 scan.nextLine();
							 System.out.println("Enter your PIN:");
							 newUserPojo.setUserPin(scan.nextInt());
							 AccountPojo returnedAccPojo = null;
							 try {
								returnedAccPojo = accountService.linkToAccount(newAccountPojo, newUserPojo);
							 } catch (SQLException s) {
									s.printStackTrace();
								} catch (SystemException e) {
									System.out.println(e.getMessage());
								} 
							 System.out.println("You are now linked to " + returnedAccPojo.getAccountType()+" account number: "+returnedAccPojo.getAccessCode());
							a = 'n';
							break;
						case 4:
							System.out.println("Enter PIN");
							newUserPojo.setUserPin(scan.nextInt());
							System.out.println("Enter account number to be closed");
							newAccountPojo.setAccountNumber(scan.nextInt());
							boolean success = false;
							try {
								success = accountService.closeBankAccount(newAccountPojo, newUserPojo);
							} catch (BalanceNotEmptyException b1) {
								System.out.println(b1.getMessage());
							} catch (SQLException s) {
								s.printStackTrace();
							} catch (SystemException e) {
								System.out.println(e.getMessage());
							} finally {
								if (success) {
									System.out.println("success");
								} else {
									System.out.println("fail");
								}
							}
							a = 'n';
							break;
						case 5:
							a = 'n';
							break;
						case 6:
							UserPojo newUserPojo2 = new UserPojo();
							userService.logOut(newUserPojo2);
							System.out.println("You are logged out...Goodbye.");
							System.exit(a);
						}
						break;
					case 3:
						System.out.println("*****************************************************");
						System.out.println("1. Deposit funds");
						System.out.println("2. Withdraw funds");
						System.out.println("3. View account balance");
						System.out.println("4. Transfer funds between accounts");
						System.out.println("5. View transaction history");
						System.out.println("6. Return to main menu");
						System.out.println("7. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						int input4 = scan.nextInt();
						switch (input4) {
						case 1:
							System.out.println("Enter account number");
							newTransactionPojo.setAccountNumber(scan.nextInt());
							System.out.println("Enter transaction amount");
							newTransactionPojo.setTransactionAmount(scan.nextDouble());
							System.out.println("Enter source account number");
							newTransactionPojo.setTargetAccNo(scan.nextInt());
							System.out.println("enter source routing number");
							newTransactionPojo.setTargetRoutNo(scan.nextInt());
							newTransactionPojo.setTime(new Date().toString());
							returnedTransactionPojo = transactionService.makeDeposit(newTransactionPojo);
							a = 'n';
							break;
						case 2:
							System.out.println("Enter account number");
							newTransactionPojo.setAccountNumber(scan.nextInt());
							System.out.println("Enter transaction amount");
							newTransactionPojo.setTransactionAmount(scan.nextDouble());
							System.out.println("Enter source account number");
							newTransactionPojo.setTargetAccNo(scan.nextInt());
							System.out.println("enter source routing number");
							newTransactionPojo.setTargetRoutNo(scan.nextInt());
							newTransactionPojo.setTime(new Date().toString());
							try {
								returnedTransactionPojo = transactionService.makeWithdrawal(newTransactionPojo);
							} catch (BalanceBelowZeroException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							a = 'n';
							break;
						case 3:
							System.out.println("Enter account number");
							newTransactionPojo.setAccountNumber(scan.nextInt());
							returnedTransactionPojo = transactionService.viewBalance(newTransactionPojo);
							System.out.println(returnedTransactionPojo.getUpdatedBalance());
							a = 'n';
							break;
						case 4:
							System.out.println("Enter transaction amount");
							newTransactionPojo.setTransactionAmount(scan.nextDouble());
							System.out.println("Enter account number of account you are transfering funds from:");
							newTransactionPojo.setTargetAccNo(scan.nextInt());
							System.out.println("enter account number of account you are transfering funds to:");
							newTransactionPojo.setTargetRoutNo(scan.nextInt());
							newTransactionPojo.setTime(new Date().toString());
							try {
								returnedTransactionPojo = transactionService.transferFunds(newTransactionPojo);
							} catch (OnlyOneAccountException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SystemException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (BalanceBelowZeroException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Funds transfered.");
							a = 'n';
							break;
						case 5:
							System.out.println("Enter account number that you would like to see transactions for");
							newTransactionPojo.setAccountNumber(scan.nextInt());
							List<TransactionPojo> resultList = null;
							try {
								resultList = transactionService.getTransactionsForOneAccNo(newTransactionPojo);
							} catch (EmptyListException e) {
								System.out.println(e.getMessage());
							} catch (SystemException e) {
								System.out.println(e.getMessage());
							}
							System.out.println("***************************************************************************************");
							System.out.println("TRNSNO\tACCNO\tTYPE\t\tTRNSAMT\tUPDATEDBAL\tTIME\tTARGETACCNO\tTARGETROUTNO");
							System.out.println("***************************************************************************************");
							resultList.forEach((item) -> System.out.println(item.getTransactionNumber() + "\t" + item.getAccountNumber() + "\t" + item.getTransactionType() + "\t" + item.getTransactionAmount()+ "\t" + item.getUpdatedBalance()+ "\t" + item.getTime()+ "\t" + item.getTargetAccNo()+ "\t" + item.getTargetRoutNo()));
							a = 'n';
							break;
						case 6:
							System.out.println("return to main menu");
							a = 'n';
							break;
						case 7:
							System.out.println("log out");
							a = 'n';
							break;
						}
						break;
					case 4:
						UserPojo newUserPojo2 = new UserPojo();
						userService.logOut(newUserPojo2);
						System.out.println("You are logged out...Goodbye.");
						System.exit(a);
						/*
						System.out.println("user account menu");
						System.out.println("Enter 1 to return to main menu ");
						int input4 = scan.nextInt();
						switch (input4) {
						case 1:
							System.out.println("change password");
							a = 'n';
							break;
						case 2:
							System.out.println("recover password");
							a = 'n';
							break;
						case 3:
							System.out.println("remove user account");
							a = 'n';
							break;
						case 4:
							System.out.println("return to main menu");
							a = 'n';
							break;
						case 5:
							System.out.println("log out");
							a = 'n';
							break;
						}
						break;
					}
					break;*/
					}
						break;
				case 2:
					System.out.println("two");
					b = 'n';
					break;
				}
			}
		}
	}



/*
		
		
			Scanner scan = new Scanner(System.in);
			char a = 'y';
			while (a == 'y') {
			System.out.println("*****************************************************");
			String line =
			"######                       ######\n"+               
			"#     #   ##   #    # #    # #     #  ####  #####\n"+ 
			"######  #    # # #  # ####   ######  #    #   #\n"+   
			"#     # ###### #  # # #  #   #     # #    #   #\n"+   
			"#     # #    # #   ## #   #  #     # #    #   #\n"+   
			"######  #    # #    # #    # ######   ####    #\n";
			System.out.println();
			System.out.println(line);
			System.out.println("ONLINE BANK ACCOUNT MANAGEMENT SYSTEM FROM LandisWare");;
			System.out.println();
			System.out.println("*****************************************************");
			System.out.println("1. Log in");
			System.out.println("2. Sign up");
			System.out.println("*****************************************************");
			System.out.println("Please enter an option:");
			int option = scan.nextInt();
			char b = 'y';
			while (b == 'y') {
			switch(option) {
			case 1:
				System.out.println("Enter user name:");
				scan.nextLine();
				newUserPojo.setUserName(scan.nextLine());
				System.out.println("Enter password:");
				newUserPojo.setUserPassword(scan.nextLine());
				try {
					returnedUserPojo = userService.logIn(newUserPojo);
					System.out.println("");
					System.out.println("Welcome back, "+returnedUserPojo.getUserFirstName()+"!");
				} catch (NoAccountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				
				System.out.println("");
				System.out.println("****************     MAIN MENU     ******************");
				System.out.println("1. Manage user account");
				System.out.println("2. Manage bank accounts");
				System.out.println("3. Manage funds");
				System.out.println("4. Log out");
				System.out.println("*****************************************************");
				System.out.println("Please enter an option:");
				int option2 = scan.nextInt();
				switch (option2) {
				case 1:
					// b = 'y';
					// break;
					System.out.println("*********	     USER ACCOUNT MANAGEMENT	 *********");
					System.out.println("1. Change password");
					System.out.println("2. Recover password");
					System.out.println("3. Remove user account");
					System.out.println("4. Return to main menu");
					System.out.println("5. Log out");
					System.out.println("*****************************************************");
					System.out.println("Please enter an option:");
					int option3 = scan.nextInt();
					char d = 'y';
					while (d == 'y') {
					switch (option3) {
					case 1:
						System.out.println("*****************************************************");
						System.out.println("Enter a new password:");
						scan.nextLine();
						newUserPojo.setUserPassword(scan.nextLine());
						System.out.println("Enter your 4-digit PIN:");
						newUserPojo.setUserPin(scan.nextInt());
						returnedUserPojo = userService.changePassword(newUserPojo);
						System.out.println("Your new password is: " + returnedUserPojo.getUserPassword());
						System.out.println("");
						System.out.println("Your options are:");
						System.out.println("Press 1 to continue your session.");
						System.out.println("Press 2 to log out.");
						
						int wheretogo = scan.nextInt();
						switch (wheretogo) {
						case 1:
							b = 'n';
						case 2:
							userService.logOut(newUserPojo);
						}
						scan.nextLine();
						String mainmenu = scan.nextLine();
						if (mainmenu != null) {
							b = 'n';
						}
						break;
					case 2:
						System.out.println("recover password");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						System.out.println("*****************************************************");
						break;
					case 3:
						//c = 'y';
						System.out.println("remove user account");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						System.out.println("*****************************************************");
						break;
					case 4:
						System.out.println("yes");
						b = 'n';
						break;
					case 5:
						//c = 'y';
						System.out.println("log out");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					}
					break;
					}
				case 2:
					System.out.println("manage bank accounts");
					System.out.println("*********	     USER ACCOUNT MANAGEMENT	 *********");
					System.out.println("1. View all accounts");
					System.out.println("2. Open new bank account");
					System.out.println("3. Link to existing account");
					System.out.println("4. Return to main menu");
					System.out.println("5. Log out");
					System.out.println("*****************************************************");
					
					int option4 = scan.nextInt();
					switch (option4) {
					case 1:
						b = 'y';
						System.out.println("view all accounts");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 2:
						b = 'y';
						System.out.println("open new bank account");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 3:
						b = 'y';
						System.out.println("link to existing account");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 4:
						b = 'n';
						System.out.println("return to main menu");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 5:
						b = 'y';
						System.out.println("log out");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					}
					b = 'y';
					break;
				case 3:
					System.out.println("*****************************************************");
					System.out.println("1. Deposit funds");
					System.out.println("2. Withdraw funds");
					System.out.println("3. View account balance");
					System.out.println("4. Transfer funds between accounts");
					System.out.println("5. View transaction history");
					System.out.println("6. Return to main menu");
					System.out.println("7. Log out");
					System.out.println("*****************************************************");
					System.out.println("Please enter an option:");
					int option5 = scan.nextInt();
					switch (option5) {
					case 1:
						System.out.println("deposit funds");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 2:
						System.out.println("withdraw funds");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 3:
						System.out.println("view account balance");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 4:
						
						System.out.println("transfer funds between accounts");
						System.out.println("*****************************************************");
						System.out.println("4. Return to main menu");
						System.out.println("5. Log out");
						System.out.println("*****************************************************");
						System.out.println("Please enter an option:");
						
						break;
					case 5:
						System.out.println("view transaction history");
						break;
					case 6:
						System.out.println("return to main menu");
						break;
					case 7:
						System.out.println("log out");
						break;
					}
					b = 'y';
					break;
				case 4:
					System.out.println("ma");
					b = 'n';
					break;
				}
				break;
				}
			/*
			case 2:
				System.out.println("sign up");
				b = 'n';
				break;
				*/
	/*
			}
		}
	}
}
*/
		
	/*
		UserPojo newUserPojo = new UserPojo();
		UserPojo returnedUserPojo = null;
		AccountPojo newAccountPojo = new AccountPojo(); 

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
		/*
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
			userService.logOut(newUserPojo);
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
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		// ACCOUNT METHODS
		
		 AccountService accountService = new AccountServiceImpl();
		  
		 AccountPojo returnedAccountPojo = null;
		 
		 //create new bankaccount
		 /*
		 System.out.println("Enter new account type(checking or savings)");
		 //use switch statement to validate choice: 1 = checking, 2 = savings, else=not a valid choice, choose again
		 newAccountPojo.setAccountType(scan.nextLine());
		 System.out.println("Enter new acount access code");
		 newAccountPojo.setAccessCode(scan.nextInt()); 
		 boolean creation = accountService.addAccount(newAccountPojo);
		 System.out.println("Acount creation was: " + creation);
		*/
		
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
		 
		// close bank account
		 /*
		System.out.println("Enter PIN");
		newUserPojo.setUserPin(scan.nextInt());
		System.out.println("Enter account number to be closed");
		newAccountPojo.setAccountNumber(scan.nextInt());
		boolean success = false;
		try {
			success = accountService.closeBankAccount(newAccountPojo, newUserPojo);
		} catch (BalanceNotEmptyException b) {
			System.out.println(b.getMessage());
		} catch (SQLException s) {
			s.printStackTrace();
		} catch (SystemException e) {
			System.out.println(e.getMessage());
		} finally {
			if (success) {
				System.out.println("success");
			} else {
				System.out.println("fail");
			}
		}
		*/
		
		//link to existing account
		 /*
		 System.out.println("Enter account to link to");
		 newAccountPojo.setAccountNumber(scan.nextInt());
		 scan.nextLine();
		 System.out.println("Enter access code");
		 newAccountPojo.setAccessCode(scan.nextInt());
		 scan.nextLine();
		 System.out.println("Enter your PIN");
		 newUserPojo.setUserPin(scan.nextInt());
		 AccountPojo returnedAccPojo = null;
		 try {
			returnedAccPojo = accountService.linkToAccount(newAccountPojo, newUserPojo);
		 } catch (SQLException s) {
				s.printStackTrace();
			} catch (SystemException e) {
				System.out.println(e.getMessage());
			} 
		 System.out.println("You are now linked to " + returnedAccPojo.getAccountType()+" account number: "+returnedAccPojo.getAccessCode());
		*/
		 
		// ACCOUNTUSERS METHODS
		/*
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
		/*
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
		
		//make deposit (user)
		/*
		System.out.println("Enter account number");
		newTransactionPojo.setAccountNumber(scan.nextInt());
		System.out.println("Enter transaction amount");
		newTransactionPojo.setTransactionAmount(scan.nextDouble());
		System.out.println("Enter source account number");
		newTransactionPojo.setTargetAccNo(scan.nextInt());
		System.out.println("enter source routing number");
		newTransactionPojo.setTargetRoutNo(scan.nextInt());
		newTransactionPojo.setTime(new Date().toString());
		returnedTransactionPojo = transactionService.makeDeposit(newTransactionPojo);
		*/
		
		/*
		//make withdrawal (user)
		System.out.println("Enter account number");
		newTransactionPojo.setAccountNumber(scan.nextInt());
		System.out.println("Enter transaction amount");
		newTransactionPojo.setTransactionAmount(scan.nextDouble());
		System.out.println("Enter source account number");
		newTransactionPojo.setTargetAccNo(scan.nextInt());
		System.out.println("enter source routing number");
		newTransactionPojo.setTargetRoutNo(scan.nextInt());
		newTransactionPojo.setTime(new Date().toString());
		try {
			returnedTransactionPojo = transactionService.makeWithdrawal(newTransactionPojo);
		} catch (BalanceBelowZeroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//view balance (user)
		/*
		System.out.println("Enter account number");
		newTransactionPojo.setAccountNumber(scan.nextInt());
		returnedTransactionPojo = transactionService.viewBalance(newTransactionPojo);
		System.out.println(returnedTransactionPojo.getUpdatedBalance());
		*/
		
		//transfer funds (user)
		/*
		System.out.println("Enter transaction amount");
		newTransactionPojo.setTransactionAmount(scan.nextDouble());
		System.out.println("Enter account number of account you are transfering funds from:");
		newTransactionPojo.setTargetAccNo(scan.nextInt());
		System.out.println("enter account number of account you are transfering funds to:");
		newTransactionPojo.setTargetRoutNo(scan.nextInt());
		newTransactionPojo.setTime(new Date().toString());
		try {
			returnedTransactionPojo = transactionService.transferFunds(newTransactionPojo);
		} catch (OnlyOneAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BalanceBelowZeroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Funds transfered.");
		*/
		
		//view transaction history
		/*
		System.out.println("Enter account number that you would like to see transactions for");
		newTransactionPojo.setAccountNumber(scan.nextInt());
		List<TransactionPojo> resultList = null;
		try {
			resultList = transactionService.getTransactionsForOneAccNo(newTransactionPojo);
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		} catch (SystemException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("***************************************************************************************");
		System.out.println("ID\tNAME\tDESCRIPTION\t\t\tCOST");
		System.out.println("***************************************************************************************");
		resultList.forEach((item) -> System.out.println(item.getTransactionNumber() + "\t" + item.getAccountNumber() + "\t" + item.getTransactionType() + "\t" + item.getTransactionAmount()+ "\t" + item.getUpdatedBalance()+ "\t" + item.getTime()+ "\t" + item.getTargetAccNo()+ "\t" + item.getTargetRoutNo()));
		*/
	
		
		// SESSION METHODS
		/*
		SessionService sessionService = new SessionServiceImpl();
		
		SessionPojo newSessionPojo = new SessionPojo(); 
		SessionPojo returnedSessionPojo = null;
		/*
		System.out.println("Enter new userId");
		newSessionPojo.setUserId(scan.nextInt());
		System.out.println("Enter new login time"); 
		
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


