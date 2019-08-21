/* priority lock (plock) test driver * 
* compile with: gcc -Wall plock.c main.c -pthread 
* run helgrind tests with:  valgrind --tool=helgrind ./a.out 
*/

#include "plock.h"

plock_t *priority_lock;      
/* global lock shared among worker threads */

void *worker( void *args ){  
    int thread_id = *( (int *) args );  
    int priority  = *( ( (int *) args ) + 1 );  
    plock_enter( priority_lock, priority );  
    printf( "thread %d starts with priority %d\n", thread_id, priority );  
    sleep( 1 );  
    printf( "thread %d ends\n", thread_id );  
    plock_exit( priority_lock );  
    pthread_exit( NULL );
}
    
int main( int argc, char **argv ){  
    pthread_t threads[20];  
    int i;  
    int rc;  
    int args[4][2] =            
    /* pairs of thread id and priority */    
    { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 3, 5 } };  
    priority_lock = plock_create();  
    for( i = 0; i < 4 ; i++ ){    
        rc = pthread_create( &threads[i], NULL, &worker, (void *)( args[i] ) );    
        if( rc ){ 
            printf( "** could not create thread %d\n", i ); 
            exit( -1 ); 
        }  
    }  
        
    for( i = 0; i < 4; i++ ){    
        rc = pthread_join(threads[i], NULL);    
        if( rc ){ printf("** could not join thread %d\n", i); 
        exit( -1 ); 
        }  
    }  
        
    plock_destroy( priority_lock );  
        
    return 0;
    }