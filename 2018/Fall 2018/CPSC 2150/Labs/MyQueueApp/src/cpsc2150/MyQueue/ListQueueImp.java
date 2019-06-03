/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 04
 * 9/21/2018
 */
package cpsc2150.MyQueue;

import java.util.*;

/**
 * Correspondence: this = myQ. The front of myQ is the front of
 the Queue
 * Correspondence: size = myQ.size();
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ListQueueImp implements IntegerQueueI {
    private List<Integer> myQ;

    /**
     * Function: ListQueueImp()
     * Description: This function is the default constructor for the class
     * Input: none
     * Output: initialized class object
     * @pre list data structure called by user
     * @post memory allocated for linked list
     */
    public ListQueueImp(){
        myQ = new LinkedList<>();//create memory for the linked list
    }

    /**
     * Function: add(Integer x)
     * Description: This function will add to the linked list
     * Input: Integer x
     * Output: none
     * @pre LinkedList must be initialized
     * @post Integer x is in the linked list
     * @param x is the value to be added to the queue.
     */
    public void add(Integer x)
    {
        myQ.add(x);
    }//use linked list add method

    /**
     * Function: pop()
     * Description: This function will return and remove the first list item
     * Input: none
     * Output: int first list item
     * @pre myQ.size() > 0
     * @post the linkedlist will dynamically adjust to the removal.
     * @return the list item is returned after removal
     */
    public Integer pop()
    {
        return myQ.remove(0);//use linked list removal method to remove and return list item
    }

    /**
     * Function: size()
     * Description: This function will return the size of the linked list
     * Input: none
     * Output: int size of linked list
     * @pre object must be initialized
     * @post list size remains unchanged
     * @return returns the list size as an int
     */
    public int size()
    {
        return myQ.size();
    }//use linked list size method to return list size
}

