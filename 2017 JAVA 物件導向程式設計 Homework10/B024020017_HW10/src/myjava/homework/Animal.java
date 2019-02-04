package myjava.homework;

/**
 * An animal class for Cat and Dog.
 * @author saberLiou
 * @param <T> the generic types of weight of the animal
 * @param <K> the generic types of blood pressure of the animal
 */
public class Animal<T, K> extends Object{
	public String id;
	public String sex;
	public T weight;
	public K bloodpressure;
	
	/**
	 * Constructs an Animal.
	 * @param id the animal name
	 * @param sex the animal sex
	 * @param weigth the animal weight
	 * @param bloodpressure the animal blood pressure
	 */
	public Animal(String id, String sex, T weigth, K bloodpressure){
		this.id = id;
		this.sex = sex;
		this.weight = weigth;
		this.bloodpressure = bloodpressure;
	}
	
	/**
	 * Overrides from Object, checks if the hash code of two animals are all the same or not.
	 * @return true if the hash code of two animals are the same, false if not
	 */
	@Override
	public boolean equals(Object object){
		return object instanceof Animal && this.hashCode() == object.hashCode();
	}
	
	/**
	 * Overrides from Object, generates the hash code number of the animal.
	 * @return the hash code number of the animal
	 */
	@Override
	public int hashCode(){
		return id.hashCode() + sex.hashCode() + weight.hashCode() + bloodpressure.hashCode();
	}
	
	/**
	 * Overrides from Object, displays the four characteristics of the animal.
	 * @return the four characteristics of the animal
	 */
	@Override
	public String toString(){
		return "(" + id + ", " + sex + ", " + weight + ", " + bloodpressure + ")";
	}
}