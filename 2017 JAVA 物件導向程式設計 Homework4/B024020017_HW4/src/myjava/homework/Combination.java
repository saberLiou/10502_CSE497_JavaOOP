package myjava.homework;

/**
 * A class to check Combination of 4-Star Game.
 * @author saberLiou
 */
public class Combination extends fourStarGame{
	/**
	 * Checks if the 4 numbers in userNums are the same with the 4 numbers in winNums
	 * without order of precedence.
	 */
	public void checkOfWin(){
		/* Generate winNums and userNums. */
		super.generateWinNums();
		super.generateUserNums();
		
		if (super.getUserNums().size() == 4){
			if (super.getUserNums().containsAll(super.getWinNums())){
				System.out.println("**You win!");
			}
			else{
				System.out.println("**You lose!");
			}
			/*
			for (int n : super.getWinNums()){
				if (!super.getUserNums().contains(n)){
					System.out.println("**You lose!");
					return;
				}
			}
			System.out.println("**You win!");*/
		}
		else{
			/* Alert wrong input if length of userNums isn't four,
			 * or userNums contains Non-digit ones.
			 */
			System.out.println("Wrong input, try again.");
		}
	}
}