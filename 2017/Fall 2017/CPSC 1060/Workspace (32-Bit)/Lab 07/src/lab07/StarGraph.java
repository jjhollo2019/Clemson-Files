/*
 * StarGraph.java
 * Author: Jeremy Holloway
 * Submission Date: 10/20/2017
 * 
 * Purpose: To print a graphical representation of a trig function using
 * the astrik character.
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
package lab07;
import java.util.Scanner;
public class StarGraph {
public static void main(String[] args) {
Scanner keyboard = new Scanner(System.in);
	System.out.print("Please enter the number of x values to process: ");
		double xvar = keyboard.nextDouble();
		if (xvar < 1){ //start if block 1
			System.out.println("The number of x values must be an integer greater than 0.");
			System.exit(0);
			} //end if block 1
		else 
	System.out.print("Enter a minimum value for x: ");
		double xmin = keyboard.nextDouble();
	System.out.print("Enter the amount to increment x: ");
		double xinc = keyboard.nextDouble();
		if (xinc <= 0){ //start if block 2
			System.out.println("The increment must be a decimal number greater than 0.");
			System.exit(0);
			} //end if block 2
		else
	keyboard.close();
	System.out.println("");
	System.out.println("Values");
		double [] xvalues = new double[(int) xvar];
		double [] yvalues = new double[(int) xvar];
	for (int a = 0; a < xvar; a++, xmin = xmin + xinc)	{ //start for loop 1
		xvalues[a] = xmin;
		double y = 20.0 * Math.abs(Math.sin(xmin));
		yvalues[a] = y;
		System.out.print("x: ");
		System.out.printf("%.3f", xmin);
		System.out.print(", y: ");
		System.out.printf("%.3f", y);
		System.out.print("\n");
	} //end for loop 1
	System.out.println(" ");
	System.out.println("Graph");
	int d = 0;
		for (int b = 1; b <= xvar; b++){ //start for loop 2
			System.out.print(':');
				for (int c = 1; c <= (int) Math.floor(yvalues[d]); c++){ //start for loop 3
					System.out.print('*');		
				} //end for loop 3
			System.out.println("");
			d++;
			} //end for loop 2
	} //end of main
} //end of class
