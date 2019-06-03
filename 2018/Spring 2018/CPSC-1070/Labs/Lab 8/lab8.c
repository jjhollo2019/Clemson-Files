/* Author: Jeremy Holloway jjhollo
*  CPSC-1071-001
*  Lab 8
*  Due Date: 3/10/18
*  Description: This program will count the various types of numbers until
*  it reaches a zero and dis[lay the counts for each type
*/

#include <stdio.h>

/* Function: main
*  Description: This function will import the numbers one at a time and 
*  classify each one into its assigned counter
*/
int main()
{
   int positive_count = 0;
   int positive_sum = 0;
   int negative_count = 0;
   int negative_sum = 0;
   int even_count = 0;
   int even_sum = 0;
   int all_count = 0;
   int all_sum = 0;
   int num;

   scanf("%d", &num);
   while(num != 0)
   {
       all_count += 1;
       all_sum += num;

       printf("%d\n", num);
       
       if(num > 0)
       {
          positive_count += 1;
          positive_sum += num;
          #if DEBUG
          printf("  debug: %d is positive (count = %d, sum = %d)\n",
                    num, positive_count , positive_sum);
          #endif
        }

       else   // number is negative
       {
          negative_count += 1;
          negative_sum += num;
          #if DEBUG
          printf("  debug: %d is negative (count = %d, sum = %d\n", 
                    num, negative_count, negative_sum);
          #endif
       }
       
       if(num % 2 == 0)
       {
          even_count += 1;
          even_sum += num;
          #if DEBUG
          printf("  debug: %d is even (count = %d, sum = %d)\n", 
                    num, even_count, even_sum);
          #endif
             even_count += 1;
             even_sum += num;
       }
          
       #if DEBUG 
       printf("  debug: total (count = %d, sum = %d)\n", 
                         all_count, all_sum);
       #endif

       scanf("%d", &num);
   }
   
   printf("\nOverall totals:\n");
   printf("   There were %d positive numbers, totaling %d\n", 
              positive_count, positive_sum);
   printf("   There were %d negative numbers, totaling %d\n", 
              negative_count, negative_sum);
   printf("   There were %d even numbers, totaling %d\n", 
              even_count, even_sum);
   printf("   There were %d total numbers, totaling %d\n\n", 
              all_count, all_sum);
           

   return 0;
}
