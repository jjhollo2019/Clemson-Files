/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 03
 * 9/18/2018
 * This file is the main for Lab3
 */

 #include "lab3.h"
 #include <fstream>
 #include <iostream>
 #include <string>

using namespace std;

 /* Function: main
  * Description: This function will initialize the node
  * structure and import the data structure into a hash table
  * and call the function to determine the smallest distance
  * Input: none
  * Output: smallestDistance 
  */
 int main()
 {
  Lab3 *pointList;//declare hash table
  pointList = new Lab3();//malloc memory
  double x1;
  double y1;
  ifstream file("points.txt");//declare input file
  while (file >> x1 >> y1)
  {
   pointList->insert(x1, y1);//build the hash table
  }
  double smallestDistance = pointList->findSmallestDistance();//compute smallest distance
  cout << "The smallest distance is: " << smallestDistance << endl;//output distance
 }
