package project5_dice_game;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ClassSection0W1_Project5_Do_Michelle {

		// Programmer: Michelle Do
		// Class Section: 3311.0w1
			
		/* 
		Refactor your solution from Project 4 to implement object classes, Dice and Messages, 
		with instance variables and methods.
		Instantiate objects to access the methods and integrate them as part of the Project 5 solution.
		*/
	
	public static void main(String[] args) {
		
		
		// Declare vars
		int die1;
		int die2;
		int tempDie;
		int sum;
		int pairBonus = 0;
		int seven11Bonus = 0;
		int total;
		String name;
		int counter = 1;
		int games;
		String playAgain = "N";
		int score;
	
		// Instantiate objects
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		LocalDate date = LocalDate.now();
		
		//Prompt for and read-in the user's name
		System.out.print("Please enter your name: ");
		name = scan.next();
		
		// System.out.println(name); // Interim Test
		
		// Invoke prtWelcome(String name) method from Messages class
		Messages welcome = new Messages();
		welcome.prtWelcome(name);
		
		// Play again do while loop
		do {
					
			// Instantiate Dice object
			Dice dice = new Dice();
			
			// Reinitialize counter
			counter = 1;
			
			// Reinitialize the bonuses for the new game set
			pairBonus = 0;
			seven11Bonus = 0;
			
			// Prompt the user to enter how many games they would like to play
			System.out.println();
			System.out.print("Please enter how many games would you like to play: ");
			games = scan.nextInt();
			
			// Game counter while loop
			while (counter <= games) {
				
				// Reinitialize the bonuses for each roll
				pairBonus = 0;
				seven11Bonus = 0;
			
				// Prompt the user to roll the dice
				System.out.println();
				System.out.println("Are you ready to roll the dice?");
				System.out.print("Press any key on your keyboard and then enter to roll the dice: ");
				scan.next();
				System.out.println();
				System.out.println("-----------------------------------------------");
				System.out.println();
				
				// Dice are rolled and the timer is started
				die1 = rand.nextInt(6) + 1;
				die2 = rand.nextInt(6) + 1;
				
				// Invoke setDice() method from Dice class
				// Set dice value
				dice.setDice();
				long startTime = System.currentTimeMillis();
				
				// Invoke getDice() method from Dice class
				int[] diceValues = dice.getDice();
				System.out.println("Dice Roll: " + Arrays.toString(diceValues));
				
				// Calculate sum
				sum = Arrays.stream(diceValues).sum();
				System.out.println("Score: " + sum);
				
				// Separate the score and bonus points
				System.out.println();
				
				// Determine if, Doubles (aka Pairs), 7/11 (aka Natural) or Craps
				// Invoke getEval() method from Dice class
				String eval = dice.getEval(die1, die2, sum);
				
				// Implement switch statement
				switch(eval) {
				case "D": // Doubles Bonus
					pairBonus = 10;
					break;
				case "N": // 7/11 Bonus
					seven11Bonus = 5;
					break;
				case "C": // Craps
					sum = 0;
					System.out.println("Oh no! You rolled craps!");
					break;
				}
				
				// Display bonuses
				System.out.println("Doubles (aka Pairs) Bonus: " + pairBonus);
				System.out.println("7/11 (aka Natural) Bonus: " + seven11Bonus);
				
				// Calculate the total
				total = sum + pairBonus + seven11Bonus;
				System.out.println();
				System.out.println("Total: " + total);
			
				// The timer is stopped
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				System.out.println("Elapsed Time: " + elapsedTime + " milliseconds");
				
				// Print the date
				System.out.println("Date: " + date);
				
				// Make the outcome look more organized
				System.out.println();
				System.out.println("-----------------------------------------------");
				
				// Increment game counter
				counter++;
				
			} // End of game counter while loop
		
		// End of play again do while loop
		} while(playAgain(scan,name).equals("Y")); // Invoke playAgain method
		
		// Invoke prtClose method to print close method
		Messages close = new Messages();
		close.prtClose(name);
		
		scan.close();
			
	} // End of main method, class methods are below
	
	// Method playAgain (prompts the user to indicate whether or not they wish to play again)
	public static String playAgain(Scanner newScan, String newName) {
		System.out.println();
		System.out.print("Would you like to play again, " + newName + "? Enter Y or N: ");
		String playAgain = newScan.next().toUpperCase();
		System.out.println();
		System.out.println("-----------------------------------------------");
		return playAgain;
	}
			
} // End of Project5 class

// New class called Dice class
class Dice {
	
	// Declare vars
	private int[] dice = new int[2];
	private int sum;
	
	// Implement setDice() method
	public void setDice() {
		Random rand = new Random();
		sum = 0;
		for(int i = 0; i < dice.length; i++) {
			dice[i] = rand.nextInt(6) + 1;
		}
		Arrays.sort(dice);
	}
	
	// Implement getDice() method
	public int[] getDice() {
		return dice;
	}
	
	// Implement getEval() method
	public String getEval(int newDie1, int newDie2, int newDiceSum) {
		String eval = " ";
		// Determine if, Doubles (aka Pairs), 7/11 (aka Natural) or Craps
		// Doubles Bonus
		if (newDie1 == newDie2 && !(newDie1 == 1 || newDie1 == 6)) { // Nullifies the pair bonus in case of 2 and 12
			eval = "D";
		}
		
		// 7/11 Bonus
		if (newDiceSum == 7 || newDiceSum == 11) {
			eval = "N";
		}
		
		// Craps
		if (newDiceSum == 2 || newDiceSum == 3 || newDiceSum == 12) {
			eval = "C";
		}
		
		return eval;
	}
	
} // End of Dice class

// New class called Messages class
class Messages {
	
	// Implement prtWelcome(String name)
	public void prtWelcome(String newName) {
		// Print welcome message and rules
		System.out.println();
		System.out.print("Welcome to the game of Dice, " + newName + "!");
		System.out.println();
		System.out.println("Here are the rules of the game...");
		System.out.println("In this game, the computer will roll the dice for you.");
		System.out.println("You will earn points based on the sum of the 2 dice.");
		System.out.println("If you roll a pair (aka Doubles), you will earn an additional 10 bonus points.");
		System.out.println("If you roll a 7 or 11 (aka a Natural), you will earn an additional 5 bonus points.");
		System.out.println("If you roll a 2, 3, or 12 (aka Craps), your total score is wiped out and you will get 0 points.");
		System.out.println("In the case of 2 and 12, the pair bonus is nullified because of the 0 for rolling Craps.");
		System.out.println("Good luck, and let the game of Dice begin!");	
	}
	
	// Implement prtClose(String name)
	// Method prtClose (prints the closing method)
	public void prtClose(String newName) {
		System.out.println();
		System.out.println("Thank you, " + newName + ", for playing the game of Dice!");
		System.out.println("Please come back and play again any time! :)");
	}
	
}	
