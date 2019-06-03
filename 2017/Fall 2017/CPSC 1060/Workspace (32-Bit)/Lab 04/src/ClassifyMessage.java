/*
 * ClassifyMessage.java
 * Author: Jeremy Holloway
 * Submission Date: 9/29/2017
 * 
 * Purpose: Parse the text of usr specified messages into an 
 * enumeration which can be assigned to variables.
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
public class ClassifyMessage {
// Step 1: Declare enumeration
public enum MessageCategory{NEED, OFFER, ALERT, INFO, UNKNOWN}
public static void main(String[] args) {
// Step 2: Declare scanner as keyboard
	Scanner Keyboard = new Scanner (System.in);
// Step 3: Ask for user input
	System.out.println("Please enter a formatted message:");
// Step 4: Obtain catString from input
	String catString = Keyboard.next();
// Step 5: Obtain latitude from input
	double latitude = Keyboard.nextDouble();
// Step 6: Obtain longitude from input
	double longitude = Keyboard.nextDouble();
// Step 7: Obtain message string from input
	String message = Keyboard.nextLine();
// Step 8: Close keyboard scanner
	Keyboard.close();
// Step 9: Declare strings for comparison to catString
	String Fire = "fire";
	String Smoke = "smoke";
	String Need = "need";
	String Offer = "offer";
	String Structure = "structure";
	String Road = "road";
	String Photo = "photo";
	String Evac = "evac";
// Step 10: Declare longitude and latitude boundries
	final double south = 39.882343;
	final double north = 40.231315;
	final double west = -105.743511;
	final double east = -104.907864;
// Step 11: Using if-else statements classify the catString and add enumeration value
	if (catString.equalsIgnoreCase(Fire))
		System.out.println("Category:\t" + MessageCategory.ALERT);
	else if (catString.equalsIgnoreCase(Smoke))
		System.out.println("Category:\t" + MessageCategory.ALERT);
	else if (catString.equalsIgnoreCase(Need))
		System.out.println("Category:\t" + MessageCategory.NEED);
	else if (catString.equalsIgnoreCase(Offer))
		System.out.println("Category:\t" + MessageCategory.OFFER);
	else if (catString.equalsIgnoreCase(Structure))
		System.out.println("Category:\t" + MessageCategory.INFO);
	else if (catString.equalsIgnoreCase(Road))
		System.out.println("Category:\t" + MessageCategory.INFO);
	else if (catString.equalsIgnoreCase(Photo))
		System.out.println("Category:\t" + MessageCategory.INFO);
	else if (catString.equalsIgnoreCase(Evac))
		System.out.println("Category:\t" + MessageCategory.INFO);
	else System.out.println("Category:\t" + MessageCategory.UNKNOWN);
// Step 12: Disply the raw catergory, message, latitude, and longitude
	System.out.println("Raw Cat:\t" + catString);
	System.out.println("Message:\t" + message);
	System.out.println("Latitude:\t" + latitude);
	System.out.println("Longitude:\t" + longitude);
// Step 13: Using a boolean if-else statement determine if the lat and long are in the boundry
	boolean isInRange = true;
	if (isInRange == true)
		;
	if (latitude >= south && latitude <= north && longitude >= west && longitude <= east)
		System.out.println("In Range:\ttrue");
	else System.out.println("In Range:\tfalse");
		;
	
		}
	}


