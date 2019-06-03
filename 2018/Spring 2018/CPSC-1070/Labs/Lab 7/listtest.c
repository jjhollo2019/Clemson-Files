/* Author: Jeremy Holloway/jjhollo
*  CPSC-1071-001
*  Lab 7
*  Due Date: 3/3/2018
*  Description: This program will call the functions made in list.c and
*  use them to generate structs of the assigned data.
*/

/** Test program for CPSC 1071  lab 7 **/
#include "list.h"

typedef struct vehicle_type {
   int vin;
   char *make;
   char *model;
   int year;
} veh_t;

int main() {
   int data[] = {5, 10, 15, 20, 25};
 
   char *fruit[] = {"apple", "orange", "peach", "banana"};

   veh_t vehicles[] = { {23456, "Ford", "Mustang", 2017},
                        {32168, "Honda", "Accord", 2015},
                        {32565, "Toyota", "Camry", 2016},
                        {15677, "Jeep", "Cherokee", 2014},
                        {34257, "Chevrolet", "Impala", 2017},
                        {54387, "Nissan", "Altima", 2016},
                        {34577, "Toyota", "Prius", 2013}};

   int index;
   int size;

   int   *ptr1;
   char  *ptr2;
   veh_t *ptr3;

   list_t *list1;
   list_t *list2;
   list_t *list3;

   iterator_t *iter1;
   iterator_t *iter2;
   iterator_t *iter3;
                        
   /** Create the lists **/
   list1 = newList();
   list2 = newList();
   list3 = newList();

   /*  Populate the lists  */
   size = sizeof(data)/sizeof(int);
   for(index = 0; index < size; index++) 
   {
      list_add(list1, &data[index]);
   }

   size = sizeof(fruit)/sizeof(char *);
   for(index = 0; index < size; index++) 
   {
      list_add(list2, fruit[index]);
   }
   
   size = sizeof(vehicles)/sizeof(veh_t);
   for(index = 0; index < size; index++) 
   {
      list_add(list3, &vehicles[index]);
   }

   /** Retrieve data from lists **/

   /* Print list 1 */
   fprintf(stdout, "List 1: ");
   iter1 = newIterator(list1);
   while(list_hasnext(iter1) == 1) {
      ptr1 = list_next(iter1);
      fprintf(stdout, "%d, ", *ptr1);
   }

   fprintf(stdout, "\n");
   fprintf(stdout, "\nTest list_reset() function: ");

   /* Test of list_reset() function */
   list_reset(iter1);
   ptr1 = list_next(iter1);
   fprintf(stdout, "First value = %d\n", *ptr1);

   /* Print list 2 */
   fprintf(stdout, "\nList 2: ");
   iter2 = newIterator(list2);
   while(list_hasnext(iter2) == 1) {
      ptr2 = list_next(iter2);
      fprintf(stdout, "%s, ", ptr2);
   }

   /* Print list 3 */
   fprintf(stdout, "\n\nList 3: ");
   iter3 = newIterator(list3);
   while(list_hasnext(iter3) == 1) {
      ptr3 = list_next(iter3);
      fprintf(stdout, "[%d %s %s %d], ", ptr3->vin, ptr3->make, ptr3->model, ptr3->year); 
   }
   fprintf(stdout, "\n\n");
   return 0;
}
