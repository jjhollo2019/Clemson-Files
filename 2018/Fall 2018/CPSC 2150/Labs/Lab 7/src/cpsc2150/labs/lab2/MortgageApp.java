/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 07
 * 10/19/2018
 */

package cpsc2150.labs.lab2;

import javax.swing.*;
import java.util.Scanner;

public class MortgageApp {

    public static void main(String [] args)
    {
        Scanner in = new Scanner(System.in);//establish input scanner
        System.out.println("Insert Customer's Name:");
        String name = in.nextLine();//get customer name
        System.out.println("What is your monthly debt payment?");
        double monthlyDebt = Double.parseDouble(in.nextLine());//get customer monthly debt
        if(monthlyDebt < 0)//check for invalid input
            do
            {
                System.out.println("Invalid input. Monthly debt must be greater than or equal to 0.");//print error
                System.out.println("What is your monthly debt payment?");//ask for input again
                monthlyDebt = Double.parseDouble(in.nextLine());//store value
            } while (monthlyDebt < 0);//check if value is greater than 0
        System.out.println("What is your Annual income?");//ask for income
        double annualIncome = Double.parseDouble(in.nextLine());//store income value
        if(annualIncome < 0)//check if value is in range
            do
            {
                System.out.println("Invalid input. Annual income must be greater than or equal to 0.");//print error
                System.out.println("What is your Annual income?");//ask for input again
                annualIncome = Double.parseDouble(in.nextLine());////store input
            } while (annualIncome < 0);//check for valid input
        System.out.println("What is your credit score?");//get customer credit score
        int creditScore = Integer.parseInt(in.nextLine());//store credit score
        if(creditScore < 0 || creditScore > 850)//check if in range
            do
            {
                System.out.println("Invalid input. Credit score must fall between 0 and 850.");//print error
                System.out.println("What is your credit score?");//ask for input again
                creditScore = Integer.parseInt(in.nextLine());//store credit score
            } while (creditScore < 0 || creditScore > 850);//check if in range
        System.out.println("How much is the home you wish to purchase?");//ask for house cost
        double houseCost = Integer.parseInt(in.nextLine());//store input
        if(houseCost < 0)//check if in range
            do
            {
                System.out.printf("Error: house cost must be >= 0");//print error
                System.out.println("what is the cost of the house?");//ask for input again
                houseCost = Integer.parseInt(in.nextLine());//store input
            } while(houseCost < 0);//check if value is in range
        System.out.println("What is your down payment?");//ask for down payment
        double downPayment = Integer.parseInt((in.nextLine()));//store input
        if(downPayment < 0)//check if in range
            do
            {
                System.out.println("Error: downpayment must be greater than or equal to 0");//print error
                System.out.println("What is your down payment?");//ask for input again
                downPayment = Integer.parseInt(in.nextLine());//store input
            } while(downPayment < 0);//check in range
        System.out.println("How many years do you want to finance your home for?");//ask for years to finance
        int years = Integer.parseInt(in.nextLine());//get input
        if(years != 15 && years != 20 && years != 25 && years != 30)//check if in range
            do
            {
                System.out.println("Error: must be financed for 15, 20, 25 , or 30 years.");//print error
                System.out.println("How many years would you like to finance the home for?");//ask for input again
                years = Integer.parseInt(in.nextLine());//store input
            } while(years != 15 && years != 20 && years != 25 && years != 30);//check if in range
        Customer cust = new Customer(monthlyDebt, annualIncome, creditScore, name);//initialize the customer object
        Mortgage loan = new Mortgage(houseCost, downPayment, years, cust);//initialize the loan object
        System.out.println(cust.toString()+loan.toString());//print customer details

    }
}