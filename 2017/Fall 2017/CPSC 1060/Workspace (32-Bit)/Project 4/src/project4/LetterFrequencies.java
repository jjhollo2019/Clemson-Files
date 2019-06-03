/*
 * LetterFrequencies.java
 * Author: Jeremy Holloway
 * Submission Date: 12/1/2017
 * 
 * Purpose: To count all of the letters in the provided inputs.
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
package project4;
public class LetterFrequencies{

	//letterCount method will first check the array for nulls and replaces them with blank spaces and 
	//then use a switch statement to add the number of characters to an array.
	public static void letterCount(String[] s, int[] count){//start lettercount method
		if(s == null)
			return;
		if (count == null)
			return;
		else{//start else statement 1
			boolean checkNull = true;
			while(checkNull){//start while loop
				if (s.length == 0)
					checkNull = false;
				else{//start else statment 2
				for (int d = 0; d < s.length; d++){//start for loop 1
					if (s[d] == null)
						s[d] = " ";
					else if (s[s.length - 1] != null)
						checkNull = false;
					else
						;
					}//end for loop 1
				}//end else statement 2
			}//end while loop
			for (int a = 0; a < s.length; a++){//start loop 2
				for (int b = 0; b < s[a].length(); b++){//start for loop 3
					char c = s[a].charAt(b);
					switch (c){//start switch statement
					case ' ':
						break;
					case 'a':
						count[0] += 1;
						break;
					case 'b':
						count[1] += 1;
						break;
					case 'c': 
						count[2] += 1;
						break;
					case 'd':
						count[3] += 1;
						break;
					case 'e':
						count[4] += 1;
						break;
					case 'f':
						count[5] += 1;
						break;
					case 'g':
						count[6] += 1;
						break;
					case 'h':
						count[7] += 1;
						break;
					case 'i':
						count[8] += 1;
						break;
					case 'j':
						count[9] += 1;
						break;
					case 'k':
						count[10] += 1;
						break;
					case 'l':
						count[11] += 1;
						break;
					case 'm':
						count[12] += 1;
						break;
					case 'n':
						count[13] += 1;
						break;
					case 'o':
						count[14] += 1;
						break;
					case 'p':
						count[15] += 1;
						break;
					case 'q':
						count[16] += 1;
						break;
					case 'r':
						count[17] += 1;
						break;
					case 's':
						count[18] += 1;
						break;
					case 't':
						count[19] += 1;
						break;
					case 'u':
						count[20] += 1;
						break;
					case 'v':
						count[21] += 1;
						break;
					case 'w':
						count[22] += 1;
						break;
					case 'x':
						count[23] += 1;
						break;
					case 'y':
						count[24] += 1;
						break;
					case 'z':
						count[25] += 1;
						break;
					case 'A':
						count[0] += 1;
						break;
					case 'B':
						count[1] += 1;
						break;
					case 'C': 
						count[2] += 1;
						break;
					case 'D':
						count[3] += 1;
						break;
					case 'E':
						count[4] += 1;
						break;
					case 'F':
						count[5] += 1;
						break;
					case 'G':
						count[6] += 1;
						break;
					case 'H':
						count[7] += 1;
						break;
					case 'I':
						count[8] += 1;
						break;
					case 'J':
						count[9] += 1;
						break;
					case 'K':
						count[10] += 1;
						break;
					case 'L':
						count[11] += 1;
						break;
					case 'M':
						count[12] += 1;
						break;
					case 'N':
						count[13] += 1;
						break;
					case 'O':
						count[14] += 1;
						break;
					case 'P':
						count[15] += 1;
						break;
					case 'Q':
						count[16] += 1;
						break;
					case 'R':
						count[17] += 1;
						break;
					case 'S':
						count[18] += 1;
						break;
					case 'T':
						count[19] += 1;
						break;
					case 'U':
						count[20] += 1;
						break;
					case 'V':
						count[21] += 1;
						break;
					case 'W':
						count[22] += 1;
						break;
					case 'X':
						count[23] += 1;
						break;
					case 'Y':
						count[24] += 1;
						break;
					case 'Z':
						count[25] += 1;
						break;
					}//end switch statement	
				}//end for loop 3	
			}//end for loop 2
		}//end else statement
	}//end lettercount method

	//initialize count method will set all values of the count array to zero.
	public static void initializeCount(int[] count){//start initialize count method
		if (count == null)
			return;
		else {//start else block
			for (int a = 0; a < count.length; a++)
				count[a] = 0;
		}//end else block
	}//end initialize count method
}//end LetterFrequencies class
