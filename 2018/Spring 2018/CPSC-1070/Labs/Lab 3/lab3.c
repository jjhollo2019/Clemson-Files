/* Author: Jeremy Holloway
*  File Name: lab3.c
*  Date: 2/2/2018
*  Description: This program will sort a data file into positive and
*  negative integers and write them to an output file 
*/

#include <stdio.h>
#include <stdlib.h>

/* Function: Main
*  Description: This function will sort the input file into the defined
*  output file and display a type count for each after writing the integers
*  to the designated files
*/
int main (int argc, char *argv[])
{
	FILE *infile = fopen("lab3.dat", "r");
	FILE *outfile1 = fopen("positives.txt", "w");
	FILE *outfile2 = fopen("negatives.txt", "w");
	int b;
	int posCount = 0;
	int negCount = 0;
	int zeros = 0;
	
	while (fscanf(infile, "%d", &b) !=EOF){
		if (b > 0){
			fprintf(outfile1, "%d\n", b);
			posCount++;
		}
		if (b < 0){
			fprintf(outfile2, "%d\n", b);
			negCount++;
		}
		if (b == 0)
			zeros++;
	}
	printf("Positives: %d\n", posCount);
	printf("Negavtives: %d\n", negCount);
	printf("Zeros: %d\n", zeros);
	fclose(infile);
	fclose(outfile1);
	fclose(outfile2);
}
