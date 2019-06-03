/*
 * CircleTester.java
 * Author: Jeremy Holloway
 * Submission Date: 10/27/2017
 * 
 * Purpose: To test the circle class
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
public class CircleTester{
	
	public static void main(String[] args) {
		
		Circle circle1= new Circle(0.0,0.0,2);
		Circle circle2= new Circle(2.0,1.0,1);
		Circle circle3= new Circle(-3.0,4.0,3);
		Circle circle4= new Circle(-3.0,4.0,2);
		
		System.out.println("circle1=" + circle1.toString());
		System.out.println("circle2=" + circle2.toString());
		
		// If the method setRadius is implemented correctly,
		// a call to setRadius with a negative number
		// will not change the value of the circle's radius.
		//
		circle1.setRadius(-2.0); 
		
		//
		// Reset the center of circle1 (-3.0,4.0)
		//
		circle1.setX(-3.0);
		circle1.setY(4.0);
		
		
		// print circle1 characteristics (center and radius), use a statement similar 
		// to the previous println statements. Note that is not necessary to call
		//the method toString, why?
		System.out.println("circle1=" + circle1);
		// set the circle2 radius to 5.3
		circle2.setRadius(5.3);
		// print circle2 characteristics (center and radius), use a statement similar to the first and
		// second println statements
		System.out.println("circle2=" + circle2);
		// print circle1 diameter, area and perimeter
		System.out.println("Circle 1: Diameter " + circle1.diameter() + " Area " + circle1.area()
			+ " Perimeter " + circle1.perimeter());
		// print circle2 diameter, area and perimeter
		System.out.println("Circle 2: Diameter " + circle2.diameter() + " Area " + circle2.area()
			+ " Perimeter " + circle2.perimeter());
		// display whether circle1 is a unit circle
		if (circle1.getRadius() == 2)
			System.out.println("Circle 1 is a unit circle");
		else
			System.out.println("Circle 1 is not a unit circle");
		// display whether circle2 is a unit circle
		if (circle2.getRadius() == 2)
			System.out.println("Circle 2 is a unit circle");
		else
			System.out.println("Circle 2 is not a unit circle");
		// your additional tests should be placed below here
		// display whether the radius of the circles are equal
			//determining if the circles are the same using the equals method
			if (circle1.equals(circle2) == true)
				System.out.println("Circle 1 is equal to circle 2");
			else
				System.out.println("Circle 1 is not equal to circle 2");
			if (circle2.equals(circle3) == true)
				System.out.println("Circle 2 is equal to circle 3");
			else
				System.out.println("Circle 2 is not equal to circle 3");
			if (circle1.equals(circle4) == true)
				System.out.println("Circle 1 is equal to circle 4");
			else
				System.out.println("Circle 1 is not equal to circle 4");
			//determing concentricity via the isConcentric method of circles
			if (circle1.isConcentric(circle2) == true)
				System.out.println("Circle 1 is concentric to circle 2");
			else
				System.out.println("Circle 1 is not concentric to circle 2");
			if (circle2.isConcentric(circle3) == true)
				System.out.println("Circle 2 is concentric to circle 3");
			else
				System.out.println("Circle 2 is not concentric to circle 3");
			if (circle1.isConcentric(circle4) == true)
				System.out.println("Circle 1 is concentric to circle 4");
			else
				System.out.println("Circle 1 is not concentric to circle 4");
			//determing the distance of the center of circles from each other
		System.out.println("Circle 1 is " + circle1.distance(circle2) + " from circle 2");
		System.out.println("Circle 2 is " + circle2.distance(circle3) + " from circle 3");
		System.out.println("Circle 1 is " + circle1.distance(circle4) + " from circle 4");
			//determining the intersection of circles via intersects method
			if (circle1.intersects(circle2) == true)
				System.out.println("Circle 1 intersects with circle 2");
			else
				System.out.println("Circle 1 does not intersect with circle 2");
			if (circle2.intersects(circle3) == true)
				System.out.println("Circle 2 intersects with circle 3");
			else
				System.out.println("Circle 2 does not intersect with circle 3");
			if (circle1.intersects(circle4) == true)
				System.out.println("Circle 1 intersects with circle 4");
			else
				System.out.println("Circle 1 does not intersect with circle 4");
	}
	
}
