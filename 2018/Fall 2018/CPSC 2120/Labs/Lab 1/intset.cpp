/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 01
 * 8/31/2018
 * Description: This file contains the implementation methods for the Intset class.
 */
#include <iostream>
#include "intset.h"

using namespace std;

/* Function: Intset()
 * Description: This function constructs the head node of the list by calling for a 
 *		new node struct and setting the next node to NULL. 
 * Input: none
 * Output: none
 */
Intset::Intset(){
	head = new Node;	//head node is allocated memory
	head->next = NULL;	//set the next node to NULL
}

/* Function: ~Intset()
 * Description: This function will use a pointer to move through the list and place the 
 *		previous node into a temporary node for deletion.
 * Input: none
 * Output: none
 */
Intset::~Intset(){
	Node *n = head;		//set n to the address of the head node 
	Node *p;		//declare pointer for deleting nodes
	while(n != NULL){	//while loop will iterate through the nodes
		p = n;		//set p the the address of n
		n = n->next;	//set n to the address of the next node
		delete p;	//delete the temporary node
	}
}

/* Function: find(int key)
 * Description: This function will use a pointer to iterate through a while loop and check if the  
 *              payload matches with the provided key.
 * Input: int key
 * Output: true, false
 */
bool Intset::find(int key){
	Node *n = head->next;		//set n to the memory address of the first list item
	while (n != NULL){		//while loop will iterate through the list
		if (n->payload == key){	//if statement will activate if finds a matching node
			return true;	//return to break out of the function
		}
		n = n->next;		//iterate if payload doesn't match
	}
  	return false;			//function defaults to false and breaks out of the function
}

/* Function: insert(int key)
 * Description: This function will use a previous pointer and a next pointer to keep the target insertion point 
 * 		accessible while mallocing memory for the new node.  The if statement will activate if it is 
 * 		the first item of the list, otherwise it will iterate until it reaches the insertion point.
 * Input: int key
 * Output: none 
 */
void Intset::insert(int key){
	Node *n = head->next;			//set n to the memory address for the first list item
	Node *p = head;				//set p to the head node
	Node *add = new Node;			//allocate memory for the new node to be inserted
	if (n == NULL){				//if statement activates if this is the first item
		add->payload = key;		//assign key to the payload of the new node
		add->next = head->next;		//set add next address to the address head is pointing to
		head->next = add;		//set the next address of the head node to the inserted node
		return;				//break out of the function when complete
	}
	while(n != NULL && n->payload < key){	//while loop finds correct position for the key
		p = n;				//iterates while maintaining the previous node
		n = n->next;			//iterates to the next node
	}
	p->next = add;				//set the address of the previous node to address of add
	add->payload = key;			//set the payload of add to the key
	add->next = n;				//set the address next of add to the next node
}

/* Function: remove(int key)
 * Description: This function will use two pointers to iterate through the list until it finds the value defined
 * 		by key.  The if statement will set the previous node to node after the target node and delete
 * 		the target value, otherwise it will iterate through the list.
 * Input: int key
 * Output: none 
 */
void Intset::remove(int key){
	Node *n = head->next;			//set n to the address of the first item
	Node *p = head;				//set p to the address of the head node
	while(n != NULL){			//while loop iterates through the list
		if(n->payload == key){		//if statement catches when the payload equals the key
			p->next = n->next;	//set the previous node address to the next item address
			delete n;		//delete the target node
		}
		else{
			p = p->next;		//iterate the previous node
			n = n->next;		//iterate the next node
		}
	}
}	

/* Function: print(void)
 * Description: This function will use pointer to iterate through the list and print the payload of each node.
 * Input: void
 * Output: none
 */
void Intset::print(void){
	Node *n = head->next;			//allocates memory for a new n
	while(n != NULL){			//while loop iterates through the list
		cout << n->payload << endl;	//print payload
		n = n->next;			//iterate to next list item
	}
}
