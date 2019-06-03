/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 12 
 * Problem 2
 * 12/04/2018
 * Running Time: O(n^3), This could probably run in n log n if i understood how to use the triangular number formula
 */ 

 #include <iostream>

 using namespace std;

 int main(){
  bool cont = true;//set a break condition
  for(int i = 750000; i >= 10 && cont == true; i--){//loop through indexes of pells house
   long long right = 0;//declare a right sum
   long long left = 0; //declare a left sum
   for(int j = 1; j < i; j++)//loop through the left side
    left += j;//sum the houses on the left
   for(int k = i+1; right <= left && k <= 1000000; k++){//loop through the right side
    right += k;//sum the right side
    if(right == left){//check if sums are equal
     cout << i + k << endl;//print answer
     cont = false;//break the main loop
    }
   }  
  }
  return 0;//exit program
 }
