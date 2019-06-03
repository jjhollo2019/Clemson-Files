/* Author: Jeremy Holloway jjhollo / Shawn Picardy picardy
*  CPSC-1070-001
*  Assignment 3
*  Due Date: 4/02/2018
*  Description: This program will hold the functions to read the input, build 
*  a list of the input, print the list, add to the list, and subtract the list.
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "list.h"
#include "bigint.h"
/* Function: *readNumber
*  Description: This function will read the string length to dynamically 
*  allocate an array to hold the string
*/
char *readNumber(FILE *infile) {
	int length;
	fscanf(infile, "%d", &length);
	char *numString = (char *) malloc(length * sizeof(char));
	getc(infile);
	fgets(numString, (length + 1), infile);
	return numString;
}

/* Function: buildList
*  Description: This function will convert the array to groups of three
*  digit integers and store them in the struct
*/
void buildList(list_t *bigint, char *numstring) {
	data_t *substrNum;
	int size = strlen(numstring);
	int leftOver = 0;
	const char *numstringCopy = numstring;
	char *substring = (char *)malloc(3 * sizeof(char));
	if((size % 3) != 0){
		leftOver = size % 3;
		strncpy(substring, numstringCopy, leftOver);
		substrNum = (data_t *)malloc(sizeof(data_t *));
		substrNum->number = atoi(substring);
		list_addToFront(bigint, (void *) substrNum);
		for(int i = leftOver; i < size; i += 3){
			char *substr2 = (char *)malloc(3 * sizeof(char));
			strncpy(substr2, numstringCopy + i, 3);
			substrNum = (data_t *)malloc(3 * sizeof(data_t *));
			substrNum->number = atoi(substr2);
			list_addToFront(bigint, (void *) substrNum);
		}
	}
}

/* Function: size
*  Description: This function will count the number of nodes and return
*  an integer value 
*/
int size(list_t *bigint) {
	int count = 0;
	iterator_t *iter;
	iter = newIterator(bigint);
	list_reset(iter);
	while(list_next(iter) != NULL){
		count++;
	} 
	return(count);	
}

/* Function: printReverse 
*  Description: This function will print the array in reverse order
*/
void printReverse(list_t *bigint){
	list_t *newBig = (list_t *)malloc(sizeof(list_t));
	newBig = bigint;
	iterator_t *iter = newIterator(newBig);
	list_reset(iter);
   	int num;
   	data_t *data;

   	if(!list_hasnext(iter))
      		return;

   	data = (data_t *) list_next(iter); 
   	num = data->number;
   	newBig->head = newBig->head->next;
   	printReverse(newBig);
   	printf("%03d", num);
}


/* Function: print
*  Description: This function will print the list in its current order
*/
void print(list_t *bigint) {
	list_t *newBig = (list_t *)malloc(sizeof(list_t *));
	newBig = bigint;
	iterator_t *iter;
	iter = newIterator(newBig);
	list_reset(iter);
	data_t *data;
	int num;
	if (!list_hasnext(iter))
		return;
	
	data = (data_t *) list_next(iter);
	num = data->number;
	newBig->head = newBig->head->next;
	printf("%03d", num);
	print(newBig);
}

/* Function: *add
*  Description: This function will create an empty list and use iterators to 
*  add the groups of ints together until it reaches the end node
*/
list_t *add(list_t *bigint1, list_t *bigint2) {
	int sum = 0;
	int carry = 0;
	list_t *bigintSum = newList();
	iterator_t *iter1;
	iterator_t *iter2;
	data_t *newData;
	data_t *data1;
	data_t *data2;
	iter1 = newIterator(bigint1);
	iter2 = newIterator(bigint2);
	while(list_hasnext(iter1) && list_hasnext(iter2)){
		newData = (data_t *)malloc(sizeof(data_t *));
		data1 = (data_t *) list_next(iter1);
		data2 = (data_t *) list_next(iter2);
		sum = carry + data1->number + data2->number;
		newData->number = sum;
		if(sum > 999){
			sum = sum - 1000;
			newData->number = sum;
			carry = 1;
		}
		else{
			carry = 0;
		}
		list_addToBack(bigintSum, (void *) newData);
	}
	if(carry != 0){
		newData = (data_t *)malloc(sizeof(data_t *));
		newData->number = carry;
		list_addToBack(bigintSum, (void *) newData);
	}
	return bigintSum;	
}

/* Function: *subtract
*  Description: This function will create an empty list and use iterators to 
*  subtract the groups of ints together until it reaches the end node 
*/
list_t *subtract(list_t *bigint1, list_t *bigint2) {
	int diff = 0;
	int carry = 0;
	list_t *bigintDiff = newList();
	iterator_t *iter1;
	iterator_t *iter2;
	data_t *newData;
	data_t *data1;
	data_t *data2;
	iter1 = newIterator(bigint1);
	iter2 = newIterator(bigint2);
	while(list_hasnext(iter1) && list_hasnext(iter2)){
		newData = (data_t *)malloc(sizeof(data_t *));
		data1 = (data_t *) list_next(iter1);
		data2 = (data_t *) list_next(iter2);
		if(data1->number > data2->number){
			diff = carry + data1->number - data2->number;
			newData->number = diff;
		}
		if(data1->number < data2->number){
			diff = carry + data2->number - data1->number;
			newData->number = diff;
		}
		if(diff < 0){
			diff = diff + 1000;
			newData->number = diff;
			carry = -1;
		}
		else{
			carry = 0;
		}
		list_addToBack(bigintDiff, (void *) newData);
	}
	if(carry != 0){
		newData = (data_t *)malloc(sizeof(data_t *));
		newData->number = carry;
		list_addToBack(bigintDiff, (void *) newData);
	}
	return bigintDiff;	
}
 
