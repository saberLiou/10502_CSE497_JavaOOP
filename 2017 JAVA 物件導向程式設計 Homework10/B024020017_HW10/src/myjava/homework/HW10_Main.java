package myjava.homework;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The program to connect to database, query SQL statements and display the results
 * of Generic Type and HashSet practice.
 * @author saberLiou
 */
public class HW10_Main {
	/**
	 * Database URL.
	 */
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/java_db?useSSL=false";
	/**
	 * Manage results.
	 */
	private static ResultSet resultSet;
	/**
	 * Query header.
	 */
	private static ResultSetMetaData metaData;
	/**
	 * Number of result column(s).
	 */
	private static int numberOfColumns;
	
	/**
	 * Animals record containers.
	 */
	private static HashSet<Animal<?, ?>> cats;
	private static HashSet<Animal<?, ?>> dogs;
	private static ArrayList<Integer> catWeights;
	private static ArrayList<Double> catBloodPressures;
	private static ArrayList<Double> dogWeights;
	private static ArrayList<Integer> dogBloodPressures;
	
	/**
	 * @param args not used
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		/* Manage connection. */
		Connection connection;
		/* Standard input object. */
		Scanner scan = new Scanner(System.in);
		/* Check the account and password for connection to database. */
		String userAccount, password;
		while (true){
			System.out.println("MYSQL Database account:");
			userAccount = scan.nextLine();
			System.out.println("MYSQL Database password:");
			password = scan.nextLine();
			try{
				/* Check connection to database. If successed, established. */
				connection = DriverManager.getConnection(DATABASE_URL, userAccount, password);
			} catch(SQLException sqlException){
				System.out.println("Wrong user account and password, please try again!\n");
				continue;
			}
			System.out.println("Connection to database success\n");
			/* Close the Scanner. */
			scan.close();
			break;
		}
		
		/* Prepare query statements */
		PreparedStatement catStatement = null;
		PreparedStatement dogStatement = null;
		
