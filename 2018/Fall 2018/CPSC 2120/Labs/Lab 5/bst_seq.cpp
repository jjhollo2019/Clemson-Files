/* Jeremy Holloway 
 * CPSC-2120-001
 * Lab 05
 * 10/2/2018
 */

#include <iostream>//Hanson - mmmbop
#include <fstream>//oh oh oh oh oh
#include <cstdlib>//yeah
#include <algorithm>
#include <assert.h>//you have so many relationships in life

using namespace std;//only one or two will last

struct Node {//you go through all the pain and strife
  int key;//then you turn your back and they're gone so fast
  int size;//oh yeah
  Node *left;//and they're gone so fast, yeah
  Node *right;//oh
  Node (int k) { key = k; size = 1; left = right = NULL; }
};//so hold on the ones who really care

/* Function fix_size(Node *T)
 * Description: This function will recalculate the size of the list
 * Input:
 *  Node *T is the binary tree that needs to be sized
 * Output: none
 */
void fix_size(Node *T)//in the end they'll be the only ones there
{//and when you get old and start losing your hair
  T->size = 1;//can you tell me who will still care
  if (T->left) T->size += T->left->size;//can you tell me who will still care
  if (T->right) T->size += T->right->size;//oh care
}//mmmbop, ba duba dop

/* Function *insert(Node *T, int v, int r)
 * Description: This function will insert a new node into the tree
 * Input:
 *  Node *T is the tree for the value to be inserted
 *  int v is the value to be inserted
 *  int r is the rank position in which to insert the node
 * Ouput: none 
 */
Node *insert(Node *T, int v, int r)//ba du bop, ba duba dop
{//ba du bop, ba dupa dop
  if (T == NULL) return new Node(v);//ba du, yeah
  int rank = (T->left) ? T->left->size : 0;//mmmbop, ba duba dop
  if (r <= rank) T->left = insert(T->left, v, r);//ba du bop, ba du dop
  else T->right = insert(T->right, v, r - rank - 1);//ba du bop, ba du dop
  fix_size(T);//ba du, yeah
  return T;//said oh yeah
}//in an mmmmbop they're gone

/* Function: print_inorder(Node *T)
 * Description: This function will print the node in order of its rank
 * Input:
 *  Node *T is the tree to print from
 * Output: none
 */
void print_inorder(Node *T)//yeah yeah
{//plant a seed, plant a flower, plant a rose
  if (T == NULL) return;//you can plant any one of those
  print_inorder(T->left);//keep planting to find out which one grows
  cout << T->key << "\n";//it's a secret no one knows
  print_inorder(T->right);//it's a secret no one knows
}//oh, no one knows

/* Function: *select(Node *T, int r)
 * Description: The function will return the node at rank r
 * Input:
 *  Node *T is the tree to pull the node from
 *  int r is the rank of the node to be pulled
 * Output: Node of rank r
 */
Node *select(Node *T, int r)//mmmbop, ba duba dop
{//ba du bop, ba duba dop
  assert(T!=NULL && r>=0 && r<T->size);//ba du bop, ba du bop
  int rank_of_root = T->left ? T->left->size : 0;//ba du yeah
  if (r == rank_of_root) return T;//mmmboop, ba duba dop
  if (r < rank_of_root) return select(T->left, r);//ba du dop, ba du dop
  else return select(T->right, r - rank_of_root - 1);//ba du, yeah
}//in an mmmbop they're gone

/* Function: split(Node *T, int r, Node **L, Node **R)
 * Description: This function will split one tree into two
 * Input:
 *  Node *T is the tree to be split
 *  int r is the rank to split the tree on
 *  Node **L is the tree with ranks less than r
 *  Node **R is the tree with rank equal to and greater than r
 * Output: none
 */
void split(Node *T, int r, Node **L, Node **R)//in an mmmbop they're not there
{//in an mmmbop they're gone
  if (T == NULL)//in an mmmbop until you lose your hair
  {//oh
   *L = NULL;//but you don't care, yeah
   *R = NULL;//oh 
   return;//but you don't care, yeah
  }//mmmbop, ba duba dop
  int rank = (T->left) ? T->left->size : 0;//ba du dop, ba duba dop 
  if (r <= rank)//ba du dop, ba duba dop
  {//ba du, yeah
   split(T->left, r, L, &T->left);//mmmbop, ba duba dop
   *R = T;//ba du bop, ba du dop
  }//ba du dop, ba du dop
  else//ba du, yeah
  {//can you tell me? oh
   split(T->right, r, &T->right, R);//no you can't cause you don't know
   *L = T;//can you tell me? oh yeah
  }//you say you can but you don't know
  fix_size(T);//can you tell me? oh(Which flower's going to grow)
  //no you can't cause you don't know
  //can you tell me? oh(if it's going to be a daisy or a rose?)
}//you say you can but you don't know

