package exception;

public class PSQLException extends Exception {

	
	@Override
	public String getMessage() {
		return "Something went wrong! Double check the data you entered or try again later.";
	}
	
}
