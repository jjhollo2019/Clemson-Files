/* Jeremy Holloway
 * CPSC-3220-001
 * 6/10/2019
 * Project 3
 */

#include <stdio.h>
#include "hybrid.h"

char *bitmap_allocate(){
  //declare a header pointer
  long long unsigned int *header_ptr;
  
  //set the start pointer to the start of the arena
  char *start = arena_head[0];
  //set the end to the last part of the memeory space
  char *end = start + ARENA_0_SIZE;

  //iterate through the memory space while not at the end
  //and while the memory pointer is used
  while(start != end && *start != 0) {
    //move the size of one block
    start += ARENA_0_BLOCK_SIZE;
  }

  //get the address of start
  header_ptr = (long long unsigned int *) start;
  //point start to the header signature
  *header_ptr = HEADER_SIGNATURE;

  //decrement the number of available blocks
  arena_count[0]--;

  //return the pointer while leaving space for the header bits
  return start + 8;
}

char *list_allocate( int arena ){
  long long unsigned int *head_ptr;
  char **block_ptr = (char**) arena_head[arena];

  char *current = arena_head[arena];
  char *next;
  if(arena == 1){
    next = *block_ptr + ARENA_1_BLOCK_SIZE;

    while(*next != ARENA_1_SIZE && *current != 0){
      printf("while\n");
      *current += ARENA_1_BLOCK_SIZE;
      block_ptr = (char**) current;
      next = current + ARENA_1_SIZE;
      *block_ptr = next;
    }    

    head_ptr = (long long unsigned int *) block_ptr;
    *head_ptr = HEADER_SIGNATURE;
    
    arena_count[1]--;

    return *block_ptr + 8;
  }
  else{
    next = *block_ptr;

    while(*next != ARENA_2_SIZE && *current != 0){ 
      current += ARENA_2_BLOCK_SIZE;
      block_ptr = (char**) current;
      next = current + ARENA_2_SIZE;
      *block_ptr = next;
    }    

    head_ptr = (long long unsigned int *) block_ptr;
    *head_ptr = HEADER_SIGNATURE;
    
    arena_count[2]--;

    return *block_ptr + 8;
  }
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
