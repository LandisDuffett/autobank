package exception;

public class NoAccountException extends Exception{

	@Override
	public String getMessage() {
		return "User name or password is incorrect or you do not have an account. Press any key to return to main menu.";
	}
	

}