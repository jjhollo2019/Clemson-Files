/* Author: Jeremy Holloway / jjhollo
 * CPSC-1071-001
 * Lab 12
 * Due Date: 4/21/2018
 * Description: This file is the header file foe the rectangle class
 */

#ifndef RECTANGLE_H
#define RECTANGLE_H

#include "polygon.h"

class Rectangle : public Polygon 
{
  public:
    Rectangle();
    Rectangle(double length, double width);
    double computeArea();
    double computePerimeter();
    void print();

  protected:
    double length;
    double width; 
};

#endif
