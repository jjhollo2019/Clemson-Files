/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 03
 * 9/14/2018
 */
/**
 * Created by kplis on 1/23/2018.
 */
public class Customer {
    private String name;

    private double monthlyDebtPayments;
    private double income;
    private int creditScore;
    private Mortgage loan;

    /**
     * Function: Customer(double debt, double inc, int score, String n)
     * Description: This function is the constructor for the customer class
     * Input: double debt, double inc, int score, String n
     * Output: Customer object
     * @param debt is the amount of debt tied to this customer
     * @param inc is the income of this customer
     * @param score is the calculated credit score for this customer
     * @param n is the name of this customer
     * @return a created customer object
     * @pre
     * debt >= 0, inc >= 0, score >= 0, n != NULL
     * @post
     * Customer object exists with intiated variables
     */
    Customer( double debt, double inc, int score, String n)
    {

        /*
        Set the monthly debt payments, income, credit score and name
        Credit scores range from 0 - 850
         */


    }

    /**
     * Function: applyForLoan(double downPayment, double houseCost, int years)
     * Description: This function will store store the details of the customer loan and returns
     * the final disposition of the loan approval
     * Input: double downPayment, double houseCost, int years
     * Output: boolean true/false
     * @param downPayment is the amount the customer will pay at the time of the loan
     * @param houseCost is the agreed upon buying price for the property
     * @param years is the amount of years the customer will finance for
     * @return a boolean value for the loan dispostion
     * @pre
     * downPayment >= 0, houseCost > 0, years > 0
     * @post
     * true/false
     */
    public boolean applyForLoan(double downPayment, double houseCost, int years)
    {
        //Take the parameters and apply for a loan. Save the loan details as the customers current loan
        // Return whether or not the loan was approved
        return true;

    }

    /**
     * Function: getRate()
     * Description: This function will return the value stored privately in the mortgage class
     * Input: none
     * Output: interestRate
     * @return will return the interest amount on the mortgage
     * @pre
     * Mortgage object exists
     * @post
     * interestRate is returned
     */
    public double getRate()
    {
        //return the interest rate on the current loan
        return 0;
    }

    /**
     * Function: getMonthlyPay()
     * Description: This function will return the monthly pay
     * Input: none
     * Output: income / 12
     * @pre
     * Income must be an initialized value
     * @post
     * The variable remains privately secured
     */
    public double getMonthlyPay()
    {
        //return the monthly payment on the current loan
        return 0;
    }

    /**
     * Function: getMonthlyDebtPayments()
     * Description: This function will return a monthly debt payment
     * Input: none
     * Output: monthlyDebtPayments
     * @pre
     * monthlyDebtPayments must be initialized value
     * @post
     * The variable remains privately secured
     */
    public double getMonthlyDebtPayments()
    {
        //return the customers monthly debt payments
        return 0;
    }

    /**
     * Function: getIncome()
     * Description: This function will return income
     * Input: none
     * Output: income
     * @pre
     * income must be initialized
     * @post
     * income remains a private member of mortgage
     */
    public double getIncome()
    {
        //return the customers income
        return 0;
    }

    /**
     * Function: getCreditScore()
     * Description: This function return creditScore
     * Input: none
     * Output: creditScore
     * @pre
     * creditScore must be initialized
     * @post
     * creditScore remains a private member
     */
    public int getCreditScore()
    {
        //return the customers credit score
        return 0;
    }

    /**
     * Function: toString()
     * Description: This function overrides the normal toString function
     * Input: none
     * Output: formatted print string
     * @pre
     * name, income, creditScore, and monthlyDebtPayments must be initialized
     * @post
     * All variables remain private
     */
    @Override
    public String toString()
    {
        //this function is provided
        //DO you need contracts for this?
        String str = "";
        str += "Name: " + name + "\n";
        str += "Income: $" + income + "\n";
        str += "Credit Score: " + creditScore + "\n";
        str += "Monthly Debt: $" + monthlyDebtPayments + "\n";
        str += "Mortgage info: \n";
        //str += loan.toString();

        return str;

    }
}
