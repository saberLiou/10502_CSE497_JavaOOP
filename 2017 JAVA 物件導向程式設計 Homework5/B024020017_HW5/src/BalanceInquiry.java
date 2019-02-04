/**
 * A sub class of Transaction to inquire the information of an account.
 * @author saberLiou
 */
public class BalanceInquiry extends Transaction{
	
	/**
	 * Constructs a BalanceInquiry.
	 * @param userAccount the user account number
	 * @param screen the output screen
	 * @param bankDatabase the bank database
	 */
	public BalanceInquiry(int userAccount, Screen screen, BankDatabase bankDatabase){
		super(userAccount, screen, bankDatabase);
	}
	
	/**
	 * Executes inquiry.
	 */
	@Override
	public void execute(){
		getScreen().displayMessageLine("Balance Information\nTotalBalance:"
			+ getBankDatabase().getTotalBalance(getAccountNumber()) + "\n");
	}
}