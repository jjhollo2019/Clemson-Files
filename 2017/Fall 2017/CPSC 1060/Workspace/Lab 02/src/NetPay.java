import java.util.Scanner;
public class NetPay {

	public static void main(String[] args) {
	Scanner Keyboard = new Scanner(System.in); 
		double FEDERAL_TAX_PERCENT = 10;
		double STATE_TAX_PERCENT = 4.5;
		double SS_PERCENT = 6.2;
		double MEDICARE_PERCENT = 1.45;
		double PAY_PER_HOUR = 7.25;
	System.out.println("Enter number of hours per Week: ");
		int hoursPerWeek = Keyboard.nextInt();
	
	double grossPay = hoursPerWeek * PAY_PER_HOUR;
	double federalTax = (grossPay * FEDERAL_TAX_PERCENT) / 100;
	double stateTax = (grossPay * STATE_TAX_PERCENT) / 100;
	double socialSecurity = (grossPay * SS_PERCENT) / 100;
	double medicare = (grossPay * MEDICARE_PERCENT) / 100;
	double netPay = grossPay - federalTax - stateTax - medicare
			- socialSecurity;
	
	
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
