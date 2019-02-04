package JAVA_HW3;
import java.util.Scanner;

/**
 * A program creating a student's list to provide user to add some new student's data,
 * remove and show a student's data if successed, and display all the students' data.
 * @author saberLiou
 */
public class Main {
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		/* Standard input object. */
		Scanner intScanner = null;
		Scanner strScanner = null;
		/* Create a students list. */
		Student_list students = new Student_list();
		
		while (true){
			/* Show the usage. */
			System.out.println("<1> Add one student to the list."
					+ "\n<2> Remove one student from the list."
					+ "\n<3> Display the student list."
					+ "\n<4> Exit.");
			System.out.print("Input:");
			intScanner = new Scanner(System.in);
			/* Alert user if the input option isn't an integer. */
			if (!intScanner.hasNextInt()){
				System.out.println("[Error] Wrong Input!\n[Error] Try Again!\n");
				continue;
			}
			int option = intScanner.nextInt();
			
			if (option != 4){
				switch (option){
					case 1:
						/* Let user add new data. */
						strScanner = new Scanner(System.in);
						
						System.out.print("Student's name:");
						String name = strScanner.nextLine();
						
						System.out.print("Stdeunt's ID:");
						/* Alert user if the input score isn't an integer. */
						if (!intScanner.hasNextInt()){
							System.out.println("Please make sure your input is an integer.\n"
									+ "Failed to add one student to the list.\n");
							break;
						}
						int id = intScanner.nextInt();
						
						/* Add the new data into students. */
						students.add(new Student_info(name, id));
						break;
					case 2:
						/* Let user remove the data from student's name or ID. */
						System.out.println("Remove from <1>name <2>id:");
						/* Alert user if the input option isn't an integer. */
						if (!intScanner.hasNextInt()){
							System.out.println("Please make sure your input is an integer.\n");
							break;
						}
						int removedOption = intScanner.nextInt();
						
						if (removedOption == 1){
							/* Remove from student's name. */
							strScanner = new Scanner(System.in);
							System.out.print("Student's name:");
							
							/* Remove the student's data from students. */
							students.remove(strScanner.nextLine());
						}
						else if (removedOption == 2){
							/* Remove from student's ID. */
							System.out.print("Student's ID:");
							/* Alert user if the input ID isn't an integer. */
							if (!intScanner.hasNextInt()){
								System.out.println("Please make sure your input ID is an integer.\n"
										+ "Fail to remove the student from the list.\n");
								break;
							}
							
							/* Remove the student's data from students. */
							students.remove(intScanner.nextInt());
						}
						else{
							/* Alert user if the input option isn't one or two. */
							System.out.println("Input error : incorrect option");
						}
						break;
					case 3:
						/* Display all students' data. */
						students.display();
						break;
					default:
						/* Alert user if the input option isn't from 1 to 3. */
						System.out.println("Input error : incorrect option");
						break;
				}
			}
			else{
				/* Close the Scanner and end the process. */
				intScanner.close();
				break;
			}
		}
	}
}