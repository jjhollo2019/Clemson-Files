/* Jeremy Holloway / Damion Anderson
 * CPSC-2150-001
 * Lab 11
 * 11/30/2018
 */
package cpsc2150.mortgages;

public class MortgageController {

    private IMortgageView view;//holds the view passed in by the constructor
    private final int maxCred = 850;//holds maximum credit score
    /**
     * Function: MortgageController(IMortgageView v)
     * Description: This function will set the view for this instance
     * @param v is the IMortgage view object passed from the main
     * @return initializes the global IMortgage object
     */
    public MortgageController(IMortgageView v){
        view = v;//set view
    }

    /**
     * Function: submitApplication()
     * Description: This function handles the loan processing
     */
    public void submitApplication() {
        String name;//declare customer name
        double income, debt, houseCost, downPayment;//declare income, debt, houseCost, downPayment
        int credit, years;//declare credit score and years financed
        boolean approved = true;//set approval condition
        name = view.getName();//get customer name
        income = view.getYearlyIncome();//grabbing annual income with boundary testing.
        if (income < 0){//if income is negative
            view.printToUser("Yearly income must be greater than 0");//print error message
            approved = false;//set approval to false
        }
        debt = view.getMonthlyDebt();//Grabbing monthly debt with boundary checking.
        if (debt < 0) {//if debt is negative
            view.printToUser("Debt must be greater than or equal to 0.");//print error message
            approved = false;//set approval to false
        }
        credit = view.getCreditScore();//Grabbing credit score with boundary checking.
        if (credit <= 0 || credit > maxCred){//if credit score is negative or over 850
            view.printToUser("Credit must be greater than 0 and less than 850");//print error message
            approved = false;//set approval to false
        }
        houseCost = view.getHouseCost();//Checking house costs with boundary checking.
        if (houseCost < 0) {//if house cost is negative
            view.printToUser("House must cost must be greater than 0");//print error message
            approved = false;//set approval to false
        }
        downPayment = view.getDownPayment();//getting down payment. Must be greater than 0 and less than house cost.
        if (downPayment < 0 || downPayment >= houseCost) {//if down payment is negative or greater than house cost
            view.printToUser("Down payment must be greater than 0 and less than the cost of the house.");//print error message
            approved = false;//set approval to false
        }
        years = view.getYears();//Getting years, years must be > 0
        if (years < 0) {//if years to finance is negative
            view.printToUser("Years must be greater than 0.");//print error message
            approved = false;//set approval to false
        }
        if(approved) {//if all previous conditions were met
            Customer c = new Customer(debt, income, credit, name);//get customer object
            c.applyForLoan(downPayment, houseCost, years);//apply for a loan
            Mortgage n = new Mortgage(houseCost, downPayment, years, c);//create a mortgage object
            view.displayPayment(n.getPayment());//display payment
            view.displayApproved(n.loanApproved());//display loan approval status
            view.displayRate(n.getRate());//display interest rate
        }
        else{//default to non-approval, rate and payment set to zero
            view.displayRate(0.0);//rate is 0
            view.displayApproved(false);//loan not applied for
            view.displayPayment(0.0);//payment is zero
        }
    }
}