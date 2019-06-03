/* Author: Jeremy Holloway / jjhollo
*  CPSC-1071-001
*  Lab 9
*  Due Date: 3/31/2018
*  Description: This file holds the function definitions declared in the
*  class header file 
*/

#include <iostream>
#include <cstdlib>
#include <cmath>
#include "money.h"

using namespace std;

/* Function: Money
*  Description: default constructor
*/
Money::Money(){
	dollars = 0;
	cents = 0;
}

/* Function: Money
*  Description: This function will reassign the values to zero if negative
*  or store the new amount
*/
Money::Money(double amount){
	if (amount < 0){
		dollars = 0;
		cents = 0;
	}
	else{
		dollars = (int) amount;
		//double newCent = (amount / (double) dollars) * 100;
		cents = (amount - (double) dollars) * 100;
	}
}

/* Function: Money
*  Description: This function will assign the passed amounts into their 
*  respective values
*/
Money::Money(int dollars, int cents){
	this->dollars = dollars;
	this->cents = cents;
}
/* Function: add
*  Description: This function will add the passed dollars and cents values
*/
Money Money::add(Money otherAmount){
	Money total;
	total.dollars += otherAmount.dollars + this->dollars;
	total.cents += otherAmount.cents + this->cents;
	if(cents >= 100){
		dollars++;
		cents -= 100;
	}
	return(total);	
}

/* Function: ~Money
*  Description: destructor
*/
Money::~Money(void){
}

/* Function: getDollars
*  Description: This function will return the dollar value
*/
int Money::getDollars(){
	return(dollars);
}

/* Function: getCents
*  Description: This function will return the cents value
*/
int Money::getCents(){
	return(cents);
}

/* Function: set
*  Description: This function will assign the passed values to their 
*  respective data values
*/
void Money::set(int dollars, int cents){
	this->dollars = dollars;
	this->cents = cents;
}

/* Function: valueInCents
*  Description: This function will return the total amount in cents 
*/
int Money::valueInCents(){
	int totalCents = 0;
	int addCents = dollars * 100;
	totalCents = addCents + cents;
	return(totalCents);
}

/* Function: dollarsAndCents
*  Description: This function will convert the dollars and cents integers
*  into a double value
*/
double Money::dollarsAndCents(){
	double dolcent = (double) dollars + (((double) cents / 100));
	return (dolcent);
}

