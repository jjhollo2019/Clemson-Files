/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 06
 * 10/12/2018
 */
package cpsc2150.myQueue;

/**
 * Initialization ensures:
 *  The queue is instantiated
 * Defines:
 *  depth: 0
 * Constraints:
 *  0 <= size <= MAX_DEPTH
 */
public interface IQueue<T>
{
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
    public void add(T x);

    /**
     * Function: pop()
     * Description: This function is the pop method for the interface
     * Input: none
     * Output: Integer at the front of the queue
     * @pre Queue must have at least one data member before being used.
     * @post Queue size is one less after each call
     * @return returns the number of integers in the queue.
     */
    public T pop();

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

    /**
     * Function: peek()
     * Description: This function will show the value of the first item in the Queue
     * Input: none
     * Output: Integer value of the first item
     * @invariant object is initialized
     * @pre the user must choose implementation before calling this function
     * @post no values were changed
     * @return the returned item is the first item value
     */
    default public T peek()
    {
        T r = null;//initialize return int
        T y;//declare iteration int
        for (int i = 0; i < size(); i++)//loop through the structure
        {
            if (i == 0)//catch the first element for peek
            {
                y = pop();//pop the value
                r = y;//set the return to the first element
                add(y);//put element back in the queue
            }
            else//otherwise continue through the queue
            {
                y = pop();//pop the element
                add(y);//add it back in
            }
        }
        return r;//return the first element
    }

    /**
     * Function: endOfQueue()
     * Description: This function will run through the data structure to find the last element
     * Input: none
     * Output: Integer value for last list item
     * @invariant object is initialized
     * @pre the user must choose implementation before calling this function
     * @post no values were changed
     * @return the returned value is the last item in the structure
     */
    default T endOfQueue()
    {
        T r = null;//initialize return value
        T y;//declare iteration int
        for (int i = 0; i < size(); i++) {//loop through the structure
            if (i == (size() - 1)) {//catch last element
                y = pop();//pop the value out of the queue
                r = y;//set return to the last element
                add(y);//add the popped element back in
            } else {//otherwise continue through the queue
                y = pop();//pop element
                add(y);//add it back into the queue
            }
        }
        return r;
    }

    /**
     * Function: insert(Integer x, int pos)
     * Description: This function will insert a value at a user specified position
     * Input:
     *  Integer x is the value to be inserted
     *  int pos is the position for the item to be inserted
     * Output:
     *  Integer value for the last item in the queue
     * @param x can be any number
     * @param pos must be a valid position in the data structure
     * @invariant object is initialized
     * @pre the user must choose implementation before calling this function, user must verify correct input
     * @post value is inserted to the user specified position
     */
    default void insert(T x, int pos)
    {
        T y;//declare iteration int
        if (pos == (size() + 1)){
            add(x);
        }
        else {
            for (int i = 0; i < size(); i++)//loop through the queue
            {
                if (i == (pos - 1)) {//find the position of the value
                    add(x);//all the value into the queue
                } else {//otherwise iterate through the queue
                    y = pop();//pop the value
                    add(y);//add it back into the queue
                }
            }
        }
    }

    /**
     * Function: remove(int pos)
     * Description: This function will remove a value at a user specified position
     * Input:
     *  int pos is the user specified position
     * Output: none
     * @param pos must be a valid position in the data structure
     * @invariant object is initialized and contains at least one item
     * @pre user must choose implementation before calling this function, user must verify input
     * @post value at user specified position is removed from the structure
     * @return is the value removed from the queue
     */
    default T remove(int pos)
    {
        T m = null;//initialize return value
        T y;//declare iteration int
        if (pos == 1)
        {
            m = pop();
            return m;
        }
        if (pos < size())
        {
            for (int i = 0; i < size(); i++)//loop through the queue
            {
                if (i == (pos - 1)) {//catch the desired element
                    m = pop();//pop the value from the queue to remove it
                }
                    y = pop();//pop the value from the queue
                    add(y);//add it back in
            }
            return m;//return the removed value
        }
        else {
            for (int i = 0; i < size(); i++)//loop through the queue
            {
                if (i == (pos - 1)) {//catch the desired element
                    m = pop();//pop the value from the queue to remove it
                    i++;//increment the loop counter
                } else {
                    y = pop();//pop the value from the queue
                    add(y);//add it back in
                }
            }
            return m;//return the removed value
        }
    }

    /**
     * Function: get(int pos)
     * Description: This function will return the value held at the user specified position
     * Input:
     *  int pos is the user specified position
     * Output:
     *  Integer value of the position in the data structure
     * @param pos must be a valid position in the structure
     * @pre user must choose implementation before calling this function, user must verify input
     * @post no values will be changed
     * @return is the data value at that position
     */
    default public T get(int pos)
    {
        T r = null;//initialize the return value
        T y;//declare iteration int
        for (int i = 0; i < size(); i++) {//loop through the queue
            if (i == (pos - 1)) {//catch the desired position
                y = pop();//pop the value
                r = y;//set the return equal to the popped value
                add(y);//add it back into the queue
            } else {//otherwise iterate through the queue
                y = pop();//pop the value
                add(y);//add it back into the queue
            }
        }
        return r;//return the position value
    }
}