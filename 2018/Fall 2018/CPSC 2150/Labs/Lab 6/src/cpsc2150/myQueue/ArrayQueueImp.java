/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 06
 * 10/12/2018
 */
package cpsc2150.myQueue;

/**
 * Correspondence: this = myQ[0...depth-1], myQ[0] is the front
 * of the Queue
 * Correspondence: size = depth
 *
 * @invariant: 0 <= depth <= MAX_DEPTH
 */
public class ArrayQueueImp<T> implements IQueue<T>
{
    private T myQ[];
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
        myQ = (T[]) new Object[MAX_DEPTH];//create memory space for the array
        depth = 0;//set depth to number of current items
    }

    public void add(T x)
    {
        myQ[depth] = x;//input the value
        depth++;//increase number of items in the list
    }

    public T pop()
    {
        T temp = myQ[0];//set the first element to a temporary Integer
        //loop will resize the list
        for (int i = 0; i < depth; i++) {
            if (myQ[i + 1] != null)
                myQ[i] = myQ[i + 1];//move each item up one space
        }
        depth--;
        return temp;
    }

    public int size()
    {
        return depth;
    }//return the depth of the array

    /**
     * Function: toString()
     * Description: This function will format the queue print out
     * Input: none
     * Output: Formatted string for printing
     * @invariant user must choose implementation before calling this function
     * @pre none
     * @post all array items are added into print string
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
        for (int a = 0; a < size(); a++)//loop through the array
        {
            queue += myQ[a] + "\n";//add each array item plus the new line char
        }
        return queue;//return the string
    }
}
