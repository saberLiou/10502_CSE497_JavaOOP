package myjava.homework;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The program to implement server portion of a client/server stream-socket connection.
 * @author saberLiou
 */
public class Server {
	private static final int PORT = 9453;
	private HashMap<String, User> relationshipsTable;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	
	/**
	 * Constructs a Server.
	 */
	public Server() {
		relationshipsTable = new HashMap<String, User>();
	}
	
	/**
	 * Sets up the server to receive and process connections.
	 * @throws IOException the input/output streams exception
	 */
	public void runServer() throws IOException{
		/* Create a server socket. */
		server = new ServerSocket(PORT, 100);
		while (true){
			try{
				/* Wait for a client connection to arrive. */
				connection = server.accept();
				/* Set up output stream for objects. */
				output = new ObjectOutputStream(connection.getOutputStream());
				/* Set up input stream for objects. */
				input = new ObjectInputStream(connection.getInputStream());
				/* Process the connection. */
				processConnection();
			} catch (EOFException eofException){
				System.out.println("Server terminated I/O streams connection.");
			} finally{
				/* Close the connection and input/output streams. */
				output.close();
				input.close();
				connection.close();
			}
		}
	}
	
	/**
	 * Processes the connection with client.
	 * @throws IOException the input/output streams exception
	 */
	private void processConnection() throws IOException{
		/* Create users and the relationships table of users. */
		relationshipsTable.put("spoiled_brat", new User("spoiled_brat"));
		relationshipsTable.put("loser", new User("loser"));
		relationshipsTable.put("brat", new User("brat"));
		relationshipsTable.put("loner", new User("loner"));
		relationshipsTable.put("hater", new User("hater"));
		
		/* Create friends of each user. */
		relationshipsTable.get("spoiled_brat")
				.addFriend(relationshipsTable.get("loser"))
				.addFriend(relationshipsTable.get("brat"))
				.addFriend(relationshipsTable.get("hater"));
		
		relationshipsTable.get("loser")
				.addFriend(relationshipsTable.get("spoiled_brat"))
				.addFriend(relationshipsTable.get("brat"));
		
		relationshipsTable.get("brat")
				.addFriend(relationshipsTable.get("loser"))
				.addFriend(relationshipsTable.get("spoiled_brat"))
				.addFriend(relationshipsTable.get("hater"));
		
		relationshipsTable.get("loner")
				.addFriend(relationshipsTable.get("loser"));
		
		relationshipsTable.get("hater")
				.addFriend(relationshipsTable.get("spoiled_brat"))
				.addFriend(relationshipsTable.get("brat"));
		
		/* Create the json object and encode relationships table to it. */
		JSONObject jsonTable = new JSONObject();
		relationshipsTable.forEach((un, uo) ->
			jsonTable.put(un, new JSONArray(uo.getFriends()
					.stream()
					.map(f -> f.getUsername())
					.collect(Collectors.toCollection(ArrayList::new))))
		);
		
		/* Send json object string to client. */
		sendData(jsonTable.toString());
	}
	
	/**
	 * Send message to client.
	 * @param message the message string
	 */
	private void sendData(String message){
		try{
			output.writeObject(message);
			output.flush();
		} catch(IOException ioException){
			System.out.println("Error writing object.");
		}
	}
	
	/**
	 * Triggers the server portion.
	 * @param args not used
	 * @throws IOException the input/output streams exception
	 */
	public static void main(String[] args) throws IOException{
		new Server().runServer();
	}
}