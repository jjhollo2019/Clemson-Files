/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 10
 * 11/13/2018
 */

 #include <string>
 #include <vector>
 #include <algorithm>
 #include <utility>
 #include <iostream>

 using namespace std;
 //declare the data type for input
 typedef pair<int,int> input;

 //recursive print function for decoding
 void print_code(vector<input> x, int a){
  if(x.at(a).first == 0){//base case prints if the root is zero
   cout << ((char) x.at(a).second);
   return;
  }
  print_code(x, x.at(a).first);//recurse on the root as the array index
  cout << ((char) x.at(a).second);//print everything along the way in order
 }

 int main(void){
 int root = 0;
 int key = 0;
 string out = "";
 input in;
 vector<pair<int,int>> trie;//using a vector to store input
 in.first = 0;
 in.second = 0;
 trie.push_back(in);//push in a blank entry to set the indicies
 while(cin >> root >> key){//read in input
  if(key != 10 && key != -1){
   in.first = root;
   in.second = key;
   trie.push_back(in); 
  }
 } 
 //iterate through the vector and print the input in order
 for(int i = 1; i < ((signed) trie.size()); i++)
  print_code(trie, i);
 cout << '\n';//print a newline 
 return 0;
 }
