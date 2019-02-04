import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The program to implement client portion of a client/server stream-socket connection.
 * @author saberLiou
 */
public class client{
	private static Scanner scan;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String chatServer;
	private int port;
	private Socket client;
	
	/**
	 * Constructs a client.
	 * @param host the IP address
	 * @param port the port
	 */
	public client(String host, int port){
		chatServer = host;
		this.port = port;
	}
	
	/**
	 * Sets up the client to connect to server and receive message from it.
	 * @throws IOException the input/output streams exception
	 */
	public void runClient() throws IOException{
		try{
			/* Create a socket to make the connection. */
			client = new Socket(InetAddress.getByName(chatServer), port);
			/* Set up output stream for objects. */
			output = new ObjectOutputStream(client.getOutputStream());
			/* Flush output buffer to send header information. */
			output.flush();
			/* Set up input stream for objects. */
			input = new ObjectInputStream(client.getInputStream());
			/* Process the connection. */
			processConnection();
		} catch(EOFException eofException){
			System.out.println("\nClient terminated connection");
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
		/* Standard input object. */
		scan = new Scanner(System.in);
		
		String message, request;
		while (true){
			try{
				/* Read message sent from server. */
				message = (String) input.readObject();
				System.out.println(message);
				/* Leave. */
				if (message.substring(0, 7).equals("Account") || message.substring(0, 5).equals("Leave")){
					break;
				}
			} catch (ClassNotFoundException classNotFoundException){
				System.out.println("Unknown object type received");
			}
			request = scan.nextLine();
			/* Send request to server. */
			sendData(request);
		}
	}
	
	/**
	 * Send message to server.
	 * @param message the message string
	 */
	private void sendData(String message){
		try{
			output.writeObject(message);
			output.flush();
		} catch(IOException ioException){
			System.out.println("Error writing object");
		}
	}
	
	/**
	 * Triggers the client portion.
	 * @param args not used
	 * @throws IOException the input/output streams exception
	 */
	public static void main(String[] args) throws IOException{
		while (true){
			/* Standard input object. */
			scan = new Scanner(System.in);
			System.out.println("input your address:");
			String address = scan.nextLine();
			/* Use regular expression to check correct IP address format. */
			String regularExpression = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
			if (Pattern.compile(regularExpression).matcher(address).matches()){
				System.out.println("input your port:");
				int port = scan.nextInt();
				if (port == 12345){
					System.out.println("Connection....");
					new client(address, port).runClient();
				}
				else{
					System.out.println("wrong port");
				}
			}
			else{
				System.out.println("wrong ip address");
			}
		}
	}
}