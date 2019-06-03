/* Jeremy Holloway / Damion Anderson
 * CPSC-2150-001
 * Lab 11
 * 11/30/2018
 */
package cpsc2150.mortgages;

/**
 * This interface specifiec the functionality required by the view for our mortgage application program
 * There are very few contracts as the view does not do any sort of input validation, it just
 * interacts with the user.
 */
public interface IMortgageView {

    /**
     * Function: setController
     * Description: This function will set the controller to be used in the implementation
     * @param c the Controller object
     * @pre c != NULL
     */
    void setController(MortgageController c);

    /**
     * Function: getHouseCost
     * Description: This function will get the cost of the house
     * @return the house cost provided by the user
     */
    double getHouseCost();

    /**
     *
     * @return the down payment provided by the user
     */
    double getDownPayment();

    /**
     *
     * @return the years provided by the user
     */
    int getYears();

    /**
     *
     * @return the monthly debt provided by the user
     */
    double getMonthlyDebt();

    /**
     *
     * @return the income provided by the user
     */
    double getYearlyIncome();

    /**
     *
     * @return the credit score provided by the user
     */
    int getCreditScore();

    /**
     *
     * @return the name provided by the user
     */
    String getName();

    /**
     *
     * @param s the message to provide to the user
     */
    void printToUser(String s);

    /**
     *
     * @param p the monthly payment amount for the loan
     * @requires p >= 0
     */
    void displayPayment(double p);

    /**
     *
     * @param r the (APR) interest rate for the loan
     * @pre 0 <= r <= 1
     */
    void displayRate(double r);

    /**
     *
     * @param a whether or not the mortgage application was approved
     * @pre a iff [loan was approved]
     */
    void displayApproved(boolean a);
}
