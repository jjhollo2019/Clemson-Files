/**
  CPSC 1071
  Lab 10 - Inheritance
**/

#ifndef POLYGON_H
#define POLYGON_H

#include <string>

using namespace std;

class Polygon 
{
  public:
    Polygon();
    Polygon(string type, int sides);
    string getType();
    int getSides();

    virtual double computeArea() = 0;
    virtual double computePerimeter() = 0;
    virtual void print();

  protected:
    string type;
    int sides;

  private:
    int id;
};

#endif
