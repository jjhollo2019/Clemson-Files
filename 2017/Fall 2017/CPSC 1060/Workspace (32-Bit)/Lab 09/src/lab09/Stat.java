/*
 * Stat.java
 * Author: Jeremy Holloway
 * Submission Date: 11/03/2017
 * 
 * Purpose: To compute the min, max, mode, and average of
 * the stored values.
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
package lab09;
import java.util.Arrays;

public class Stat { //start class
private double[] data;
	
	//default constructor for the class
	public Stat(){ //start stat method
			double[] d = {0.0};
			data = d;
		} 		//end stat method

	//main constructor for the class
	public Stat(double[] d){
		this.data = new double[d.length];
		for (int a = 0; a < d.length; a++){
			data[a] = d[a];
		}
	}
	
	//toString method converts array to a string
	public String toString(){ //start toString method
		String toString = ("[");
		for (int a = 0; a < data.length; a++){
			if (a == (data.length - 1))
				toString += (data[a] + "]");
			else
				toString += (data[a] + ", ");}
		return toString;
	} //end toString method
	
	//min method sets the value of c as the first value and 
	//replaces it with the next lowest encountered value
	public double min(){ //start min method
		double c = data[0];
		for (int b = 0; b < data.length; b++){ //start for loop
			if (data[b] > c )
				;
			else
				c = data[b];		
		} //end for loop
		return c;
		} //end min method
	
	//max method sets the value of d as the first value and 
	//replaces it with the next highest encountered value
	public double max(){ //start max method
		double d = data[0];
		for (int e = 0; e < data.length; e++){ //start for loop
			if (data[e] < d )
				;
			else
				d = data[e];		
		} //end for loop
		return d;
		} //end max method
	
	//average method will add the values stored in the array
	//and divide by the length of the array
	public double average(){//start average method
		double f = 0;
		for (int g = 0; g < data.length; g++){ //start for loop
			f += data[g];
		} //end for loop
		double average = f / data.length;
		return average;
		} //end average method
	
	//mode method will sort the array and count the repitions of each number 
	//and assign the most common number to mode, otherwise it will return NaN
	public double mode(){ //start mode method
		Arrays.sort(data);
		int counter1;
	    int counter2 = 0;
	    double num;
	    double mode = Double.NaN;
	    for (int a = 0; a < data.length; a++){ //start for loop 1
	            num = data[a];
	            counter1 = 1;    
	        for (int b = a + 1; b < data.length; b++) { //start for loop 2
	            if (num == data[b]) 
	            	counter1++;
	        	} //end for loop 2
	        if (counter1 > counter2) { //start if block
	            mode = num;
	            counter2 = counter1;
	        	} //end if block
	        else if(counter1 == counter2)
	            mode = Double.NaN;       
	    } // end for loop 1
	    return mode;
	} //end mode method

	//equals method will compare each individual value of the array one at a time
	public boolean equals(Stat s){ //start equals method
	boolean equals = true;
	double[] c = s.getData();
	for (int a = 0; a < c.length; a++){ //start for loop
		if (data[a] != c[a])
			equals = false;
		else
			;
		} //end for loop
	return equals;}
	
	//getData creates a deep copy of the data array and returns the copy
	public double[] getData(){ //start getData method
		double[] b = new double [data.length];
		for (int a = 0; a < data.length; a++)
			b[a] = data[a];
		return b;
	} //end getData method
	
	//setData makes a copy of d and assigns it to a new array named a
	//then assigns those values to the main data array
	public void setData(double[] d){ //start setData method
		data = new double [d.length];
		for (int a = 0; a < d.length; a++)
			data[a] = d[a];
	} //end setdata method

	public static void main(String[] args) { //start main
		double[] data = {1,2,2,3,3,4};
		Stat stat1 = new Stat(data);
		System.out.println("stat1 data = " + stat1.toString());
		System.out.println("stat1 min = " + stat1.min());
		System.out.println("stat1 max = " + stat1.max());
		System.out.println("stat1 average = " + stat1.average());
		System.out.println("stat1 mode = " + stat1.mode());
		System.out.println("stat1 data = " + stat1.toString());
	} //end main
} //end class
