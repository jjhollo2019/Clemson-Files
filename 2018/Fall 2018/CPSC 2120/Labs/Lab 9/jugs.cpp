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

 typedef pair<int,int> state;//using a pair instead of a string
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
   //print the current jug levels
  cout << s.first << " " << s.second << endl;
 }

 // GENERIC
 void print_path(state s, state t)
 {
  if (s != t) print_path(s, pred[t]);
  print_state(t);
 }

 bool are_neighbors(state a, state b)
 {
  if(a.first == b.first && a.second != 4 && b.second == 4){//fill bucket 2
   edge_label[make_pair(a,b)] = "Fill bucket 2 : ";
   return true;
  }
  if(a.second == b.second && a.first != 3 && b.first == 3){//fill bucket 1
   edge_label[make_pair(a,b)] = "Fill bucket 1 : ";
   return true;
  }
  if(a.first == 0 && a.second == 0 && b.first == 0 && b.second == 0){//start
   edge_label[make_pair(a,b)] = "Initial state : ";
   return true;
  }
  if(a.first == 0 && a.second == 0 && b.first == 0 && b.second == 4){//fill bucket 2
   edge_label[make_pair(a,b)] = "Fill bucket 2 : ";
   return true;
  }
  if(a.first == 0 && a.second == 4 && b.first == 3 && b.second == 1){//fill bucket 1
   edge_label[make_pair(a,b)] = "Fill bucket 1 from bucket 2 : ";
   return true;
  }
  if(a.first == 3 && a.second == 1 && b.first == 0 && b.second == 1){//empty bucket 1
   edge_label[make_pair(a,b)] = "Empty bucket 1 : ";
   return true;
  }
  if(a.first == 0 && a.second == 1 && b.first == 1 && b.second == 0){//pour bucket 2
   edge_label[make_pair(a,b)] = "Fill bucket 1 from bucket 2 : ";
   return true;
  }
  if(a.first == 1 && a.second == 0 && b.first == 1 && b.second == 4){//fill bucket 2
   edge_label[make_pair(a,b)] = "Fill bucket 2 : ";
   return true;
  }
  if(a.first == 1 && a.second == 4 && b.first == 3 && b.second == 2){//pour bucket 2
   edge_label[make_pair(a,b)] = "Fill bucket 1 : ";
   return true;
  }
  else
   return false;
 }

 void build_graph(void)
 {
  vector<state> words;
  state s, t;
  //add valid states to the words table
  state moves[] = { make_pair(0,0), make_pair(3,4), make_pair(0,4), 
   make_pair(3,0), make_pair(3,1), make_pair(0,1), make_pair(1,0), 
   make_pair(1,4), make_pair(3,2), make_pair(2,3) };
  for(int i = 0; i < 10; i++)
   words.push_back(moves[i]);
  for (state s : words)
    for (state t : words)
      if (are_neighbors(s,t)) {
	   nbrs[s].push_back(t);
      }  
 }

 int main(void)
 {
  build_graph();
  //determine the starting position
  state start = make_pair(0,0);
  //determine possible win states
  state valid[] = { make_pair(3,2), make_pair(2,3), make_pair(1,4) };  
  //search for a solution
  search (start);
  //check for a valid solution
  for(int i = 0; i < 3; i++){
   if (visited[valid[i]])
     print_path (start, valid[i]);
   else
     cout << "No path!\n";
  }
  return 0;
 }
