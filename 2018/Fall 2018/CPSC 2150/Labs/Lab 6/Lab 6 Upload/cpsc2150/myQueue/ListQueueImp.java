/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 06
 * 10/12/2018
 */
package cpsc2150.myQueue;

import java.util.*;

/**
 * Correspondence: this = myQ. The front of myQ is the front of the Queue
 * Correspondence: size = myQ.size();
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ListQueueImp<T> implements IQueue<T> {
    private List<T> myQ;

    /**
     * Function: ListQueueImp()
     * Description: This function is the default constructor for the class
     * Input: none
     * Output: initialized class object
     * @pre list data structure called by user
     * @post memory allocated for linked list
     */
    public ListQueueImp(){
        myQ = new LinkedList<T>();//create memory for the linked list
    }

    public void add(T x)
    {
        myQ.add(x);
    }//use linked list add method

    public T pop() { return myQ.remove(0);}//use linked list removal method to remove and return list item

    public int size() { return myQ.size(); }//use linked list size method to return list size

    /**
     * Function: toString()
     * Description: This function will format the queue print out
     * Input: none
     * Output: Formatted string for printing
     * @invariant user must choose implementation before calling this function
     * @pre none
     * @post all list items are added into print string
     * @return
     */
    @Override
    public String toString()
    {
        String queue = "";//intialize the string
        if (size() == 0)
        {
            queue = "The queue is empty!";
        }
        for (int a = 0; a < size(); a++)//loop through all the list
        {
            queue += myQ.get(a) + "\n";//add item plus a new line char
        }
        return queue;//return the string
    }
}



