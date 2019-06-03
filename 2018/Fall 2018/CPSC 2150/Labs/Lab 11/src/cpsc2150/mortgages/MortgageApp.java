package cpsc2150.mortgages;
public class MortgageApp {
    public static void main(String [] args)
    {
        IMortgageView view = new MortgageView();
        MortgageController controller = new MortgageController(view);
        view.setController(controller);
    }
}
