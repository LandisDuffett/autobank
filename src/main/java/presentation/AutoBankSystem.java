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
				
				System.out.println("3. Forgot password");
				
				System.out.println("*****************************************************");
				
				System.out.println("Please enter an option:");
				
				opt = scan.nextInt();
			
			}
			
			} catch (Exception e) {
				
				System.out.println("invalid input: input must be 1, 2, or 3. Try again.");
				
				System.exit(a);
				
			}
			
			switch (opt) {
			
			case 1:
				
				try {
					
					System.out.println("");
					
					System.out.println("");
					
					System.out.println("********************LOGIN PAGE*******************");
					
					System.out.println("Enter user name:");
					
					scan.nextLine();
					
					newUserPojo.setUserName(scan.nextLine());
					
					System.out.println("Enter password:");
					
					newUserPojo.setUserPassword(scan.nextLine());
					
					returnedUserPojo = userService.logIn(newUserPojo);
					
					System.out.println("");
					
					System.out.println("Welcome to Bankbot, " + returnedUserPojo.getUserFirstName() + "!");
					
				} catch (NoAccountException e) {
					
					System.out.println(e.getMessage());
					
					System.exit(a);
					
				} catch (SystemException e) {
					
					System.out.println(e.getMessage());
					
					System.exit(a);
					
				}
				
				break;
				
			case 2:
				
				try {
					
					System.out.println("Enter new user name:");
					
					scan.nextLine();
					
					newUserPojo.setUserName(scan.nextLine());
					
					System.out.println("Choose your password:");
					
					newUserPojo.setUserPassword(scan.nextLine());
					
					System.out.println("Enter your first name:");
					
					newUserPojo.setUserFirstName(scan.nextLine());
					
					System.out.println("Enter your last name:");
					
					newUserPojo.setUserLastName(scan.nextLine());
					
					System.out.println("Choose a four-digit PIN:"); 
					
					newUserPojo.setUserPin(scan.nextInt());
					
					if(newUserPojo.getUserName() == "" || newUserPojo.getUserPassword() == "" || newUserPojo.getUserFirstName() == "" || newUserPojo.getUserLastName() == "") {
						
						System.out.println("You must enter a value for all fields. Try again");
						
						System.exit(a);
						
					}
					
					returnedUserPojo = userService.addUser(newUserPojo);
					
					System.out.println("");
					
					System.out.println("Welcome to Bankbot, " + returnedUserPojo.getUserFirstName());
					
					System.out.println("");
					
					System.out.println("");
					
					break;
					
				} catch (SQLException s) {
					
					System.out.println("Make sure your PIN is exactly four digits. Someone else might also already have that user name. "
							+ "User name and password should each be fewer than 20 characters. Try again.");
					
					System.exit(a);
					
				} catch (SystemException e) {
					
					System.out.println(e.getMessage());
					
				}
				
				break;
				
			case 3:
				scan.nextLine();
				
				System.out.println("Enter your user name:");
				
				newUserPojo.setUserName(scan.nextLine());
				
				System.out.println("Enter your 4-digit PIN:");
				
				newUserPojo.setUserPin(scan.nextInt());
				
				System.out.println("Enter the account number for one of your accounts:");
				
				System.out.println("(If you do not currently have any accounts, you will need to visit a bank branch to change your password.)");
				
				newAccountPojo.setAccountNumber(scan.nextInt());
				
				System.out.println("enter Access Code for that account");
				
				newAccountPojo.setAccessCode(scan.nextInt());
				
				returnedUserPojo = userService.recoverPassword(newUserPojo, newAccountPojo);
				
				if (returnedUserPojo.getUserPassword() != null) {
					
					System.out.println("Your password is: " + returnedUserPojo.getUserPassword());

				} else {
					
					System.out.println("No password matches the data you entered. Check your input data and try again.");
					System.exit(a);
				}
				
				opt = 0;
				
						try {
							
							System.out.println("");
							
							System.out.println("");
							
							System.out.println("********************LOGIN PAGE*******************");
							
							System.out.println("Enter user name:");
							
							scan.nextLine();
							
							newUserPojo.setUserName(scan.nextLine());
							
							System.out.println("Enter password:");
							
							newUserPojo.setUserPassword(scan.nextLine());
							
							returnedUserPojo = userService.logIn(newUserPojo);
							
							System.out.println("");
							
							System.out.println("Welcome to Bankbot, " + returnedUserPojo.getUserFirstName() + "!");
							
						} catch (NoAccountException e) {
							
							System.out.println(e.getMessage());
							
							System.exit(a);
							
						} catch (SystemException e) {
							
							System.out.println(e.getMessage());
							
							System.exit(a);
						}
					
						}
			
						break;
						
					}	
		
			System.out.println("Your options:");
			
			System.out.println("*****************************************************");
			
			System.out.println("1. Main menu");
			
			System.out.println("2. Log out");
			
			System.out.println("*****************************************************");
			
			System.out.println("Please enter an option:");
			
			a = 'n';
			
			int option = 0;
			
			try {
				
			option = scan.nextInt();
			
			}  catch (Exception e) {
				
				System.out.println("invalid input: input must be 1, 2, or 3. Try again.");
				
				System.exit(a);
				
			}
			
			char b = 'y';
			
			while (b == 'y') {
				
				switch (option) {
				
				case 1:
					
					System.out.println("****************     MAIN MENU     ******************");
					
					System.out.println("1. Manage user account");
					
					System.out.println("2. Manage bank accounts");
					
					System.out.println("3. Manage funds");
					
					System.out.println("4. Log out");
					
					System.out.println("*****************************************************");
					
					System.out.println("Please enter an option:");
					
					int entry = 0;
					
					try {
					
					entry = scan.nextInt();
					
					}  catch (Exception e) {
						
						System.out.println("invalid input: input must be 1, 2, 3, or 4. Try again.");
						
						System.exit(a);
						
					}
					
					switch (entry) {
					
					case 1:
						
						System.out.println("***************USER ACCOUNT MANAGEMENT***************");
						
						System.out.println("1. Change password");
						
						System.out.println("2. Remove user account");
						
						System.out.println("3. Return to main menu");
						
						System.out.println("4. Log out");
						
						System.out.println("*****************************************************");
						
						System.out.println("Please enter an option:");
						
						int input = 0;
						
						try {
						
						input = scan.nextInt();
						
						}  catch (Exception e) {
							
							System.out.println("invalid input: input must be 1, 2, 3, or 4. Try again.");
							
							System.exit(a);
							
						}
						
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
							
								userService.logOut(newUserPojo);
								
								String input2 = scan.nextLine();
								
								if(input2 != null) {
									
									System.out.println("");
									
									System.out.println("You are logged out...Goodbye.");
									
								}
								
								System.exit(a);
							
							break;
							
						case 2:
							
							System.out.println("This action will delete your electronic account only and will not affect your bank account(s). Enter PIN");
							
							newUserPojo.setUserPin(scan.nextInt());
							
							try {
								
								boolean success = userService.removeUserAccount(newUserPojo);
								
								if(success) {
									
									System.out.println("user account deleted");
									
									System.out.println("You are logged out...Goodbye.");
									
									System.exit(a);
									
								} else {
									
									System.out.println("Failed to remove user account. Try again.");
									
								}
								
							} catch (SQLException e) {
								
								e.printStackTrace();
								
							}
							
						case 3:
							
							//System.out.println("return to main menu");
							
							a = 'n';
							
							break;
							
						case 4:
							
							UserPojo newUserPojo2 = new UserPojo();
							
							userService.logOut(newUserPojo2);
							
							System.out.println("You are logged out...Goodbye.");
							
							System.exit(a);
							
						}
						
						break;
						
					case 2:
												
						System.out.println("*********	     BANK ACCOUNT MANAGEMENT	 *********");
						
						System.out.println("1. View all accounts");
						
						System.out.println("2. Open new bank account");
						
						System.out.println("3. Link to existing account");
						
						System.out.println("4. Close bank account");
						
						System.out.println("5. Return to main menu");
						
						System.out.println("6. Log out");
						
						System.out.println("*****************************************************");
						
						int input3 = 0;
						
						try {
							
						input3 = scan.nextInt();
						
						}  catch (Exception e) {
							
							System.out.println("invalid input: input must be 1, 2, 3, 4, 5, or 6. Try again.");
							
							System.exit(a);
							
						}
						
						switch (input3) {
						
						case 1:
							
							List<AccountPojo> everyAccount;
							
							System.out.println("account no.     account type    balance");
							
							System.out.println("___________     _____________   ________");
							
							everyAccount = accountService.getAllAccounts();
							
							everyAccount.forEach((item) -> System.out.println(item.getAccountNumber() +"\t\t"+ item.getAccountType()+ "\t\t" + item.getAccountBalance()));
							
							System.out.println("");
							
							System.out.println("");
							
							a = 'n';
							
							break;
							
						case 2:
							
							 System.out.println("Enter 1 or 2 for new account type:");
							 
							 System.out.println("1. checking");
							 
							 System.out.println("2. savings");
							 
							 int choose = 0;
							 
							 try {
							 
							 choose = scan.nextInt();
							 
							 }  catch (Exception e) {
									
									System.out.println("invalid input: input must be 1 or 2. Try again.");
									
									System.exit(a);
									
								}
							 
							 switch(choose) {
							 
							 case 1:
								 
								 newAccountPojo.setAccountType("checking");
								 
								 break;
								 
							 case 2:
								 
								 newAccountPojo.setAccountType("savings");
								 
								 break;
								 
							 }
							 
							 System.out.println("Enter new acount access code:");
							 
							 try {
							 newAccountPojo.setAccessCode(scan.nextInt()); 
							 
							 boolean creation = accountService.addAccount(newAccountPojo);
							 
							 if(creation == true) {
								 
								 System.out.println("account creation successful");
								 
							 } else {
								 
								 System.out.println("account creation failed");
								 
							 }}  catch (Exception e) {
									
									System.out.println("Accont creation failed. Make sure your access code is 4 digits long and consists only of numbers. Try again.");
									
									System.exit(a);
									
								}
							
							System.out.println("Enter any key to return to main menu.");
							
							String go = scan.nextLine();
							
							if (go != "") {
								
								a = 'n';
								
							}
							
							break;
							
						case 3:
							
							 AccountPojo returnedAccPojo = new AccountPojo();

							 try {
							
							 System.out.println("You may add yourself as an owner of any account for which you have the proper credentials.");
							 
							 System.out.println("You must have the account number and access code for the account you are linking to as well as your own PIN.");
							
							 System.out.println("Enter account number to link to:");
							 
							 newAccountPojo.setAccountNumber(scan.nextInt());
							 
							 scan.nextLine();
							 
							 System.out.println("Enter access code for the account:");
							 
							 newAccountPojo.setAccessCode(scan.nextInt());
							 
							 scan.nextLine();
							 
							 System.out.println("Enter your PIN:");
							 
							 newUserPojo.setUserPin(scan.nextInt());
							 	 
							returnedAccPojo = accountService.linkToAccount(newAccountPojo, newUserPojo);
								
							 } catch (SQLException s) {
								 
									System.out.println(s.getMessage());;
									
								}  catch (SystemException e) {
									
									System.out.println(e.getMessage());
									
								}
							 
							 int result = returnedAccPojo.getAccountNumber();
							 
							 if (result > 0) {
							 
							 System.out.println("You are now linked to " + returnedAccPojo.getAccountType()+" account number: "+returnedAccPojo.getAccountNumber());
							 
							 } else {
								 
								 System.out.println("Something went wrong. Check the data you entered.");
								 
							 }
							 
							a = 'n';
							
							break;
							
						case 4:
							
							System.out.println("This action will close any bank account to which you are linked.");
							
							System.out.println("Account balance must be 0.00 in order to close the account.");
							
							System.out.println("Enter PIN:");
							
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
						
						System.out.println("****************FUND MANAGEMENT***********************");
						
						System.out.println("1. Deposit funds");
						
						System.out.println("2. Withdraw funds");
						
						System.out.println("3. View account balance");
						
						System.out.println("4. Transfer funds between accounts");
						
						System.out.println("5. View transaction history");
						
						System.out.println("6. Return to main menu");
						
						System.out.println("7. Log out");
						
						System.out.println("*****************************************************");
						
						System.out.println("Please enter an option:");
						
						int input4 = 0;
						
						try {
						
						input4 = scan.nextInt();
						
						}  catch (Exception e) {
							
							System.out.println("invalid input: input must be 1, 2, 3, 4, 5, 6, or 7. Try again.");
							
							System.exit(a);
							
						}
						
						switch (input4) {
						
						case 1:
							
							try {
							
							System.out.println("You may deposit funds to any account at this bank.");
							
							System.out.println("For the source of the funds, you may enter the routing number and account number for an account at this bank or any bank.");
							
							System.out.println("Account balance of receiver of funds will be updated immediately but routing and account number of");
							
							System.out.println("depositor account mut be verified before funds are fully available.");
							
							System.out.println("Enter account number to deposit to");
							
							newTransactionPojo.setAccountNumber(scan.nextInt());
							
							System.out.println("Enter transaction amount");
							
							newTransactionPojo.setTransactionAmount(scan.nextDouble());
							
							System.out.println("Enter source account number");
							
							newTransactionPojo.setTargetAccNo(scan.nextInt());
							
							System.out.println("enter source routing number");
							
							newTransactionPojo.setTargetRoutNo(scan.nextInt());
							
							newTransactionPojo.setTime(new Date().toString());
							
							returnedTransactionPojo = transactionService.makeDeposit(newTransactionPojo);
							
							} catch (Exception e) {
								
								System.out.println("invalid input: try again");
								
								System.exit(a);
								
							}
							
							a = 'n';
							
							break;
							
						case 2:
							
							try {
							
							System.out.println("You may only withdraw funds from an account you are linked to.");
								
							System.out.println("You may enter the routing number and account number for an account at this bank or any bank");
							
							System.out.println("for the party that is to receive the funds you are withdrawing.");
								
							System.out.println("Your account balance will be updated immediately but routing and account number of");
								
							System.out.println("receiver account mut be verified before funds are fully available to that account.");
							
							System.out.println("Enter your account number");
							
							newTransactionPojo.setAccountNumber(scan.nextInt());
							
							System.out.println("Enter transaction amount");
							
							newTransactionPojo.setTransactionAmount(scan.nextDouble());
							
							System.out.println("Enter source account number");
							
							newTransactionPojo.setTargetAccNo(scan.nextInt());
							
							System.out.println("enter source routing number");
							
							newTransactionPojo.setTargetRoutNo(scan.nextInt());
							
							newTransactionPojo.setTime(new Date().toString());
								
							returnedTransactionPojo = transactionService.makeWithdrawal(newTransactionPojo);
								
							} catch (Exception e) {
								
								System.out.println("invalid input or illegitimate transaction attempt: try again");
								
								System.exit(a);
								
							}
							
							a = 'n';
							
							break;
							
						case 3:
							
							System.out.println("Enter account number");
							
							newTransactionPojo.setAccountNumber(scan.nextInt());
							
							returnedTransactionPojo = transactionService.viewBalance(newTransactionPojo);
							
							if (returnedTransactionPojo.getTransactionNumber() != 0) {
								
								System.out.println(returnedTransactionPojo.getUpdatedBalance());

							} else {
								System.out.println("Failed to retrieve information. Check your input.");
							}
							
																					
							a = 'n';
							
							break;
							
						case 4:
							
							try {
								
							System.out.println("You may transfer funds between any accounts to which you are linked.");
							
							System.out.println("Enter transaction amount");
							
							newTransactionPojo.setTransactionAmount(scan.nextDouble());
							
							System.out.println("Enter account number of account you are transfering funds from:");
							
							newTransactionPojo.setTargetAccNo(scan.nextInt());
							
							System.out.println("enter account number of account you are transfering funds to:");
							
							newTransactionPojo.setTargetRoutNo(scan.nextInt());
							
							newTransactionPojo.setTime(new Date().toString());
								
							returnedTransactionPojo = transactionService.transferFunds(newTransactionPojo);
								
							} catch (Exception e) {
								
								System.out.println("invalid input or illegitimate transaction attempt: try again");
								
								System.exit(a);
								
							}
							
							System.out.println("Funds transfered.");
							
							a = 'n';
							
							break;
							
						case 5:
							
							List<TransactionPojo> resultList = null;
							
							try {
							
							System.out.println("Enter account number that you would like to see transactions for");
							
							newTransactionPojo.setAccountNumber(scan.nextInt());
							
							resultList = transactionService.getTransactionsForOneAccNo(newTransactionPojo);
								
							} catch (Exception e) {
								
								System.out.println("invalid input or illegitimate transaction attempt: try again");
								
								System.exit(a);
								
							}
							
							System.out.println("***************************************************************************************");
							
							System.out.println("TRNSNO\tACCNO\tTYPE\t\tTRNSAMT\tUPDATEDBAL\tTIME\tTARGETACCNO\tTARGETROUTNO");
							
							System.out.println("***************************************************************************************");
							
							resultList.forEach((item) -> System.out.println(item.getTransactionNumber() + "\t" + item.getAccountNumber() + "\t" + item.getTransactionType() + "\t" + item.getTransactionAmount()+ "\t" + item.getUpdatedBalance()+ "\t" + item.getTime()+ "\t" + item.getTargetAccNo()+ "\t" + item.getTargetRoutNo()));
							
							a = 'n';
							
							break;
							
						case 6:
							
							//System.out.println("return to main menu");
							
							a = 'n';
							
							break;
							
						case 7:
							
							UserPojo newUserPojo2 = new UserPojo();
							
							userService.logOut(newUserPojo2);
							
							System.out.println("You are logged out...Goodbye.");
							
							System.exit(a);
							
							a = 'n';
							
							break;
							
						}
						
						break;
						
					case 4:
						UserPojo newUserPojo2 = new UserPojo();
						
						userService.logOut(newUserPojo2);
						
						System.out.println("You are logged out...Goodbye.");
						
						System.exit(a);
						
					}
					
						break;
						
				case 2:
					
					UserPojo newUserPojo2 = new UserPojo();
					
					userService.logOut(newUserPojo2);
					
					System.out.println("You are logged out...Goodbye.");
					
					System.exit(a);
					
					//b = 'n';
					
					break;
				}
			}
		}
	}


	


