/* Author: Jeremy Holloway
*  File Name: leak.c
*  Date: 2/23/2018
*  Description: This program will reserve a block of memory large enough
*  to hold 100 characters.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Function: Main
*  Description: The main will assign the string to the new space created in p
*  Fix: The string was not properly assigned to pointer 
*/
int main()
{
    char *p = malloc(sizeof(char) * 100);
    strcpy(p, "constantionople"); //changed from p = "constantionople";
    printf("%s\n", p); //added a newline for better printing
    free (p); //freed declared space after use

    return 0;
}

