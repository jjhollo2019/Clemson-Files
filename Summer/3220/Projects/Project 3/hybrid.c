/* Jeremy Holloway
 * CPSC-3220-001
 * 6/10/2019
 * Project 3
 */

#include <stdio.h>
#include "hybrid.h"
#include "assert.h"
#include "math.h"

unsigned int mask(int index){
  int x = 31 - index;
  unsigned int mask = 0x00000000;
  mask += pow(2, x);
  printf("%X\n", 0xf0000000);
  printf("%X\n", x | bitmap[0]);
  return mask;
}

char *bitmap_allocate(){
  char *ptr;
  //declare a header pointer
  long long unsigned int *header_ptr;
  int index;
  int word;

  if(arena_count[0] <= 0) return NULL;
  
  //set the start pointer to the start of the arena
  char *start = arena_head[0];
  //set the end to the last part of the memeory space

  //create an index for the bit to flip
  int index = 0;

  //iterate through the memory space while not at the end
  //and while the memory pointer is used
  while(*start != ARENA_0_SIZE && *start != 0) {
    //move the size of one block
    start += ARENA_0_BLOCK_SIZE;
    //increment the index 
    index++;
  }

  //get the address of start
  header_ptr = (long long unsigned int *) start;
  //point start to the header signature
  *header_ptr = HEADER_SIGNATURE;

  //decrement the number of available blocks
  arena_count[0]--;

  //declare a word index
  int word = 0;
  //while the index is greater than 31
  while(index > 31){
    //subtract 31
    index -= 31;
    //increment the word index
    word++;
  }

  //create a bitmask with the mask function
  unsigned int bitmask = mask(index);
  //bitwise OR the mask with the bitmap
  bitmap[word] |= bitmask;

  //return the pointer while leaving space for the header bits
  return start + 8;
}

char *list_allocate( int arena ){
  //check if the arena is full
  if(arena_head[arena] == NULL){
    //return NULL if it is
    return NULL;
  }
  //declare a header for setting the signature
  long long unsigned int *header_ptr;
  //declare a block double pointer
  char **block_ptr = (char**) arena_head[arena];

  //set the arena head to the next pointer in arena
  arena_head[arena] = *block_ptr;
  
  //set the header pointer to the block pointer
  header_ptr = (long long unsigned int *) block_ptr;
  //set the header equal to the header signature plus the arena ID
  *header_ptr = HEADER_SIGNATURE + arena;

  //decrement the number of available blocks
  arena_count[arena]--;

  //return the block pointer + 8
  return (char*) block_ptr + 8;
}

void release( char *release_ptr ) {

  char *ptr;
  long long unsigned int header, *header_ptr;

  if( (long long int) release_ptr & 0x7 ){
    printf( "pointer not aligned on 8B boundary in release() function\n" );
    printf( "  => no action taken\n" );
    return;
  }
  if( ( release_ptr < min_address ) || ( release_ptr > max_address )  ){
    printf( "pointer out of range in release() function\n" );
    printf( "  => no action taken\n" );
    return;
  }
  ptr = release_ptr - 8;
  header_ptr = (long long unsigned int *) ptr;
  header = *header_ptr;
  if( ( header & 0xfffffff0 ) != HEADER_SIGNATURE ){
    printf( "header does not match in release() function\n" );
    printf( "  => no action taken\n" );
    return;
  }

  char **block_ptr = (char**)release_ptr;
  int arena = header & 0x0000000f;
  if(arena == 0){
    int index = 0;
    char *start = arena_head[arena];
    while(start != ptr){
      start += ARENA_0_BLOCK_SIZE;
      index++;
      //assert(index > 128);
      printf("%d\n", index);
    }

    int word = 0;
    while(index > 31){
      index -= 31;
      word++;
    }

    int bitmask = mask(index);
    bitmap[arena] &= 0xffffffff;
    bitmap[arena] ^= bitmask;
  }
  //printf("%d\n", arena);
  *block_ptr = arena_head[arena];
  //arena_head[arena] = release_ptr;//this piece kills it
  arena_count[arena]++;
  return;
}

char *allocate( int size ){
    if( size <= 0 ){
      return NULL;
    }else if( size <= ( arena_block_size[0] - 8 ) ){
      return bitmap_allocate();
    }else if( size <= ( arena_block_size[1] - 8 ) ){
      return list_allocate( 1 );
    }else if( size <= ( arena_block_size[2] - 8 ) ){
      return list_allocate( 2 );
    }else{
      return NULL;
    }
}
