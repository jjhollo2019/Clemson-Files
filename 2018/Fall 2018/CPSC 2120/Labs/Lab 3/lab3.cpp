/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 03
 * 9/18/2018
 * This is the implementation file for Lab3
 */

 using namespace std;

 #include <cmath>
 #include <string>
 #include "lab3.h"

 /* Function: Lab3()
  * Description: This function is the default constructor for the class
  * Input: none
  * Output: initialized class object
  */
 Lab3::Lab3()
 {
  b = 1000;//set b equal to the sqrt of the amount of data entries
  arr = new Node **[b];//intialized the list node
  for (int i = 0; i < b; i++)//this loop creates the row node
  {
   arr[i] = new Node *[b];//declare new row node
   for (int j = 0; j < b; j++)//this loop will create column nodes
   {
    arr[i][j] = NULL;//set each column node to NULL
   }
  }
 }

 /* Function: ~Lab3()
  * Description: This function is the class destructor
  * Input: none
  * Output: none
  */
 Lab3::~Lab3()
 {
  for (int i = 0; i < b; i++)
  {
   for (int j = 0; j < b; j++)
   {
    while (arr[i][j] != NULL)//if there is a list in the element
    {
     Node *temp = arr[i][j];//declare a temp node
     arr[i][j] = arr[i][j]->next;//iterate through the list
     delete temp;//delete temp node
    }
    delete []arr[i];//delete row
   }
  }
  delete []arr;//delete table
 }

 /* Function: insert(double x1, double y1)
  * Description: This function will insert new nodes into the hash table
  * based on a hash of their x and y values
  * Input: x1, y1
  * Output: none
  */
 void Lab3::insert(double x1, double y1) 
 {
  Node *add;//declare the new node
  add = new Node(x1, y1, arr[hash(x1)][hash(y1)]);//malloc memory for the new node
  arr[hash(x1)][hash(y1)] = add;//insert the node
 }

 /* Function: hash(double x)
  * Description: This function will compute the hash value of the provided double
  * Input: x
  * Output: none
  */
 int Lab3::hash(double x)
 {
  return (int)(x * b);//return the computed hash value
 }

 /* Function: findSmallestDistance()
  * Description: This function will find the smallest distance between the
  * members of the hash table using the distance formula held in the 
  * calculateDistance function
  * Input: none
  * Output: none
  */
 double Lab3::findSmallestDistance()
 {
  double distance = 100;//set destance to an arbitrary number > 0
  for(int i = 0; i < b; i++)
  {
   for(int j = 0; j < b; j++)
   {
    if (arr[i][j] == NULL)//do nothing if the element is empty
    {
    }
    else
    {
     if(arr[i][j]->next != NULL)//iterate through the list
     {
      double tempCalc = calculateDistance(arr[i][j], arr[i][j]->next);//calculate distance
      arr[i][j] = arr[i][j]->next;//iterate to next item
      if (tempCalc < distance)//check if new distance is less than previous distance
      {
       distance = tempCalc;//set new distance to smaller value
      }
     }
    }
   }
  }
  return distance;//return the smallest distance
 }

 /* Function: calculateDistance(Node *one, Node *two)
  * Description: This function will compute the distance between
  * two points on a cartesian coordinate plane
  * Input: *one, *two
  * Output: double distance
  */
 double Lab3::calculateDistance(Node *one, Node *two)
 {
  double distance = 0;//intialize distance variable
  distance = sqrt(pow((one->x - two->x), 2) + pow((one->y - two->y), 2));//distance formula
  return distance;//return calculation
 }
 
