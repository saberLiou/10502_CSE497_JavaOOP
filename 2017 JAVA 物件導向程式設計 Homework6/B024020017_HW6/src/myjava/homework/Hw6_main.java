package myjava.homework;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

/**
 * A game of killing a randomly-generated monster
 * with three fighting role: fighter, magician and archer.
 * @author saberLiou
 */
public class Hw6_main {
	/** 
	 * Standard input object.
	 */
	private static Scanner scan;
	
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		Adventurer adventurer = null;
		int monsterHp = 0;
		int monsterAtk = 0;
		
		while (true){
			/* Show the role option. */
			System.out.print("(1) Fighter    (2) Magician    (3) Archer\n"
					+ "Choose your character (By default: (1)): ");
			scan = new Scanner(System.in);
			int option;
			try{
				option = scan.nextInt();
			} catch(InputMismatchException e) {
				option = 1;
			}
			
			/* Generate a monster. */
			monsterHp = randomGenerator(150, 300);
			monsterAtk = randomGenerator(30, 35);
			System.out.println("[Monster appeared!]");
			
			switch (option){
				default:
				case 1:
					/* Fighter. */
					adventurer = new Fighter();
					while(true) {
						statusBoard("Fighter", adventurer.getHp(), adventurer.getAtk(), adventurer.getUnique(), monsterHp, monsterAtk);
						if (adventurer.getHp() == 0){
							/* User lose. */
							System.out.println("You dead...");
							break;
						}
						else{
							/* Show the action option. */
							System.out.println("(1) Brandish (2) Iron Body (3) Rage");
							System.out.print("Action (By Default: (1)) ");
							monsterHp -= adventurer.action();
							if (monsterHp <= 0){
								/* User win. */
								statusBoard("Fighter", adventurer.getHp(), adventurer.getAtk(), adventurer.getUnique(), 0, monsterAtk);
								System.out.println("You win...");
								break;
							}
							else{
								/* Generate monster launched attack. */
								int monsterAtkGo = randomGenerator(monsterAtk, monsterAtk + 10);
								System.out.println("[Monster]: " + monsterAtkGo + " damage.");
								
								if (monsterAtkGo <= adventurer.getUnique()){
									/* Fighter's defense is enough to bear monster's damage. */
									System.out.println("[Fighter]: HP - 0 points.");
								}
								else{
									/* Fighter's defense isn't enough to bear monster's damage. */
									System.out.println("[Fighter]: HP - " + (monsterAtkGo - adventurer.getUnique()) + " points.");
									adventurer.setHp(adventurer.getHp() - (monsterAtkGo - adventurer.getUnique()));
								}
							}	
						}
					}
					break;
				case 2:
					/* Magician. */
					adventurer = new Magician();
					while (true) {
						statusBoard("Magician", adventurer.getHp(), adventurer.getAtk(), adventurer.getUnique(), monsterHp, monsterAtk);
						if (adventurer.getHp() == 0){
							/* User lose. */
							System.out.println("You dead...");
							break;
						}
						else{
							/* Show the action option. */
							System.out.println("(1) Cold Beam (2) Energy Shield (3) Heal");
							System.out.print("Action (By Default: (1)) ");
							monsterHp -= adventurer.action();
							if (monsterHp <= 0){
								/* User win. */
								statusBoard("Magician", adventurer.getHp(), adventurer.getAtk(), adventurer.getUnique(), 0, monsterAtk);
								System.out.println("You win...");
								break;
							}
							else{
								/* Generate monster launched attack. */
								int monsterAtkGo = randomGenerator(monsterAtk, monsterAtk + 10);
								System.out.println("[Monster]: " + monsterAtkGo + " damage.");
								
								if (monsterAtkGo <= adventurer.getUnique()){
									/* Migician's shield is enough to bear monster's damage. */
									System.out.println("[Energy Shield]: Shield - " + monsterAtkGo + " points");
									adventurer.setUnique(adventurer.getUnique() - monsterAtkGo);
								}
								else{
									/* Migician's shield isn't enough to bear monster's damage. */
									System.out.println("[Energy Shield]: Shield - " + adventurer.getUnique() + " points");
									System.out.println("[Magician]: HP - " + (monsterAtkGo - adventurer.getUnique()) + " points.");
									adventurer.setHp(adventurer.getHp() - (monsterAtkGo - adventurer.getUnique()));
									adventurer.setUnique(0);
								}
							}
						}
					}
					break;
				case 3:
					/* Archer. */
					adventurer = new Archer();
					while (true) {
						statusBoard("Archer", adventurer.getHp(), adventurer.getAtk(), adventurer.getUnique(), monsterHp, monsterAtk);
						if (adventurer.getHp() == 0){
							/* User lose. */
							System.out.println("You dead...");
							break;
						}
						else{
							/* Show the action option. */
							System.out.println("(1) Arrow Blow (2) Speed Boost (3) Critical Shot");
							System.out.print("Action (By Default: (1)) ");
							monsterHp -= adventurer.action();
							if (monsterHp <= 0){
								/* User win. */
								statusBoard("Archer", adventurer.getHp(), adventurer.getAtk(), adventurer.getUnique(), 0, monsterAtk);
								System.out.println("You win...");
								break;
							}
							else{
								/* Generate monster launched attack. */
								int monsterAtkGo = randomGenerator(monsterAtk, monsterAtk + 10);
								System.out.println("[Monster]: " + monsterAtkGo + " damage.");
								
								/* Decide evasion successed or failed. */
								int isEvasion = randomGenerator(1, 100);
								if (isEvasion <= adventurer.getUnique()){
									/* Archer is able to evase. */
									System.out.println("Evasion succeed!");
								}
								else{
									/* Archer isn't able to evase. */
									System.out.println("[Archer]: HP - " + monsterAtkGo + " points.");
									adventurer.setHp(adventurer.getHp() - monsterAtkGo);
								}
							}
						}
					}
					break;
			}
		}
	}
	
	/**
	 * Generates random number.
	 * @param minimum the minimum bound
	 * @param maximum the maximum bound
	 * @return the random number
	 */
	public static int randomGenerator(int minimum, int maximum) {
		Random random = new Random();
		return random.nextInt((maximum - minimum) + 1) + minimum;
	}
	
	/**
	 * The status board of the current fighting adventurer and the monster.
	 * @param role the fighting adventurer role
	 * @param adventurerHp the adventurer hp
	 * @param adventurerAtk the adventurer attack
	 * @param adventurerUnique the adventurer unique
	 * @param monsterHp the monster hp
	 * @param monsterAtk the monster attack
	 */
	private static void statusBoard(String role, int adventurerHp, int adventurerAtk, int adventurerUnique, int monsterHp, int monsterAtk) {
		System.out.println("----Adventurer----      ----Monster----");
		System.out.printf("  HP: %-2d                  HP: %-3d\n", adventurerHp, monsterHp);
		switch(role){
			case "Fighter":
				if (adventurerAtk == Integer.MAX_VALUE){
					System.out.printf("  ATK: Infinite           ATK: %-2d\n", monsterAtk);
				}
				else{
					System.out.printf("  ATK: %-10d         ATK: %-2d\n", adventurerAtk, monsterAtk);
				}
					
				if (adventurerUnique == Integer.MAX_VALUE){
					System.out.println("  DEF: Infinite");
				}
				else{
					System.out.println("  DEF: " + adventurerUnique);
				}
				break;
			case "Magician":
				System.out.printf("  ATK: %-2d                 ATK: %-2d\n", adventurerAtk, monsterAtk);
				System.out.println("  ES: " + adventurerUnique);
				break;
			case "Archer":
				System.out.printf("  ATK: %-2d                 ATK: %-2d\n", adventurerAtk, monsterAtk);
				System.out.println("  EVA: " + adventurerUnique);
				break;
		}
		System.out.println("-----------------       ---------------");
	}
}