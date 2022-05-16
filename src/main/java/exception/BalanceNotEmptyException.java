package exception;

public class BalanceNotEmptyException extends Exception{

	@Override
	public String getMessage() {
		return "Balance must be 0 in order to close an account.";
	}
	

}
