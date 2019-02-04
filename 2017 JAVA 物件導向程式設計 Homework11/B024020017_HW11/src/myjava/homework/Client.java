package myjava.homework;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The program to implement client portion of a client/server stream-socket connection.
 * @author saberLiou
 */
public class Client {
	private static final int PORT = 9453;
	private static final String IP_ADDRESS = "127.0.0.1";
	private HashMap<String, User> relationshipsTable;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket client;
	private Scanner scan;
	
	/**
	 * Constructs a Client.
	 */
	public Client(){
		relationshipsTable = new HashMap<String, User>();
	}
	
	/**
	 * Sets up the client to connect to server and receive message from it.
	 * @throws IOException the input/output streams exception
	 */
	public void runClient() throws IOException{
		try{
			/* Create a socket to make the connection. */
			client = new Socket(InetAddress.getByName(IP_ADDRESS), PORT);
			/* Set up output stream for objects. */
			output = new ObjectOutputStream(client.getOutputStream());
			/* Set up input stream for objects. */
			input = new ObjectInputStream(client.getInputStream());
			/* Process the connection. */
			processConnection();
		} catch(EOFException eofException){
			System.out.println("Client terminated I/O streams connection.");
		} finally{
			/* Close the connection and input/output streams. */
			output.close();
			input.close();
			client.close();
		}
	}
	
	/**
	 * Processes the connection with server.
	 * @throws IOException the input/output streams exception
	 */
	private void processConnection() throws IOException{
		System.out.println("Connected to server.");
		/* Standard input object. */
		scan = new Scanner(System.in);
		
		String message = "";
		try{
			/* Read json object string from server. */
			message = (String) input.readObject();
			/* Display json object string. */
			//System.out.println(message);
		} catch (ClassNotFoundException classNotFoundException){
			System.out.println("Unknown object type received.");
		}
		
		while (true){
			/* Decode json object string to json object. */
			JSONObject jsonObj = new JSONObject(message);
			
			/* Create users and the relationships table of users. */
			jsonObj.keySet().forEach(u ->
				relationshipsTable.put(u, new User(u))
			);
			
			/* Create friends of each user. */
			jsonObj.keySet().forEach(u ->
				((JSONArray) jsonObj.get(u)).forEach(f ->
					relationshipsTable.get(u).addFriend(relationshipsTable.get(f))
				)
			);
			
			/* Show the usage. */
			System.out.println("Username:\tFriends\n"
					+ "-----------------------------------\n"
					+ relationshipsTable.entrySet()
							.stream()
							.map(u -> u.getKey() + ":  {"
									+ u.getValue().getFriends()
											.stream()
											.map(f -> "\"" + f.getUsername() + "\",")
											.collect(Collectors.joining())
									+ "}\n-----------------------------------\n")
							.collect(Collectors.joining())
					+ "Enter a name:"
			);
			
			/* Display relationships of particular user. */
			String user = scan.nextLine();
			System.out.println(
				relationshipsTable.keySet().contains(user)
					?	/* Unidirectional and bidirectional relationships. */
						"-------------------------\nUnidirectional:\n"
						+ relationshipsTable.get(user).getFriends()
								.stream()
								.map(f -> user + " -----> " + f.getUsername() + "\n")
								.collect(Collectors.joining())
						+ "-------------------------\nBidirectional:\n"
						+ relationshipsTable.get(user).getFriends()
								.stream()
								.filter(f -> f.getFriends().contains(relationshipsTable.get(user)))
								.map(f -> user + " <-----> " + f.getUsername() + "\n")
								.collect(Collectors.joining()) 
						+ "-------------------------\n"
					:	/* None relationship. */
						user + " doesn't have friends.\n"
			);
		}
	}
	
	/**
	 * Triggers the client portion.
	 * @param args not used
	 * @throws IOException the input/output streams exception
	 */
	public static void main(String[] args) throws IOException{
		new Client().runClient();
	}
}