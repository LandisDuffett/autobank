package exception;

public class OnlyOneAccountException extends Exception{

	@Override
	public String getMessage() {
		return "Must have more than one account to transfer money.";
	}
	

}