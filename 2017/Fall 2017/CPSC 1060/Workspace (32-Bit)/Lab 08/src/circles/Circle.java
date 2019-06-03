/*
 * Circle.java
 * Author: Jeremy Holloway
 * Submission Date: 10/27/2017
 * 
 * Purpose: To create a circle 
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
package circles;
public class Circle {
	

	private double radius;		// declare the private double instance  radius
	private double x;			// declare the private double instance  x
	private double y;			// declare the private double instance  y
	private double distance;
	private boolean equals;
	public boolean isConcentric; 
	public boolean intersects;
	//----------------------------------------------
	// Class Constructor: set the initial values of
	//                    the instance variables
	//                    for this circle
	//----------------------------------------------	
	public Circle(double x, double y, double  radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;  	   	
	}
	
	
	//----------------------------------------------
	// getX - returns the value of x
	//----------------------------------------------
	public double getX() {
		
	return x;	// Your code goes here	
	}
	

	//----------------------------------------------
	// getY - returns the value of y
	//----------------------------------------------
	public double getY() {
		
	return y;	// Your code goes here	
	}
	
	//----------------------------------------------
	// getRadius - returns the value of radius
	//----------------------------------------------
	public double getRadius() {
		
	return radius;	// Your code goes here	
	}

	//----------------------------------------------
	// setX - assigns a new value to x
	//----------------------------------------------
	public void setX(double x) {
		
	this.x = x;	// Your code goes here	
	}
	

	//----------------------------------------------
	// setY - assigns a new value to y
	//----------------------------------------------
	public void setY(double y) {
		
	this.y = y;	// Your code goes here	
	}	
	
	
	//----------------------------------------------
	// setRadius - assigns a new value to radius
	//----------------------------------------------
	public void setRadius(double radius) {
	if (radius > 0)	
		this.radius = radius;	// Your code goes here
	else
		;
	}
	
	//--------------------------------------------------------
	// diameter - calculates the diameter of the circle
	//--------------------------------------------------------
	public double diameter() {
		
	double diameter = radius / 2;	// Your code goes here
	return diameter;
	}
	

	//--------------------------------------------------------
	// area - returns the area of the circle
	//--------------------------------------------------------
	public double area() {
		
	double area = Math.PI * radius * radius;	// Your code goes here
	return area;
	}

	//--------------------------------------------------------
	// perimeter - returns the perimeter of the circle
	//--------------------------------------------------------
	public double perimeter() {
		
	double perimeter = 2 * Math.PI * radius;	// Your code goes here
	return perimeter;
	}
	
	//--------------------------------------------------------
	// isUnitCircle - return true if the radius of this circle
	//                is 1 and its center is (0,0) and false
	//      	      otherwise.
	//--------------------------------------------------------
	public boolean isUnitCircle(double x, double y, double radius) {
	
	boolean isUnitCircle;
	if (x == 0 && y == 0 && radius == 1 ) // Your code goes here	
		isUnitCircle = true;
	else
		isUnitCircle = false;
	return isUnitCircle;
	}
	
	
	//--------------------------------------------------------
	// toString - return a String representation of
	//            this circle in the following format:
	//            center:(x,y)
	//            radius: r
	//--------------------------------------------------------
	public String toString() {
	
	return "center:\t(" + x + "," + y + ")\n" + "radius:\t" + radius;
	}

	public boolean equals(Circle anotherCircle){
	if (x == anotherCircle.x && y == anotherCircle.y && radius == anotherCircle.radius)
		equals = true;
	else 
		equals = false;
	return equals;
	}
	
	public boolean isConcentric(Circle anotherCircle){
	if (x == anotherCircle.x && y == anotherCircle.y && radius != anotherCircle.radius)
		isConcentric = true;
	else
		isConcentric = false;
	return isConcentric;
	}
	
	public double distance(Circle anotherCircle){
		distance = Math.sqrt( Math.pow(anotherCircle.x - x, 2) 
				+ Math.pow(anotherCircle.y - y, 2));
	return distance;
	}
	
	public boolean intersects(Circle anotherCircle){
	if (radius + anotherCircle.radius > distance + anotherCircle.distance)
		intersects = true;
	else
		intersects = false;
	return intersects;
	}
}
