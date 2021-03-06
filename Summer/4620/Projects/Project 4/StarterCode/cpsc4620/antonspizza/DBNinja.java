package cpsc4620.antonspizza;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/*
This file is where most of your code changes will occur
You will write the code to retrieve information from the database, or save information to the database

The class has several hard coded static variables used for the connection, you will need to change those to your connection information

This class also has static string variables for pickup, delivery and dine-in. If your database stores the strings differently (i.e "pick-up" vs "pickup") changing these static variables will ensure that the comparison is checking for the right string in other places in the program. You will also need to use these strings if you store this as boolean fields or an integer.


*/

/**
 * A utility class to help add and retrieve information from the database
 */

public final class DBNinja
{
    //enter your user name here
    private static String user = "project3_8o0x";
    //enter your password here
    private static String password = "p@$$word";
    //enter your database name here
    private static String database_name = "project3_36as";
    //Do not change the port. 3306 is the default MySQL port
    private static String port = "3306";
    private static Connection conn;

    //Change these variables to however you record dine-in, pick-up and delivery, and sizes and crusts
    public final static String pickup = "PICK_UP";
    public final static String delivery = "DELIVERY";
    public final static String dine_in = "DINE_IN";

    public final static String size_s = "Small";
    public final static String size_m = "Medium";
    public final static String size_l = "Large";
    public final static String size_xl = "X-Large";

    public final static String crust_thin = "Thin";
    public final static String crust_orig = "Original";
    public final static String crust_pan = "Pan";
    public final static String crust_gf = "Gluten-Free";

    /**
     * This function will handle the connection to the database
     * @return true if the connection was successfully made
     * @throws SQLException
     * @throws IOException
     */
    private static boolean connect_to_db() throws SQLException, IOException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println ("Could not load the driver");

            System.out.println("Message     : " + e.getMessage());

