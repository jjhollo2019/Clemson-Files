/*
 * CreditCardPayOff.java
 * Author: Jeremy Holloway
 * Submission Date: 9/22/2017
 * 
 * Purpose: Compute the number of months needed to pay 
 * off a credit card debt.
 * 
 * Statement of Academic Honesty:
 * 
 * The following code represents my own work. I have neither
 * received nor given inappropriate assistance. I have not
 * copied or modified code from any source other than the course
 * webpage or the course textbook. i recognize that any unauthorized
 * assistance or plagiarism will be handled in accordance
 * with the policies at Clemson University and the policies
 * of this course. I recognize that my work is based on an assignment 
 * created by the School of Computing at Clemson University.
 * Any publishing or posting of source code for this project
 * is strictly prohibited unless you have written consent
 * from the instructor.
 */
import java.util.Scanner;
import java.lang.Math;
public class CreditCardPayOff {

	public static void main(String[] args) {
	Scanner Keyboard = new Scanner(System.in);
		// Step 1: User input the principal amount
	System.out.println("Enter Principal Amount: \t");
		double Principal = Keyboard.nextDouble();
		// Step 2: User input the annual interest rate
	System.out.println("Enter Annual Interest Rate: \t");
		double Annual_Interest_Rate = Keyboard.nextDouble();
		// Step 3: User input the monthly payment
	System.out.println("Enter Monthly Payment: \t");
		double Monthly_Payment = Keyboard.nextDouble();
		// Step 4: Calculate the numerator of the equation
		double mon = Math.log10(Monthly_Payment) - Math.log10( Monthly_Payment - ( Annual_Interest_Rate / 1200 ) * Principal );
		// Step 5: Calculate the denominator of the equation
		double ths = Math.log10( ( Annual_Interest_Rate / 1200 ) + 1) ;
		// Step 6: Calculate the ceiling value of the equation
		double months = Math.ceil( mon / ths );
		// Step 7: Calculate the total amount by multiplying the ceiling value of the months by the monthly payment
		double Total_Amount = ( months ) * Monthly_Payment;
		// Step 8: Calculate the total interest by subtracting the principal from the total amount
		double Total_Interest = Total_Amount - Principal;
		// Step 9: Calculate the overpayment by subtracting the raw month value times the monthly payment from the total amount
		double OverPay = Total_Amount - ( ( mon / ths ) * Monthly_Payment ); 
		// Step 10: Display the results with given decimal formatting
	System.out.printf( "Principal: \t\t\t%.2f%n",Principal );
	System.out.println( "Annual Interest Rate (%): \t" + Annual_Interest_Rate );
	System.out.printf( "Monthly Payment: \t\t%.2f%n",Monthly_Payment );
	System.out.println( " " );
	System.out.printf( "Months Needed to Pay Off: \t%.0f%n",months );
	System.out.printf( "Total Amount Paid: \t\t$%.2f%n$",Total_Amount );
	System.out.printf( "Total Interest Paid: \t\t$%.2f%n",Total_Interest );
	System.out.printf( "Overpayment: \t\t\t$%.2f",OverPay );

}

	}
