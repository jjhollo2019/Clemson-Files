/* Jeremy Holloway
 * CPSC-2120-001
 * Homework 4
 * 11/20/2018
 */

 #include <cfloat>
 #include <queue>
 #include <map>
 #include <random>
 #include <iostream>
 #include <cstdlib>
 #include <fstream>
 #include <cmath>
 #include <random>
 #include <vector>
 #include <algorithm>
 #include <limits>

 using namespace std;

 struct Node {//struct for holding data in a k-d tree
  double key;//key in which to store on
  double quality;//wine quality
  double *data;//wine characteristics data
  int size;//node size
  Node *left;//left pointer
  Node *right;//right pointer
  //node constructor
  Node(double k,double q,double *d){key=k;quality=q;data=d;size=1;left=right=NULL;}
  Node(){key=0;quality=0;data=NULL;size=0;left=right=NULL;}
 };

 struct Point {//struct for holding input data
  double quality;//wine quality
  double *data;//wine characteristics array
 };

 priority_queue<pair<double,Node> > pq;//priority queue to hold the nearest neighbors
 int N, D, K;//N = num elements, D = num dimensions, K = num of neighbors 
 Point *P;//input data array
 vector<Node> NN;//vector for holding nearest neighbors
 double total_error = 0.0;//total error of the solution

 /* Function: make_Node(Node *T)
  * Description: This function will make a node from the current node in the tree
  * Input:
  *  *T = the current node from which to make a new node
  * Output: the node from the tree is created as a single node
  */
 Node make_Node(Node *T){
  Node temp;//declare temp node
  temp.key = T->key;//copy key
  temp.quality = T->quality;//copy quality
  temp.data = new double[D];//allocate memory for data array
  for(int i = 0; i < D; i++)//loop[ through data array
   temp.data[i] = T->data[i];//copy data members
  return temp;//return temp node
 }
 
 /* Function: same_Node(Node *T, Node r)
  * Description: This function will compare two Nodes to determine if they are the same
  * Input:
  *  *T = r = the current node to compare
  * Ouput: returns true if the nodes are the same, otherwise false
  */
 bool same_node(Node *T, Node r){
  for(int i = 0; i < D; i++){//loop through the data memebers
   if(T->data[i] != r.data[i])//compare each member
    return false;//return false if any are the same
  }
  return true;//otherwise return true
 }  

 /* Function: size(Node *T)
  * Description: This function will find the size of the tree
  * Input:
  *  *T = the tree in which to find the size
  * Output: returns the size of the tree
  */
 int size(Node *T){
  if (T == NULL) return 0;
  return size(T->left) + size(T->right) + 1;
 } 

 /* Function: operator<(pair<double,Node>A, pair<double,Node>B)
  * Description: This function will tell the queue how to sort pairs of this type
  * Input:
  *  A = B = pairs to compare
  * Output: returns true of false if A is greater or less than B
  */
 bool operator<(pair<double,Node>A, pair<double,Node>B){
  return A.first < B.first;
 }

 /* Function: insert(Node *T, double k, double q, double *d, int dim)
  * Description: This function will insert an element into the k-d tree 
  * Input:
  *  *T = the tree for the elements to be stored in
  *  k = the key inwhich to store the element
  *  q = the quality of the wine
  *  *d = the data array of the wine
  *  dim = the dimension in which to store the wine
  * Output: returns tree with the node stored in it
  */
 Node *insert(Node *T, double k, double q, double *d, int dim){
  if(T == NULL) return new Node(k,q,d);//base case to add new node
  if(dim == D) dim = 0;//reset dimension if needed
  if(d[dim] > T->data[dim]) T->right = insert(T->right, k, q, d, dim+1);//recurse left
  else T->left = insert(T->left, k, q, d, dim+1);//else recurse right
  T->size++;//increment size
  return T;//return T
 } 

 /* Function: calc_dist(double *d1, double *d2)
  * Description: This function will return the euclidian distance from two points
  * Input:
  *  d1 = the data array of the first point
  *  d2 = the data array of the second point
  * Output: sqrt(|(xsubi - xsubj)|^2)
  */
 double calc_dist(double *d1, double *d2){
  double sum = 0.0;//intialize return
  for(int i = 0; i < D; i++)//loop through the data array
   sum += pow(abs(d1[i] - d2[i]), 2);//add the euclidian distances
  return sqrt(sum);//return the square root of the sum
 } 

 /* Function: NN_search(Node *T, Node root)
  * Description: This function will search for the nearest neighbors of some root
  * Input:
  *  *T = the tree in which to search
  *  root = the node whose neighbors are being searched for
  * Output: none
  */
 void NN_search(Node *T, Node root){
  if(T == NULL) return;//base case return if T is NULL
  NN_search(T->left, root);//search left
  if(!same_node(T, root)){//if the current node isn't the root
   pq.push(make_pair(calc_dist(T->data, root.data),make_Node(T)));//push it on the queue
   if((signed)pq.size() > K) pq.pop();//pop if larger than K
  }
  NN_search(T->right, root);
 }

 /* Function: find_error(double q, double guess)
  * Description: This function will return the error rate for a guess
  * Input:
  *  q = the wine quality of the guess
  *  guess = the guessed value of the wine
  * Output: ((quality - guess)^2)/number of elements
  */
 double find_error(double q, double guess){
  double err = pow((q - guess), 2);//err = (quality - guess)^2
  return err/N;//err = err/number of elements
 }

 /* Function: best_guess(Node x)
  * Description: This function will make a guess at the wine quality and compare it to the actual value
  * Input:
  *  x is the node being compared to
  * Output: none
  */
 void best_guess(Node x){
  double sum = 0.0;//intialize sum
  double weight = 0.0;//initialize weight
  double total_weights = 0.0;//intialize total weights
  double alpha = 0.1;//set alpha to 0.1
  double d = 0.0;//intialize distance
  for(int i = 0; i < K; i++){//loop through nearest neighbors
   d = calc_dist(NN[i].data, x.data);//get the distance to the root
   weight = exp(-alpha*d);//set the weight
   sum += weight;//add to sum
   total_weights += weight * NN[i].quality;//multiply weights by the neighbor quality
  }
  NN.clear();//clear the vector of nearest neighbors
  total_weights /= sum;//divide total weights by the sum
  total_error += find_error(x.quality, total_weights);//find the error rate
 }

 int main(int argc, char *argv[])
 {
  if (argc != 3) {//must be 3 inputs
    cout << "Usage: wine <data file> <k>\n";
    return 0;
  }
  /* Read input data */
  ifstream fin(argv[1]);//grab input file
  K = atoi(argv[2]);//get number of dimensions
  fin >> N >> D;//get total number of elements and max dimensions
  P = new Point[N];//create array of data points
  for (int i=0; i<N; i++) {//loop until all values are stored
    fin >> P[i].quality;
    P[i].data = new double[D];
    for (int j=0; j<D; j++)
      fin >> P[i].data[j];
  }
  fin.close();//close file
  /* Normalize data in each coordinate */
  for (int j=0; j<D; j++) {
    double mean = 0.0;
    for (int i=0; i<N; i++) mean += P[i].data[j];
    mean /= N;
    for (int i=0; i<N; i++) P[i].data[j] -= mean;
    double var = 0.0;
    for (int i=0; i<N; i++) var += pow(P[i].data[j],2);
    double stddev = sqrt(var/N);
    for (int i=0; i<N; i++) P[i].data[j] /= stddev;
  }
  // Now in each coordinate, we have a mean of zero and variance/standard deviation
  // of one, so the data set is "centered" at the origin and has the same relative
  // scale along each axis (so no attribute is "more important" than any other
  // attribute when computing distances).
  random_shuffle(&P[0], &P[N-1]);
  Node *W = NULL;//initialize the tree
  vector<Node> nbrs;//store all added nodes
  int j = 0;//set dimension iterator to 0
  for(int i = 0; i < N; i++){
   W = insert(W, P[i].data[j], P[i].quality, P[i].data, 0);//insert into tree
   nbrs.push_back(Node(P[i].data[j], P[i].quality, P[i].data));//push into vector as well
   j++;//increment key
   if(j == D) j = 0;//reset if j == 11
  }
  for(int i = 0; i < N; i++){//loop through the nodes to find nearest neighbors
   NN_search(W, nbrs[i]);//search for NN's
   while(!pq.empty()){//empty the queue while store the NN's
    NN.push_back(pq.top().second);//store NN
    pq.pop();//pop queue
   }
   best_guess(nbrs[i]);//make best guess
  }
  cout << "Total Err: " << total_error << endl;//print out total error
 }
