package myjava.homework.part1;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A program creating a student's list to provide user to add some new data,
 * find out and show a student's data, and display all the students' data.
 * @author saberLiou
 */
public class Controller {
	/**
	 * Standard input object.
	 */
	private static Scanner intScanner;

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		/* Create a students list. */
		ArrayList<StudentInformation> students = new ArrayList<StudentInformation>();
		
		while (true){
			/* Show the usage. */
			System.out.println("Type 1: add a student's data(student ID, student name and score)\n"
					+ "Type 2: show student's data\n"
					+ "Type 3: show all student's data.");
			intScanner = new Scanner(System.in);
			/* Alert user if the input option isn't an integer. */
			if (!intScanner.hasNextInt()){
				System.out.println("Please make sure your input is an integer.\n");
				continue;
			}
			
			switch (intScanner.nextInt()){
				case 1:
					/* Let user add new data. */
					System.out.println("Add new student's data:");
					/* Get string input object. */
					Scanner strScanner = new Scanner(System.in);
					
					System.out.print("student id: ");
					String id = strScanner.nextLine();
					
					System.out.print("student name: ");
					String name = strScanner.nextLine();
					
					System.out.print("Score: ");
					/* Alert user if the input score isn't an integer. */
					if (!intScanner.hasNextInt()){
						System.out.println("Please make sure your input is an integer.\n"
								+ "Adding new student's information failed.\n");
						break;
					}
					int score = intScanner.nextInt();
					
					/* Create a new StudentInformation object. */
					StudentInformation student = new StudentInformation(id, name, score);
					/* Add the object into students. */
					students.add(student);
					/* Show student's number in students */
					System.out.println("This is student " + (students.indexOf(student) + 1)+ "\n");
					break;
				case 2:
					/* Let user choice student, then display it. */
					System.out.println("To show which student's information");
					/* Alert user if the input student's number isn't an integer. */
					if (!intScanner.hasNextInt()){
						System.out.println("Please make sure your input is a student number.\n");
						break;
					}
					int number = intScanner.nextInt();
					
					/* Search students, try to find out the target student's data. */
					boolean found = false;
					for (StudentInformation s : students){
						if (number == (students.indexOf(s) + 1)){
							students.get(number - 1).show_data();
							System.out.println("This is student " + number + "\n");
							found = true;
							break;
						}
					}
					if (!found){
						System.out.println("Data not found\n");
					}
					break;
				case 3:
					/* Display all students' data and how many student passed and failed this project. */
					/* Count the number of passed student. */
					int passCount = 0;
					System.out.println("====Student's data=====");
					for (StudentInformation s : students){
						if (s.getScore() >= 60){
							passCount++;
						}
						System.out.println("Number: " + (students.indexOf(s) + 1));
						s.show_data();
						System.out.println();
					}
					System.out.println("===============");
					System.out.println("Pass: " + passCount + "\nNo pass: " + (students.size() - passCount) + "\n");
					break;
				default:
					/* Alert user if the input option isn't from 1 to 3. */
					System.out.println("Please type the right number of 1 to 3 to choose the option.\n");
					break;
			}
		}
	}
}
