/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 08
 * 10/30/2018
 */

using namespace std;
#include <iostream>
#include <cstdlib>

 /* Function: solve_queen
  * Description: This function will solve the n queens problem
  * Input:
  *  int *Arr is the board in which to solve the problem
  *  int col is the column to start searching on
  *  int N is the total size of the board (N x N)
  *  bool row is a boolean array to prune searching by marking columns occupied
  *  bool diag_for is a boolean array to prune diagonal searching
  *  bool diag_back is a boolean array to prune diagonal searching
  *  int sol is the number of solutions to be recorded
  * Output:
  *  none
  */
 void solve_queen(int *Arr, int col, int N, bool *row, bool *diag_for, 
  bool *diag_back, int &sol){
  if(col == N){//base case, solution is found
   sol++;//record solution
   return;
  }
  for(int i = 0; i < N; i++){
   int k = i;//store current row
   int diag1 = i + col;//store diagonal position
   int diag2 = i - col + N;//store diagonal position
   //check if next placement is free
   if(!row[k] && !diag_for[diag1] && !diag_back[diag2]){
    row[k] = true;//mark the row occupied
    diag_for[diag1] = true;//mark the forward diagonal occupied
    diag_back[diag2] = true;//mark the backward diagonal occupied
    Arr[i] = 1;//drop a queen
    solve_queen(Arr, col + 1, N, row, diag_for, diag_back, sol);//recurse
    Arr[i] = 0;//pick up queen
    row[k] = false;//mark row unoccupied
    diag_for[diag1] = false;//mark diagonal forward unoccupied
    diag_back[diag2] = false;//mark diagonal back unoccupied
   }
  }
 }

 /* Function: main
  * Description: This function is the main driver for the program
  * Input: none
  * Output: none
  */
 int main(int argc, char *argv[]){
  int solutions = 0;
  int n = atoi(argv[1]);
  int board[n];//make a board of columns
  bool *row = new bool[n];//make a row of booleans
  bool *diag1 = new bool[2*n-1];//make a diagonal board of booleans
  bool *diag2 = new bool[2*n-1];//make a diagonal board of booleans
  for(int r = 0; r < n; r++){//set the board to 0 and the row to false
   row[r] = false;
   board[r] = 0;
  }
  for(int i = 0; i < (2*n-1); i++){//set the diagonals to false
   diag1[i] = diag2[i] = false;
  }
  solve_queen(board, 0, n, row, diag1, diag2, solutions);//calculate solution
  cout << solutions << endl;//display solution
  return 0;//exit
 }
