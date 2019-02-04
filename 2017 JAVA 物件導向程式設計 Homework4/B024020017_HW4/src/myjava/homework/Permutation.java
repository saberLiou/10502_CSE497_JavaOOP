package myjava.homework;

/**
 * A class to check Permutation of 4-Star Game.
 * @author saberLiou
 */
public class Permutation extends fourStarGame{
	/**
	 * Checks if the 4 numbers in userNums are the same with the 4 numbers in winNums
	 * with the same order.
	 */
	public void checkOfWin(){
		/* Generate winNums and userNums. */
		super.generateWinNums();
		super.generateUserNums();
		
		if (super.getUserNums().size() == 4){
			for (int i = 0; i < 4; i++){
				if (super.getUserNums().get(i) != super.getWinNums().get(i)){
					System.out.println("**You lose!");
					return;
				}
			}
			System.out.println("**You win!");
		}
		else{
			/* Alert wrong input if length of userNums isn't four,
			 * or userNums contains Non-digit ones.
			 */
			System.out.println("Wrong input, try again.");
		}
	}
}