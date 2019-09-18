/* Blue Team
 * CPSC-4910-001
 * Team Project
 */
package project;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Sponsor {
    //connection object
    private static Connection conn;
    //connection port number: default mysql port
    private static String port = "3306";
    //set DB name here
    private static String database_name = "testDB";
    //set user name for server login
    private static String user;
    //set password for server login
    private static String password;
    //Sponsor identification number
    private String identification;
    //Sponsor company name
    private String companyName;
    //Sponsor email address
    private String emailAddress;

    /**
     * This function will handle the connection to the database
     * @pre database_name, user, password must be properly set along with connection string
     * @post The class is connected to the database
     * @return true if the connection was successfully made
     * @throws SQLException
     * @throws IOException
     */
    private static boolean connect_to_db() throws SQLException, IOException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println ("Could not load the driver");

            System.out.println("Message     : " + e.getMessage());

            return false;
        }
        //this needs to change for our RDS server
        conn = DriverManager.getConnection("jdbc:mysql://mysql1.cs.clemson.edu:"+port+"/"+database_name, user, password);
        return true;
    }

    /**
     * This function will create a sponsor object and add it to the database
     * @pre connect_to_db must be properly configured
     * @post This object will be stored in the SQL database
     * @throws SQLException
     * @throws IOException
     */
    private void createSponsor() throws SQLException, IOException {
        //set sql statement string
        String create = "INSERT INTO SPONSOR VALUES (?, ?, ?);";
        try{
            //connect to the database
            connect_to_db();
            //use prepared statement
            PreparedStatement stmt = conn.prepareStatement(create);
            //clear the statement
            stmt.clearParameters();
            //set statement parameters
            stmt.setString(1, identification);
            stmt.setString(2, companyName);
            stmt.setString(3, emailAddress);
            //execute query
            ResultSet rset = stmt.executeQuery();
        }
        catch (SQLException e){
            System.out.println("Error creating Sponsor");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        conn.close();
        //inform user of success
        System.out.println("Sponsor creation success");
        return;
    }

    /**
     * @pre connect_to_db must be properly configured
     * @post params are used to create and store a new SQL db entry
     * @param id This is the Sponsor identifcation used for the primary key
     * @param name This is the Sponsor company name
     * @param email This is the Sponsor email
     * @throws SQLException
     * @throws IOException
     */
    Sponsor(String id, String name, String email) throws SQLException, IOException{
        //set object variables
        identification = id;
        companyName = name;
        emailAddress = email;
        //call private function to store object in the database
        createSponsor();
    }

    /**
     * This function will update the sponsor name
     * @pre Sponsor object must be instantiated && connect_to_db must be properly configured
     * @post Sponsor object and database entry are updated
     * @param name This is the updated name for the Sponsor and its object
     * @throws SQLException
     * @throws IOException
     */
    public void updateName(String name) throws SQLException, IOException {
        companyName = name;
        //SQL query statement
        String update = "UPDATE SPONSOR SET 'NAME' = ? WHERE SPONSOR_ID = ?;";
        try {
            //connect to the db
            connect_to_db();
            //create prepared statement
            PreparedStatement stmt = conn.prepareStatement(update);
            //clear params
            stmt.clearParameters();
            //set variables
            stmt.setString(1, name);
            stmt.setString(2, identification);
            //execute SQL statement
            ResultSet rset = stmt.executeQuery();
        } 
        catch (SQLException e) {
            System.out.println("Error updating Sponsor name");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        conn.close();
        //inform user of success
        System.out.println("Sponsor name updated");
        return;
    }

    /**
     * This function will update the sponsor email address
     * @pre sponsor object must be instantiated && connect_to_db must be properly configured
     * @post sponsor object and SQL entry will be updated with new email
     * @param email This is the new email for the sponsor
     * @throws SQLException
     * @throws IOException
     */
    public void updateEmail(String email) throws SQLException, IOException {
        emailAddress = email;
        //create SQL statement 
        String update = "UPDATE SPONSOR SET 'EMAIL' = ? WHERE SPONSOR_ID = ?;";
        try {
            //connect to DB
            connect_to_db();
            //prepare SQL statement
            PreparedStatement stmt = conn.prepareStatement(update);
            //clear parameters
            stmt.clearParameters();
            //set variables
            stmt.setString(1, email);
            stmt.setString(2, identification);
            //execute query
            ResultSet rset = stmt.executeQuery();
        } 
        catch (SQLException e) {
            System.out.println("Error updating Sponsor email");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return;
        }
        conn.close();
        //inform user of success
        System.out.println("Sponsor email updated");
        return;
    }
}