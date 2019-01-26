// Represents the bank account information database

public class BankDatabase {
	
	private Account[] accounts; // array of Accounts
	
    // no-argument BankDatabase constructor initializes accounts
	public BankDatabase () {
		accounts = new Account[3];  // just 3 accounts for testing
		accounts[0] = new Account(123, 321, 5000,'A');
		accounts[1] = new Account(456, 654, 3000,'B');
		accounts[2] = new Account(789, 987, 1000,'C');
	}
	
	/* Fill your code here */
	
}
