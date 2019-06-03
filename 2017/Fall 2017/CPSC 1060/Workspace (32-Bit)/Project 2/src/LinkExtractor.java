/*
 * LinkExtractor.java
 * Author: Jeremy Holloway
 * Submission Date: 10/13/2017
 * 
 * Purpose: To extract links from user entered web pages.
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

public class LinkExtractor {

	public static void main(String[] args) {
	Scanner Keyboard = new Scanner(System.in);
	
	
	boolean valid = true;
	System.out.println("Enter a URL to read from (include the "
	+ "\"http://\" or \"https://\"), or type Q to quit:");
	String entry = Keyboard.nextLine();
	
		if (valid == true)  {//start of if block
			if (entry.contains("http://"))
				valid = true;
			else if (entry.contains("https://"))
				valid = true;
			else
				valid = false;	
			}//end of if block
		else if (entry.contains("Q")) { //start of else if block
			System.out.println("Goodbye!");
			System.exit(0);
			} //end of else if block
		else {
			while (valid = false) {
				System.out.println("Invalid URL Entered");
				System.out.println("Enter a URL to read from (include the "
				+ "\"http://\" or \"https://\"), or type Q to quit:");
				entry = Keyboard.nextLine();
				}
			}
	while (valid = true){
		int b = entry.indexOf("ht");
		int c = entry.indexOf("edu");
		String URL = entry.substring(b, c + 3);
		System.out.print("Enter the type of link to look for (1-5)\n" +
				"1. Images (PNG, JPG, BMP, GIF)\n"+
				"2. Documents (DOC, TXT, PDF)\n"+
				"3. Hypertext (HTM, HTML)\n"+
				"4. All Links\n"+
				"5. Quit");
		double link = Keyboard.nextDouble();
			if (link >= 1 && link <= 5) {
				String webpage = ("");
			if (link == 1) 
				webpage = Fetch.fetchURL(URL);
				int start = webpage.indexOf("href=");
				int finish = webpage.indexOf("\">");
				String output = webpage.substring(start, finish);
					if ((start < 0) || (finish < 0))
							webpage = ("\n");
					else {
						System.out.println(output);
						while (output.endsWith("png"))
							System.out.print(output + "\n");
						while (output.endsWith("jpg"))
							System.out.println(output + "\n");
						while (output.endsWith("bmp"))
							System.out.println(output + "\n");
						while (output.endsWith("gif"))
							System.out.println(output + "\n");
						}
					System.out.println(webpage);
			if (link == 2)
				webpage = Fetch.fetchURL(URL);
			if (link == 3)
				webpage = Fetch.fetchURL(URL);
			if (link == 4)
				webpage = Fetch.fetchURL(URL);
			if (link == 5) {
				System.out.println("Goodbye!");
				System.exit(0);
			} //end of current if
			} //end of do-while loop	
		else 
		System.out.println("Invalid number Entered");
	} //end of 1st while loop
	
	
	
		
	
	

	
	Keyboard.close();
	} //end of main
} //end of class

