/**
   CPSC 1071
   Lab 12 - Inheritance

 **/

#include <iostream>
#include "polygon.h"

Polygon::Polygon() :
   type(""), sides(0)
{ }

Polygon::Polygon(string t, int sides) 
{ 
    type = t; 
    this->sides = sides;
}

string Polygon::getType()
{
    return type;
}

int Polygon::getSides()
{
    return sides;
}

void Polygon::print()
{
   std::cout << "\ntype: " << type 
             << "  sides: " << sides
             << std::endl;
}
            
