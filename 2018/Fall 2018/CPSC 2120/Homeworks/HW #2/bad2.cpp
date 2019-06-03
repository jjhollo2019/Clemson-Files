/* Jeremy Holloway
 * CPSC-2120-001
 * Homework 2
 * 10/18/2018
 */

#include <iostream>
#include <cstdlib>
using namespace std;

int main(int argc, char *argv[])
{
  if (argc != 2) {
    cout << "Usage: bad1 <input size>\n";
    return 0;
  }
  
  int N = atoi(argv[1]);  // get first command-line argument
  if (N<1 || N>100000) {
    cout << "Invalid input size!\n";
    return 0;
  }
  cout << N << endl;
  //hash can be broken using initial table size from prog1
  for (int i = 0; i < N; i++){//create input
   cout << i * 10000 << endl;
  }
  return 0;
}
