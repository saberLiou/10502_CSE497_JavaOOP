package myjava.homework;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A program creating a 4-Star Game with Permutation and Combination.
 * @author saberLiou
 */
public class Main {
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		/* Standard input object. */
		Scanner scan = null;
		
		while (true){
			/* Show the usage. */
			System.out.println("Choose your Four Star Game type : "
					+ "(1:Permutation 2:Combination 3:Exit)");
			scan = new Scanner(System.in);
			
			try{
				int option = scan.nextInt();
				if (option == 1){
					/* Permutation. */
					Permutation p = new Permutation();
					p.checkOfWin();
				}
				else if (option == 2){
					/* Combination. */
					Combination c = new Combination();
					c.checkOfWin();
				}
				else if (option == 3){
					/* End the process. */
					System.out.println("Bye.");
					/* Close the Scanner. */
					scan.close();
					break;
				}
				else{
					/* Alert user if the input option isn't from 1 to 3. */
					System.out.println("Input error, incorrect option.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Input option should be an integer, try again.");
			}
		}
	}
}