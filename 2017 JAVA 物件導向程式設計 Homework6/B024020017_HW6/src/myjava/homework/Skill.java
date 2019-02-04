package myjava.homework;

/**
 * An interface of the adventurer's three abstract skills.
 * @author saberLiou
 */
public interface Skill {
	/**
	 * Attack skill of an adventurer.
	 * @return the attack output amount
	 */
	public int attack_skill();
	
	/**
	 * Defense skill of an adventurer.
	 * @return the defense output amount
	 */
	public int defense_skill();
	
	/**
	 * Buff skill of an adventurer.
	 * @return the buff output amount
	 */
	public int buff_skill();
}