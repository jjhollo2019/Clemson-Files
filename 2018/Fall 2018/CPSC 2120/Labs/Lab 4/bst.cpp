/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 04
 * 9/25/2018
 */

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <algorithm>
#include <assert.h>

 using namespace std;
 //declare node struct to build the tree of
 struct Node {
  int key;//holds the stored value
  int size;//holds the node size
  Node *left;//left child node
  Node *right;//right child node
  Node (int k) { key = k; size = 1; left = right = NULL; }
 };

 /* Function: size(Node *T)
  * Description: This function will recurse through the BST to determine the tree size
  * Input: 
  *  Node *T is the BST to determine the size of
  * Output: 
  *  int size is the return of T's size
  */
 int size(Node *T)
 {
  if (T == NULL) return 0;//if tree is empty return 0
  //otherwise return the sum of the recursed child nodes plus the root
  return size(T->left) + size(T->right) + 1;
 } 

 /* Function: insert(Node *T, int k)
  * Description: This function will insert a new node into the BST
  * Input: 
  *  Node *T is the BST to insert into
  *  int k is the value to be inserted into the BST
  * Output: 
  *  Node* T is the returned BST with the new node inserted
  */
 Node *insert(Node *T, int k)
 {
  //base case to add new node 
  if (T == NULL) return new Node(k);
  T->size++;//increment size after adding node
  //if the key is less than the root then go left
  if (k < T->key) T->left = insert(T->left, k); 
  //otherwise go right
  else T->right = insert(T->right, k);
  return T;//return the tree with the added node
 }

 /* Function: print_inorder(Node *T)
  * Description: This function will print the BST in order
  * Input: 
  *  Node *T is the BST to be printed
  * Output: prints BST elements to the terminal
  */
 void print_inorder(Node *T)
 {
  //base case will exit the function
  if (T == NULL) return;
  print_inorder(T->left);//recurse left
  cout << T->key << endl;//print the element
  print_inorder(T->right);//recurse right
 }

 /* Function: find(Node *T, int k)
  * Description: This function will find the entered element or return NULL
  * Input: 
  *  Node *T is the BST to find a value in
  *  int k is the value to find
  * Output: 
  *  Node *T is root holding the targeted value
  */
 Node *find(Node *T, int k)
 {
  //base case will return NULL if element is not found
  if (T == NULL) return NULL;
  //return the node if the key is found in it
  if (k == T->key) return T;
  //recurse left is k is less than key
  if (k < T->key) return find(T->left, k);
  //otherwise recurse right
  else return find(T->right, k);
 }

 /* Function: select(Node *T, int r)
  * Description: This function return a node a specific position in the BST
  * Input: 
  *  Node *T is the BST to search through
  *  int r is the desired position to return
  * Output: 
  *  Node *T is the value at position r
  */
 Node *select(Node *T, int r)
 {
  //verify call to select is valid
  assert(T != NULL && r >= 0 && r < T->size);
  int rank;//rank will serve as size holder for going right
  //determine if there is anything in the left side
  if (T->left == NULL) rank = 0;
  //otherwise set rank = the left size to determine the position of right nodes
  else rank = T->left->size;
  //return the node called for
  if (r == rank) return T;
  //determine if we need to go left 
  if (r < rank) return select(T->left, r);
  //otherwise go right
  else return select(T->right, (r - rank - 1));
 }

 /* Function: join(Node *L, Node *R)
  * Description: This function will join two BST's 
  * Input: 
  *  Node *L is a BST to be joined
  *  Node *R is a BST to be joined
  * Output: Node L or R (depends on the join)
  */
 Node *join(Node *L, Node *R)
 {
  if (L == NULL) return R;//base case for left
  if (R == NULL) return L;//base case for right
  //randomly determine going left or right
  if (rand() % (L->size + R->size) < L->size)
  {
   //join R to the right side of the L node
   L->right = join(L->right, R);
   L->size = size(L);//recalculate size
   return L;//return newly joined tree
  }
  else 
  {
   //otherwise go right
   R->left = join(L, R->left);
   R->size = size(R);//recalculate size 
   return R;//return newly joined tree
  }
 }

 /* Function: remove(Node *T, int k)
  * Description: This function will remove a node from the BST
  * Input: 
  *  Node *T is the BST to remove a node from
  *  int k is the value to be removed
  * Output: 
  *  Node *T is the BST without the called value
  */
 Node *remove(Node *T, int k)
 {
  //validate legal call to this function
  assert (find(T,k) != NULL);
  if (T->key == k)//base case 
  {
   //replace target node with a join of its sub nodes
   T = join(T->left, T->right); 
   return T;//return the new BST
  }
  //recurse right if k is larger than the root value
  if (k > T->key) T->right = remove(T->right, k);
  //otherwise recurse left
  else T->left = remove(T->left, k);
  T->size = size(T);//resize T
  return T;//return T
 }

 /* Function: split(Node *T, int k, Node **L, Node **R)
  * Description: This function will split a BST into two BST's
  * Input: Node *T, int k, Node **L, Node **R
  * Output: none
  */
 void split(Node *T, int k, Node **L, Node **R)
 {
  //base case if tree is empty
  if (T == NULL) { *L = NULL; *R = NULL; return;}
  if (T->key > k)//split left if root is greater than k
  { 
   //split the left side into a BST
   split(T->left, k, L, &T->left); 
   *R = T;//set what is left to R
  } 
  else 
  {   
   //split right side into BST
   split(T->right, k, &T->right, R);
   *L = T;//set what is left to L
  }
  T->size = size(T);//recalculate T's size
 }

 /* Function: insert_keep_balanced(Node *T, int k)
  * Description: This function will insert into the tree 
  * while maintaining balance
  * Input: Node *T, int k
  * Output: Node *T
  */
 Node *insert_keep_balanced(Node *T, int k)
 {
  //base case to insert the new node
  if (T == NULL) return new Node(k);
  //randomly determine a mod of T's size
  int rando = rand() % (T->size + 1);
  if (rando < 1)//randomly split T into the subnodes of the new node
  {
   Node *add = new Node(k);//create new node
   split(T, k, &add->left, &add->right);//split T 
   add->size = size(add);//determine size
   return add;//return new BST
  }
  else//determine recurse path
  {
   //if k is greater than the root recurse right
   if (k > T->key) T->right = insert_keep_balanced(T->right, k);
   //otherwise recurse left
   else T->left = insert_keep_balanced(T->left, k);
  }
  //increment size of T
  T->size++;
  //return T
  return T;
 }

 int main(void)
 { 
  // Testing insert and print_inorder
  int A[10];
  
  // put 0..9 into A[0..9] in random order
  for (int i=0; i<10; i++) A[i] = i;
  for (int i=9; i>0; i--) swap(A[i], A[rand()%i]);
  
  // insert contents of A into a BST
  Node *T = NULL;
  for (int i=0; i<10; i++) T = insert(T, A[i]);
  
  // print contents of BST (should be 0..9 in sorted order)
  cout << "\nTesting insert and print_inorder (should be 0...9):\n";
  print_inorder(T);
  cout << "Size (should be 10): " << (T ? T-> size : 0) << "\n";

  // test Select
  cout << "\nTesting select (should be no output):\n";
  for (int i=0; i<10; i++) {
    Node *result = select(T, i);
    if (!result || result->key != i) cout << "Select test failed for select(" << i << ")!\n";
  }
  
  // test find: Elements 0..9 should be found; 10 should not be found
  cout << "\nTesting find (should be 1-9 found, 10 not found) \n";
  for (int i=0; i<11; i++)
    if (find(T,i)) cout << i << " found\n";
    else cout << i << " not found\n";
 
  // test split
  cout << "\nTesting split\n";
  Node *L, *R;
  split(T, 2, &L, &R);
  cout << "Contents of left tree after split (should be 0..2):\n";
  print_inorder(L);
  cout << "\nSize left subtree (should be 3): " << L->size << "\n";
  cout << "Contents of right tree after split (should be 3..9):\n";
  print_inorder(R);
  cout << "\nSize right subtree (should be 7): " << R->size << "\n";

  // test join
  T = join(L, R);
  cout << "\nTesting join\n";
  cout << "Contents of re-joined tree (should be 0..9)\n";
  print_inorder(T);
  cout << "\nSize (should be 10): " << T->size<< "\n";

  // test remove
  cout << "\nTesting remove\n";
  for (int i=0; i<10; i++) A[i] = i;
  for (int i=9; i>0; i--) swap(A[i], A[rand()%i]);
  for (int i=0; i<10; i++) {
    T = remove(T, A[i]);
    cout << "Contents of tree after removing " << A[i] << ":\n";
    print_inorder(T);
    if (i < 2) {
      cout << "\nSize of tree after this removal (should be 1 less than before): " << (T ? T-> size : 0);
    }    
    cout << "\n";
  }
  int size = T ? T->size : 0;
  cout << "Size (should be 0): " << size << "\n";

  // test insert_keep_balanced basic functionality
  // insert contents of A into a BST
  for (int i=0; i<10; i++) T = insert_keep_balanced(T, A[i]);

  // print contents of BST (should be 0123456789)
  cout << "\n" << "Testing insert_random (should be 0..9)\n";
  print_inorder(T);
  cout << "\n" << "Size (should be 10): " << T->size << "\n";

  // test insert_keep_balanced speed
  cout << "Inserting 10 million elements in order; this should be very fast if insert_balance is working...\n";
  for (int i=0; i<10000000; i++) T = insert_keep_balanced(T, i); // 10 million
  cout << "Done\n";
  cout << "Size (should be 10000010): " << T->size << "\n\n";

  return 0;
 }
