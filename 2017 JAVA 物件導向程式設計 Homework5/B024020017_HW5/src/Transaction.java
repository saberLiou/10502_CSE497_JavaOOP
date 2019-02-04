/**
 * An abstract super class to manipulate transaction of ATM.
 * @author saberLiou
 */
public abstract class Transaction {
	private int accountNumber;
	private Screen screen;
	private BankDatabase bankDatabase;
	
	/**
	 * Constructs a Transaction.
	 * @param accountNumber the account number
	 * @param screen the output screen
	 * @param bankDatabase the bank database
	 */
	public Transaction(int accountNumber, Screen screen, BankDatabase bankDatabase){
		this.accountNumber = accountNumber;
		this.screen = screen;
		this.bankDatabase = bankDatabase;
	}
	
	/**
	 * Gets the user account number.
	 * @return the user account number
	 */
	protected int getAccountNumber(){
		return accountNumber;
	}
	
	/**
	 * Gets the output screen
	 * @return the output screen
	 */
	protected Screen getScreen(){
		return screen;
	}
	
	/**
	 * Gets the bank database.
	 * @return the bank database
	 */
	protected BankDatabase getBankDatabase(){
		return bankDatabase;
	}
	
	/**
	 * Executes transaction.
	 */
	protected abstract void execute();
}