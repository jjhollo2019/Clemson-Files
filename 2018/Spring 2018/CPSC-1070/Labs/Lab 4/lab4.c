/* Author: Jeremy Holloway
*  File Name: lab4.c
*  Date: 2/9/2018
*  Description: This program will convert a total amount of inches to a total amount of yards,
*  feet, and inches based on the input value. 
*/
#include <stdio.h>

void convertInches(int totalInches, int* yards, int* feet, int* inches);

/* Function Name: Main
*  Description: The main will call the convert inches function on the assigned value.
*/
int main()
{
   int totalInches = 213;
   int yards = 0;
   int feet = 0;
   int inches = 0;
  
   
   convertInches(totalInches, &yards, &feet, &inches);  // function call

   printf("\n%d total inches = %d yards, %d feet, %d inches\n\n",
          totalInches, yards, feet, inches);

   return 0;

}

/* Function Name: convertInches
*  Description: This function will convert the totalInches into sub groups of yards,
*  feet, and inches.
*/
void convertInches(int totalInches, int* yards, int* feet, int* inches)
{
   *yards = totalInches / 36;
   totalInches = totalInches % 36;
   *feet = totalInches / 12;
   *inches = totalInches % 12;
}
