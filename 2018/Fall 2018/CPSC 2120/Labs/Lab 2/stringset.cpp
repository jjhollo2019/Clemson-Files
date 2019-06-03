/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 02
 * 9/11/2018
 * Description: This file contains the implmentation for using a hash table 
 * to store user inputted strings
 */
#include <iostream>
#include <string.h>
#include <assert.h>
#include "stringset.h"

using namespace std;

/* Function: hash(string s, int table_size)
 * Description: This function will create an array index 
 * for storing user strings into the hash table
 * Input: string s, int table_size
 * Output: int h
 */
int hash(string s, int table_size)
{
  unsigned int i, h = 0;//declare the ints unsigned to maximize value space
  for (i=0; i<s.length(); i++)
    h = (h * 2917 + (unsigned int)s[i]) % table_size;//calculate the hash
  return h;//return the hash index
}

/* Function: **allocate_table(int size)
 * Description: This function will malloc space for a new table
 * Input: int size
 * Output: Node **table 
 */
Node **allocate_table(int size)
{
  Node **table = new Node *[size];//declare and malloc space for new table
  for (int i=0; i<size; i++)//iterate through array members
    table[i] = NULL;//set each member to NULL
  return table;//return empty table
}

/* Function: Stringset()
 * Description: This function is the default constructor for the class
 * Input: none
 * Output: creates instance object of the class
 */
Stringset::Stringset()
{
  size = 4; // initial size of table    
  table = allocate_table(size);//allocate memory for the table
  num_elems = 0; //set the number of elements
}

/* Function: ~Stringset
 * Description: This is the desctructor for the class
 * Input: none
 * Output: none
 */
Stringset::~Stringset()
{
  for (int i=0; i<size; i++) {//iterates through the array
    while (table[i] != NULL) {//while iterates through the linked list
      Node *temp = table[i];//set temp to the table element 
      table[i] = table[i]->next;//move table to the next node
      delete temp;//delete the temporary node
    }
  }
  delete[] table;//delete the array
}

/* Function: find(string key)
 * Description: This function will determine the hash with the input key
 * and iterate through the nodes at that specific element 
 * Input: string key
 * Output: boolean true/false 
 */
bool Stringset::find(string key)
{
  int h = hash(key, size);//claculate the hash of the key
  Node *n = table[h];//set n to the the target table element
  while (n != NULL) {//iterate through the element nodes
    if (n->key == key) return true;//break if key is found
    n = n->next;//continue if key is not found
  }
  return false;//return false if key is not present
}

/* Function: insert(string key)
 * Description: This function will place a new user input into the
 * corresponding hash element
 * Input: string key
 * Output: none 
 */
void Stringset::insert(string key)
{
  assert (!find(key));//ensure key is not present in table
  num_elems++;//increase the number of elements present 
  if (num_elems == size) {//establishes new array if needed
   Node **newTable = allocate_table((size * 2));//delcare new table
   for(int a = 0; a < size; a++){//iterate through previous array elements
    while(table[a] != NULL){//iterate through the element nodes
    int h = hash(table[a]->key, (size * 2));//deteremine new hash index
    Node *n = table[a];//set original element to a node pointer
    table[a] = table[a]->next;//increment table pointer
    n->next = newTable[h];//increment n pointer
    newTable[h] = n;//set new table node to previous table node
    }
   }
  delete table;//delete old table memory
  table = newTable;//set table to new array
  }
  int h = hash(key, size);//determine new item hash index
  table[h] = new Node(key, table[h]);//insert new node
}

/* Function: remove(string set)
 * Description: This function will remove a user inputted string
 * Input: string key
 * Output: none 
 */
void Stringset::remove(string key)
{
  assert (find(key));//ensure target key is present in table
  num_elems--;//decrement the number of items in the list
  int h = hash(key, size);//determine probable hash index
  Node *p = table[h];//create a previous node pointer
  Node *n = table[h]->next;//create a next node pointer
  while(n != NULL){//iterate through the list
   if(p->key.compare(key) == 0){//if this is the only item at this index
    delete p;//delete node
    table[h] = NULL;//set the element index to NULL
   }
   if(n->key.compare(key) == 0){//determine if keys match
    p->next = n->next;//set previous pointers next to the node after n
    delete n;//delete target node
   }
   p = p->next;//iterate p
   n = n->next;//iterate n
  }  
}

/* Function: print()
 * Description: This function will print all list items
 * Input: none
 * Output: none
 */
void Stringset::print(void)
{
  for(int a = 0; a < size; a++){//iterate through array elements 
   Node *n = table[a];//set n to the table element 
   while(n != NULL){//use while loop to iterate through the nodes
    cout << n->key << endl;//print the key
    n = n->next;//iterate through the nodes
   }  
  } 
}
