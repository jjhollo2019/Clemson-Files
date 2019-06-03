/* Author: Jeremy Holloway / jjhollo 
 * CPSC-1071-001
 * Lab 12
 * Due Date: 4/21/2018
 * Description: Triangle class header file
 */

#ifndef TRIANGLE_H
#define TRIANGLE_H

#include "polygon.h"


class Triangle : public Polygon    
{
  public:
    Triangle();
    Triangle(double a, double b, double c);
    double getSide1();
    double getSide2();
    double getSide3();
    double computeArea();
    double computePerimeter();
    void print();

  protected:
    double side1;
    double side2; 
    double side3; 
};

#endif
