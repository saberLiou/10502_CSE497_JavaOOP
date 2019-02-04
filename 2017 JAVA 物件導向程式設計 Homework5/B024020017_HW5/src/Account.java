/**
 * A bank account.
 * @author saberLiou
 */
public class Account {
	private int accountNumber;
	private int pin;
	private int totalBalance;
	private int debt;
	private char creditLevel;
	
	/**
	 * Constructs an Account.
	 * @param accountNumber the account number
	 * @param pin the PIN
	 * @param totalBalance the total balance
	 * @param creditLevel the credit level
	 */
	public Account(int accountNumber, int pin, int totalBalance, char creditLevel){
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.totalBalance = totalBalance;
		debt = 0;
		this.creditLevel = creditLevel;
	}
	
	/**
	 * Checks if the account PIN match the input PIN or not.
	 * @param aPIN the input PIN
	 * @return true if the account PIN and the input PIN matched
	 */
	public boolean validatePIN(int aPIN){
		return pin == aPIN;
	}
	
	/**
	 * Gets the account number.
	 * @return the account number
	 */
	public int getAccountNumber(){
		return accountNumber;
	}
	
	/**
	 * Gets the total balance.
	 * @return the total balance
	 */
	public int gettotalBalance(){
		return totalBalance;
	}
	
	/**
	 * Gets the credit level.
	 * @return the credit level
	 */
	public char getcreditLevel(){
		return creditLevel;
	}
	
	/**
	 * Gets the account debt.
	 * @return the account debt
	 */
	public int getdebt(){
		return debt;
	}
	
	/**
	 * Saves funds into the account.
	 * @param amount funds to be saved
	 */
	public void credit(int amount){
		totalBalance += amount;
	}
	
	/**
	 * Withdraws funds from the account.
	 * @param amount funds to be withdrawn
	 */
	public void debit(int amount){
		totalBalance -= amount;
	}
	
	/**
	 * Loans debt with the account.
	 * @param amount debt to be loaned
	 */
	public void loan(int amount){
		debt += amount;
	}
}