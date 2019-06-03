package game;
import java.util.Random;
public class Engine { //start class
	
private int numDigits;
private int[] secretNumber;
private Random randomNumberGenerator;

//convert method will take the user input string and convert it into a 
//data array indexed to the number of digits to play with
	public int[] convertNumToDigitArray(String number) {//start convert
		int[] numberArray = new int[(numDigits)];
		int digit = 0;
		int numVar = 0;
		numVar = Integer.parseInt(number);
		for (int a = 0; a < numberArray.length; a++){//start for loop
			digit = (numVar % 10);
			numVar = (digit / 10);
			numberArray[a] = digit;
		}//end for loop
		return numberArray;
	}//end convert

	//getNumDigits method will return the stored variable
	public int getNumDigits() { //start getNumDigits
		return numDigits;
	} //end getNumDigits
	
	//setNumDigits will store the user input into the stored variables
	public void setNumDigits(int numDigits) { //start setNumDigits
	this.numDigits = numDigits;
	}//end setNumDigits
	
	//generate method will create an array of random numbers set to a user specified length
	public void generateNewSecret(){ //start generate
		int[] numberArray = new int[(numDigits)];
		randomNumberGenerator = new Random();
		for (int a = 0; a < numberArray.length; a++)
			numberArray[a] = randomNumberGenerator.nextInt(9);
		secretNumber = numberArray;		
	} //end generate
	
	//getSecretNumber will return the stored user number
	public int[] getSecretNumber() { //start getSecretNumber
		return secretNumber;
	}//end getSecretNumber
} //end class
