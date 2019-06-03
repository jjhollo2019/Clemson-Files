/*
 * ClassifyMessage.java
 * Author: Jeremy Holloway
 * Submission Date: 10/13/2017
 * 
 * Purpose: Drawing triangles with a user specified number or stars 
 * and repeated pattern.
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
public class Drawing {

	public static void main(String[] args) {
	Scanner keyboard = new Scanner(System.in);
	System.out.println("Indicate the number of stars (>1): ");
	int numstar = keyboard.nextInt();
	System.out.println("How many times do you want to repeat the figure?");
	int numloop = keyboard.nextInt();
	keyboard.close();
	if (numstar <= 1 || numloop <= 1){ //start if statement
		System.out.println("Invalid Input");
		System.exit(0);
			} //end if statement
	else { //start else statement
	while (numloop > 0) { //start while loop
		for (int a = numstar; a >= 1; a--){ //start 1st for loop
			System.out.println("");
				for (int b = 1; b <= a; b++){ //start 2nd for loop
					System.out.print("*");
			} //end 2nd for loop
		} //end 1st for loop
		for (int c = 1; c <= numstar; c++){ //start 3rd for loop
			System.out.println("");
				for (int d = 1; d <= c; d++){ //start 4th for loop
					System.out.print("*");
				} //end 4th for loop
			} //end 3rd for loop
	numloop--;
			} //end while loop
		} //end of else statement
	} //end of main
} //end of class
