/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 04
 * 9/21/2018
 */
package cpsc2150.MyQueue;

public interface IntegerQueueI
{
    /**
     * A queue containing integers.
     * A queue is a data structure where the first item added to the
     structure is the first item removed from the structure
     * This queue is bounded by MAX_DEPTH
     *
     * Initialization ensures the queue is empty
     * Defines: size:Z
     * Constraints: 0 <= size <= MAX_DEPTH
     */

    int MAX_DEPTH = 100;

    /**
     * Function: add(Integer x)
     * Description: This function is the add method for the interface
     * Input: Integer x
     * Output: none
     * @pre int x != NULL
     * @post Queue.size() >= 0
     * @param x is the value to be added to the queue.
     */
    public void add(Integer x);

    /**
     * Function: pop()
     * Description: This function is the pop method for the interface
     * Input: none
     * Output: Integer at the front of the queue
     * @pre Queue must have at least one data member before being used.
     * @post Queue size is one less after each call
     * @return returns the number of integers in the queue.
     */
    public Integer pop();

    /**
     * Function: size()
     * Description: This function will return the size of data structure
     * Input: none
     * Output: int size
     * @pre Queue must be initialized.
     * @post size >= 0
     * @return returns the number of integers in the queue.
     */
    public int size();
}