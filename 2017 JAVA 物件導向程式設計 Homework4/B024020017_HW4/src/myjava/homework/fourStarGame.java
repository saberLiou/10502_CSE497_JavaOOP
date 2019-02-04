package myjava.homework;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Two integral lists, userNums with 4 numbers from user's input, 
 * winNums consist of 4 randomly generating lucky numbers in range of 0 to 9.
 * @author saberLiou
 */
public class fourStarGame {
	private ArrayList<Integer> userNums;
	private ArrayList<Integer> winNums;
	
	/**
	 * Constructs a fourStarGame.
	 */
	public fourStarGame(){
		userNums = new ArrayList<Integer>();
		winNums = new ArrayList<Integer>();
	}
	
	/**
	 * Stores 4 numbers from user input to userNums.
	 */
	protected void generateUserNums(){
		System.out.print("\nInput four user numbers: ");
		Scanner scan = new Scanner(System.in);
		/* Split the input string into units with one or more spaces. */
		String[] temp = scan.nextLine().split(" +");
		
		if (temp.length <= 4){
			for (String s : temp){
				/* If s isn't a digit, stop adding userNums. */
				if (!s.matches("^-?\\d+$")){
					break;
				}
				else{
					/* Integer.valueOf(s) creates a new Integer object for s,
					 * so I use Integer.parseInt(s). 
					 */
					userNums.add(Integer.parseInt(s));
				}
			}
		}
	}
	
	/**
	 * Generates 4 non-duplicate and ranged in 0 to 9 numbers randomly,
	 * and stores them to winNums.
	 */
	protected void generateWinNums(){
		System.out.print("Win numbers: ");
		do {
			int randomNumber = (int) (Math.random() * 10);
			/* Avoid duplication. */
			if (!winNums.contains(randomNumber)){
				winNums.add(randomNumber);
				System.out.print(randomNumber + " ");
			}
		} while(winNums.size() < 4);
	}
	
	/**
	 * Gets the user input numbers safely.
	 * @return the user input numbers
	 */
	protected ArrayList<Integer> getUserNums(){
		ArrayList<Integer> clone = new ArrayList<Integer>();
		for (int n : userNums){
			clone.add(n);
		}
		return clone;
	}
	
	/**
	 * Gets the four lucky numbers safely.
	 * @return the four lucky numbers
	 */
	protected ArrayList<Integer> getWinNums(){
		ArrayList<Integer> clone = new ArrayList<Integer>();
		for (int n : winNums){
			clone.add(n);
		}
		return clone;
	}
}