package myjava.homework;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Calculates the average, maximum or minimum of blood pressure or weight in a list of animals.
 * @author saberLiou
 * @param <T> the generic types of blood pressure or weight of a list of animals
 */
public class HealthCheck<T extends Number>{
	
	/**
	 * Calculates the average of blood pressure in a list of animals.
	 * @param bloodpressures the list of animals' blood pressure
	 * @return the average of blood pressure in a list of animals
	 */
	public double AverageBloodPressure(ArrayList<T> bloodpressures){
		return bloodpressures.stream().mapToDouble(b -> b.doubleValue()).sum() / bloodpressures.size();
		/* Alternative writting pattern: */
		//return bloodpressures.stream().mapToDouble(b -> b.doubleValue()).average().orElse(-1);
	}
	
	/**
	 * Calculates the average of weight in a list of animals.
	 * @param weights the list of animals' weight
	 * @return the average of weight in a list of animals
	 */
	public double AverageWeight(ArrayList<T> weights){
		return weights.stream().mapToDouble(w -> w.doubleValue()).sum() / weights.size();
		/* Alternative writting pattern: */
		//return weights.stream().mapToDouble(w -> w.doubleValue()).average().orElse(-1);
	}
	
	/**
	 * Calculates the maximum of blood pressure in a list of animals.
	 * @param bloodpressures the list of animals' blood pressure
	 * @return the maximum of blood pressure in a list of animals
	 */
	public T MaxBloodPressure(ArrayList<T> bloodpressures){
		return Collections.max((Collection<? extends T>) bloodpressures, null);
	}
	
	/**
	 * Calculates the maximum of weight in a list of animals.
	 * @param weights the list of animals' weight
	 * @return the maximum of weight in a list of animals
	 */
	public T MaxWeigth(ArrayList<T> weights){
		return Collections.max((Collection<? extends T>) weights, null);
	}
	
	/**
	 * Calculates the minimum of blood pressure in a list of animals.
	 * @param bloodpressures the list of animals' blood pressure
	 * @return the minimum of blood pressure in a list of animals
	 */
	public T MinBloodPressure(ArrayList<T> bloodpressures){
		return Collections.min((Collection<? extends T>) bloodpressures, null);
	}
	
	/**
	 * Calculates the minimum of weight in a list of animals.
	 * @param weights the list of animals' weight
	 * @return the minimum of weight in a list of animals
	 */
	public T MinWeigth(ArrayList<T> weights){
		return Collections.min((Collection<? extends T>) weights, null);
	}
}