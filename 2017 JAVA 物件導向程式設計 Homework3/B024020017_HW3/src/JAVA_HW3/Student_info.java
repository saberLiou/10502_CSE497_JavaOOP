package JAVA_HW3;

/**
 * A student data.
 * @author saberLiou
 */
public class Student_info {
	private String name;
	private int id;
	
	/**
	 * Constructs a Student_info.
	 * @param aName the student's name
	 * @param anId the student's ID
	 */
	public Student_info(String aName, int anId){
		name = aName;
		id = anId;
	}
	
	/**
	 * Gets the student's name.
	 * @return the student's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the student's ID.
	 * @return the student's ID
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Shows the student's data.
	 */
	public void show_data(){
		System.out.println("Name:" + getName() + "\nID:" + getID());
	}
}