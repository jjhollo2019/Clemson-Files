/* Author: Jeremy Holloway
*  File Name: mystrings.c
*  Date: 2/15/2018
*  Description: This file holds the three functions needed for the tester
*/
#include <stdio.h>
#include <stdlib.h>
#include "mystrings.h"

/* Function: mystrlen
*  Description: This function will return the length of the string
*/ 
int mystrlen(char *s1)
{
	int a;
	int b = 0;
	for (a = 0; *(s1 + a) != '\0'; a++)
		b++;
	return(b);
}

/* Function: *mystrupper
*  Description: This function will convert and character from lower to upper case
*/
char *mystrupper(char *s1)
{
	int a;
	for (a = 0; *(s1 + a) != '\0'; a++){
		if (*(s1 + a) >= 'a' && *(s1 + a) <= 'z'){
			*(s1 + a) = *(s1 + a) - 'a' + 'A';
		}
	}
	return(s1);
}

/* Function: mystrcmp
*  Description: This function will compare each element to check for an inequality
*/
int mystrcmp(char *s1, char *s2)
{
	int a, b;
	int c = 0;
	for (a = 0; *(s1 + a) != '\0'; a++){
		for (b = 0; *(s1 + b) != '\0'; b++){
			if (*(s1 + a) == *(s2 + b))
				c = 0;
			if (*(s1 + a) > *(s2 + b))
				c = -1;
			if (*(s1 + a) < *(s2 + b))
				c = 1;
		}
	}
	return(c);
}