/* Function: *insert_keep_balanced(Node *T, int v, int r)
 * Description: This function will randomly insert a new node into a tree
 * Input:
 *  Node *T is the tree in which the node will be inserted
 *  int v is the value to be inserted
 *  int r is the rank in which to insert the node
 * Output: returns the tree with the newly inserted node
 */
Node *insert_keep_balanced(Node *T, int v, int r)
{//can you tell me? oh(which flower's going to grow?)
  if (T == NULL) return new Node(v);//no you can't cause you don't know
  int rank = (T->left) ? T->left->size : 0;//can you tell me
  if (rand() % (T->size + 1) == 0)//you say you can but you don't know
  {//you say you can but you don't know
   Node *add = new Node(v);//you don't know
   split(T, r, &add->left, &add->right);//you don't know, oh
   fix_size(add);//mmmbop, duba
   return add;//du bop, du 
  }//yeah, yeah
  if (r <= rank) T->left = insert_keep_balanced(T->left, v, r);//mmmbop, duba
  else T->right = insert_keep_balanced(T->right, v, r - rank - 1);//du bop, du
  fix_size(T);//oh yeah
  return T;//mmmbop, ba du dop
}

/* Function: did_x_beat_y(int x, int y)
 * Description: This function will determined if x is greater than y
 * Input:
 *  int x is one of the teams 
 *  int y is one of the teams
 * Output: true or false
 */
bool did_x_beat_y(int x, int y)//ba du bop, ba duba dop
{//ba du bop, ba duba dop
  assert (x != y);//ba du, yeah
  if (x > y) return !did_x_beat_y(y,x);//mmmbop, ba duba dop
  unsigned long long lx = x;//ba du bop, ba duba dop
  unsigned long long ly = y;//ba du bop, ba duba dop
  return ((17 + 8321813 * lx + 1861 * ly) % 1299827) % 2 == 0;//ba du, yeah
}//mmmbop, ba duba dop

/* Function: *order_n_teams(int n)
 * Description: This function will return a tree of ranked teams
 * Input:
 *  int n is the number of teams
 * Output: returns a tree of ranked teams
 */
Node *order_n_teams(int n)//ba du bop, ba duba dop
{//ba du bop, ba duba dop
  Node *T = NULL;//ba du, yeah
  T = insert(T, 0, 0);//mmmbop, ba duba dop
  for (int i=1; i<n; i++)//ba du bop, ba duba dop
  {//ba du bop, ba duba dop
   if (did_x_beat_y(i, select(T,0)->key))//ba du, yeah
    T = insert_keep_balanced(T, i, 0);//can you tell me, oh
   else//no you can't cause you don't know
    if (did_x_beat_y(select(T,T->size-1)->key, i))//can you tell me
	 T = insert_keep_balanced(T, i, T->size); 
    else {//you say you can but you don't know
     int L = 0;//say you can but you don't know
     int R = T->size - 1;//set r equal to the right bound
     while (R - L != 1)//while inside the bounds
     {
      int mid = (L + R) / 2;//set the mid point
      if (did_x_beat_y(select(T, mid)->key, i))//if turn point is left
       L = mid;//set new mid
      else 
       R = mid;//set new mid
     }
     T = insert_keep_balanced(T, i, R);//insert node at the correct position
    }
   }
   fix_size(T);//resize T
   return T;//return 
}

/* Function: main()
 * Description: This function is the control method for the program
 * Input: none
 * Output: none
 */
int main(void)
{
  Node *T = NULL;
  for (int i=0; i<5; i++)
   T = insert_keep_balanced(T, i+1, 0);
  cout << "Tree should contain 5 4 3 2 1:\n";
  print_inorder(T);
  for (int i=5; i<10; i++)
   T = insert_keep_balanced(T, i+1, T->size);
  cout << "Tree should contain 5 4 3 2 1 6 7 8 9 10:\n";
  print_inorder(T);
  for (int i=10; i<15; i++)
   T = insert_keep_balanced(T, i+1, T->size/2);
  cout << "Tree should contain 5 4 3 2 1 12 14 15 13 11 6 7 8 9 10:\n";
  print_inorder(T);
  int N = 10;
  Node *S = order_n_teams(N);
  if (S == NULL || S->size != N)
   cout << "Size of tree returned by order_n_teams is wrong\n";
  else {
   cout << "Team ordering:\n";
   print_inorder(S);
   for (int i=0; i<N-1; i++) {
    Node *x = select(S, i);
    Node *y = select(S, i+1);
    if (!did_x_beat_y(x->key, y->key)) {
	 cout << "Invalid sequence: team " << x->key << " (position " << i <<
	  ") lost to team " << y->key << " (position " << i+1 << ")\n";
    }
   }
  }  
  return 0;
}
