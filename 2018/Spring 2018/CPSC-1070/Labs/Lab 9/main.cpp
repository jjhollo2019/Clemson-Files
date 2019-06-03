/* Author: Jeremy Holloway / jjhollo
*  CPSC-1071-001
*  Lab 9
*  Due Date: 3/31/2018
*  Description: premade main file for using the money class
*/

#include <iostream>
#include <iomanip>
#include "money.h"

using std::cin;
using std::cout;
using std::endl;
using std::showpoint;
using std::fixed;
using std::setprecision;

/* Function: Main
*  Description:  The main function will provide input and call the required
*  functions for testing the money class
*/
int main() {
   Money checking;            // uses default constructor
   Money savings(575.85);
   Money checking2 = Money(100, 0);
   Money *other = new Money(1500, 35);
   Money total;

   cout << fixed << showpoint << setprecision(2);

   cout << "\nchecking amount:  "
        << checking.dollarsAndCents() 
        << endl;
   
   cout << "\nchecking2 amount:  "
        << checking2.dollarsAndCents() 
        << endl;
   
   cout << "\nsavings amount in dollars and cents:  "
        << savings.getDollars() 
        << " dollars and "
        << savings.getCents()
        << " cents" << endl;

   cout << "\nother amount in cents:  "
        << other->valueInCents() 
        << " cents" << endl;

   cout << "\nsetting amount in checking" << endl;
   checking.set(1200, 98);
   cout << "new checking amount:  ";
   cout << checking.dollarsAndCents() 
        << endl;

   cout << "\nother amount:  "
        << other->dollarsAndCents() 
        << endl << endl;

   cout << "Adding checking and savings, storing in total" << endl;

   total = checking.add(savings);
   cout << "total amount:  "
        << total.dollarsAndCents() 
        << endl << endl;

   return 0;
}

