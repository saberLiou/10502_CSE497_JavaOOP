package myjava.homework;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A program that checks whether the number input by user is a prime number,
 * and print out all the prime numbers from 2 to the number user input.
 * @author saberLiou
 */
public class Prime {
	/**
	 * Checks whether it's prime number.
	 * @param num the input number
	 * @return true if it's a prime number
	 */
	public static boolean isPrime(int num) {
		/* Square-root the input number, round down and cast it to integer. */
		int sqrtFloorNum = (int) Math.floor(Math.sqrt(num));
		for (int i = 2; i <= sqrtFloorNum; i++){
			if (num % i == 0){
				/* If the input number is the multiple of any number
				   from 2 to the root of itself, return false. */
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args not used
	 */
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
						/* Check whether it's prime number. */
						System.out.println("Input the number:");
						num = scan.nextInt();
						/* Check whether the input number is greater than 2. */
						if (num < 2){
							System.out.println("Input error : N must equal greater than 2");
							break;
						}
						if (isPrime(num)){
							System.out.println(num + " is a prime");
						}
						else{
							System.out.println(num + " is a not prime");
						}
						break;
					case 2:
						/* Find prime number(2~N). */
						System.out.println("Input the number:");
						num = scan.nextInt();
						/* Check whether the input number is greater than 2. */
						if (num < 2){
							System.out.println("Input error : N must equal greater than 2");
							break;
						}
						System.out.println("Show the prime numbers(2 ~ " + num + ")");
						for (i = 2; i <= num; i++){
							if (isPrime(i)){
								System.out.print(i);
								
								/* Indent all the prime numbers. */
								count++;
								if (count == 10){
									System.out.println("");
									count = 0;
								}
								else{
									System.out.print("\t");
								}
							}
						}
						System.out.println("");
						break;
					case 3:
						/* End the process. */
						System.out.println("Bye!!!");
						/* Close the Scanner. */
						scan.close();
						break;
					default:
						System.out.println("Input error : incorrect option");
						break;
				}
				if (option == 3){
					/* Escape from while loop. */
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Input error : ONLY Integers.");
			}
		}
	}
}