/*
 * LinearSearch.java
 * Author: Jeremy Holloway
 * Submission Date: 11/17/2017
 * 
 * Purpose: Perform a linear search on command line arguements 
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
package lab11;

public class LinearSearch {//start class

//getFirstIndex method search through the array and return the
//index of the first occurance or return -1 for not found
public static int getFirstIndex(double item, double[] x){//start getFirstIndex
	boolean getItem = true;
	int itemNum = 0;
	int a = 0;
	while (getItem){//start while loop
		if (a == (int) x.length){//start if length
			itemNum = -1;
			getItem = false;
		}//end if length
		else{//start else 
			if (x[a] == item){	//start if equals
			itemNum = a;
			getItem = false;
			}//end if equals
			else
				a++;
			}//end else block
		}//end while loop
	return itemNum;
}//end getFirstindex

//getFirstIndex method search through the array and return the
//index of the first occurance or return -1 for not found
public static int getFirstIndex(String item, String[] x){//start getFirstIndex
	boolean getItem = true;
	int itemNum = 0;
	int a = 0;
	while(getItem){//start while loop
		if (a == x.length){//start if length
			itemNum = -1;
			getItem = false;
		}//end if length
		else{//start else 
			if (x[a].equals(item)){//start if equals
			itemNum = a;
			getItem = false;
			}//end if equals
			else
				a++;
			}//end else
		}//end while loop
	return itemNum;
}//end getFirstindex

//the main will create a new array of the sorted input values and 
//call the designated methods to perform a linear search based on the input
public static void main(String[] args){//start main
	String item = "";
	double itemdub = 0;
	int a = 0;
	if (args[0].equals("-s")){//start string block
		item = args[1];
		String [] data = new String[(args.length - 3)];
		for (int i = 3; i < args.length; i++, a++){//start new array loop
			data[a] = args[i];
		}//end new array loop
		int b = getFirstIndex(item, data);
		if (b == -1)
			System.out.println(item + " not fount in x");
		else
			System.out.println(item + " found in x at index " + b);	
	}//end string block
	else if (args[0].equals("-d")){//start double block
		itemdub = Double.parseDouble(args[1]);
		double[] dataNum = new double[(args.length - 3)];
		for (int i = 3; i < args.length; i++, a++){//start new array loop
			dataNum[a] = Double.parseDouble(args[i]);
		}//end new array loop
		int b = getFirstIndex(itemdub, dataNum);
		if (b == -1)
			System.out.println(itemdub + " not found in x");
		else
			System.out.println(itemdub + " found in x at index " + b);
	}//end double block
	else
	;
	}//end main
}//end class
