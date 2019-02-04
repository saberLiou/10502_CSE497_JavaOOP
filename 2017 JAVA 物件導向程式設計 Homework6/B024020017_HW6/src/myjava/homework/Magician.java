package myjava.homework;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A sub class of Adventurer in the magician role whose unique is ES, namely energy shield.
 * @author saberLiou
 */
public class Magician extends Adventurer {
	/**
	 * Standard input object.
	 */
	private Scanner scan;
	
	/**
	 * Constructs a magician.
	 */
	public Magician() {
		super(40, 20, 0);
	}
	
	/**
	 * Triggers a magician's action.
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
				/* Cold Beam. */
				int coldBeam = attack_skill();
				System.out.println("[Cold Beam]: " + coldBeam + " damage");
				return coldBeam;
			case 2:
				/* Energy Shield. */
				int originalShield = getUnique();
				setUnique(defense_skill());
				System.out.println("[Energy Shield]: Shield + " + (getUnique() - originalShield) + " points");
				return 0;
			case 3:
				/* Heal. */
				int heal = buff_skill();
				if (heal > 40){
					setHp(40);
				}
				else{
					setHp(heal);
				}
				System.out.println("[Heal]: HP + " + heal + " points");
				return 0;
		}
	}
	
	/**
	 * Magician attack skill: Cold Beam.
	 * @return the attack amount of the cold beam
	 */
	@Override
	public int attack_skill() {
		return Hw6_main.randomGenerator(getAtk() * 4, getAtk() * 4 + 10);
	}

	/**
	 * Magician defense skill: Energy Shield.
	 * @return the new energy shield amount
	 */
	@Override
	public int defense_skill() {
		return Hw6_main.randomGenerator(getAtk() * 4, getAtk() * 4 + 10);
	}

	/**
	 * Magician buff skill: Heal.
	 * @return the recovered hp amount
	 */
	@Override
	public int buff_skill() {
		return Hw6_main.randomGenerator(getAtk() * 2, getAtk() * 2 + 10);
	}
}