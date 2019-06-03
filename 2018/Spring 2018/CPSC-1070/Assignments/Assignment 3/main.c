/* Author: Jeremy Holloway-jjhollo / Shawn Picardy-picardy
*  CPSC-1070-001
*  Assignment 3
*  Due Date: 4/2/2018
*  Description: This file will call the functions built in bigint.c
*/ 
#include "bigint.h"

/* Function: main
*  Description:  The main will call the appropriate functions to add the two
*  big integers together and print them to the screen
*/
int main(int argc, char *argv[])
{

   FILE *infile;
   list_t *bigint1 = newList();
   list_t *bigint2 = newList();
   list_t *result = newList(); 
   list_t *result2 = newList();
   
   char *numstr1 = NULL;
   char *numstr2 = NULL;

   if(argc < 2)
   {
      fprintf(stderr, "Usage:  ./p3 inputfilename\nexiting\n");
      exit(1);
   }

   infile = fopen(argv[1], "r");
   
   if(infile == NULL)
   {
      fprintf(stderr, "\nfailed to open %s.\nexiting\n\n", argv[1]);
      exit(1);
   } 

   numstr1 = readNumber(infile);
   numstr2 = readNumber(infile); 
   
   buildList(bigint1, numstr1);
   buildList(bigint2, numstr2);

   result = add(bigint1, bigint2);
   
   result2 = subtract(bigint1, bigint2);
   
   printf("\n\n");
   printf("Bigint1:  ");
   printReverse(bigint1);
   printf("\n");
   printf("bigint2:  ");
   printReverse(bigint2);
   printf("\n");
   printf("sum:      ");
   printReverse(result);
   printf("\n");
   printf("diff:     ");
   printReverse(result2);
   printf("\n\n");

   return 0;
}
