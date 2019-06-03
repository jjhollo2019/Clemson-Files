/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 12
 * Problem 3
 * 12/04/2018
 * Running Time: O(n)
 */

 #include <iostream>
 #include <fstream>
 #include <cstdlib>
 #include <map>
 #include <string>

 using namespace std;

 int hashed(string k){//create a function to determine what bucket a string would go in
  int h = 0;//declare the hash value
  for(int i = 0; i <(signed)k.length(); i++)//loop through each char
   h += (int)(k[i]);//sum them all 
  return h%101;//return the sum modulus 101
 }

 int main(){
  int *buckets = new int[101];//declare the buckets
  for(int j = 0; j < 101; j++)//loop through all buckets
   buckets[j] = 0;//initialize all of the buckets
  map<string,int> check;//map strings to integers
  ifstream infile ("p3.txt");//declare the filestream
  string input = "";//declare an input string
  while(infile >> input){//read input string
   if(check.count(input) == 0){//check if string is already in the map
    check[input] = 1;//add it to the map
    buckets[hashed(input)]++;//update the bucket for which it would go in
   }
  }
  infile.close();//close file stream
  int largest = 0;//declare the largest value found
  for(int i = 0; i < 101; i++){//loop through the buckets
   if(buckets[i] > largest)//check if the current bucket is the highest seen thus far
    largest = buckets[i];//update the largest seen
  }
  cout << largest << endl;//print out the answer
  return 0;//exit program
 }
