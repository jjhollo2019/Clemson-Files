/* Author: Jeremy Holloway / jjhollo
 * CPSC-1071-001
 * Lab 12
 * Due Date: 4/21/2018
 * Description: Triangle class definition file
 */

#include "triangle.h"
#include <iostream>
#include <cmath>

/* Function: Triangle
 * Description: This function is the default constructor
 */
Triangle::Triangle() : Polygon(){
	side1 = 0;
	side2 = 0;
	side3 = 0;
}

/* Function: Triangle
 * Description: This function is a constructor with a three passed variables
 */
Triangle::Triangle(double a, double b, double c) : Polygon("Triangle", 3){
	side1 = a;
	side2 = b;
	side3 = c;
}

/* Function: getSide1
 * Description: This function will return side1
 */
double Triangle::getSide1(){
	return side1;
}

/* Function: getSide2
 * Description: This function will return side2
 */
double Triangle::getSide2(){
	return side2;
}

/* Function: getSide3
 * Description: This function will return side3
 */
double Triangle::getSide3(){
	return side3;
}

/* Function: computeArea()
 * Description: This function will compute the area by multiplying
 * half the base times the height 
 */
double Triangle::computeArea(){
	double sp;
	double area;
	sp = (side1 + side2 + side3) / 2.0;
	area = sqrt(sp * ((sp - side1) * (sp - side2) * (sp - side3)));
	return area;
}

/* Function: computePerimeter
 * Description: This function will compute the perimeter by adding 
 * all the sides
 */
double Triangle::computePerimeter(){
	double perimeter;
	perimeter = side1 + side2 + side3;
	return perimeter;
}

/* Function: print
 * Description: This function will print all three sides
 */
void Triangle::print(){
	Polygon::print();
	cout << "Side1: " << side1 << " Side2: " << side2 << " Side3: " 
	<< side3 << endl;
}
