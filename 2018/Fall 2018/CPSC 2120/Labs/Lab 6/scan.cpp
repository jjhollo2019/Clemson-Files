/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 06
 * 10/16/2018
 */

 #include <algorithm>
 #include <iostream>

 using namespace std;
 //declare struct for holding input
 struct Node {
  int location;
  int height;
  int origin;
  Node(){location = 0; height = 0; origin = 0;}
  Node(int x, int y, int z){location = x; height = y; origin = z;}
 };
 
 //declare struct for the binary search tree
 struct Tree {
  int location;
  int origin;
  int size;
  Tree *left;
  Tree *right;
  Tree(){location = 0; size = 0; left = right = NULL;}
  Tree(int x){location = x; size = 1; left = right = NULL;}
 };
 
 //declare struct for the answer array
 struct sol {
  int left;
  int right;
  sol(){left = 0; right = 0;}
 };

 //recycled size function 
 void fix_size(Tree *T)
 {
  T->size = 1;
  if (T->left) T->size += T->left->size;
  if (T->right) T->size += T->right->size;
 }
 
 //recycled split
 void split(Tree *T, int r, Tree **L, Tree **R)
 {
  if (T == NULL)
  {
   *L = NULL;
   *R = NULL; 
   return;
  }
  int rank = (T->left) ? T->left->size : 0; 
  if (r <= rank)
  {
   split(T->left, r, L, &T->left);
   *R = T;
  }
  else
  {
   split(T->right, r, &T->right, R);
   *L = T;
  }
  fix_size(T);
 }
 
 //recycled insert function
 Tree *insert_keep_balanced(Tree *T, int k)
 {
  //base case to insert the new node
  if (T == NULL) return new Tree(k);
  //randomly determine a mod of T's size
  int rando = rand() % (T->size + 1);
  if (rando < 1)//randomly split T into the subnodes of the new node
  {
   Tree *add = new Tree(k);//create new node
   split(T, k, &add->left, &add->right);//split T 
   fix_size(add);//determine size
   return add;//return new BST
  }
  else//determine recurse path
  {
   //if k is greater than the root recurse right
   if (k > T->location) T->right = insert_keep_balanced(T->right, k);
   //otherwise recurse left
   else T->left = insert_keep_balanced(T->left, k);
  }
  //increment size of T
  T->size++;
  //return T
  return T;
 }
 
 Tree *predfind(Tree *root, int x)
 {
  if (root == NULL) return NULL;//base case return null
  if (root->location == x) return root;//return root if x is found
  if (root->location > x) return predfind(root->left, x);//recurse left if > x
  if (root->location < x && root->right){//if < x and the is a right sub tree
   Tree *temp = predfind(root->right, x);//recurse right to check for a lower x
   if (temp && (temp->location > root->location))//compare 
    return temp;//return if temp > root
   else return root;
  }
  else return root;
 }

 Tree *succfind(Tree *root, int x)
 {
  if (root == NULL) return NULL;//base case return null
  if (root->location == x) return root;//return root if found
  //recurse left if root < x
  else if (root->location < x) return succfind(root->left, x);
  //if the root > x and there is a left sub tree
  else if (root->location > x && root->left){
   Tree *temp = succfind(root->left, x);//recurse left
   if (temp && (temp->location < root->location))//compare the root
    return temp;//return if < the root
   else return root;
  }
  else return root;
 }  

 //overloaded operator for the sort function
 bool operator<(Node &A, Node &B)
 {
  return A.height > B.height;
 }

 int main()
 {
  int loc;
  int hgt;
  int size;
  cin >> size;//read array size
  int a = 0;
  //declare input array
  Node *Arr = new Node[size];
  while (cin >> loc >> hgt)//read input values
  {
   Arr[a] = Node(loc, hgt, a);//store location, height, and original index
   a++;
  }
  sort(Arr, Arr + size);//sort the array
  for (int i = 0; i < size; i++)
  {
   cout << Arr[i].height << endl;//print the heights
  }
  cout << endl;
  sol *answers = new sol[size];//declare output array
  Tree *Distance = NULL;//declare BST
   
  for (int i = 0; i < size; i++){
   //get the predecessor node
   Tree *temp = predfind(Distance, Arr[i].location);
   //if null store -1
   if (temp == NULL) answers[Arr[i].origin].left = -1;
   //otherwise store its location minus the predecessor location
   else answers[Arr[i].origin].left = Arr[i].location - temp->location;
   //get the succesor
   temp = succfind(Distance, Arr[i].location);
   //if null store -1
   if (temp == NULL) answers[Arr[i].origin].right = -1;
   //otherwise store the sucessor location minus its location
   else answers[Arr[i].origin].right = temp->location - Arr[i].location;
   Distance = insert_keep_balanced(Distance, Arr[i].location);
  }
  //print output
  for (int i = 0; i < size; i++){
   cout << answers[i].left << " " << answers[i].right << endl;
  }
  return 0;
 }
