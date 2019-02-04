/**
 * The bank account information database.
 * @author saberLiou
 */
public class BankDatabase {
	/* Array of demo accounts. */
	private Account[] accounts;
	
    /**
     * Constructs a BankDatabase.
     */
	public BankDatabase () {
		/* Just 3 accounts for testing. */
		accounts = new Account[3];
		accounts[0] = new Account(123, 321, 5000, 'A');
		accounts[1] = new Account(456, 654, 3000, 'B');
		accounts[2] = new Account(789, 987, 1000, 'C');
	}
	
	/**
	 * Checks if the user account number match its PIN or not.
	 * @param userAccount the user account number
	 * @param aPIN the PIN
	 * @return true if the user account and its PIN matched,
	 * false if they aren't matched or the user account isn't in accounts database
	 */
	public boolean authenticateUser(int userAccount, int aPIN){
		for (Account a : accounts){
			if (a.getAccountNumber() == userAccount){
				if (a.validatePIN(aPIN)){
					return true;
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Gets the total balance of the user account.
	 * @param userAccount the user account number
	 * @return the total balance, -1 if user account not found
	 */
	public int getTotalBalance(int userAccount){
		for (Account a : accounts){
			if (a.getAccountNumber() == userAccount){
				return a.gettotalBalance();
			}
		}
		return -1;
	}
	
	/**
	 * Gets the credit level of the user account.
	 * @param userAccount the user account number
	 * @return the credit level, X if user account not found
	 */
	public char getCreditLevel(int userAccount){
		for (Account a : accounts){
			if (a.getAccountNumber() == userAccount){
				return a.getcreditLevel();
			}
		}
		return 'X';
	}
	
	/**
	 * Gets the debt of the user account.
	 * @param userAccount the user account number
	 * @return the debt, -1 if user account not found
	 */
	public int getDebt(int userAccount){
		for (Account a : accounts){
			if (a.getAccountNumber() == userAccount){
				return a.getdebt();
			}
		}
		return -1;
	}
	
	/**
	 * Saves funds into the user account.
	 * @param userAccount the user account number
	 * @param amount funds to be saved
	 */
	public void credit(int userAccount, int amount){
		for (Account a : accounts){
			if (a.getAccountNumber() == userAccount){
				a.credit(amount);
			}
		}
	}
	
	/**
	 * Withdraws funds from the user account.
	 * @param userAccount the user account number
	 * @param amount funds to be withdrawn
	 */
	public void debit(int userAccount, int amount){
		for (Account a : accounts){
			if (a.getAccountNumber() == userAccount){
				a.debit(amount);
			}
		}
	}
	
	/**
	 * Loans debt with the user account.
	 * @param userAccount the user account number
	 * @param amount debt to be loaned
	 */
	public void loan(int userAccount, int amount){
		for (Account a : accounts){
			if (a.getAccountNumber() == userAccount){
				a.loan(amount);
			}
		}
	}
}