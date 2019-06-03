/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 03
 * 9/14/2018
 */
/**
 * Created by kplis on 1/23/2018.
 */
public class Mortgage {



    private double houseCost;
    private double downPayment;
    private double percentDown;
    private int years;
    private double interestRate;
    private double payment;
    private Customer customer;

    /**
     * Function: Mortgage(double cost, double dp, int y, Customer cust)
     * Description: This function is the default constructor for the class
     * Input: double cost, double dp, int y, Customer cust
     * Output: Initialized Customer object
     * @param cost is the cost of the house
     * @param dp is the down payment on the house
     * @param y is the number of financed years
     * @param cust is the customer data attached to the loan
     * @pre
     * cost >= 0, dp >= 0, y >= 0, Customer object must exist
     * @post
     * Mortgage object exists
     */
    Mortgage(double cost, double dp, int y, Customer cust)
    {


        /*
        Sets the house cost, down payment, years and customer. Calculates the percent down, rate, and payment
         */
    }

    /**
     * Function: calcRate()
     * Description: This function will calculate the interest rate for the mortgage
     * Input: none
     * Output: none
     * @pre
     * years > 0, interestRate = 2.5
     * @post
     * interestRate will reflect the calculated interest rate
     */
    private void calcRate()
    {
        /*
        Calculates the interest rate to use.
        The rate starts at the base rate of 2.5%
        If the loan is for 30 years, add the normal addition of 1%
        If the loan is for for less than 30 years, add the good addition of .5%
        The loan must be for 15, 20, 25 or 30 years

        Add the rate for the credit score based on the following table
        Credit rating | Credit score | add to rate
        Bad           | 0 - 500      | 10%
        Fair          | 500 - 599    | 5%
        Good          | 600 - 699    | 1%
        Great         | 700 - 749    | .5%
        Excellent     | 750 - 850    | 0%

        If the down payment is less than 20% of the price of the house, add .5%
         */


    }

    /**
     * Function: calcPayment()
     * Description: This function will calculate the monthly payment
     * Input: none
     * Output: none
     * @pre
     * income >= 0, houseCost >= 0, downPayment >= 0
     * @post
     * payment will reflect the calculated payment amount
     */
    private void calcPayment()
    {
        /*
        Calculate the monthly payment
        terms:
        r - monthly rate - the interest rate divided by 12
        p - principal balance - the cost of the house minus the down payment
        n - number of payments - the total number of monthly payments

        monthly payment is (rp)/(1-(1+r)^-n)
         */

    }

    /**
     * Function: loanApproved()
     * Description: This function will determine if the loan is approved or not
     * Input: none
     * Output: true/false
     * @pre
     * interestRate >= 0, income >= 0, income >= 0, monthlyDebtPayments >= 0, payment >= 0
     * @post
     * The function will output the loan disposition
     */
    public boolean loanApproved()
    {
        /*
        If the intereset rate is too high (10% or higher) the loan is denied
        If the down payment is less than 3.5% of the price of the house then the loan is denied

        If the Debt to income ratio is above 40% the loan is denied
        The debt to income ratio is the total monthly debt payments (including the mortgage payment) / monthly income

        Otherwise the loan is approved
         */

        return true;
    }

    /**
     * Function: getPayment()
     * Description: This function will return payment
     * Input: none
     * Output: payment
     * @pre
     * payment >= 0
     * @post
     * payment remains a private member
     */
    public double getPayment()
    {
        //return the monthly payment on the loan
        return 0;
    }

    /**
     * Function: getRate()
     * Description: This function will return interestRate
     * Input: none
     * Output: interestRate
     * @pre
     * interestRate >= 0;
     * @post
     * interestRate remains a private member
     */
    public double getRate()
    {
        //return the interest rate on the loan
        return 0;
    }

    /**
     * Function: toString()
     * Description: This function will override the toString function
     * Input: none
     * Output: formatted print string
     * @pre
     * houseCost, downPayment, interestRate, years, payment >= 0
     * @post
     * formatted print string is displayed to the user
     */
    @Override
    public String toString()
    {
        //this function is provided
        //DO you need contracts for this?
        String str = "";
        if(loanApproved())
        {
            str += "Principal Amount: $" + (houseCost - downPayment) + "\n";
            str += "Interest Rate: " + (interestRate * 100) + "%\n";
            str += "Term: " + years + " years\n";
            str += "Monthly Payment: $" + payment + "\n";
        }
        else
        {
            str += "Loan was not approved";
        }
        return str;
    }

}
