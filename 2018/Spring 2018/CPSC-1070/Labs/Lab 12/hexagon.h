/* Author: Jeremy Holloway / jjhollo
 * CPSC-1071-001
 * Lab 12
 * Due Date: 4/21/2018
 * Description: This is the header file for the hexagon class
 */

#ifndef HEXAGONE_H
#define HEXAGONE_H

#include "polygon.h"

class Hexagon : public Polygon{
	public:
		Hexagon();
		Hexagon(double a);
		double getSide();
		double computeArea();
		double computePerimeter();
		void print();

	protected:
		double side;
};

#endif
