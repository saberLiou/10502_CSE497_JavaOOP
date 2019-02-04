import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A class to receive input from user keypad.
 * @author saberLiou
 */
public class Keypad {
	private BufferedReader br;
	
	/**
	 * Constructs a Keypad.
	 */
	public Keypad (){
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	/**
	 * Gets the number from user input.
	 * @return the input number, -1 if user enter negative or non-integer input
	 */
	public int getInput(){
		int input = -1;
		try{
			input = Integer.parseInt(br.readLine());
			if (input < 0){
				throw new NumberFormatException();
			}
		} catch (IOException e) {
			System.out.println("System IO Error: " + e);
		} catch(NumberFormatException e){
			input = -1;
		}
		return input;
	}
}