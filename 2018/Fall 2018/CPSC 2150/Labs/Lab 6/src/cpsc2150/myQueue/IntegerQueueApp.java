/* Jeremy Holloway / Damion Anderson
 * CPSC-2151-001
 * Lab 06
 * 10/12/2018
 */
package cpsc2150.myQueue;

import java.util.*;

public class IntegerQueueApp{

    private static Scanner in;

    public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.println("Enter 1 for array implementation or 2 for List implementation");
        int answer = Integer.parseInt(in.nextLine());
        IQueue<Integer> q;
        if(answer == 1) {
            q = new ArrayQueueImp<>();
        }
        else
        {
            q = new ListQueueImp<>();
        }
        displayMenu();
        answer = Integer.parseInt(in.nextLine());
        while(answer != 8)
        {
            if(answer == 1)
            {
                addToQueue(q);
            }
            else if(answer == 2)
            {
                getNext(q);
            }
            else if(answer == 3)
            {
                peekInQueue(q);
            }
            else if(answer == 4)
            {
                peekAtEnd(q);
            }
            else if(answer == 5)
            {
                insertInQueue(q);
            }
            else if(answer == 6)
            {
                getFromQueue(q);
            }
            else if(answer == 7)
            {
                removeFromQueue(q);
            }
            else
            {
                System.out.println("Not a valid option!");
            }
            System.out.println("Queue is: ");
            System.out.println(q.toString());
            System.out.println(" ");
            displayMenu();
            answer = Integer.parseInt(in.nextLine());
        }
    }
    private static void displayMenu()
    {
        System.out.println("Select an option: ");
        System.out.println("1. Add to the Queue");
        System.out.println("2. Get next number from the Queue");
        System.out.println("3. Peek at the front of the Queue");
        System.out.println("4. Peek at the end of the Queue");
        System.out.println("5. Insert in the Queue");
        System.out.println("6. Get a position in the Queue");
        System.out.println("7. Remove from a position in the Queue");
        System.out.println("8. Quit");
    }

    private static void peekInQueue(IQueue<Integer> x)
    {
        if (x.size() == 0)
        {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println("Peek: " + x.peek());
    }

    private static void getNext(IQueue<Integer> x)
    {
        if (x.size() == 0)
        {
            System.out.println("Queue is empty!");
            return;
        }
        else
            System.out.println("Next number is " + x.remove(1));
    }

    private static void addToQueue(IQueue<Integer> x)
    {
        System.out.println("What number to add to the Queue?");
        int adder = Integer.parseInt(in.nextLine());
        x.add(adder);
    }

    private static void peekAtEnd(IQueue<Integer> x)
    {
        if (x.size() == 0){
            System.out.println("Queue is empty!");
            return;
        }
        else
        System.out.println("Peek at the end: " + x.endOfQueue());
    }

    private static void insertInQueue(IQueue<Integer> x)
    {
        Integer num;
        Integer pos;
        System.out.println("What number to add to the Queue?");
        num = Integer.parseInt(in.nextLine());
        System.out.println("What position to insert in?");
        pos = Integer.parseInt(in.nextLine());
        while (pos < 1 || pos > (x.size() + 1))
        {
            System.out.println("Not a valid position in the Queue!");
            System.out.println("What position to insert in?");
            pos = Integer.parseInt(in.nextLine());
        }
        x.insert(num, pos);
    }

    private static void getFromQueue(IQueue<Integer> x)
    {
        Integer pos;
        if (x.size() == 0)
        {
            System.out.println("The Queue is empty!");
            return;
        }
        System.out.println("What position to get from the Queue?");
        pos = Integer.parseInt(in.nextLine());
        while (pos < 1 || pos > x.size())
        {
            System.out.println("Not a valid position in the Queue!");
            System.out.println("What position to get from the Queue?");
            pos = Integer.parseInt(in.nextLine());
        }
        System.out.println(x.get(pos) + " is at position " + pos + " in the queue");
    }

    private static void removeFromQueue(IQueue<Integer> x)
    {
        Integer pos;
        if (x.size() == 0)
        {
            System.out.println("The Queue is empty!");
            return;
        }
        System.out.println("What position to remove from the Queue?");
        pos = Integer.parseInt(in.nextLine());
        while (pos < 1 || pos > x.size())
        {
            System.out.println("Not a valid position in the Queue!");
            System.out.println("What position to remove from the Queue?");
            pos = Integer.parseInt(in.nextLine());
        }
        System.out.println(x.remove(pos) + " was at position " + pos + " int the queue");
    }
}