		try{
			/* Create queries. */
			catStatement = connection.prepareStatement("SELECT * FROM cat");
			dogStatement = connection.prepareStatement("SELECT * FROM dog");
			
			/* Prepare animal HashSet containers. */
			cats = new HashSet<Animal<?, ?>>();
			dogs = new HashSet<Animal<?, ?>>();
			
			/* Make HashSets. */
			cats = makeHashSet(catStatement);
			dogs = makeHashSet(dogStatement);

			/* Prepare animal ArrayList containers. */
			catWeights = new ArrayList<Integer>();
			catBloodPressures = new ArrayList<Double>();
			dogWeights = new ArrayList<Double>();
			dogBloodPressures = new ArrayList<Integer>();
			
			/* Make ArrayLists. */
			makeArrayList(cats);
			makeArrayList(dogs);
			
			/* Create HealthCheck object. */
			HealthCheck healthCheck = new HealthCheck();
			
			/* Show the statistic results. */
			String animalsInfo = "";
			System.out.print("----------------------[Cat]----------------------\n"
					+ "CatList : [");
			for (Animal<?, ?> c : cats){
				animalsInfo += c.toString() + ", ";
			}
			System.out.println(animalsInfo.substring(0, animalsInfo.length() - 2) + "]");
			System.out.println("MaxWeight : " + healthCheck.MaxWeigth(catWeights)
					+ "\nMinWeight : " + healthCheck.MinWeigth(catWeights)
					+ "\nAverageWeight : " + healthCheck.AverageWeight(catWeights)
					+ "\nMaxBloodPressure : " + healthCheck.MaxBloodPressure(catBloodPressures)
					+ "\nMinBloodPressure : " + healthCheck.MinBloodPressure(catBloodPressures)
					+ "\nAverageBloodPressure : " + healthCheck.AverageBloodPressure(catBloodPressures));
			System.out.println("--------------------------------------------------\n");
			animalsInfo = "";
			System.out.print("----------------------[Dog]----------------------\n"
					+ "DogList : [");
			for (Animal<?, ?> d : dogs){
				animalsInfo += d.toString() + ", ";
			}
			System.out.println(animalsInfo.substring(0, animalsInfo.length() - 2) + "]");
			System.out.println("MaxWeight : " + healthCheck.MaxWeigth(dogWeights)
					+ "\nMinWeight : " + healthCheck.MinWeigth(dogWeights)
					+ "\nAverageWeight : " + healthCheck.AverageWeight(dogWeights)
					+ "\nMaxBloodPressure : " + healthCheck.MaxBloodPressure(dogBloodPressures)
					+ "\nMinBloodPressure : " + healthCheck.MinBloodPressure(dogBloodPressures)
					+ "\nAverageBloodPressure : " + healthCheck.AverageBloodPressure(dogBloodPressures));
			System.out.println("--------------------------------------------------");
			
		} catch(SQLException sqlException){
			sqlException.printStackTrace();
		} finally{
			/* Ensure that catStatement, dogStatement, resultSet and connection are closed. */
			try{
				catStatement.close();
				dogStatement.close();
				resultSet.close();
				connection.close();
			} catch(Exception exception){
				exception.printStackTrace();
			}
		}
	}
	
	/**
	 * Makes the HashSet for distinct animals' records.
	 * @param statement the query statement
	 * @return the HashSet of animals' records
	 */
	private static HashSet<Animal<?, ?>> makeHashSet(PreparedStatement statement){
		/* Prepare the HashSet of animals' records. */
		HashSet<Animal<?, ?>> animals = new HashSet<Animal<?, ?>>();
		
		try{
			/* Execute query. */
			resultSet = statement.executeQuery();
			
			/* Get query header. */
			metaData = resultSet.getMetaData();
			
			/* Get columns number. */
			numberOfColumns = metaData.getColumnCount();
			
			/* Prepare the weight and blood pressure type storage. */
			String weightType = "", bloodPressureType = "";
			for (int i = 1; i <= numberOfColumns; i++){
				if (metaData.getColumnName(i).equals("weight")){
					weightType = "class " + metaData.getColumnClassName(i);
				}
				else if (metaData.getColumnName(i).equals("bloodpressure")){
					bloodPressureType = "class " + metaData.getColumnClassName(i);
				}
			}
			
			/* Get animals' each distinct record and store in HashSet. */
			while (resultSet.next()){
				String[] record = new String[numberOfColumns];
				for (int i = 1; i <= numberOfColumns; i++){
					record[i - 1] = resultSet.getObject(i).toString();
				}
				
				/* Raise repeat record flag. */
				boolean isRepeat = false;
				if (isCat(weightType, bloodPressureType)){
					/* Animal is Cat. */
					Animal<Integer, Double> cat = new Animal<Integer, Double>(record[0], record[1], Integer.valueOf(record[2]), Double.valueOf(record[3])); 
					for (Animal<?, ?> c : animals){
						if (c.equals(cat)){
							isRepeat = true;
							break;
						}
					}
					if (!isRepeat){
						animals.add(cat);
					}
				}
				else{
					/* Animal is Dog. */
					Animal<Double, Integer> dog = new Animal<Double, Integer>(record[0], record[1], Double.valueOf(record[2]), Integer.valueOf(record[3]));
					for (Animal<?, ?> d : animals){
						if (d.equals(dog)){
							isRepeat = true;
							break;
						}
					}
					if (!isRepeat){
						animals.add(dog);
					}
				}
			}
			return animals;
			
		} catch(SQLException sqlException){
			sqlException.printStackTrace();
			return null;
			
		} finally{
			/* Ensure that statement and resultSet are closed. */
			try{
				statement.close();
				resultSet.close();
			} catch(Exception exception){
				exception.printStackTrace();
			}
		}
	}
	
	/**
	 * Uses the HashSet records of each animal to make the ArrayList of weight or blood pressure.
	 * @param animals the HashSet records of animal
	 */
	@SuppressWarnings("unchecked")
	private static void makeArrayList(HashSet<Animal<?, ?>> animals){
		for (Animal<?, ?> a : animals){
			if (isCat(a.weight.getClass().toString(), a.bloodpressure.getClass().toString())){
				/* Animal is Cat. */
				Animal<Integer, Double> cat = (Animal<Integer, Double>) a;
				catWeights.add(cat.weight);
				catBloodPressures.add(cat.bloodpressure);
			}
			else{
				/* Animal is Dog. */
				Animal<Double, Integer> dog = (Animal<Double, Integer>) a;
				dogWeights.add(dog.weight);
				dogBloodPressures.add(dog.bloodpressure);
			}
		}
	}
	
	/**
	 * Judges Animal is Cat or Dog by its weight and blood pressure type.
	 * @param weightType the weight type of Animal
	 * @param bloodPressureType the blood pressure type of Animal
	 * @return true if Animal is Cat, false if Animal is Dog
	 */
	private static boolean isCat(String weightType, String bloodPressureType){
		return weightType.equals(Integer.class.toString()) && bloodPressureType.equals(Double.class.toString());
	}
}