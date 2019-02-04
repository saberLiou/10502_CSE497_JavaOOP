package myjava.homework;

/**
 * An abstract super class of an adventurer.
 * @author saberLiou
 */
public abstract class Adventurer implements Skill {
	private int hp;
	private int atk;
	private int unique;
	
	/**
	 * Constructs an adventurer.
	 * @param hp the adventurer hp
	 * @param atk the adventurer attack
	 * @param unique the adventurer unique
	 */
	public Adventurer(int hp, int atk, int unique){
		this.hp = hp;
		this.atk = atk;
		this.unique = unique;
	}
	
	/**
	 * Gets the hp of the adventurer.
	 * @return the hp of the adventurer
	 */
	protected int getHp(){
		return hp;
	}
	
	/**
	 * Gets the attack of the adventurer.
	 * @return the attack of the adventurer
	 */
	protected int getAtk(){
		return atk;
	}
	
	/**
	 * Gets the unique of the adventurer.
	 * @return the unique of the adventurer
	 */
	protected int getUnique(){
		return unique;
	}
	
	/**
	 * Sets the hp of the adventurer.
	 * @param hp the hp of the adventurer
	 */
	protected void setHp(int hp){
		if (hp < 0){
			this.hp = 0;
		}
		else{
			this.hp = hp;
		}
	}
	
	/**
	 * Sets the attack of the adventurer.
	 * @param atk the attack of the adventurer
	 */
	protected void setAtk(int atk){
		this.atk = atk;
	}
	
	/**
	 * Sets the unique of the adventurer.
	 * @param unique the unique of the adventurer
	 */
	protected void setUnique(int unique){
		if (unique < 0){
			this.unique = 0;
		}
		else{
			this.unique = unique;
		}
	}
	
	/**
	 * Triggers an action.
	 * @return the action output amount
	 */
	protected abstract int action();
}