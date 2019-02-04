package JAVA_HW3;
import java.util.ArrayList;

/**
 * A student's list to provide the main class with adding a new student,
 * removing a student from its name or ID, and displaying all the students' data.
 * @author saberLiou
 */
public class Student_list {
	/**
	 * A student's list.
	 */
	private ArrayList<Student_info> list;
	
	/**
	 * Constructs a Student_list.
	 */
	public Student_list(){
		list = new ArrayList<Student_info>();
	}
	
	/**
	 * Adds the student's data in the student's list.
	 * @param student the student's data
	 */
	public void add(Student_info student){
		for(Student_info s : list){
			if (student.getID() == s.getID()){
				if (student.getName().equals(s.getName())){
					System.out.println("Student " + student.getName() + " with ID " + student.getID()
							+ " has existed in the list.\n"
							+ "Fail to add the student in the list.");
					return;
				}
				else{
					System.out.println("Student " + student.getName() + " with ID " + student.getID()
							+ " has a same ID with Student " + s.getName() + " in the list.\n"
							+ "Fail to add the student in the list.");
					return;
				}
			}
		}
		list.add(student);
	}
	
	/**
	 * Removes all the students' data from the name.
	 * @param name the removed student's name
	 * @return 1 if successed, 0 if the list is empty,
	 * and -1 if the removed student's name isn't in the list
	 */
	public int remove(String name){
		if (!list.isEmpty()){
			boolean removed = false;
			/* To avoid ConcurrentModificationException while removing in for-each loop,
			 * prepare a list to remove all the students from the same name.
			 */
			ArrayList<Student_info> removedList = new ArrayList<Student_info>();
			for (Student_info student : list){
				if (name.equals(student.getName())){
					System.out.println("Student " + name + " with ID " + student.getID() + " is removed from the list.");
					removedList.add(student);
					removed = true;
				}
			}
			if (removed){
				list.removeAll(removedList);
				return 1;
			}
			else{
				System.out.println("Name " + name + " is not in the list.");
				return -1;
			}
		}
		else{
			System.out.println("The list is empty.");
			return 0;
		}
	}
	
	/**
	 * Removes the student's data from its ID.
	 * @param ID the removed student's ID
	 * @return 1 if successed, 0 if the list is empty,
	 * and -1 if the removed student's ID isn't in the list
	 */
	public int remove(int id){
		if (!list.isEmpty()){
			for (Student_info student : list){
				if (id == student.getID()){
					System.out.println("Student " + student.getName() + " with ID " + id + " is removed from the list.");
					list.remove(student);
					return 1;
				}
			}
			System.out.println("Name " + id + " is not in the list.");
			return -1;
		}
		else{
			System.out.println("The list is empty.");
			return 0;
		}	
	}
	
	/**
	 * Prints all the students in the list.
	 */
	public void display(){
		if (list.isEmpty()){
			System.out.println("[null]");
			return;
		}
		for (Student_info student : list){
			System.out.println("<" + (list.indexOf(student) + 1) + ">");
			student.show_data();
		}
	}
}