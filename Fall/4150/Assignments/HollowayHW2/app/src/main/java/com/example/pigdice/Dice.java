/* Jeremy Holloway
 * CPSC-4150-001
 * 9/24/2019
 * HW2
 * C20581376
 * jjhollo@g.clemson.edu
 */
package com.example.pigdice;

import java.util.Random;

//This class borrowed code from the zybook dice roller game
public class Dice {

    //static bound integers
    private static int LARGEST_NUM = 6;
    private static int SMALLEST_NUM = 1;

    //private variables for rolling
    private int mNumber = SMALLEST_NUM;
    private int mImageId;
    private Random mRandomGenerator;

    /**
     * This function is the class constructor
     * @pre LARGEST_NUM && SMALLEST_NUM must be set to a value
     * @post a Dice instance is created
     * @param number = the starting dice value
     */
    public Dice(int number) {
        setNumber(number);
        mRandomGenerator = new Random();
    }

    /**
     * This function returns the dice integer value
     * @pre Dice instance must be created
     * @post mNumber is not changed
     * @return the dice value is returned as an integer
     */
    public int getNumber() {
        return mNumber;
    }

    /**
     * This function sets the dice value and image
     * @pre class must be instantiated
     * @post mNumber is set to number and the image matches number
     * @param number = the new dice number to set
     */
    private void setNumber(int number) {
        if(number >= SMALLEST_NUM && number <= LARGEST_NUM) {
            //set mNumber
            mNumber= number;
            //switch on image
            switch (number) {
                case 1:
                    mImageId = R.drawable.dice_1;
                    break;

                case 2:
                    mImageId = R.drawable.dice_2;
                    break;

                case 3:
                    mImageId = R.drawable.dice_3;
                    break;

                case 4:
                    mImageId = R.drawable.dice_4;
                    break;

                case 5:
                    mImageId = R.drawable.dice_5;
                    break;

                case 6:
                    mImageId = R.drawable.dice_6;
                    break;
            }
        }
    }

    /**
     * This function will return the image integer value
     * @pre class must be instantiated
     * @post mImageID is unchanged
     * @return mImageID is returned
     */
    public int getImageId() {
        return mImageId;
    }

    /**
     * This function picks a random number in the bounds of 1 and 6
     * @pre class must be instantiated && LARGEST_NUM must be set
     * @post the randomly selected number is passed to setNumber
     */
    public void roll(){
        setNumber(mRandomGenerator.nextInt(LARGEST_NUM) + 1);
    }
}
