package myjava.homework;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A sub class of Adventurer in the archer role whose unique is EVA, namely evasion rate.
 * @author saberLiou
 */
public class Archer extends Adventurer {
	/**
	 * Standard input object.
	 */
	private Scanner scan;
	/**
	 * Critical rate.
	 */
	private int cri;
	
	/**
	 * Constructs an archer.
	 */
	public Archer() {
		super(70, 60, 40);
		cri = 0;
	}
	
	/**
	 * Triggers an archer's action.
	 * @return the action output amount
	 */
	@Override
	public int action() {
		scan = new Scanner(System.in);
		int option;
		try{
			option = scan.nextInt();
		} catch(InputMismatchException e) {
			option = 1;
		}
		
		switch (option){
			default:
			case 1:
				/* Arrow Blow. */
				int arrowBlow = attack_skill();
				System.out.println("[Arrow Blow]: " + arrowBlow + " damage");
				return arrowBlow;
			case 2:
				/* Evasion Boost. */
				setUnique(defense_skill());
				System.out.println("[Evasion Boost]: EVA: " + getUnique() + "%");
				return 0;
			case 3:
				/* Critical Shot. */
				cri = buff_skill();
				System.out.println("[Critical Shot]: CRI: " + cri + "%");
				return 0;
		}
	}
	
	/**
	 * Archer attack skill: Arrow Blow
	 * @return the attack amount of arrow blow, double if critical
	 */
	@Override
	public int attack_skill() {
		/* Decide whether an arrow blow is a critical shot. */
		int isCri = Hw6_main.randomGenerator(1, 100);
		if (isCri <= cri){
			return Hw6_main.randomGenerator(getAtk(), getAtk() + 10) * 2;
		}
		else{
			return Hw6_main.randomGenerator(getAtk(), getAtk() + 10);
		}
	}

	/**
	 * Archer defense skill: Evasion Boost.
	 * @return the double evasion rate up to 100% limited
	 */
	@Override
	public int defense_skill() {
		if (getUnique() < 50){
			return getUnique() * 2;
		}
		else{
			return 100;
		}
	}

	/**
	 * Archer buff skill: Critical Shot.
	 * @return the critical rate with an addition of 25%, up to 100% limited
	 */
	@Override
	public int buff_skill() {
		if (cri < 100){
			return cri + 25;
		}
		else{
			return 100;
		}
	}
}