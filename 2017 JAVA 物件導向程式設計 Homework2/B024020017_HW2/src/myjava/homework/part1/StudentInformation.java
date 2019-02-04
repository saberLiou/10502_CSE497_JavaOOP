package myjava.homework.part1;

/**
 * A student data.
 * @author saberLiou
 */
public class StudentInformation {
	private String id;
	private String name;
	private int score;
	
	/**
	 * Constructs a StudentInformation.
	 * @param anId the student's ID
	 * @param aName the student's name
	 * @param aScore the student's score
	 */
	public StudentInformation(String anId, String aName, int aScore){
		setData(anId, aName, aScore);
	}
	
	/**
	 * Sets the student's ID.
	 * @param id the student's ID
	 */
	public void setID(String id){
		this.id = id;
	}
	
	/**
	 * Sets the student's name.
	 * @param name the student's name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Sets the student's score.
	 * @param score the student's score
	 */
	public void setScore(int score){
		this.score = score;
	}
	
	/**
	 * Gets the student's ID.
	 * @return the student's ID
	 */
	public String getID(){
		return id;
	}
	
	/**
	 * Gets the student's name.
	 * @return the student's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the student's score.
	 * @return the student's score
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Sets the student's data.
	 * @param id the student's ID
	 * @param name the student's name
	 * @param score the student's score
	 */
	public void setData(String id, String name, int score){
		this.id = id;
		this.name = name;
		this.score = score;
	}
	
	/**
	 * Shows the student's data.
	 */
	public void show_data(){
		System.out.println("Student id: " + getID() + "\nStudent name: " + getName());
		if (getScore() >= 60){
			System.out.println("Student " + getName() + " pass this project");
		}
		else{
			System.out.println("Student " + getName() + " fail this project");
		}
	}
}