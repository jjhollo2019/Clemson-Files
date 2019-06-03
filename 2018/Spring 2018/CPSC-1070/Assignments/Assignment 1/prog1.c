/* Author: Jeremy Holloway
*  File Name: prog1.c
*  Date: 2/1/2018
*  Description: This program will eliminate the random noise and enhance an image.
*/

#include <stdio.h>
#include <stdlib.h>
#include "prog1.h"

/*
* Function: main
* Description: The main will declare a new array and call various functions to read, enhance, and print the image.
*/
int main()
{
	const int row = 20;
	const int column = 77;
	int image[20][77];
	readImage(image, row, column);
	enhance(image, row, column);
	printImage(image, row, column);
}

/*
* Function: readImage
* Description: This function will read the input array and input into a constant array
*/
void readImage(int image[ROW_SIZE][COL_SIZE], int rows, int columns)
{
	int a, b;
	for(a = 0;a < rows; a++){
		for(b = 0; b < columns; b++){
		   scanf("%d", &image[a][b]); 
		}
	}
}

/*
* Function: enhance
* Description: This function will use the reduceNoise and addContrast functions to 
* enhance the image
*/
void enhance(int image[ROW_SIZE][COL_SIZE], int rows, int columns)
{
	reduceNoise(image, rows, columns);
	addContrast(image, rows, columns);
}

/*
* Function: printImage
* Description: This fuction will loop through the array and print each element to 
* the monitor
*/
void printImage(int image[ROW_SIZE][COL_SIZE], int rows, int columns)
{
	int a, b;
	for(a = 0; a < rows; a++){
		for(b = 0; b < columns; b++){
			printf("%d", image[a][b]);
			if(b == (columns - 1))
		  	printf("\n");
		}
	}
}

/*
* Function: reduceNoise
* Description: This function will check each array element thats 3 greater than its 8 neighbors 
* and change it to the average of the eight.
*/
void reduceNoise(int image[ROW_SIZE][COL_SIZE], int rows, int columns)
{
	int a, b;
	int c = 0;
	for(a = 1; a < (rows - 1); a++){ 
		for(b = 1; b < (columns - 1); b++){
			if (abs(image[a][b] - image[a - 1][b - 1]) >= 3)
			   c++; 
			if (abs(image[a][b] - image[a - 1][b]) >= 3)
			   c++; 
			if (abs(image[a][b] - image[a - 1][b + 1]) >= 3)
			   c++;
			if (abs(image[a][b] - image[a][b - 1]) >= 3)
			   c++;
			if (abs(image[a][b] - image[a][b + 1]) >= 3)
			   c++;
			if (abs(image[a][b] - image[a + 1][b - 1]) >= 3)
			   c++;
			if (abs(image[a][b] - image[a + 1][b]) >= 3) 
			   c++;
			if (abs(image[a][b] - image[a + 1][b + 1]) >= 3)
			   c++;
			if (c == 8){
			   image[a][b] = (image[a - 1][b - 1] + image[a - 1][b] + image[a - 1][b + 1] + image[a][b - 1] + image[a][b + 1]
				      + image[a + 1][b - 1] + image[a + 1][b] + image[a + 1][b + 1]) / 8;
			   c = 0;
			}
  		}
	}
}
/*
* Function: addContrast
* Description: This function will replace determined integers to add image contrast
*/
void addContrast(int image[ROW_SIZE][COL_SIZE], int rows, int columns)
{
	int a, b;
	for(a = 0; a < rows; a++){
		for(b = 0; b < columns; b++){
	   		if (image[a][b] == 0 || image[a][b] == 1 || image[a][b] == 2 || image[a][b] == 3)
	      		image[a][b] = 0;
	   		if (image[a][b] == 4 || image[a][b] == 5 || image[a][b] == 6)
	      		image[a][b] = 1;
	   		if (image[a][b] == 7 || image[a][b] == 8 || image[a][b] == 9)
	      		image[a][b] = 4;
		}	
	}
}


