/* Author: Jeremy Holloway / jjhollo 
 * CPSC-1071-001
 * Lab 12
 * Due Date: 4/21/2018
 * Description: This is the implementation file for the rectangle class 
 */

#include <iostream>
#include "rectangle.h"

/* Function: Rectangle
 * Description: This function is the default constructor 
 */
Rectangle::Rectangle() : Polygon(){
	length = 0;
	width = 0;
}

/* Function: Rectangle
 * Description: This function is a constructor with a passed width and
 * length value
 */
Rectangle::Rectangle(double length, double width) : Polygon("Rectangle", 4){
   this->length = length;
   this->width = width; 
}

/* Function: computeArea
 * Description: This function will compute the area by multipling the 
 * length by the width
 */
double Rectangle::computeArea(){
    return length * width;
}

/* Function: computePerimeter
 * Description: This function will compute the perimeter by adding 
 * the length and width and multiplying the sum by two
 */
double Rectangle::computePerimeter(){
    return 2 * (length + width);
}

/* Function: print
 * Description: This function will print the length and width
 */
void Rectangle::print(){
   Polygon::print();
   std::cout << "length: " << length
             << "  width: " << width << std::endl; 
}

