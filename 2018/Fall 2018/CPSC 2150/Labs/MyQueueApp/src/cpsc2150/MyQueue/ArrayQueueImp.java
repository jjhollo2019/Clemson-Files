/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 04
 * 9/21/2018
 */
package cpsc2150.MyQueue;

/**
 * Correspondence: this = myQ[0...depth-1], myQ[0] is the front
 * of the Queue
 * Correspondence: size = depth
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ArrayQueueImp implements IntegerQueueI
{
    private Integer[] myQ;
    private int depth;

    /**
     * Function: ArrayQueueImp()
     * Description: This function is the default constructor for this class
     * Input: none
     * Output: initialized class object
     * @pre user call for Array structure
     * @post myQ initialized, depth = 0
     */
    public ArrayQueueImp()
    {
        myQ = new Integer[MAX_DEPTH];//create memory space for the array
        depth = 0;//set depth to number of current items
    }

    /**
     * Function: add(Integer x)
     * Description: This function will add an integer to the array
     * Input: Integer x
     * Output: none
     * @pre depthCheck == false
     * @post myQ[depth] = x
     * @param x must be a whole number
     */
    public void add(Integer x)
    {
        myQ[depth] = x;//input the value
        depth++;//increase number of items in the list
    }

    /**
     * Function: pop()
     * Description: This function return and remove the first list item
     * @pre depth >= 0
     * @post myQ[i] = myQ[i+1]
     * @return returns the item that was popped off the queue.
     */
    public Integer pop()
    {
        Integer temp = myQ[0];//set the first element to a temporary Integer
        //loop will resize the list
        for (int i = 0; i < depth; i++) {
            if (myQ[i + 1] != null)
                myQ[i] = myQ[i + 1];//move each item up one space
        }
        depth--;
        return temp;
    }


    /**
     * Function: size()
     * Description: This function will return the size of the array
     * @pre checkDepth == false
     * @post depth >= 0
     * @return int depth, which is how many elements are in the array.
     */
    public int size()
    {
        return depth;
    }//return the depth of the array
}
