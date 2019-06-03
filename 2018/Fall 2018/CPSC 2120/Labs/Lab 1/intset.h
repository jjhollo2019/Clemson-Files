/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 01
 * 8/31/2018
 * Description: This file is the header file for the intset class
 */
#ifndef INTSET_H /* Prevent multiple inclusion... */
#define INTSET_H

class Intset {

 private: 
  struct Node{
   int payload;
   Node *next;
  };
  Node *head;

 public:
  Intset();
  ~Intset();
  bool find(int key);
  void insert(int key);
  void remove(int key);
  void print(void);
};

#endif
