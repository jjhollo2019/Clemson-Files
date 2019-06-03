package Loops;
import java.util.Scanner;
public class GreatestCommonDivisor {

	public static void main(String[] args) {
	Scanner Keyboard = new Scanner(System.in);
	
	System.out.println("Enter first interger: ");
	int n1 = Keyboard.nextInt();
	System.out.println("Enter second integer: ");
	int n2 = Keyboard.nextInt();
	Keyboard.close();
	int gcd = 1;
	int k = 2;
	while (k <= n1 && k <= n2) {
		if (n1 % k == 0 && n2 % k == 0)
		 gcd = k;
		k++;
	}
		
	System.out.println("The greatest common divisor for " + n1 + 
	" and " + n2 + " is " + gcd);
	
		}
	}


