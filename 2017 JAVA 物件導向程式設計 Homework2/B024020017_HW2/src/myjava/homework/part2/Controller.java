package myjava.homework.part2;
import java.util.Scanner;

/**
 * A program creating a item list to add some new item name,
 * remove particular item name, and display all the item names.
 * @author saberLiou
 */
public class Controller {
	/**
	 *  Standard input object.
	 */
	private static Scanner intScanner;
	private static Scanner strScanner;

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		/* Create item list. */
		ItemList items = new ItemList();
		
		while (true){
			/* Show the usage. */
			System.out.println("Type 1: add item to list"
					+ "\nType 2: remove item from list"
					+ "\nType 3: show the list");
			intScanner = new Scanner(System.in);
			/* Alert user if the input option isn't an integer. */
			if (!intScanner.hasNextInt()){
				System.out.println("Please make sure your input is an integer.\n");
				continue;
			}
			
			strScanner = new Scanner(System.in);
			switch (intScanner.nextInt()){
				case 1:
					/* Let user add new item name. */
					System.out.print("Add item name: "); 
					items.addItem(strScanner.nextLine());
					System.out.println();
					break;
				case 2:
					/* Let user input item name for removing it. */
					System.out.print("remove item name: ");
					items.remove(strScanner.nextLine());
					System.out.println();
					break;
				case 3:
					/* Display all the items. */
					items.printList();
					break;
				default:
					/* Alert user if the input option isn't from 1 to 3. */
					System.out.println("Please type the right number of 1 to 3 to choose the option.\n");
					break;
			}
		}
	}
}