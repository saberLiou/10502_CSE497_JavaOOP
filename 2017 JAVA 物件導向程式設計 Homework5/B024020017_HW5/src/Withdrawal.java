/**
 * A sub class of Transaction to withdraw funds from an account.
 * @author saberLiou
 */
public class Withdrawal extends Transaction{
	private int amount;
	private Keypad keypad;
	
	/**
	 * Constructs a Withdrawal.
	 * @param userAccount the user account number
	 * @param screen the output screen
	 * @param bankDatabase the bank database
	 * @param keypad the input keypad
	 */
	public Withdrawal(int userAccount, Screen screen, BankDatabase bankDatabase, Keypad keypad){
		super(userAccount, screen, bankDatabase);
		amount = 0;
		this.keypad = keypad;
	}
	
	/**
	 * Executes withdrawal.
	 */
	@Override
	public void execute(){
		/* Show the usage. */
		getScreen().displayMessageLine("How much do you want to withdraw:");
		amount = keypad.getInput();
		if (amount == -1){
			/* Alert user if the input amount isn't a positive number. */
			getScreen().displayMessageLine("Failed, "
				+ "make sure your input is a Positive Number in correct format!\n");
		}
		else if (amount >= getBankDatabase().getTotalBalance(getAccountNumber())){
			/* Alert user if over-withdrawn. */
			getScreen().displayMessageLine("Error, "
				+ "you don't have that much balance amount to withdraw!\n");
		}
		else{
			/* Withdrawal successed. */
			getBankDatabase().debit(getAccountNumber(), amount);
			getScreen().displayMessageLine("Success.\n");
		}
	}
}