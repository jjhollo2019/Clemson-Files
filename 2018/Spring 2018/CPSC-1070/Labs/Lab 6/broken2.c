/* Author: Jeremy Holloway
*  File Name: broken2.c
*  Date: 2/23/2018
*  Description: This program will copy the data of a given array.
*/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#define NUM_ARGS 2

/* The command to execute the program is

      ./broken n

   where n is the number of elements to copy
*/

void copyData(int *data, int *copy, int n);

/* Function: Main
*  Description: The main will call the function copyData to make a full copy
*  of the given array.
*/
int main(int argc, char *argv[])
{
    int i;
    int n;
    int size;
    int data[15] = {1, 9, 7, 14, 20, 11, 6, 8, 23, 45, 17, 36, 48, 62, 35};
    int *copy = NULL;

    assert(argc == NUM_ARGS);

    n = atoi(argv[1]);

    /* verify that the number of values to copy does not exceed the
       number of values in the data array
    */
    
    size = sizeof(data) / sizeof(int);
    assert(n <= size);
    copy = (int*)malloc(sizeof(data));

    copyData(data, copy, n);

    printf("\ndata:\n");
    for(i = 0; i < size; i++)
    {
        printf("%d\n", *(data + i));
    }

    printf("\ncopied data:\n");
    for(i = 0; i < n; i++)
    {
        printf("%d\n", *(copy + i));
    }

    return 0;
}
         

/* Function: copyData
*  Description: Copies n elements of data array to the copy array  
*/
void copyData(int *data, int *copy, int n)
{
   int i;
   for(i = 0; i < n; i++)
   {
      *(copy + i) = *(data + i);
   }
}
