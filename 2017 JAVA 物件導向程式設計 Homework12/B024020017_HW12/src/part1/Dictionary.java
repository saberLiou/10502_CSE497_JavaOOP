package part1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

/**
 * The program to implement look up words in a dictionary by two files.
 * @author saberLiou
 */
public class Dictionary {
	/**
	 * Dictionary file.
	 */
	private static final String DICTIONARY = "words.txt";
	/**
	 * Test file.
	 */
	private static final String TEST = "test.txt";
	
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		BufferedReader inputStream = null;
		HashSet<String> dictionary = new HashSet<String>();
		try{
			inputStream = new BufferedReader(new FileReader(DICTIONARY));
			String line = inputStream.readLine();
			if (line == null){
				System.out.println("File " + DICTIONARY + " is empty.");
			}
			else{
				while (line != null){
					/* Add words into dictionary. */
					dictionary.add(line);
					line = inputStream.readLine();
				}
			}
		} catch (FileNotFoundException e){
			System.out.println("File " + DICTIONARY + " was not found or could not be opened.");
			System.exit(0);
		} catch (IOException e){
			System.out.println("Error reading from " + DICTIONARY + ".");
			System.exit(0);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Error closing of " + DICTIONARY + ".");
				System.exit(0);
			}
		}
		
		try{
			inputStream = new BufferedReader(new FileReader(TEST));
			String line = inputStream.readLine();
			if (line == null){
				System.out.println("File " + TEST + " is empty.");
			}
			else{
				/* Trim the front space(s) of the line if exist. */
				for (char c : line.toCharArray()){
					if (!Character.isWhitespace(c)){
						line = line.substring(line.indexOf(c));
						break;
					}
				}
				
				/* Split the line by one (or more if exist) space. */
				String[] vocabularies = line.split("\\s+");
				System.out.println("The words in " + TEST + ":");
				for (String v : vocabularies){
					System.out.println(v);
				}
				
				/* Search each vocabulary in dictionary word by word. */
				System.out.println("\nThe words out of dictionary " + DICTIONARY + ":");
				for (String v : vocabularies){
					if (!dictionary.contains(v)){
						System.out.println(v);
					}
				}
			}
		} catch (FileNotFoundException e){
			System.out.println("File " + TEST + " was not found or could not be opened.");
			System.exit(0);
		} catch (IOException e){
			System.out.println("Error reading from " + TEST + ".");
			System.exit(0);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Error closing of " + TEST + ".");
				System.exit(0);
			}
		}
	}
}