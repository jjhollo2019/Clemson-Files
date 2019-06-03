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
  int Arr1[N / 2];
  int Arr2[N / 2];
  // Generate and print bad input of size N for prog1
  // (currently just generates an input of N random numbers)
  cout << N << "\n";
  int j = N;
  //the idea is to make a reverse funnel shape so it has to span greater
  //and greater distance
  for (int i=0; i<(N/2); i++){//create first half
     Arr1[i] = j;
     j--;
  }
  j = N/2;
  for (int i = 0; i < (N/2); i++){//create second half
     Arr2[i] = j;
     j++;
  }
  for (int i = 0; i < N/2; i++){//print first half
    cout << Arr1[i] << endl;
  }
  for (int i = 0; i < N/2; i++){//print second half
    cout << Arr2[i] << endl;
  }
  return 0;
}
