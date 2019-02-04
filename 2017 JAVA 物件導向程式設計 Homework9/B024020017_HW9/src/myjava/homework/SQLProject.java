package myjava.homework;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * The program to connect to database, query SQL statements and display the results.
 * @author saberLiou
 */
public class SQLProject {
	/**
	 * Database URL.
	 */
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/java_db?useSSL=false";
	
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		/* Manage connection. */
		Connection connection;
		/* Standard input object. */
		Scanner scan = new Scanner(System.in);
		/* Check the username and password for connection to database. */
		String userAccount, password;
		while (true){
			System.out.println("Please enter useraccount:");
			userAccount = scan.nextLine();
			System.out.println("Please enter password:");
			password = scan.nextLine();
			try{
				/* Check connection to database. If successed, establish. */
				connection = DriverManager.getConnection(DATABASE_URL, userAccount, password);
			} catch(SQLException sqlException){
				System.out.println("Wrong user account and password, please try again!\n");
				continue;
			}
			System.out.println("Connection establish\n");
			break;
		}

		/* Query statements */
		PreparedStatement[] statements = new PreparedStatement[5];
		/* Manage results */
		ResultSet resultSet = null;
		/* Query header */
		ResultSetMetaData metaData = null;
		/* Number of result column(s) */
		int numberOfColumns;
		
		try{
			/* Create queries. */
			statements[0] = connection.prepareStatement("SELECT AVG(chinese) AS c_avg, MAX(math) AS m_max, MIN(chinese) AS c_min FROM grades");
			statements[1] = connection.prepareStatement("SELECT id, chinese FROM grades ORDER BY chinese DESC");
			statements[2] = connection.prepareStatement("SELECT id, sex, birthmonth FROM students WHERE Left(id, 1) = 'A' ORDER BY sex, birthmonth DESC");
			statements[3] = connection.prepareStatement("SELECT AVG(english) AS e_avg FROM students NATURAL JOIN grades WHERE sex = 'F'");
			statements[4] = connection.prepareStatement("SELECT sex, COUNT(sex) AS count FROM students WHERE birthmonth BETWEEN 6 AND 10 GROUP BY sex");
			
			for (int q = 0; q < 5; q++){
				/* Execute query. */
				System.out.println("Query " + (q + 1) + ":");
				resultSet = statements[q].executeQuery();
				
				/* Get query header. */
				metaData = resultSet.getMetaData();
				numberOfColumns = metaData.getColumnCount();
				
				/* Display header. */
				for (int i = 1; i <= numberOfColumns; i++){
					System.out.printf("%-8s\t", metaData.getColumnName(i));
				}
				System.out.println();
				
				/* Display result(s). */
				if (q == 2 || q == 4){
					while (resultSet.next()){
						for (int i = 1; i <= numberOfColumns; i++){
							System.out.printf("%-8s\t", resultSet.getObject(i));
						}
						System.out.println();
					}
					System.out.println();
				}
				else if (q == 0 || q == 3){
					resultSet.first();
					for (int i = 1; i <= numberOfColumns; i++){
						if (i == 1){
							System.out.printf("%-8.1f\t", resultSet.getDouble(i));
						}
						else{
							System.out.printf("%-8s\t", resultSet.getObject(i));
						}
					}
					System.out.println("\n");
				}
				else{
					resultSet.first();
					for (int i = 1; i <= numberOfColumns; i++){
						System.out.printf("%-8s\t", resultSet.getObject(i));
					}
					System.out.println("\n");
				}
				
				/* Close resultSet to prevent memory leak. */
				resultSet.close();
			}
			
		} catch(SQLException sqlException){
			sqlException.printStackTrace();
		} finally{
			/* Ensure that resultSet, statements and connection are closed. */
			try{
				for (PreparedStatement ps : statements){
					ps.close();
				}
				resultSet.close();
				connection.close();
			} catch(Exception exception){
				exception.printStackTrace();
			}
		}
	}
}