            return false;
        }

        conn = DriverManager.getConnection("jdbc:mysql://mysql1.cs.clemson.edu:"+port+"/"+database_name, user, password);
        return true;
    }

    /**
     *
     * @param o order that needs to be saved to the database
     * @throws SQLException
     * @throws IOException
     * @requires o is not NULL. o's ID is -1, as it has not been assigned yet. The pizzas do not exist in the database
     *          yet, and the topping inventory will allow for these pizzas to be made
     * @ensures o will be assigned an id and added to the database, along with all of it's pizzas. Inventory levels
     *          will be updated appropriately
     */
    public static void addOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
        /*
        add code to add the order to the DB. Remember to add the pizzas and discounts as well,
        which will involve multiple tables. Customer should already exist. Toppings will need to be added to the pizzas.
        It may be beneficial to define more functions to add an individual pizza to a database, add a topping to a pizza, etc.
        Note: the order ID will be -1 and will need to be replaced to be a fitting primary key.
        You will also need to add timestamps to your pizzas/orders in your database. Those timestamps are not stored in this program,
        but you can get the current time before inserting into the database
        Remember, when a new order comes in the ingredient levels for the topping need to be adjusted accordingly. Remember to check
        for "extra" of a topping here as well.
        You do not need to check to see if you have the topping in stock before adding to a pizza. You can just let it go negative.
        */

		//get order counts
		String count = "select COUNT(*) from ORDERS;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(count);
		int ID = 0;
		while(!rset.wasNull()) { ID++; }
        ID++;

        try {
			String order = "insert into ORDERS values(?);";
			PreparedStatement stmt2 = conn.prepareStatement(order);
			stmt2.clearParameters();
			stmt2.setInt(1, ID);
            stmt2.executeQuery();
            set_pizzas(ID);	
            if(o.getCustomer() instanceof DineInCustomer){
                String update = "update DINE_IN set ORDER_ID = ? where CUSTOMER_ID = ?;";
                PreparedStatement stmt4 = conn.prepareStatement(update);
                stmt4.clearParameters();
                stmt4.setInt(1, o.getID());
                stmt4.setInt(2, o.getCustomer().getID());
                stmt4.executeQuery();
                String update2 = "update SEAT_NUMBER set ORDER_ID = ? where SEAT_NUMBER = ?;";
                PreparedStatement stmt5 = conn.prepareStatement(update2);
                stmt5.clearParameters();
                DineInCustomer cust = (DineInCustomer) o.getCustomer();
                List<Integer> seats = cust.getSeats();
                for(int i = 0; i < seats.size(); i++){
                    stmt5.setInt(1, o.getID());
                    stmt5.setInt(2, seats.get(i));
                    stmt5.clearParameters();
                }
            }
            else if(o.getCustomer() instanceof DeliveryCustomer){
                String update = "update DELIVERY set ORDER_ID = ? where CUSTOMER_ID = ?;";
                PreparedStatement stmt3 = conn.prepareStatement(update);
                stmt3.clearParameters();
                stmt3.setInt(1, o.getID());
                stmt3.setInt(2, o.getCustomer().getID());
            }
            else{
                String update = "update PICK_UP set ORDER_ID = ? where CUSTOMER_ID = ?;";
                PreparedStatement stmt3 = conn.prepareStatement(update);
                stmt3.clearParameters();
                stmt3.setInt(1, o.getID());
                stmt3.setInt(2, o.getCustomer().getID());
            }
        }
        catch (SQLException e) {
            System.out.println("Error adding order");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        return;
    }

    /**
     *
     * @param c the new customer to add to the database
     * @throws SQLException
     * @throws IOException
     * @requires c is not null. C's ID is -1 and will need to be assigned
     * @ensures c is given an ID and added to the database
     */
    public static void addCustomer(ICustomer c) throws SQLException, IOException
    {
        connect_to_db();
		  int ID = 0;
        /*add code to add the customer to the DB.
        Note: the ID will be -1 and will need to be replaced to be a fitting primary key
        Note that the customer is an ICustomer data type, which means c could be a dine in, carryout or delivery customer
        */
        String count = "select COUNT(*) from CUSTOMER;";
        Statement counts = conn.createStatement();
        ResultSet rset = counts.executeQuery(count);
		  while (rset.next()) {
			  ID = rset.getInt(1);
			  ID++;
		  }

        try{
            if(c instanceof DeliveryCustomer){
                DeliveryCustomer cust = (DeliveryCustomer) c;
                String query = "insert into CUSTOMER values(?, ?, ?, ?);";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.clearParameters();
                stmt.setInt(1, ID);
                stmt.setString(2, cust.getName());
                stmt.setString(3, cust.getAddress());
                stmt.setString(4, cust.getPhone());
                stmt.executeUpdate();
            }
            else if(c instanceof DineOutCustomer){
                DineOutCustomer cust = (DineOutCustomer) c;
                String query = "insert into CUSTOMER values(?, ?, ?, ?);";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.clearParameters();
                stmt.setInt(1, ID);
                stmt.setString(2, cust.getName());
                stmt.setString(3, " ");
                stmt.setString(4, cust.getPhone());
                stmt.executeUpdate();
            }
            else{
                DineInCustomer cust = (DineInCustomer) c;
                String query = "insert into SEAT_NUMBER values(-1, ?);";
                PreparedStatement stmt = conn.prepareStatement(query); 
                stmt.clearParameters();
                stmt.setInt(1, cust.getTableNum());
                stmt.executeUpdate();
                String query2 = "insert into SEAT_NUMBER values(-1, ?);";
                PreparedStatement stmt2 = conn.prepareStatement(query2);
                stmt.clearParameters();
                List<Integer> seats = cust.getSeats();
                for(int i = 0; i < seats.size(); i++){
                    stmt2.setInt(1, seats.get(i));
                    stmt2.executeUpdate();
                    stmt2.clearParameters();
                }           
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading inventory");
            while (e != null)
            {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        conn.close();
        return;
    }

    /**
     *
     * @param o the order to mark as complete in the database
     * @throws SQLException
     * @throws IOException
     * @requires the order exists in the database
     * @ensures the order will be marked as complete
     */
    public static void CompleteOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
		String update = "UPDATE PIZZA SET ORDER_STATUS = TRUE WHERE PIZZA_ID = ?;";
		PreparedStatement stmt = conn.prepareStatement(update);
        stmt.clearParameters();
        try {
            List<Pizza> za = o.getPizzas();
            for(int i = 0; i < za.size(); i++){
                stmt.setInt(1, za.get(i).getID());
                stmt.executeUpdate();
                stmt.clearParameters();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error loading inventory");
            while (e != null)
            {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        conn.close();
        return;
    }

    /**
     *
     * @param t the topping whose inventory is being replenished
     * @param toAdd the amount of inventory of t to add
     * @throws SQLException
     * @throws IOException
     * @requires t exists in the database and toAdd > 0
     * @ensures t's inventory level is increased by toAdd
     */
    public static void AddToInventory(Topping t, double toAdd) throws SQLException, IOException
    {
        connect_to_db();
		  //String query = "alter table TOPPINGS set INVENTORY_LEVEL = ? where NAME = ?;"; 
		  String query = "UPDATE TOPPINGS set INVENTORY = ? where NAME = ?;"; 
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        try{
		    stmt.setDouble(1, (t.getInv() + toAdd));
		    stmt.setString(2, t.getName());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Error loading inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        conn.close();
        return;
    }


    /*
        A function to get the list of toppings and their inventory levels. I have left this code
        "complete" as an example of how to use JDBC to get data from the database. This query will
        not work on your database if you have different field or table names, so it will need to be changed.

        Also note, this is just getting the topping ids and then calling getTopping() to get the actual
        topping. You will need to complete this on your own.  You don't actually have to use and write the getTopping()
        function, but it can save some repeated code if the program were to expand, and it keeps the functions simpler,
        more elegant and easy to read. Breaking up the queries this way also keeps them simpler. I think it's a better
        way to do it, and many people in the industry would agree, but its a suggestion, not a requirement.
    */

    /**
     *
     * @return the List of all toppings in the database
     * @throws SQLException
     * @throws IOException
     * @ensures the returned list will include all toppings and accurate inventory levels
     */
    public static ArrayList<Topping> getInventory() throws SQLException, IOException
    {
        connect_to_db();
        ArrayList<Topping> ts = new ArrayList<Topping>();

        //create a string with our query, this one is an easy one
        String query = "SELECT NAME FROM TOPPINGS;";

        Statement stmt = conn.createStatement();
        try
        {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
                /* Use getInt, getDouble, getString to get the actual value.
                 * You can use the column number starting with 1, or use the column name as a string
                 * NOTE: You want to use rset.getInt() instead of Integer.parseInt(rset.getString()), 
					  * not just because it's shorter, but because of the possible NULL values. A NUll would cause parseInt to fail
                 * If there is a possibility that it could return a NULL value you need to check to see if it was NULL. 
					  * In this query we won't get nulls, so I didn't. If I was going to I would do:
                int ID = rset.getInt(1);
                if(rset.wasNull())
                {
                  //set ID to what it should be for NULL, and whatever you need to do.
                }
                NOTE: you can't check for NULL until after you have read the value using one of the getters.
                */
                String ID = rset.getString(1);

                //Now I'm just passing my primary key to this function to get the topping itself individually
                ts.add(getTopping(ID));
            }
        }
        catch (SQLException e){
            System.out.println("Error loading inventory");
            while (e != null)
            {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
            return ts;
        }

        //end by closing the connection
        conn.close();
        return ts;
    }

    /**
     *
     * @return a list of all orders that are currently open in the kitchen
     * @throws SQLException
     * @throws IOException
     * @ensures all currently open orders will be included in the returned list.
     */
    public static ArrayList<Order> getCurrentOrders() throws SQLException, IOException
    {
        connect_to_db();
        ArrayList<Order> os = new ArrayList<Order>();

        /*add code to get a list of all open orders.
         *Only return Orders that have not been completed.
         *If any pizzas are not completed, then the order is open.
         */

        String query = "SELECT DISTINCT ORDER_ID FROM PIZZA WHERE ORDER_STATUS = FALSE;";
        Statement stmt = conn.createStatement();
		  try
		  {
			  ResultSet rset = stmt.executeQuery(query);
			  while(rset.next())
			  {
				  os.add(getOrder(rset.getInt(1)));
			  }
		  }
        catch(SQLException e)
		  {
            System.out.println("Error loading Current Orders");
            while (e != null) 
				{
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
            return os;
        }
        conn.close();
        return os;
    }

    /**
     *
     * @param size the pizza size
     * @param crust the type of crust
     * @return the base price for a pizza with that size and crust
     * @throws SQLException
     * @throws IOException
     * @requires size = size_s || size_m || size_l || size_xl AND crust = crust_thin || crust_orig || crust_pan || crust_gf
     * @ensures the base price for a pizza with that size and crust is returned
     */
    public static double getBasePrice(String size, String crust) throws SQLException, IOException
    {
        connect_to_db();
        double bp = 0.0;

        //add code to get the base price for that size and crust pizza Depending on how you store size and crust in your database, you may have to do a conversion
        String query = "select PRICE from BASE_PRICE where CRUST = ? AND SIZE = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        stmt.setString(1, crust);
        stmt.setString(2, size);
		  try {
			  ResultSet rset = stmt.executeQuery();
			  while (rset.next()) {
				  if(rset.wasNull()){
						System.out.println("ERROR! No such pizza exists!");
				  }
				  else{
						bp = rset.getDouble(1);
				  }
			  }
		  }
		  catch (SQLException e)
		  {
            System.out.println("Error Getting Base Price");
            while (e != null) 
				{
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
            return bp;
        }
        //conn.close();
        return bp;
    }

    /**
     *
     * @return the list of all discounts in the database
     * @throws SQLException
     * @throws IOException
     * @ensures all discounts are included in the returned list
     */
    public static ArrayList<Discount> getDiscountList() throws SQLException, IOException
    {
        connect_to_db();
        ArrayList<Discount> discs = new ArrayList<Discount>();
		String query = "SELECT DISCOUNT_ID FROM DISCOUNTS;";
	    Statement stmt = conn.createStatement();
        try{
			ResultSet rset = stmt.executeQuery(query);
			while(rset.next()) {
			    discs.add(getDiscount(rset.getInt(1)));
			}
        }
        catch(SQLException e) {
            System.out.println("Error loading Discounts");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return discs;
        }
        conn.close();
        return discs;
    }

    /**
     *
     * @return the list of all delivery and carry out customers
     * @throws SQLException
     * @throws IOException
     * @ensures the list contains all carryout and delivery customers in the database
     */
    public static ArrayList<ICustomer> getCustomerList() throws SQLException, IOException
    {
        connect_to_db();
        ArrayList<ICustomer> custs = new ArrayList<ICustomer>();
        String query = "SELECT CUSTOMER_ID FROM CUSTOMER;";
        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
				custs.add(getCustomer(rset.getInt(1)));
			}
		}
        catch(SQLException e){
            System.out.println("Error loading Customers");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return custs;
        }
        conn.close();
        return custs;
    }

	/*
	Note: The following incomplete functions are not strictly required, but could make your DBNinja class much simpler. For instance, instead of writing one query to get all of the information about an order, 
	you can find the primary key of the order, and use that to find the primary keys of the pizzas on that order, then use the pizza primary keys individually to build your pizzas. We are no longer trying 
	to get everything in one query, so feel free to break them up as much as possible
	You could also add functions that take in a Pizza object and add that to the database, or take in a pizza id and a topping id and add that topping to the pizza in the database, etc. I would recommend 
	this to keep your addOrder function much simpler
	These simpler functions should still not be called from our menu class. That is why they are private
	We don't need to open and close the connection in these, since they are only called by a function that has opened the connection and will close it after
	*/
    private static Topping getTopping(String ID) throws SQLException, IOException
    {
        connect_to_db();
        Topping t = new Topping("fake", 0.25, 100.0);
		  String query = "Select NAME, PRICE, INVENTORY From TOPPINGS WHERE NAME = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
		  stmt.clearParameters();
		  stmt.setString(1, ID);
        try {
            ResultSet rset = stmt.executeQuery();

            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next()) {
				t = new Topping(rset.getString(1), rset.getDouble(2), rset.getDouble(3));
			}

		}
		catch (SQLException e) {
            System.out.println("Error loading Topping");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return t;
        }
        return t;
    }

    private static Discount getDiscount(int ID)  throws SQLException, IOException
    {
        connect_to_db();
        Discount D = new Discount("fake", 0.0, 0.0, -1);
        String query = "SELECT * FROM DISCOUNTS WHERE DISCOUNT_ID = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
		  stmt.clearParameters();
		  stmt.setInt(1, ID);
        try {
            ResultSet rset = stmt.executeQuery();
			   while(rset.next()) {
				D = new Discount(rset.getString(1), rset.getDouble(2), rset.getDouble(3), rset.getInt(4));
			}
        }
        catch(SQLException e) {
            System.out.println("Error getting discount");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return D;
        }
        return D;
    }

    private static Pizza getPizza(int ID)  throws SQLException, IOException
    {
        //add code to get Pizza Remember, a Pizza has toppings and discounts on it
        connect_to_db();
        Pizza P = new Pizza(-1, "fake", "NULL", 0.0);
        String query = "select * from PIZZA natural join BASE_PRICE where PIZZA_ID = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        stmt.setInt(1, ID);
        try {
           ResultSet rset = stmt.executeQuery();
			  while (rset.next()) {
				   double basePrice = getBasePrice(rset.getString(8), rset.getString(9));
					P = new Pizza(rset.getInt(1), rset.getString(2), rset.getString(4), basePrice);
			  }
		  }
        catch(SQLException e) {
            System.out.println("Error getting pizza");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return P;
        }
	  	  String query2 = "select DISCOUNT_ID from PIZZA_DISCOUNTS where PIZZA_ID = ?;";
		  PreparedStatement stmt2 = conn.prepareStatement(query2);
		  stmt2.clearParameters();
		  stmt2.setInt(1, ID);
		  try {
				ResultSet rset2 = stmt2.executeQuery();
            while (rset2.next()){
                P.addDiscount(getDiscount(rset2.getInt(1)));
            }           
			}
         catch(SQLException e) {
            System.out.println("Error getting pizza");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return P;
			}
            String query3 = "select TOPPING_NAME from HAS_TOPPINGS where PIZZA_ID = ?;";
            PreparedStatement stmt3 = conn.prepareStatement(query3);
            stmt3.clearParameters();
            stmt3.setInt(1, ID);
			try {
            ResultSet rset3 = stmt3.executeQuery();
            while (rset3.next()){
                P.addTopping(getTopping(rset3.getString(1)));
            }
			}
        catch(SQLException e) {
            System.out.println("Error getting pizza");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return P;
        }
        return P;
    }


    private static ICustomer getCustomer(int ID)  throws SQLException, IOException
    {
        connect_to_db();
        ICustomer C = new DineOutCustomer(-1, "fake", "none");
        String query = "select CUSTOMER_ID from DELIVERY where CUSTOMER_ID = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
		  stmt.clearParameters();
		  stmt.setInt(1, ID);
        try
        {
            ResultSet rset = stmt.executeQuery();
            while(rset.next())
            {
                if(rset.wasNull()){
                    String query2 = "select NAME, PHONE_NUMBER from CUSTOMER where CUSTOMER_ID = ?;";
                    PreparedStatement stmt2 = conn.prepareStatement(query2);
                    stmt2.clearParameters();
                    stmt2.setInt(1, ID);
                    ResultSet rset2 = stmt2.executeQuery();
                    while(rset2.next()){
                        C = new DineOutCustomer(ID, rset2.getString(1), rset2.getString(2));
                    }
                }
                else{
                    String query2 = "select NAME, PHONE_NUMBER, ADDRESS from CUSTOMER where CUSTOMER_ID = ?;";
                    PreparedStatement stmt2 = conn.prepareStatement(query2);
                    stmt2.clearParameters();
                    stmt2.setInt(1, ID);
                    ResultSet rset2 = stmt2.executeQuery();
                    while(rset2.next()){
                        C = new DeliveryCustomer(ID, rset2.getString(1), rset2.getString(2), rset2.getString(3));
                    }
                }
                
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error loading Customers");
            while (e != null)
            {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return C;
        }
        return C;
    }

    private static Order getOrder(int ID)  throws SQLException, IOException
    {
        //add code to get an order. Remember, an order has pizzas, a customer, and discounts on it
        connect_to_db();
        ICustomer cust = new DineOutCustomer(-1, "fake", "none");
        Order O = new Order(-1, cust, "NULL"); 
        String query = "select PIZZA_ID from PIZZA where ORDER_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        stmt.setInt(1, ID);
        try {
            ResultSet rset = stmt.executeQuery();
				while (rset.next()) {
                O.addPizza(getPizza(rset.getInt(1)));
				}
		  }
        catch(SQLException e){
            System.out.println("Error fetching from getPizza");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return O;
        }
		 
		try{       
            String query2 = "select CUSTOMER_ID from DELIVERY where ORDER_ID = ?;";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.clearParameters();;
            stmt2.setInt(1, ID);
            ResultSet rset2 = stmt2.executeQuery();
            while(rset2.next()){
                if(rset2.wasNull()){
                    String query3 = "select CUSTOMER_ID from PICK_UP where ORDER_ID = ?;";
                    PreparedStatement stmt3 = conn.prepareStatement(query3);
                    stmt3.clearParameters();
                    stmt3.setInt(1, ID);
                    ResultSet rset3 = stmt3.executeQuery();
                    while(rset3.next()){
                        if(rset3.wasNull()){
                            String query4 = "select TABLE_NUMBER, SEAT_ORDER_ID from DINE_IN join SEAT_NUMBER on ORDER_ID = SEAT_ORDER_ID where ORDER_ID = ?;";
                            PreparedStatement stmt4 = conn.prepareStatement(query4);
                            stmt4.clearParameters();
                            stmt4.setInt(1, ID);
                            ResultSet rset4 = stmt4.executeQuery();
                            int table = 0;
                            List<Integer> s = new ArrayList<>();
                            while(rset4.next()){
                                table = rset4.getInt(1);
                                s.add(rset4.getInt(2));
                            }
                            cust = new DineInCustomer(table, s, ID);
                        }
                        else{
                            cust = getCustomer(rset3.getInt(1));
                        }
                    }
                }
                else{
                    cust = getCustomer(rset2.getInt(1));
                }
            }    
                
            O = new Order(ID, cust, cust.getType());
				String query6 = "select DISCOUNT_ID from ORDER_DISCOUNTS where ORDER_ID = ?;";
				PreparedStatement stmt6 = conn.prepareStatement(query6);
				stmt6.clearParameters();
				stmt6.setInt(1, ID);
				ResultSet rset6 = stmt6.executeQuery();
				while(rset6.next()){
					O.addDiscount(getDiscount(rset6.getInt(1)));
				}
		}
        catch(SQLException e){
            System.out.println("Error fetching order");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return O;
        }
        return O;

    }

    private static void set_pizzas(int ID) throws SQLException, IOException {
        ArrayList<Pizza> za = getOrder(ID).getPizzas();
        LocalDateTime now = LocalDateTime.now();
        Timestamp time = Timestamp.valueOf(now.toString());
        String query = "insert into PIZZA values(?, ?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        try{
            for(int i = 0; i < za.size(); i++){
                stmt.setInt(1, za.get(i).getID());
                stmt.setBoolean(2, false);
                stmt.setTimestamp(3, time);
                stmt.setDouble(4, getBasePrice(za.get(i).getSize(), za.get(i).getCrust()));
                stmt.setInt(5, ID);
                stmt.executeQuery();
                stmt.clearParameters();
            }
            String query2 = "update PIZZA set CUSTOMER_PRICE = ?, BUSINESS_COST = 2.0;";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.clearParameters();
            for(int i = 0; i < za.size(); i++){
                set_toppings(za.get(i).getID());
                stmt2.setDouble(1, za.get(i).calcPrice());
                stmt2.executeQuery();
                stmt2.clearParameters();
            }
        }
        catch(SQLException e){
            System.out.println("Error setting pizzas");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        return;
    }

    private static void set_toppings(int ID) throws SQLException, IOException {
        Pizza p = getPizza(ID);
        ArrayList<Topping> tops = p.getToppings();
        int size = tops.size();
        String query = "insert into HAS_TOPPINGS values(?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.clearParameters();
        try{
            for(int i = 0; i < size; i++){
                stmt.setInt(1, p.getID());
                stmt.setString(2, tops.get(i).getName());
                stmt.setBoolean(3, tops.get(i).getExtra());
                stmt.executeQuery();
                AddToInventory(tops.get(i), -2.0);
                stmt.clearParameters();
            }
        }
        catch(SQLException e){
            System.out.println("Error setting toppings");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return;
        }
        return;
    }
}


