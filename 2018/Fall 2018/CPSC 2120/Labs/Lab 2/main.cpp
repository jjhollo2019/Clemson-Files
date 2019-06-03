/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 02
 * 9/11/2018
 * Description: This file is the main for calling the stringset 
 * functions as well as definging spellcheck and test
 */
#include <string>
#include <fstream>
#include <iostream>
#include "stringset.h"

using namespace std;

/* Function: spellcheck()
 * Description: This function will will print alternatives to a 
 * user inputted word
 * Input: none
 * Output: none
 */
void spellcheck(void)
{
  Stringset S; //create a stringset item
  string word;//declare a word

  ifstream wordfile("words.txt");//read input text document
  while (wordfile >> word)//while loop will build the reference library
    S.insert(word);//insert the words into the hash table
  wordfile.close();//end text file input

  cout << "Dictionary loaded.  Please enter words to spell check.\n";

  while (cin >> word) {//grab user input
   cout << "Possible alternatives for word '" << word << "':\n";
   string check = word;//hold a copy of the input to prevent printing
   for(int a = 0; a < word.length(); a++){
    for(char b = 'a'; b <= 'z'; b++){//cycle through the alphabet
     word.at(a) = b;//change each character one at a time
     if(S.find(word) == true && word != check){//check for word
      cout << word << endl;//print word if found in the library
     }
    } 
   word = check;//restore the original word
   }
  }
}

/* Function: test()
 * Description: This function handles command line input and creates 
 * an instance item of the stringset class
 * Input: none
 * Output: none
 */
void test(void)
{
  Stringset S;//declare instance item
  string key, command;//declare strings

  while(cin >> command) {//while there is user input
    
    if (command == "insert") {//calls insert function
      
      cin >> key;//intializes key
      if (S.find(key))//checks if key is present
	cout << "Error!  Key " << key << " already in structure!\n";
      else//inserts key
	S.insert(key);

    } else if (command == "remove") {//calls remove function
      
      cin >> key;//intializes key
      if (!S.find(key)) //checks if the key is present
	cout << "Error!  Key " << key << " not in structure!\n";
      else//removes key
	S.remove(key);

    } else if (command == "find") {//calls find function

      cin >> key;//intializes key
      if (S.find(key))//calls find function
	cout << "Key " << key << " present.\n";
      else
	cout << "Key " << key << " absent.\n";

    } else if (command == "print") {//calls print function

      cout << "Contents of structure:\n";
      S.print();//prints contents

    } else if (command == "quit") {//quits the program

      break;

    } else {//warns if illegal commands are called

      cout << "Error!  Unknown command '" << command << "'!\n";

    }
  }
}

/* Function: Main 
 * Description: This is the main for the class
 * Input: none
 * Output: none
 */
int main(void)
{
  test();//call test
  spellcheck();//call spellcheck
  return 0;//end program
}
