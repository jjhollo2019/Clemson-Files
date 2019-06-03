/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 12
 * Problem 4
 * 12/04/2018
 * Running Time: O(n log n)
 */

 #include <cstdlib>
 #include <iostream>
 #include <fstream>
 #include <map>

 using namespace std;

 int main(){
  map<int,int> m;//declare int to int map
  map<int,int> used;//declare int to int map
  map<int,int>::iterator it;//declare int to int iterator
  int x = 0;//declare a read value
  int max_count = 0;//declare the max count
  int count = 1;//declare the current count
  ifstream infile ("p4.txt");//open file stream
  while(infile >> x){//while reading input
   m[count] = x;//insert count and the value of x
   used[count] = x;//insert count and the value of x
   count++;//increment the count
  }
  infile.close();//close file stream
  for(int i = 1; i <(signed) used.size(); i++){//loop through the data members 
   if(used.find(i) == used.end()){//check if our current value is at the end
    continue;//continue if true
   }
   else{//else follow the path
    count = 0;//reuse count to track the number of jumps made
    int current = i;//current node is i
    int destination = current;//set the destination
    it = used.find(current);//set iterator to the current node
    used.erase(it);//erase it from the used map
    current = m[current];//reset current
    count++;//incrememnt number of jumps
    while(current != destination){//check if current is equal to the destination
     current = m[current];//update current
     if(used.find(current) != used.end()){//check if current is at the end
      it = used.find(current);//update iterator
      used.erase(current);//delete current node from the map
     }
     count++;//increment number of jumps
    }
    if(count > max_count) max_count = count;//update the max count if the current is higher
   }
  } 
  cout << max_count << endl;//print the answer
  return 0;//exit the program
 }
