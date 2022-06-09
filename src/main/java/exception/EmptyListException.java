package exception;

public class EmptyListException extends Exception{

	@Override
	public String getMessage() {
		return "No items in list. Remember, you can only view transactions for your own account. Check to make sure you entered"
				+ " the correct account number.";
	}
	

}
