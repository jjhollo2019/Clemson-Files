package classtests;

import java.util.Random;

public class projects {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String number = ("358");
		int numDigits = 3;
		Random randomNumberGenerator;
		int[] secretNumber;
		int[] numberArray = new int[(numDigits - 1)];
		randomNumberGenerator = new Random();
		for (int a = 0; a < numberArray.length; a++){
			numberArray[a] = randomNumberGenerator.nextInt(9);
		secretNumber = numberArray;		
	}
		String pr = ("");
		for (int b = 0; b < numberArray.length; b++)
			pr += (numberArray[b] + ", ");
		System.out.print(pr);
		
		
		
		
	}

}
