/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 07
 * 10/19/2018
 */

package cpsc2150.labs.lab2;

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
     * @post
     * monthlyDebtPayments = debt, income = inc, creditScore = score, name = n
     */
    Customer( double debt, double inc, int score, String n)
    {
        monthlyDebtPayments = debt;//initialize monthlyDebtPayments
        income = inc;//initialize income
        creditScore = score;//initialize creditScore
        name = n;//initialize name
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
     * downPayment >= 0, houseCost > 0, years >= 15
     * @post
     * loan == true || false
     * @return
     * This function returns a boolean true/false if the loan is approved/declined
     */
    public boolean applyForLoan(double downPayment, double houseCost, int years)
    {
        loan = new Mortgage(houseCost, downPayment, years, this);//call Mortgage constructor
        if (loan.loanApproved() == false) return false;//return false if loan not approved
        else return true;//otherwise return true
    }

    /**
     * Function: getRate()
     * Description: This function will return the value stored privately in the mortgage class
     * Input: none
     * Output: double interestRate
     * @pre
     * loan == true || false
     * @post
     * called value equals the loan interestRate
     * @return
     * The function returns the value stored in loan interestRate
     */
    public double getRate()
    {
        return loan.getRate();//return the loan interest rate
    }

    /**
     * Function: getMonthlyPay()
     * Description: This function will return the monthly pay
     * Input: none
     * Output: double = income / 12
     * @pre
     * loan == true || false
     * @post
     * called value equals the loan payment
     * @return
     * This function will return the value stored in loan payment
     */
    public double getMonthlyPay()
    {
        return (loan.getPayment());//return monthly loan payment
    }

    /**
     * Function: getMonthlyDebtPayments()
     * Description: This function will return a monthly debt payment
     * Input: none
     * Output: monthlyDebtPayments
     * @pre
     * monthlyDebtPayments >= 0
     * @post
     * The value equals the loan monthly debt payments
     * @return
     * This function returns the value stored in monthlyDebtPayments
     */
    public double getMonthlyDebtPayments()
    {
        return monthlyDebtPayments;//return monthly debt payments
    }

    /**
     * Function: getIncome()
     * Description: This function will return income
     * Input: none
     * Output: income
     * @pre
     * Income >= 0
     * @post
     * The called value equals the customer income
     * @return
     * The function returns the value stored in income
     */
    public double getIncome()
    {
        return income;//return income
    }

    /**
     * Function: getCreditScore()
     * Description: This function return creditScore
     * Input: none
     * Output: creditScore
     * @pre
     * creditScore >= 0
     * @post
     * The called value equals the customer credit score
     * @return
     * This function returns the value stored in the customer creditScore
     */
    public int getCreditScore()
    {
        return creditScore;//return credit score
    }

    /**
     * Function: toString()
     * Description: This function overrides the normal toString function
     * Input: none
     * Output: formatted print string
     * @pre
     * name != NULL, income >= 0, creditScore >= 0, monthlyDebtPayments >= 0, loan == true || false
     * @post
     * The customer details are converted into a string
     * @return
     * The string represents the customer information stored
     */
    @Override
    public String toString()
    {
        String str = "";//declare print string
        str += "Name: " + name + "\n";//add name
        str += "Income: $" + income + "\n";//add income
        str += "Credit Score: " + creditScore + "\n";//add credit score
        str += "Monthly Debt: $" + monthlyDebtPayments + "\n";//add monthly debt
        str += "Mortgage info: \n";//add mortgage info
        if( loan != null)
            str += loan.toString();//add Mortgage toString
        return str;//return print string
    }
}