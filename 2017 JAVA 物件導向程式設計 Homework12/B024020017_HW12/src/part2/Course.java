package part2;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * The program to record each students' course by their student id and course id. 
 * @author saberLiou
 */
public class Course {
	/**
	 * Standard input object.
	 */
	private static Scanner scan;

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		/* Students's course list. */
		HashMap<Integer, ArrayList<String>> students = new HashMap<Integer, ArrayList<String>>();
		scan = new Scanner(System.in);
		while (true){
			System.out.println("Please input data:");
			String data = scan.nextLine();
			
			/* Trim the front space(s) of the data string if exist. */
			for (char c : data.toCharArray()){
				if (!Character.isWhitespace(c)){
					data = data.substring(data.indexOf(c));
					break;
				}
			}
			
			if (data.equals("-1")){
				/* Display all the data in students's course list and exit. */
				System.out.println("List: [" + students.entrySet()
						.stream()
						.sorted(HashMap.Entry.comparingByKey())
						.map(c -> c.getKey() + "=[" + c.getValue()
								.stream().map(Object::toString)
		                        .collect(Collectors.joining(", ")) + "]")
						.collect(Collectors.joining(", ")) + "]"
				);
				break;
			}
			else{
				/* Split the data string by one (or more if exist) space. */
				String[] sIdcId = data.split("\\s+");
				
				/* Check the data string is split correctly into student id and course id. */
				int inputLength = sIdcId.length;
				if (inputLength < 2){
					System.out.println("Plaese input " + 
						((sIdcId.length == 0) ? "student id and course id!" : "course id!"));
					continue;
				}
				else if (inputLength > 2){
					System.out.println("System will only save first two input into database.");
				}
				
				/* Check if student id is positive integer or not. */
				int sId;
				try{
					sId = Integer.parseInt(sIdcId[0]);
					if (sId <= 0){
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e){
					System.out.println("Plaese input correct student id!");
					continue;
				}
				
				/* Add student id and course id into students' course list distinctly. */
				if (!students.keySet().contains(sId)){
					ArrayList<String> newStudent = new ArrayList<String>();
					newStudent.add(sIdcId[1]);
					students.put(sId, newStudent);
				}
				else{
					if (!students.get(sId).contains(sIdcId[1])){
						students.get(sId).add(sIdcId[1]);
					}
					else{
						System.out.println("Course " + sIdcId[1] + " is already in Student " + sId + "'s course list.");
					}
				}
			}
		}
	}
}