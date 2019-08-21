/** list.h -- lab 7 **/
     /** List node **/
     typedef struct node_type 
     {
        void *dataPtr; /* Pointer to the associated */
        struct node_type *next; /* Pointer to next node */
     } node_t;

     /** List structure **/
     typedef struct list_type 
     {
        node_t *head; /* Pointer to front of list */
     } list_t;

     /** Iterator **/
     typedef struct list_iterator 
     {
        list_t *list; /* List iterator is associated with */
        node_t *position; /* Current position in list */
     } iterator_t;

     /** Function prototypes **/
     list_t *newList(); /* Create and initialize list object */
     void list_add(list_t *list, void *objPtr); /* Insert object into list */

     iterator_t *newIterator(list_t *list);
     void list_reset(iterator_t *iter); /* Reset position to front of list */
     void *list_next(iterator_t *iter); /* Get object from list */
     int l_hasnext(iterator_t *iter); /* Test if at end of list */