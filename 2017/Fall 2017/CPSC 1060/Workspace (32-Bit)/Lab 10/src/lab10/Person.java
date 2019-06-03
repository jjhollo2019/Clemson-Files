/*
 * Person.java
 * Author: Jeremy Holloway
 * Submission Date: 11/10/2017
 * 
 * Purpose: This class hols the functions to record personal data.
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
package lab10;
public class Person {//start class

	private int age;
	private String name;
	private boolean isFemale;
	public static int totalPersons;
	public static int totalFemales;
	public static int totalAge;

//the person constructor will set the local variables to the instance
//variables and validate the inputs
public Person (String name, int age, boolean isFemale){//start constructor
	if (age > 0)//start age if block
		this.age = age;
	else if (age == 0)
		this.name = "unknown";
	else
		;//end age if block
	this.name = name;
	if (this.name == null)//start name if block
		this.name = "Unknown";
	else
		;//end name if block
	totalPersons++;
	totalAge += this.age;
	if (isFemale == true){//start isFemale if block
		this.isFemale = isFemale;
		totalFemales++;
		}
	else
		this.isFemale = false;//end isFemale if block
}//end constructor

//getAge method will return the instance variable for age
public int getAge(){//start getAge
	return this.age;
}//end getAge

//setAge method will replace the instance level variable while
//validating the input and the static variable of total age
public void setAge(int age){//start setAge
	totalAge -= this.age;
	if (age > 0)//start age if block
		this.age = age;
	else if (age == 0)
		this.name = "unknown";
	else
		;//end age if block
	totalAge += this.age;
}//end setAge

//getName method returns the instance variable for name
public String getName(){//start getName
	return this.name;
}//end getName

//setName method will update the instance variable of the name
//while validating the input
public void setName(String name){//start setName
	if (this.name == null)//start name if block
		;
	else
		this.name = name;//end name if block
}//end setName

//getIsFemale methos will return the instance variable for isFemale
public boolean getIsFemale(){//start getIsFemale
	return this.isFemale;
}//end getIsFemale

//setIsFemale method will update the instance variable for isFemale
//and update the total count of females is applicable
public void setIsFemale(boolean isFemale){//start setIsFemale
	if (isFemale = true){//start isFemale if block
		this.isFemale = true;
		totalFemales++;
		}
	else
		;//end isFemale if block
}//end setIsFemale 

//equals method will compare the instance variables for name, age, 
//and isFemale to return a true/false value
public boolean equals(Person x){//start equals
	boolean equal = false;
	if (this.name.equalsIgnoreCase(x.name) && this.age == x.age && 
		this.isFemale == x.isFemale)//start equals if block
		equal = true;
	else
		;//end equals if block
	return equal;
}//end equals

//isYounger method will compare the age of one instance of person to 
//another and returning a true/false value
public boolean isYounger(Person x){//start isYounger
	boolean younger = false;
	if (this.age < x.age)//start isYounger if block
		younger = true;
	else
		;//end isYounger if block
	return younger;
}//end isYounger

//toString method will convert the constructor into a string 
public String toString(){//start toString
	String female = "";
	if (getIsFemale() == true)//start toString if block
		female += "Female";
	else
		female += "Male";//end toString if block
	return ("Name: " + getName() + "\nAge: " + getAge() + "\n" + female);
}//end toString

//avgAge method will divide two static variables for an overall average
//the class age values
public static double avgAge(){//start avgAge
	double avg = (totalAge / (double) totalPersons);
	return avg;
}//end avgAge

//howManyPeople method returns the stored value of the number of times
//the constructor has been used
public static int howManyPeople(){//start howManyPeople
	int peeps = totalPersons;
	return peeps;
}//end howManyPeople

//howManyFemales returns the stored value of the number of females
public static int howManyFemales(){//start howManyFemales
	int women = totalFemales;
	return women;
}//end howManyFemales
}//end class
