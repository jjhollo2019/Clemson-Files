/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 07
 * 10/19/2018
 */

package cpsc2150.labs.lab2;

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
     * @post
     * houseCost = cost, downPayment = dp, years = y, customer = cust
     */
    Mortgage(double cost, double dp, int y, Customer cust)
    {
        houseCost = cost;//initialize house cost
        downPayment = dp;//initialize downPayment
        customer = cust;//initialize the customer object
        years = y;//initialize years
        percentDown = downPayment / houseCost;//initialize percentDown
        calcRate();//call calcRate to initialize interestRate
        calcPayment();//call calcPayment to initialize payment
    }

    /**
     * Function: calcRate()
     * Description: This function will calculate the interest rate for the mortgage
     * Input: none
     * Output: none
     * @pre
     * years == 15 || 20 || 25 || 30, customer.getCreditScore() >= 0
     * @post
     * interestRate >= 3.0%
     */
    private void calcRate()
    {
        interestRate = .025;//start at 2.5%
        if(years == 30)
        {
            interestRate += .01;//add 1% for 30 year loan
        }
        else if(years < 30)
        {
            interestRate += .005;//add 0.5% for 15, 20, 25, 30 year loan
        }
        int bad = 500, fair = 599, good = 699, great = 749;//establish credit score limits
        if(customer.getCreditScore() <= bad) interestRate += .1;//add 10% for bad credit
        // add 5% for fair credit
        else if (customer.getCreditScore() >= bad && customer.getCreditScore() <= fair) interestRate += .05;
        //add 1% for good credit
        else if (customer.getCreditScore() > fair && customer.getCreditScore() <= good) interestRate += .01;
        //add 0.5% for great credit
        else if (customer.getCreditScore() > good && customer.getCreditScore() <= great) interestRate += .005;
        if(percentDown < .2) interestRate += .005;//add 0.5% if percentDown is less than 20%
    }

    /**
     * Function: calcPayment()
     * Description: This function will calculate the monthly payment
     * Input: none
     * Output: calculated payment is stored in global variable
     * @pre
     * income >= 0, houseCost >= 0, downPayment >= 0, years >= 15
     * @post
     * payment = ((interestRate/12) * (houseCost - downPayment)) / (1 - (1 + interestRate)^-years)
     */
    private void calcPayment()
    {
        double r = interestRate / 12;//calculate monthly interest
        double p = houseCost - downPayment;//calculate principal
        double n = years * 12;//calculate total payments
        payment = (r * p) / (1 -  Math.pow((1 + r), -n));//calculate payment
    }

    /**
     * Function: loanApproved()
     * Description: This function will determine if the loan is approved or not
     * Input: none
     * Output: true/false
     * @pre
     * interestRate >= 0, income >= 0, income >= 0, monthlyDebtPayments >= 0, payment >= 0
     * @post
     * loanApproved == true || false
     * @return
     * The function will return a boolean true or false if the loan is approved/declined
     */
    public boolean loanApproved()
    {
        if (interestRate >= .10) return false;//loan not approved if interest rate over 10%
        if (percentDown < .035) return false;//loan not approved if percent down is less than 3.5%
        //loan not approved if debt to income ratio including the loan payment is equal to or greater than 40%
        if(((customer.getMonthlyDebtPayments() + payment) * 12) / (customer.getIncome()) >= .4) return false;
        return true;//otherwise loan is approved
    }

    /**
     * Function: getPayment()
     * Description: This function will return payment
     * Input: none
     * Output: payment
     * @pre
     * payment >= 0
     * @post
     * value called for will equal payment
     * @return
     * The function will return a double value of payment
     */
    public double getPayment()
    {
        return payment;//return payment
    }

    /**
     * Function: getRate()
     * Description: This function will return interestRate
     * Input: none
     * Output: interestRate
     * @pre
     * interestRate >= 0;
     * @post
     * value called equals interestRate
     * @return
     * The function will return a double value of interestRate
     */
    public double getRate()
    {
        return interestRate;//return interestRate
    }

    /**
     * Function: toString()
     * Description: This function will override the toString function
     * Input: none
     * Output: formatted print string
     * @pre
     * houseCost >= 0, downPayment >= 0, interestRate >= 0, years >= 15, payment >= 0
     * @post
     * toString == loan information
     * @return
     * This function returns a string value of the mortgage information
     */
    @Override
    public String toString()
    {
        String str = "";//declare print string
        if(loanApproved())//if loan is approved
        {
            str += "Principal Amount: $" + (houseCost - downPayment) + "\n";//add principal
            str += "Interest Rate: " + (interestRate * 100) + "%\n";//add interest rate
            str += "Term: " + years + " years\n";//add finance years
            str += "Monthly Payment: $" + payment + "\n";//add payment
        }
        else
        {
            str += "Loan was not approved";//otherwise print disapproval
        }
        return str;//return print string
    }
}