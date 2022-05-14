package exception;

public class SystemException extends Exception {

	@Override
	public String getMessage() {
		return "There was an internal error! Please try again later!";
	}

}