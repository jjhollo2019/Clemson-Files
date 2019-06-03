/*
 * FindTheSums.java
 * Author: Jeremy Holloway
 * Submission Date: 12/1/2017
 * 
 * Purpose: To write a method that finds the horizontal and vertical sums.
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
package lab12;

public class FindTheSums {//start class

	//arrayToString method will convert an array into a print statement 
	public static String arrayToString(int[][] a){//start arrayToString method
		String array = "";
		for (int b = 0; b < a.length; b++){//start for loop 1
			for (int c = 0; c < a[b].length; c++){//start for loop 2
				array += a[b][c];
				if (c != (a[b].length - 1))
					array += (" ");
			}//end for loop 2
			if(b != (a.length - 1))
				array += ("\n");
		}//end for loop 1
		return array;
	}//end arrayToFind method

	//horizontalSums method will add the row values in the array and find a specific sum.  Once it finds the
	//sum it will add the corresponding values into a newly created array
	public static int[][] horizontalSums(int[][] a, int sumToFind){//start horizontalSums method
		int horzSum = 0;
		int index = 0;
		int colCount = 0;
		int[][] horzArray = new int[a.length][a[0].length];
		for (int b = 0; b < a.length; b++){//start for loop 1
			index = 0;
			for (int c = 0; c < a[b].length; c++){//start for loop 2
				colCount++;
				horzSum = 0;
				for (int d = c; d < a[b].length; d++){//start for loop 3

					horzSum += a[b][d];
					if (horzSum > sumToFind){//start if block
						d = a[b].length;
						index++;
						colCount = 0;
					}//end if block
					else if (horzSum == sumToFind){//start else if block
						for (int i = c; i <= d; i++)
							horzArray[b][i] = a[b][i];
						index++;
						colCount = 0;
					}//end else if block
					else
						;	
				}//end for loop 3
			}//end for loop 2
		}//end for loop 1
		return horzArray;
	}//end horizontal sums method

	//verticalSums method will add the column values in the array and find a specific sum.  Once it finds the
	//sum it will add the corresponding values into a newly created array
	public static int[][] verticalSums(int[][] a, int sumToFind){//start verticalSums method
		int vertSum = 0;
		int index = 0;
		int colCount = 0;
		int[][] vertArray = new int[a.length][a[0].length];
		for (int b = 0; b < a.length; b++){//start for loop 1
			index = 0;
			for (int c = 0; c < a.length; c++){//start for loop 2
				colCount++;
				vertSum = 0;
				for (int d = c; d < a.length; d++){//start for loop 3
					vertSum += a[d][b];
					if (vertSum > sumToFind){//start if block
						d = a.length;
						index++;
						colCount = 0;
					}//end if block
					else if (vertSum == sumToFind){//start else if block
						for (int i = c; i <= d; i++)
							vertArray[i][b] = a[i][b];
						index++;
						colCount = 0;
					}//end if else block
					else
						;	
				}//end for loop 3
			}//end for loop 2
		}//end for loop 1
		return vertArray;
	}//end verticalSums method

}//end class 
