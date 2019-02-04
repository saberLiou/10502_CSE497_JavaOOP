import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The program to implement server portion of a client/server stream-socket connection.
 * @author saberLiou
 */
public class server{
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private ArrayList<information> informations;
	
	/**
	 * Constructs a server.
	 */
	public server(){
		informations = new ArrayList<information>();
		/* Set a default account. */
		informations.add(new information("a", 1, 50));
	}
	
	/**
	 * Sets up the server to receive and process connections.
	 * @throws IOException the input/output streams exception
	 */
	public void runServer() throws IOException{
		/* Create a server socket for a 100 socket queue. */
		server = new ServerSocket(12345, 100);
		while (true){
			try{
				/* Wait for a client connection to arrive. */
				connection = server.accept();
				/* Set up output stream for objects. */
				output = new ObjectOutputStream(connection.getOutputStream());
				/* Flush output buffer to send header information. */
				output.flush();
				/* Set up input stream for objects. */
				input = new ObjectInputStream(connection.getInputStream());
				/* Process the connection. */
				processConnection();
			} catch (EOFException eofException){
				continue;
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
		/* A HashSet to store the ArrayList informations index of the new or modified account
		 * as client leaving.
		 */
		HashSet<Integer> finalOutputAccounts = new HashSet<Integer>();
		/* Show the usage. */
		String mainBoard = "\n(1).add new account\n(2).Sign in\n(3).leave";
		/* Send the main board to client first. */
		sendData(mainBoard);
		
		String request, account;
		int password = 0;
		int money = 0;
		while (true){
			try{
				/* Read request from client. */
				request = (String) input.readObject();
				try{
					int option = Integer.parseInt(request);
					switch (option){
						case 1:
							/* Add new account. */
							sendData("input your account:");
							request = (String) input.readObject();
							boolean sameAccount = false;
							for (information i : informations){
								if (i.getAccount().equals(request)){
									sameAccount = true;
									break;
								}
							}
							if (sameAccount){
								sendData("error, have the same account." + mainBoard);
							}
							else{
								account = request;
								sendData("input your password:");
								request = (String) input.readObject();
								try{
									password = Integer.valueOf(request);
									
									/* If account and password are all correct, stored. */
									informations.add(new information(account, password, 0));
									for (information i : informations){
										if (i.getAccount().equals(account)){
											finalOutputAccounts.add(informations.indexOf(i));
										}
									}
									sendData(mainBoard);
								} catch (NumberFormatException e){
									sendData("Input error: password should be a number" + mainBoard);
								}
							}
							break;
						case 2:
							/* Sign in. */
							sendData("input your account:");
							request = (String) input.readObject();
							account = request;
							sendData("input your password:");
							request = (String) input.readObject();
							try{
								password = Integer.valueOf(request);
								
								/* Check login account and password with later-used index. */
								int loginIndex = -1;
								for (information i : informations){
									if (i.getAccount().equals(account) && i.getPassword() == password){
										loginIndex = informations.indexOf(i);
										break;
									}
								}
								if (loginIndex != -1){
									/* Login. */
									sendData("input your money:");
									request = (String) input.readObject();
									try{
										money = Integer.parseInt(request);
										
										/* If account, password and money are all correct, stored. */
										money += informations.get(loginIndex).getMoney();
										informations.set(loginIndex, new information(informations.get(loginIndex).getAccount(), informations.get(loginIndex).getPassword(), money));
										finalOutputAccounts.add(loginIndex);
										sendData(money + mainBoard);
									} catch (NumberFormatException e){
										sendData("money should be a number" + mainBoard);
									}
								}
								else{
									sendData("Can't find the account" + mainBoard);
								}
							} catch(NumberFormatException e){
								sendData("password should be a number" + mainBoard);
							}
							break;
						case 3:
							/* Show the new or modified account and leave. */
							String totalAccounts = "";
							for (Integer ai : finalOutputAccounts){
								totalAccounts += "Account:" + informations.get(ai).getAccount()
										+ "\nPassword:" + informations.get(ai).getPassword()
										+ "\nMoney:" + informations.get(ai).getMoney() + "\n";
							}
							
							if (totalAccounts.equals("")){
								sendData("Leave with no account inserted or modified");
							}
							else{
								sendData(totalAccounts);
							}
							break;
						default:
							sendData("option should be 1 to 3" + mainBoard);
					}
				} catch(NumberFormatException e){
					sendData("option should be a number" + mainBoard);
				}
			} catch (ClassNotFoundException classNotFoundException) {
				System.out.println("Unknown object type received");
			}
		}
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
			System.out.println("Error writing object");
		}
	}
	
	/**
	 * Triggers the server portion.
	 * @param args not used
	 * @throws IOException the input/output streams exception
	 */
	public static void main(String[] args) throws IOException{
		new server().runServer();
	}
}