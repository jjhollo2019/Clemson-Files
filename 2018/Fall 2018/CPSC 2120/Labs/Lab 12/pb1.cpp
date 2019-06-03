/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 12
 * Problem 1
 * 12/04/2018
 * Running Time: O(n log n)
 */

 #include <iostream>
 #include <fstream>
 #include <algorithm>
 #include <vector>
 #include <cmath>
 #include <string>

 using namespace std;

 int main(){
  ifstream infile ("p1.txt");//declare file stream
  string in = "";//declare in stream
  int counter = 0;//declare counter
  vector<pair<string,int> > Arr;//declare vector
  while(infile >> in){//read input file
   Arr.push_back(make_pair(in,counter));//push in data members
   counter++;//increment the counter
  }
  infile.close();//close file stream
  sort(Arr.begin(), Arr.end());//sort the array
  int longest_dist = 0;//declare longest distance
  for(int i = 0; i < (signed)Arr.size(); i++){//loop through the values
   int new_dis = abs(Arr[i].second - i);//new distance = |original index - new index|
   if(new_dis > longest_dist)//check if new distance is bigger
    longest_dist = new_dis;//update distnce
  }
  cout << "Longest Distance: " << longest_dist << endl;//print out distance   
  return 0;//exit program
 }
