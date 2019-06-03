/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 09
 * 11/9/2018
 */

 #include <iostream>
 #include <fstream>
 #include <vector>
 #include <map>
 #include <stack>
 #include <queue>

 using namespace std;

 typedef string state;
 map<pair<state,state>,string> edge_label;
 // GENERIC -- these shouldn't need to be changed...
 map<state, bool> visited;         // have we queued up this state for visitation?
 map<state, state> pred;           // predecessor state we came from
 map<state, int> dist;             // distance (# of hops) from source node
 map<state, vector<state>> nbrs;   // vector of neighboring states

 // GENERIC (breadth-first search, outward from curnode)
 void search(state source_node)
 {
  queue<state> to_visit;
  to_visit.push(source_node);
  visited[source_node] = true;
  dist[source_node] = 0;
  
  while (!to_visit.empty()) {
    state curnode = to_visit.front();
    to_visit.pop();
    for (state n : nbrs[curnode])
      if (!visited[n]) {
	pred[n] = curnode;
	dist[n] = 1 + dist[curnode];
	visited[n] = true;
	to_visit.push(n);
      }
  }
 }

 void print_state(state s)
 {
  //print the edge label for the move
  cout << edge_label[make_pair(pred[s],s)];
  string print = "";
  //add whose on the left
  if(s == "0000") cout << "Initial state : ";
  for(int i = 0; i < 4; i++){
   if(s.at(i) == '0' && i == 0)
    print += "you ";
   if(s.at(i) == '0' && i == 1)
    print += "wolf ";
   if(s.at(i) == '0' && i == 2)
    print += "goat ";
   if(s.at(i) == '0' && i == 3)
    print += "cabbage ";
  }
  print += "|river|";//add the river
  //add whose on the right
  for(int i = 0; i < 4; i++){
   if(s.at(i) == '1' && i == 0)
    print += " you";
   if(s.at(i) == '1' && i == 1)
    print += " wolf";
   if(s.at(i) == '1' && i == 2)
    print += " goat";
   if(s.at(i) == '1' && i == 3)
    print += " cabbage";
  }
  cout << print << endl;//print positions
  return;
 }

 // GENERIC
 void print_path(state s, state t)
 {
  if (s != t) print_path(s, pred[t]);
  print_state(t);
 }

 bool are_neighbors(string a, string b)
 {
  //check for valid moves
  if(a == "0000" && b == "1010"){//take the goat
   edge_label[make_pair(a,b)] = "Take the goat across : ";
   return true;
  }
  if(a == "1010" && b == "0010"){//go back
   edge_label[make_pair(a,b)] = "Go Back : ";
   return true;
  }
  if(a == "0010" && b == "1110"){//take the wolf
   edge_label[make_pair(a,b)] = "Take the wolf : ";
   return true;
  }
  if(a == "0010" && b == "1011"){//or take the cabbage
   edge_label[make_pair(a,b)] = "Take the cabbage : ";
   return true;
  }
  if(a == "1110" && b == "0100"){//bring the goat back
   edge_label[make_pair(a,b)] = "Bring the goat back : ";
   return true;
  }
  if(a == "1011" && b == "0001"){//or bring back the goat
   edge_label[make_pair(a,b)] = "Bring the goat back : ";
   return true;
  }
  if(a == "0100" && b == "1101"){//take the cabbage
   edge_label[make_pair(a,b)] = "Take the cabbage : ";
   return true;
  }
  if(a == "0001" && b == "1101"){//or take the wolf
   edge_label[make_pair(a,b)] = "Take the wolf : ";
   return true;
  }
  if(a == "1101" && b == "0101"){//go back
   edge_label[make_pair(a,b)] = "Go back : ";
   return true;
  }
  if(a == "0101" && b == "1111"){//take the goat
   edge_label[make_pair(a,b)] = "Take the goat : ";
   return true;
  }
  else 
   return false;
 }

 void build_graph(void)
 {
  vector<state> words;
  string s, t;
  //determine valid states
  string states[] = {"0000","1010","0010","1110","0100","1101","0101","1111",
   "1011", "0001", "1101", "0101"};
  //add states to the word table
  for(int i = 0; i < 10; i++)
   words.push_back(states[i]);
  for (string s : words)
    for (string t : words)
      if (are_neighbors(s,t)) {
	nbrs[s].push_back(t);
	nbrs[t].push_back(s);
    }  
 }

 int main(void)
 {
  build_graph();
  //determine where to start 
  state start = "0000", end = "1111";
  //search for a solution
  search (start);
  if (visited[end])
    print_path (start, end);
  else
    cout << "No path!\n";
  
  return 0;
 }
