/*
 * ParseTheTweet.java
 * Author: Jeremy Holloway
 * Submission Date: 9/22/2017
 * 
 * Purpose: To process twitter messages using the substring and other 
 * methods to pull out information from the tweet
 * 
 * Statement of Academic Honesty:
 * 
 * The following code represents my own work. I have neither
 * received nor given inappropriate assistance. I have not
 * copied or modified code from any source other than the course
 * webpage or the course textbook. i recognize that any unauthorized
 * assistance or plagiarism will be handled in accordance
 * with the policies at Clemson University and the policies
 * of this course. I recognize that my work is based on an assignment 
 * created by the School of Computing at Clemson University.
 * Any publishing or posting of source code for this project
 * is strictly prohibited unless you have written consent
 * from the instructor.
 */
import java.util.Scanner;

public class ParseTheTweet {

public static void main(String[] args) {

	Scanner theScanner = new Scanner (System.in);
// Step 1: Ask for user input
	System.out.println("Enter Tweet");
	String tweet = ("") + theScanner.nextLine();
		theScanner.close();
// Step 2: Establish start and finish values for the variable
		int start = tweet.indexOf("#typ") + 5;
		int finish = tweet.indexOf(";");
// Step 3: Extract the substring of the start and finish values
	String Type = tweet.substring(start, finish);
// Step 4: Establish new substring of tweet
		tweet = tweet.substring(finish + 1);
// Step 5: Repeat steps 2-4 for the detail variable
		start = tweet.indexOf("#det") + 5;
		finish = tweet.indexOf(";");
	String Detail = tweet.substring(start, finish);
		tweet = tweet.substring(finish + 1);
// Step 6: Repeat steps 2-4 for the location variable
		start = tweet.indexOf("#loc") + 5;
		finish = tweet.indexOf(";");
	String Location = tweet.substring(start, finish);
		tweet = tweet.substring(finish + 1);
// Step 7: Repeat steps 2-4 for the latitudestring variable
		start = tweet.indexOf("#lat") + 5;
		finish = tweet.indexOf(";");
	String LatitudeString = tweet.substring(start, finish);
		tweet = tweet.substring(finish + 1);
// Step 8: Repeat steps 2-4 for the longitudestring variable
		start = tweet.indexOf("#lng") + 5;
		finish = tweet.indexOf(";");
	String LongitudeString = tweet.substring(start, finish);
// Step 9: Change the output of the typy variable to uppercase
	String TYPE = Type.toUpperCase();
// Step 10: Replace the commas in the detail and location variable to hyphens
	String replaceDetail = Detail.replace(",", "-" );
	String replaceLocation = Location.replace(",", "-");
// Step 11: Use the scanner to create double value of the latitude and longitude
		theScanner = new Scanner(LatitudeString);
		double Latitude = theScanner.nextDouble();
		theScanner.close();
		theScanner = new Scanner(LongitudeString);
		double Longitude = theScanner.nextDouble();
		theScanner.close();
// Step 12: Display the results
	System.out.println("Type:\t\t" + TYPE);
	System.out.println("Detail:\t\t" + replaceDetail);
	System.out.println("Location:\t" + replaceLocation);
	System.out.println("Latitude:\t" + Latitude);
	System.out.println("Longitude:\t" + Longitude);
}


}
