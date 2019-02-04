/**
 * A sub class of Transaction to loan debt with an account.
 * @author saberLiou
 */
public class Loan extends Transaction{
	private int amount;
	private Keypad keypad;
	
	/**
	 * Constructs a Loan.
	 * @param userAccount the user account number
	 * @param screen the output screen
	 * @param bankDatabase the bank data base
	 * @param keypad the input keypad
	 */
	public Loan(int userAccount, Screen screen, BankDatabase bankDatabase, Keypad keypad) {
		super(userAccount, screen, bankDatabase);
		amount = 0;
		this.keypad = keypad;
	}
	
	/**
	 * Executes loan.
	 */
	@Override
	public void execute() {
		int loanLimit;
		int debt = getBankDatabase().getDebt(getAccountNumber());
		switch(getBankDatabase().getCreditLevel(getAccountNumber())){
			case 'A':
				loanLimit = 12000;
				break;
			case 'B':
				loanLimit = 9000;
				break;
			case 'C':
				loanLimit = 7000;
				break;
			default:
				loanLimit = 0;
				break;
		}
		
		/* Show the usage. */
		getScreen().displayMessageLine("Your debt:" + debt);
		int loanRemain = loanLimit - debt;
		if (loanRemain == 0){
			getScreen().displayMessageLine("Sorry, You can't loan any money now."
					+ " Please repay your debt at our counter!\n");
		}
		else{
			/* Show the usage. */
			getScreen().displayMessageLine("Your loan limit is " + loanRemain
					+ ", how much do you want to loan:");
			amount = keypad.getInput();
			if (amount == -1){
				/* Alert user if the input amount isn't a positive number. */
				getScreen().displayMessageLine("Transaction Failed, "
					+ "make sure your input is a Positive Number in correct format!\n");
			}
			else if (amount > loanRemain){
				/* Out of loan limit. */
				getScreen().displayMessageLine("Transaction Error, "
					+ "you don't have that much Loan Limit!\n");
			}
			else{
				/* Loan successed. */
				getBankDatabase().loan(getAccountNumber(), amount);
				getScreen().displayMessageLine("Transaction Success.\n");
			}
		}
	}	
}