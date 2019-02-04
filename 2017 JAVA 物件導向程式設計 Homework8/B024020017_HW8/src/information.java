/**
 * A class to store the user account, password and money.
 * @author saberLiou
 */
public class information {
	private String account;
	private int password;
	private int money;
	
	/**
	 * Constructs an information.
	 * @param account the user account
	 * @param password the user password
	 * @param money the user money
	 */
	public information(String account, int password, int money){
		this.account = account;
		this.password = password;
		this.money = money;
	}
	
	/**
	 * Gets the account of the user.
	 * @return the account of the user
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 * Gets the password of the user.
	 * @return the password of the user
	 */
	public int getPassword(){
		return password;
	}
	
	/**
	 * Gets the money of the user.
	 * @return the money of the user
	 */
	public int getMoney(){
		return money;
	}
}