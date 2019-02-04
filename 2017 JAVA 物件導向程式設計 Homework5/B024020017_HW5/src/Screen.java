/**
 * A class to print the message.
 * @author saberLiou
 */
public class Screen {
	
	/**
	 * Displays a message without a carriage return.
	 * @param message the returned message
	 */
	public void displayMessage(String message) {
        System.out.print(message);
	}
	
	/**
	 * Displays a message with a carriage return. 
	 * @param message the returned message
	 */
	public void displayMessageLine(String message) {
		System.out.println(message);
	}
}