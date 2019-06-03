package game;
import java.util.Scanner;
public class Player { //start class

private String name;
private int fastestWin;
private int gamesCompleted;
private Scanner keyboard = new Scanner(System.in);

//setName method will set the user input to the private variable
public void setName(String name) { //start setName
	this.name = name;
	} //end setName

//getName will return the stored value of name
public String getName(){ //start getName
	return name;
} //start getName

//askForGuess method uses the keyboard to ask for user input
public String askForGuess() { //start ask for guess
	System.out.println("Enter your guess: ");
	return this.keyboard.nextLine();
} //end ask for guess

//getFastestWin method returns the stored value 
public int getFastestWin(){ //start getFastestWin
	return fastestWin;
} //end getFastestWin

//getGamesCompleted method returns the stored values
public int getGamesCompleted(){ //start getGamesCompleted
	return gamesCompleted;
} //end gatGamesCompleted

//updateStats method will update the stored values with input from the main
public void updateStats(int numberOfMovesTakenToWin){ //start updateStats
	gamesCompleted++;
	if (numberOfMovesTakenToWin < fastestWin)
		fastestWin = numberOfMovesTakenToWin;
} //end updateStats
	} //end class
