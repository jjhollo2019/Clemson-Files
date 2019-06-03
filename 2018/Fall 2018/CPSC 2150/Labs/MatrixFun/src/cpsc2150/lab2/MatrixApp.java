/* Jeremy Holloway / Damion Anderson
 * CPSC-2150-001
 * 9/7/2018
 * Lab 02
 * This program will perform matrix operations on a user defined matrix
 */
package cpsc2150.lab2;

import java.util.Scanner;


public class MatrixApp {
    private int arr[][];
    private int row;
    private int column;

    /* Function: MatrixApp(int a, int b)
     * Description: This function is the default constructor for the MatrixApp class
     */
    public MatrixApp(int a, int b){
        arr = new int[a][b];            //create memory space for the array
        row = a;                        //set the row size to the user input
        column = b;                     //set the column size to the user input
    }

    /* Function: getMember(int a, int b)
     * Description: This function will return the user defined array member
     */
    public int getMember(int a, int b) {
        int Member = arr[a][b];         //declare an int and assign the target array member
        return Member;                  //return the value
    }

    /* Function: setMember(int a, int b, int c)
     * Description: This function sets an array value to a user defined input
     */
    public void setMember(int a, int b, int c) {
        arr[a][b] = c;                  //set array member to input
    }

    /* Function: transpose()
     * Description: This function will transpose the matrix by reversing the array
     * position and return a temporarily defined array
     */
    public int[][] transpose() {
        int temp[][];                   //define the temporary array
        temp = new int[column][row];    //set the array to the reverse sive of the original
        for(int a = 0; a < (row); a++){
            for(int b = 0; b < (column); b++){
                temp[b][a] = arr[a][b]; //set the temporary members to the reverse members of the user array
            }
        }
        return temp;                    //return temporary array
    }

    /* Function: rowProduct()
     * Description: This function will call the rowSum function to get an array of the row sums and
     * multiply each array member
     */
    public int rowProduct() {
        int product = 0;                //declare the product value
        int sum [];                     //declare the array for the rowSum function
        sum = this.rowSum();            //set the array equal to the rowSum array
        product = 1;                    //set the product equal to one
        for (int a = 0; a < row; a++) {
            product = product * sum[a]; //set the product equal to the product times the array member
        }
        return product;                 //return product

    }

    /* Function: rowAvg()
     * Description: This function will add all of the array members and divide by the array size
     */
    public double rowAvg() {
        double avg = 0;                 //set the avg function to zero
        int a, b;
        for (a = 0; a <= (row - 1); a++) {
            for (b = 0; b <= (column - 1); b++) {
                avg += arr[a][b];       //add each member to average
            }
        }
        avg = avg / (double) (row * column); //divide the sum by the array size
        return avg;                     //return the avg value
    }

    /* Function: rowSum()
     * Description: This function will return an array of sums for each row
     */
    public int[] rowSum() {
        int sum[];                      //declare the sum array
        sum = new int[row];             //set the array to the size of the total rows
        for (int a = 0; a < row; a++){
            for (int b = 0; b < column; b++){
                sum[a] += arr[a][b];    //add each member value to the corresponding sum member
            }
        }
        return sum;                     //return sum array
    }

    /* Function: colSum()
     * Description: This function will create an array of column sums for the array
     */
    public int[] colSum() {
        int sum[];                      //declare the sum array
        sum = new int[column];          //set the array size to the total number of columns
        for (int a = 0; a < column; a++) {
            for (int b = 0; b < row; b++) {
                sum[a] += arr[b][a];    //set the corresponding sum member to the column sum
            }
        }
        return sum;                     //return the sum array
    }

    /* Function: main(String[] args)
     * Description: This function will call all of the functions and handle all user input
     * and printing to the screen
     */
    public static void main(String[] args) {
        int rows;
        int columns;
        int entry;
        boolean input1 = false;
        boolean input2 = false;

        Scanner keyboard = new Scanner(System.in);

        System.out.println("How many rows should your matrix have?");
        rows = Integer.parseInt(keyboard.nextLine());   //set rows to the user input
        while (input1 == false){        //while loop will verify the user input
            if(rows > 1 && rows <=10){  //checks to make sure the input is between 1 and 10
                input1 = true;          //set the boolean value to true to breakout of the loop
            }
            else{                       //else loop will display and error message and ask for new input
                System.out.println("Error: Please enter a number 1-10");
                System.out.println("How many rows should your matrix have?");
                rows = Integer.parseInt(keyboard.nextLine());   //set rows to the user input
            }
        }
        System.out.println("How many columns should your matrix have?");
        columns = Integer.parseInt(keyboard.nextLine()); //set columns to the user input
        while(input2 == false){         //while loop will verify the user input
            if (columns > 1 && columns <= 10) { //checks to make sure the input is between 1 and 10
                input2 = true;          //set the boolean value to true to breakout of the loop
            }
            else {                      //else loop will display and error message and ask for new input
                System.out.println("Error: Please enter a number 1-10");
                System.out.println("How many columns should your matrix have?");
                columns = Integer.parseInt(keyboard.nextLine()); //set columns to the user input
            }
        }
        MatrixApp m =  new MatrixApp(rows,columns); //declare a new object of MatrixApp
        int a, b;
        //these loops will ask for user input and input the values to the array
        for(a = 0; a <= (rows - 1); a++){
            for(b = 0; b <= (columns - 1); b++){
                System.out.println("What number should go in Row: " + a + " Col: " + b);
                entry = Integer.parseInt(keyboard.nextLine());  //ask for user input
                m.setMember(a, b, entry);                       //set array member to the input
            }
        }
        System.out.println("Your matrix is:");
        //this loop will print the user defined matrix
        for(a = 0; a <= (rows - 1); a++){
            for(b = 0; b <= (columns - 1); b++){
                System.out.print("|" + m.getMember(a, b)); //call the getMember function
                if((columns - 1) == b){
                    System.out.println("|");    //print an end line for the array
                }
            }
        }
        System.out.println(" ");
        System.out.println("The transposed matrix is:");
        m.transpose();  //call the transpose function
        int[][] trans;  //declare an array for the transpose function
        trans = m.transpose();  //set the array to the transpose
        for(a = 0; a < columns; a++) {
            for(b = 0; b < rows; b++) {
                System.out.print("|" + trans[a][b]); //print the transpose matrix
                if(b == (rows - 1)) {
                    System.out.println("|"); //print an end line for the array
                }
            }
        }
        System.out.println(" ");
        System.out.println("The product sum is:");
        System.out.println(m.rowProduct()); //print the return value for the rowProduct function
        System.out.println("The average is:");
        System.out.println(m.rowAvg());     //print the return value for the rowAvg function
        System.out.println("The sums of each Row are:");
        int rowSum[];                       //declare a sum array
        rowSum = m.rowSum();                //set the array to the return value of rowSum
        //this array will print the array returned by the rowSum function
        for(a = 0; a <= (rows - 1); a++){
            System.out.print("|" + rowSum[a]); //print the rowSum array
            if(a == (rows - 1)){
                System.out.println("|");       //print an end line for the array
            }
        }
        System.out.println("The sums of each Column are:");
        int colSum[];                       //declare a column sum array
        colSum = m.colSum();                //set the colSum array to the return array
        for(a = 0; a <= (columns - 1); a++){
            System.out.print("|" + colSum[a]);  //print the colSum array
            if(a == (columns - 1)){
                System.out.println("|");    //print the end line for the array
            }
        }
    }
}