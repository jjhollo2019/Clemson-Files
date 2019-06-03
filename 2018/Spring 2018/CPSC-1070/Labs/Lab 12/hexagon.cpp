/* Author: Jeremy Holloway / jjhollo
 * CPSC-1071-001
 * Lab 12
 * Due Date: 4/21/2018
 * Description: This is the definition file for the hexagon class
 */

#include <cmath>
#include <iostream>
#include "hexagon.h"

/* Function: Hexagon
 * Description: This function is the default constructor
 */
Hexagon::Hexagon() : Polygon(){
	side = 0;
}

/* Function: Hexagon
 * Description: This function is a constructor with a passed side variable
 */
Hexagon::Hexagon(double a) : Polygon("Hexagon", 6){
	side = a;
}

/* Function: getSide
 * Description: This function returns the side value
 */
double Hexagon::getSide(){
	return side;
}

/* Function: computeArea
 * Dexription: This function will compute the area by multiplying 3 times
 * the square root of 3 over two by the side length squared
 */
double Hexagon::computeArea(){
	double area;
	area = ((3 * sqrt(3)) / 2) * (side * side);
	return area;
}

/* Function: computerPerimeter
 * Description: This function will compute the perimeter by multiplying
 * the side length by 6
 */
double Hexagon::computePerimeter(){
	double perimeter;
	perimeter = side * 6;
	return perimeter;
}

/* Function: print
 * Description: This function will print the side length
 */
void Hexagon::print(){
	Polygon::print();
	cout << "side_length: " << side << endl;
}
