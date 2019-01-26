public class Prime {
	/* Check whether it's prime number */
	public static boolean isPrime(int num) {
		// Write down your code.
		
		
	}

	public static void main(String[] args) {
		int option, num, i, count = 0;
		Scanner scan = null;

		while (true) {
			try {
				/* Get standard input object. */
				scan = new Scanner(System.in);
				/* Print message. */
				System.out.println("1. Check whether it's prime number\n" + "2. Find prime number(2~N)\n" + "3. Leave");
				/* Input an integer. */
				option = scan.nextInt();
				switch (option) {
				case 1:
					/* Write down your code. */
					/* Check whether it's prime number */
					System.out.println("Input the number:");
					
					
					break;
				case 2:
					/* Write down your code. */
					/* Find prime number(2~N) */
					System.out.println("Input the number:");
					
					
					break;
				case 3:
					/* Write down your code. */
					/* End the process */
					
					
					break;
				default:
					System.out.println("Input error : incorrect option");
					break;
				}
			} catch (InputMismatchException e) {
					System.out.println("Input error : ONLY Integers.");
			}
		}
	}
}