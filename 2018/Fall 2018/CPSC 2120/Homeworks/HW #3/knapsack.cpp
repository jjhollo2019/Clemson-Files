/* Jeremy Holloway
 * CPSC-2120-001
 * Homework #3
 * 11/3/2018
 */

 using namespace std;
 #include <iostream>
 #include <fstream>
 #include <algorithm>
 #include <cstdlib>

 //global variables
 int bag1;//weight of bag 1
 int bag2;//weight of bag 2
 int bag3;//weight of bag 3
 int greedy;//greedy value
 int refinement;//refinement value
 int exhaustive;//exhaustive value

 //struct to hold candy pieces
 struct Candy{
  int weight;
  int tastiness;
  int bag;
  Candy(){weight = 0; tastiness = 0; bag = 0;}
 };
 //declare global candy array
 Candy *Arr;
 //tell sort how to compare candies
 bool operator<(Candy &A, Candy &B){
  return ((double)B.weight/B.tastiness) > ((double)A.weight/A.tastiness);
 }
 
 //helper function to tell if any bags are over 2000
 bool overfull_bags(){
  if(bag1 > 2000 || bag2 > 2000 || bag3 > 2000)
   return true;
  else
   return false;
 }

 //checks every candy combination to find the optimal amount
 void exhaustive_search(int index, int &sol){
  if(exhaustive > sol && !overfull_bags()){//check if this is the best solution
   sol = exhaustive;//update if so
  }
  if(index == 16 || overfull_bags()){//prune or end if invalid state or out of bounds
   return;
  }
  for(int i = 0; i < 4; i++){//switch between bags
   switch(i){
    case 0:
     exhaustive += Arr[index].tastiness;//add tastiness
     bag1 += Arr[index].weight;//add weight
     exhaustive_search((index + 1), sol);//recurse
     exhaustive -= Arr[index].tastiness;//sub tastiness
     bag1 -= Arr[index].weight;//sub weight
     break;
   
    case 1:
     exhaustive += Arr[index].tastiness;//add tastiness
     bag2 += Arr[index].weight;//add weight
     exhaustive_search((index + 1), sol);//recurse
     exhaustive -= Arr[index].tastiness;//sub tastiness
     bag2 -= Arr[index].weight;//sub weight
     break;

    case 2:
     exhaustive += Arr[index].tastiness;//add tastiness
     bag3 += Arr[index].weight;//add weight
     exhaustive_search((index + 1), sol);//recurse
     exhaustive -= Arr[index].tastiness;//sub tastiness
     bag3 -= Arr[index].weight;//sub weight
     break;

    case 3: 
     exhaustive_search((index + 1), sol);//recurse
     break;
   }
  }
 }

 //helper function to check a specific bag weight
 int update_bag(int a){
  int weight = 0;
  for(int i = 0; i < 16; i++)
   if(Arr[i].bag == a)//check for bag index
    weight += Arr[i].weight;//add weight
  return weight;//return total
 }
 
 //helper function to determine total tastiness
 int update_tasty(){
  int tasty = 0;
  for(int i = 0; i < 16; i++)
   if(Arr[i].bag != 0)//check if bag is in use
    tasty += Arr[i].tastiness;//add tastiness
  return tasty;//return total tastiness
 }
 
 //randomly iterates bags to find an optimal solution
 void refinement_search(int &tasty){
  bag1 = bag2 = bag3 = 0;
  for(int j = 0; j < 250000; j++){
   for(int i = 0; i < 16; i++){
    Arr[i].bag = rand()%4;//set the candy to a random bag
    Arr[rand()%16].bag = rand()%4;//set a random candy to a random bag
    bag1 = update_bag(1);//update bag 1 weight
    bag2 = update_bag(2);//update bag 2 weight
    bag3 = update_bag(3);//update bag 3 weight
    if(!overfull_bags() && update_tasty() > tasty)//check if solution is good
     tasty = update_tasty();//update if so
   }
  }
  return;
 }

 int main(){
  ifstream infile("candy.txt");//set input file
  int wgt = 0;
  int taste = 0;
  int i = 0;
  Arr = new Candy[16];
  while(infile >> wgt >> taste){//get candies
   Arr[i].weight = wgt;
   Arr[i].tastiness = taste;
   i++;
  }
  sort(Arr, Arr + 16);//sort candies
  greedy = 0;
  bag1 = bag2 = bag3 = 0;//initialize bags
  for(i = 0; i < 16; i++){
   if((Arr[i].weight + bag1) <= 2000){//add candies to bag 1
    bag1 += Arr[i].weight;
    greedy += Arr[i].tastiness;
   }
   else if((Arr[i].weight + bag2) <= 2000){//add candies to bag 2
    bag2 += Arr[i].weight;
    greedy += Arr[i].tastiness;
   }
   else if((Arr[i].weight + bag3) <= 2000){//add candies to bag 3
    bag3 += Arr[i].weight;
    greedy += Arr[i].tastiness;
   }
  }
  cout << "Greedy: " << greedy << endl;//print total
  refinement = 0;//initialize refinement
  random_shuffle(Arr, Arr+16); //randomly shuffle array
  refinement_search(refinement);//call refinement function
  cout << "Refinement: " << refinement << endl;//print refinement
  exhaustive = 0;//intialize exhaustive
  int solution = 0;//intialize solution
  bag1 = bag2 = bag3 = 0;//reset bags
  exhaustive_search(0, solution);//call exhaustive search
  cout << "Exhaustive: " << solution << endl;//print exhaustive 
  return 0;//end
 }
