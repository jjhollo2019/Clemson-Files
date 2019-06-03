/* Author: Jeremy Holloway
*  File Name: ptrlab.c
*  Date: 2/9/2018
*  Description: This file holds the functions needed for the first part
*  of the lab assignment.
*/
#include <stdio.h>

int main()
{
   int a = 42;
   int b = 7;
   int c = 999;
   int *t = &c;
   int *u = NULL;

   *t = 555;
   printf("%d\n", c);

   printf("%d %d\n", a, *t);

   c = b;
   u = t;

   printf("%d %d\n", c, *u);

   a = 8;
   b = 8;
   printf("%d %d %d %d\n", b, c, *t, *u);

   *t = 235;
   printf("%d %d %d %d %d\n", a, b, c, *t, *u);

   return 0;
}
