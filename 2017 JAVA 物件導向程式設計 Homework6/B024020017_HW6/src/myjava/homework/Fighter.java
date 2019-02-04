package myjava.homework;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A sub class of Adventurer in the fighter role whose unique is DEF, namely defense.
 * @author saberLiou
 */
public class Fighter extends Adventurer {
	/**
	 * Standard input object.
	 */
	private Scanner scan;
	
	/**
	 * Constructs a fighter.
	 */
	public Fighter() {
		super(80, 40, 10);
	}

	/**
	 * Triggers a fighter's action.
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
				/* Brandish. */
				int brandish = attack_skill();
				System.out.println("[Brandish]: " + brandish + " damage");
				return brandish;
			case 2:
				/* Iron Body. */
				setUnique(defense_skill());
				if (getUnique() == Integer.MAX_VALUE){
					System.out.println("[Iron Body]: DEF Infinite!");
				}
				else{
					System.out.println("[Iron Body]: DEF + " + (getUnique() / 2) + " points");
				}
				return 0;
			case 3:
				/* Rage. */
				setAtk(buff_skill());
				if (getAtk() == Integer.MAX_VALUE){
					System.out.println("[Rage]: ATK Infinite!");
				}
				else{
					System.out.println("[Rage]: ATK + " + (getAtk() / 2) + " points");
				}
				return 0;
		}
	}
	
	/**
	 * Fighter's attack skill: Brandish.
	 * @return the attack amount of the brandish, or infinite
	 */
	@Override
	public int attack_skill() {
		if (getAtk() == Integer.MAX_VALUE){
			return getAtk();
		}
		else{
			return Hw6_main.randomGenerator(getAtk(), getAtk() + 10);
		}
	}

	/**
	 * Fighter's defense skill: Iron Body.
	 * @return the double unique, or infinite
	 */
	@Override
	public int defense_skill() {
		if (getUnique() < Integer.MAX_VALUE / 2){
			return getUnique() * 2;
		}
		else{
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Fighter's buff skill: Rage.
	 * @return the double attack, or infinite
	 */
	@Override
	public int buff_skill() {
		if (getAtk() < Integer.MAX_VALUE / 2){
			return getAtk() * 2;
		}
		else{
			return Integer.MAX_VALUE;
		}
	}
}