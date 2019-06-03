/* Author: Jeremy Holloway/jjhollo
*  CPSC-1071-001
*  Lab 7
*  Due Date: 3/3/2018
*  Description: List of functions needed for linked list
*/

#include "list.h"

/* Function: newList
*  Description: create a new list object 
*/
list_t *newList() {
	list_t *temPtr;
	temPtr = (list_t *) malloc( sizeof (list_t));
	temPtr->head = NULL;
    return ((list_t *) temPtr);
}

/* Function: list_add 
*  Description: add an object to the linked list 
*/
void list_add(list_t *list, void *objPtr) {
	node_t *newNode;
	newNode = (node_t *) malloc( sizeof (node_t));
	newNode->dataPtr = objPtr;
	newNode->next = list->head;
	list->head = newNode;  
}

/* Function: newIterator 
*  Description: create a new iterator and associate it with the specified list
*/
iterator_t *newIterator(list_t *list) {
   	iterator_t *iter1;
	iter1 = (iterator_t *) malloc( sizeof (iterator_t));
	iter1->list = list;
	iter1->position = list->head;
   return ((iterator_t *)  iter1);
}

/* Function: list_next 
*  Description: return the object pointed to by the node pointed to by position
*  and then advance position to the next node in the list.
*/
void *list_next(iterator_t *iter) {
	
	if (iter->position->dataPtr == NULL)
		return NULL;
	else{
		node_t *tempNode = iter->position;
		iter->position = tempNode->next; 
		return (tempNode->dataPtr);
	}		
}

/* Function: list_reset 
*  Description: reset position to point to first node of list 
*/
void list_reset(iterator_t *iter) {
	if (iter->list == NULL)
		iter->position = NULL;
	else
		iter->position = iter->list->head;
}

/* Function: list_hasnext 
*  Description: test for end of list 
*/
int list_hasnext(iterator_t *iter) {
	int a;
	if (iter->position == NULL)
		a = 0;
	else
		a = 1;
	return a;
}

