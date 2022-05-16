package exception;

public class BalanceBelowZeroException extends Exception{

	@Override
	public String getMessage() {
		return "Balance cannot fall below 0. Withdrawal blocked.";
	}
	

}
