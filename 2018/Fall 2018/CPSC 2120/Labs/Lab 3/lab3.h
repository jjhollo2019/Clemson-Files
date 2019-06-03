/* Jeremy Holloway
 * CPSC-2120-001
 * Lab 03
 * 9/18/2018
 */

#ifndef LAB3_H
#define LAB3_H

 struct Node
 {
  double x;
  double y;
  Node *next;
  Node(double xin, double yin, Node *n) { x = xin; y = yin; next = n; };
 };

 class Lab3
 {
  private:
  Node ***arr;
  int b;
  double size;
  double calculateDistance(Node *one, Node *two);
  
  public:
   Lab3();
   ~Lab3();
   void insert(double x1, double y1);
   double findSmallestDistance();
   int hash(double x);
 };  

#endif
