/*
 * NetPay.java
 * Author: Jeremy Holloway
 * Submission Date: 9/14/2017
 * 
 * Purpose: Compute a person's gross pay and net pay based on 
 * on their hourly wage, hours worked, and several witholdings
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
public class NetPay {

	public static void main(String[] args) {
	Scanner Keyboard = new Scanner(System.in); 
	final double FEDERAL_TAX_PERCENT = 10;
	final double STATE_TAX_PERCENT = 4.5;
	final double SS_PERCENT = 6.2;
	final double MEDICARE_PERCENT = 1.45;
	final double PAY_PER_HOUR = 7.25;
	// Step 1: Ask for hours per week input
	System.out.println("Enter number of hours per Week: ");
	 int hoursPerWeek = Keyboard.nextInt();
	
	// Step 2: Calculate gross pay
	double grossPay = hoursPerWeek * PAY_PER_HOUR;
	// Step 3: Calculate federal tax
	double federalTax = (grossPay * FEDERAL_TAX_PERCENT) / 100;
	// Step 4: Calculate state tax
	double stateTax = (grossPay * STATE_TAX_PERCENT) / 100;
	// Step 5: Calculate social security withholding
	double socialSecurity = (grossPay * SS_PERCENT) / 100;
	// Step 6: Calculate medicare deduction
	double medicare = (grossPay * MEDICARE_PERCENT) / 100;
	// Step 7: Calculate net pay
	double netPay = grossPay - federalTax - stateTax - medicare
			- socialSecurity;
	// Step 8: Display Calculations
	System.out.println("Hours per Week:\t\t" + hoursPerWeek);
	System.out.println("Gross Pay:\t\t"  + grossPay);
	System.out.println("Net Pay:\t\t" + netPay);
	System.out.println(" ");
	System.out.println("Deductions");
	System.out.println("Federal:\t\t" + federalTax);
	System.out.println("State:\t\t\t" + stateTax);
	System.out.println("Social Security:\t" + socialSecurity);
	System.out.println("Medicare:\t\t" + medicare);
		
				

	}

}