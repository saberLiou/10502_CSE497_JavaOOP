/**
 * An ATM class.
 * @author saberLiou
 */
public class ATM {
	private boolean userAuthenticated;
	private Screen screen;
	private Keypad keypad;
	private BankDatabase bankDatabase;
	private Transaction transaction;
	
	/**
	 * Constructs an ATM.
	 */
	public ATM(){
		userAuthenticated = false;
		screen = new Screen();
		keypad = new Keypad();
		bankDatabase = new BankDatabase();
	}
	
	/**
	 * Starts up the ATM.
	 */
	public void run(){
		while (true){
			/* Show the usage. */
			screen.displayMessageLine("Welcome!");
			screen.displayMessage("Please enter your account number:");
			int userAccount = keypad.getInput();
			/* Alert user if the input user account number isn't a positive number. */
			if (userAccount == -1){
				screen.displayMessageLine("Make sure your input is a Positive Number in correct format!\n");
				continue;
			}
			
			screen.displayMessage("Please enter your pin:");
			int pin = keypad.getInput();
			/* Alert user if the input PIN isn't a positive number. */
			if (pin == -1){
				screen.displayMessageLine("Make sure your input is a Positive Number in correct format!\n");
				continue;
			}
			
			userAuthenticated = bankDatabase.authenticateUser(userAccount, pin);
			if (!userAuthenticated){
				/* Alert user if the account number doesn't match its PIN. */
				screen.displayMessageLine("Account with this PIN not in Database, Unable to Login!\n");
				continue;
			}
			else{
				/* User login. */
				screen.displayMessageLine("Account " + userAccount + " Login.\n");
				int option;
				do{
					/* Show the usage. */
					screen.displayMessage("Main_menu:\n"
						+ "1. View my balance\n"
						+ "2. Withdraw\n"
						+ "3. Deposit\n"
						+ "4. Loan\n"
						+ "5. Exit\n"
						+ "Enter a choice:");
					option = keypad.getInput();
					if (option == -1){
						/* Alert user if the input option isn't a positive number. */
						screen.displayMessageLine("Make sure your input is a Positive Number in correct format!\n");
						continue;
					}
					else{
						switch (option){
							case 1:
								/* BalanceInquiry. */
								transaction = new BalanceInquiry(userAccount, screen, bankDatabase);
								transaction.execute();
								break;
							case 2:
								/* Withdrawal. */
								transaction = new Withdrawal(userAccount, screen, bankDatabase, keypad);
								transaction.execute();
								break;
							case 3:
								/* Deposit. */
								transaction = new Deposit(userAccount, screen, bankDatabase, keypad);
								transaction.execute();
								break;
							case 4:
								/* Loan. */
								transaction = new Loan(userAccount, screen, bankDatabase, keypad);
								transaction.execute();
								break;
							case 5:
								/* User logout. */
								screen.displayMessageLine("Account " + userAccount + " Logout.\n");
								break;
							default:
								/* Alert user if the input option isn't from 1 to 5. */
								screen.displayMessageLine("Please make sure you input 1 to 5 to choose the right option!\n");
								break;
						}
					}
				} while(option != 5);
			}
		}
	}
}