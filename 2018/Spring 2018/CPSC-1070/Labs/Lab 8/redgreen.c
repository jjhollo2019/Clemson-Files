/* Author: Jeremy Holloway jjhollo
*  CPSC-1071-001
*  Lab 8
*  Due Date: 3/10/18
*  Description: This program will create a ppm file and print the values
*  to the monitor
*/


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "image.h"

/* Function: generate_image
*  Description: This function will generate the image and assign the 
*  red and green values accordingly 
*/
void generate_image(pixel_t *img, int width, int height)
{
    pixel_t *pixaddr = img;

    int half = width / 2;
    int rowNdx;
    int colNdx;

    /* generate the image                                 */
    for (rowNdx = 0; rowNdx < height; rowNdx++) 
    {
       for (colNdx = 0; colNdx < width; colNdx++) 
       {
          pixaddr->b = 0;    /* No blue in image          */
          if (colNdx < half )   /* Left half of image     */
          {    
             pixaddr->r = 255;
             pixaddr->g = 0;
          }
          else                /* Right half of image      */
          {
             pixaddr->r = 0;
             pixaddr->g = 255;
          }
	#if DEBUG
          print_pixel(stdout, pixaddr);
	#endif
          pixaddr++;
      }

    }
}
