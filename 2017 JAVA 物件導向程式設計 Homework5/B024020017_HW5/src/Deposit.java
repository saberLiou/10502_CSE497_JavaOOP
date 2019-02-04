/**
 * A sub class of Transaction to save funds into an account.
 * @author saberLiou
 */
public class Deposit extends Transaction{
	private int amount;
	private Keypad keypad;
	
	/**
	 * Constructs a Deposit.
	 * @param userAccount the user account number
	 * @param screen the output screen
	 * @param bankDatabase the bank database
	 * @param keypad the input keypad
	 */
	public Deposit(int userAccount, Screen screen, BankDatabase bankDatabase, Keypad keypad){
		super(userAccount, screen, bankDatabase);
		amount = 0;
		this.keypad = keypad;
	}
	
	/**
	 * Executes deposit.
	 */
	@Override
	public void execute(){
		/* Show the usage. */
		getScreen().displayMessageLine("How much do you want to deposit:");
		amount = keypad.getInput();
		if (amount == -1){
			/* Alert user if the input amount isn't a positive number. */
			getScreen().displayMessageLine("Failed, "
				+ "make sure your input is a Positive Number in correct format!");
		}
		else{
			/* Deposit successed. */
			getBankDatabase().credit(getAccountNumber(), amount);
			getScreen().displayMessageLine("Success.\n");
		}
	}
}