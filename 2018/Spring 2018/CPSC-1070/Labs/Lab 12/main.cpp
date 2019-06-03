/* Author: Jeremy Holloway / jjhollo
 * CPSC-1071-001
 * Lab 12
 * Due Date: 4/21/2018
 * Description: This is the main file which calls the defined functions
 */

#include <iostream>
#include "rectangle.h"
#include "triangle.h"
#include "hexagon.h"

int main(){
    // creating a rectangle with length and width 3.0 and 4.0
    Rectangle r(3.0, 4.0);
    Triangle t1(4.0, 5.0, 3.0);
    Triangle *t2 = new Triangle(7.0, 6.0, 7.0);

    r.print();
 
    std::cout << "\nRectangle's perimeter: " 
              << r.computePerimeter() << std::endl;
    std::cout << "Rectangle's area:      " 
              << r.computeArea() << std::endl;

    /** print triangle t1  **/
    t1.print();
    std::cout << "\nTriangle t1's perimeter: " 
              << t1.computePerimeter() << std::endl;
    std::cout << "Triangle t1's area: " 
              << t1.computeArea() << std::endl;
    /**
      print triangle t2 and its perimeter and area.
    **/
    t2->print();
    std::cout << "\nTriangle t1's perimeter: "
              << t2->computePerimeter()
              << "\nTriangle t2's area:      "
              << t2->computeArea()
              << std::endl;

    /**
      Testing your hexagon code here.
    **/
    Hexagon h1(7.0);
    h1.print();
    std::cout << "\nHexagon's perimeter: " 
              << h1.computePerimeter() << std::endl;
    std::cout << "Hexagon's area:        " 
              << h1.computeArea() << std::endl;

    return 0;
}
