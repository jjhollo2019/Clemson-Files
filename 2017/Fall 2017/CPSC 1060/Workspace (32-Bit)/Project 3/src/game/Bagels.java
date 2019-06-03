package game;
import java.util.Scanner;
public class Bagels { //start class

	
public static void main(String[] args) { //start main
	boolean Continue = true;
	while(Continue){ //start while loop 1
	Validator v1 = new Validator();
	Player p1 = new Player();
	Engine e1 = new Engine();
	int numberOfGames = 0;
	boolean running = true;
	Scanner keyboard = new Scanner(System.in);
	System.out.println("Welcome!");
	System.out.println("Enter the number of digits to use: ");
	int numDigits = keyboard.nextInt();
	keyboard.nextLine();
	e1.setNumDigits(numDigits);
	System.out.println("Enter the players name: ");
	p1.setName(keyboard.nextLine());
	while(running){ //start while loop 2
		e1.generateNewSecret();
		int[] secret = new int [numDigits];
		secret = e1.getSecretNumber();
		numberOfGames++;
		System.out.println("Starting game #" + numberOfGames);
		boolean incorrectGuess = true;
		int numberOfMovesTakenToWin = 0;
		while(incorrectGuess){ //start while loop 3
			String number = p1.askForGuess();
			int[] guess = e1.convertNumToDigitArray(number);
			numberOfMovesTakenToWin += 1;
			for (int a = 0; a < secret.length; a++){}
			if (v1.validateGuess(secret, guess, numDigits)){ //start if statement
				incorrectGuess = false;
				p1.updateStats(numberOfMovesTakenToWin);
				System.out.println("Congratulations! You won in " + numberOfMovesTakenToWin);
			} //end if statement
		} //end while loop 3
		boolean invalidAnswer = true;
		System.out.println("Statistics for " + p1.getName());
		System.out.println("Games completed: " + p1.getGamesCompleted());
		System.out.println("Number of digits:" + e1.getNumDigits());
		System.out.println("Fastest win: " + p1.getFastestWin());
		while(invalidAnswer){//start while loop 2
			System.out.println("p - Play again \nr - Reset \nq - Quit \n\n What would you like to do?");
			String answer = keyboard.next();
			if (answer.equalsIgnoreCase("q"))
				Continue = false;
			else if (answer.equalsIgnoreCase("r")){//start else if statement
				running = false;
				running = true;
				}//end if else statement
			else if (answer.equalsIgnoreCase("p"))
				invalidAnswer = true;
			else;
				}//end while loop 2
			}
		}//end while loop 1
	} //end main
} //end class
