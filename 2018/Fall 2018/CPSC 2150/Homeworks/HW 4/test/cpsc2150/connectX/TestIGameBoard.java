/* Jeremy Holloway
 * CPSC-2150-001
 * Homework #4
 * 11/18/2018
 */
package cpsc2150.connectX;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestIGameBoard {


    private IGameBoard factory(int r, int c, int w){
        IGameBoard testGame = new GameBoardMem(r, c, w);
        return testGame;
    }

    public String toString(char Arr[][]){
        String board = "|";
        for (int a = 0; a < Arr[0].length; a++){
            if (a < 10){
                board += " " + a + "|";
            }
            else
                board += a + "|";
        }
        board += "\n";
        for (int a = Arr.length -1; a >= 0; a--) {
            for (int b = 0; b < Arr[0].length; b++) {
                board += ("|" + Arr[a][b] + ' ');
            }
            board += ("|\n");//add closing end bracket and new line char
        }
        return board;//return the finished string
    }

    @Test
    public void test_min_board(){
        IGameBoard test = factory(3,3,3);
        assertEquals(test.getNumColumns(),3,0.01);
        assertEquals(test.getNumRows(),3,0.01);
        assertEquals(test.getNumToWin(),3,0.01);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        assertEquals(test.toString(), toString(expected));
    }

    @Test
    public void test_max_board(){
        IGameBoard test = factory(100,100,25);
        assertEquals(test.getNumColumns(),100,0.01);
        assertEquals(test.getNumRows(),100, 0.01);
        assertEquals(test.getNumToWin(),25,0.01);
        char expected[][] = new char[100][100];
        for(int row = 0; row < 100; row++){
            for(int col = 0; col < 100; col++){
                expected[row][col] = ' ';
            }
        }
        assertEquals(test.toString(), toString(expected));
    }

    @Test
    public void test_nonsquare_board(){
        IGameBoard test = factory(45,54, 6);
        assertEquals(test.getNumColumns(),54, 0.01);
        assertEquals(test.getNumRows(),45, 0.01);
        char expected[][] = new char[45][54];
        for(int row = 0; row < 45; row++){
            for(int col = 0; col < 54; col++){
                expected[row][col] = ' ';
            }
        }
        assertEquals(test.toString(), toString(expected));
    }

    @Test
    public void test_checkfree_empty(){
        IGameBoard test = factory(3,3,3);
        assertTrue(test.checkIfFree(0));
        assertTrue(test.checkIfFree(1));
        assertTrue(test.checkIfFree(2));
    }

    @Test
    public void test_full_column(){
        IGameBoard test = factory(3,3,3);
        test.placeToken('x',1);
        test.placeToken('o', 1);
        test.placeToken('x',1);
        assertTrue(!test.checkIfFree(1));
    }

    @Test
    public void test_full_board(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        test.placeToken('o', 0);
        test.placeToken('x',0);
        assertTrue(!test.checkIfFree(0));
        test.placeToken('x',1);
        test.placeToken('o', 1);
        test.placeToken('x',1);
        assertTrue(!test.checkIfFree(1));
        test.placeToken('x',2);
        test.placeToken('o', 2);
        test.placeToken('x',2);
        assertTrue(!test.checkIfFree(2));
    }

    @Test
    public void test_leftH_win(){
        IGameBoard test = factory(4,4,3);
        char expected[][] = new char[4][4];
        for(int row = 0; row < 4; row++){
            for(int col = 0; col < 4; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(test.checkHorizWin(0,0,'x'));
    }

    @Test
    public void test_rightH_win(){
        IGameBoard test = factory(4,4,3);
        char expected[][] = new char[4][4];
        for(int row = 0; row < 4; row++){
            for(int col = 0; col < 4; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(test.checkHorizWin(0,1,'x'));
    }

    @Test
    public void test_midH_win(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(test.checkHorizWin(0,1,'x'));
    }

    @Test
    public void test_normH_win(){
        IGameBoard test = factory(5,5,4);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('o',4);
        expected[0][4] = 'o';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('o',2);
        expected[0][2] = 'o';
        test.placeToken('x',0);
        expected[1][0] = 'x';
        test.placeToken('o',4);
        expected[1][4] = 'o';
        test.placeToken('x',1);
        expected[1][1] = 'x';
        test.placeToken('o', 4);
        expected[2][4] = 'o';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('o',0);
        expected[2][0] = 'o';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        test.placeToken('o',1);
        expected[2][1] = 'o';
        test.placeToken('x',3);
        expected[1][3] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(test.checkHorizWin(0,1,'x'));
    }

    @Test
    public void test_noH_win(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(!test.checkHorizWin(0,0,'x'));
    }

    @Test
    public void test_leftV(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',0);
        expected[1][0] = 'x';
        test.placeToken('x',0);
        expected[2][0] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(test.checkVertWin(0,0,'x'));
    }

    @Test
    public void test_rightV(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(test.checkVertWin(0,2,'x'));
    }

    @Test
    public void test_noWinV(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        assertEquals(test.toString(),toString(expected));
        assertTrue(!test.checkVertWin(0,2,'x'));
    }

    @Test
    public void test_noWinV_XandO(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('o',2);
        expected[1][2] = 'o';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(!test.checkVertWin(0,2,'x'));
    }

    @Test
    public void test_normV_win(){
        IGameBoard test = factory(5,5,4);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('o',4);
        expected[0][4] = 'o';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('o',2);
        expected[0][2] = 'o';
        test.placeToken('x',0);
        expected[1][0] = 'x';
        test.placeToken('o',4);
        expected[1][4] = 'o';
        test.placeToken('x',1);
        expected[1][1] = 'x';
        test.placeToken('o', 4);
        expected[2][4] = 'o';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('o',0);
        expected[2][0] = 'o';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        test.placeToken('o',1);
        expected[2][1] = 'o';
        test.placeToken('x',3);
        expected[1][3] = 'x';
        test.placeToken('o',4);
        expected[3][4] = 'o';
        assertEquals(test.toString(),toString(expected));
        assertTrue(test.checkVertWin(0,4,'o'));
    }

    @Test
    public void test_diag_down_left(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',1);
        expected[1][1] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertTrue(test.checkDiagWin(0,0,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_noWin_diag_down_left(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('o',0);
        expected[0][0] = 'o';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',1);
        expected[1][1] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertTrue(!test.checkDiagWin(0,0,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_diag_down_right(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',4);
        expected[0][4] = 'x';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        test.placeToken('x',3);
        expected[1][3] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertTrue(test.checkDiagWin(0,4,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_noWin_diag_down_right(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('o',4);
        expected[0][4] = 'o';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        test.placeToken('x',3);
        expected[1][3] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertTrue(!test.checkDiagWin(0,4,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_diag_up_right(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',4);
        expected[0][4] = 'x';
        test.placeToken('x',4);
        expected[1][4] = 'x';
        test.placeToken('x',4);
        expected[2][4] = 'x';
        test.placeToken('x',4);
        expected[3][4] = 'x';
        test.placeToken('x',4);
        expected[4][4] = 'x';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        test.placeToken('x',3);
        expected[1][3] = 'x';
        test.placeToken('x',3);
        expected[2][3] = 'x';
        test.placeToken('x',3);
        expected[3][3] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertTrue(test.checkDiagWin(4,4,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_noWin_diag_up_right(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',4);
        expected[0][4] = 'x';
        test.placeToken('x',4);
        expected[1][4] = 'x';
        test.placeToken('x',4);
        expected[2][4] = 'x';
        test.placeToken('x',4);
        expected[3][4] = 'x';
        test.placeToken('x',4);
        expected[4][4] = 'x';
        test.placeToken('x',3);
        expected[0][3] = 'x';
        test.placeToken('x',3);
        expected[1][3] = 'x';
        test.placeToken('x',3);
        expected[2][3] = 'x';
        test.placeToken('x',3);
        expected[3][3] = 'x';
        test.placeToken('o',2);
        expected[0][2] = 'o';
        test.placeToken('o',2);
        expected[1][2] = 'o';
        test.placeToken('o',2);
        expected[2][2] = 'o';
        assertTrue(!test.checkDiagWin(3,2,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_diag_up_left(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',0);
        expected[1][0] = 'x';
        test.placeToken('x',0);
        expected[2][0] = 'x';
        test.placeToken('x',0);
        expected[3][0] = 'x';
        test.placeToken('x',0);
        expected[4][0] = 'x';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',1);
        expected[1][1] = 'x';
        test.placeToken('x',1);
        expected[2][1] = 'x';
        test.placeToken('x',1);
        expected[3][1] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        test.placeToken('x',2);
        expected[1][2] = 'x';
        test.placeToken('x',2);
        expected[2][2] = 'x';
        assertTrue(test.checkDiagWin(4,0,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_noWin_diag_up_left(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',0);
        expected[1][0] = 'x';
        test.placeToken('x',0);
        expected[2][0] = 'x';
        test.placeToken('x',0);
        expected[3][0] = 'x';
        test.placeToken('x',0);
        expected[4][0] = 'x';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',1);
        expected[1][1] = 'x';
        test.placeToken('x',1);
        expected[2][1] = 'x';
        test.placeToken('x',1);
        expected[3][1] = 'x';
        test.placeToken('o',2);
        expected[0][2] = 'o';
        test.placeToken('o',2);
        expected[1][2] = 'o';
        test.placeToken('o',2);
        expected[2][2] = 'o';
        assertTrue(!test.checkDiagWin(4,0,'x'));
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_tie_empty(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        assertEquals(test.toString(),toString(expected));
        assertTrue(!test.checkTie());
    }

    @Test
    public void test_tie_horzW(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',2);
        expected[0][2] = 'x';
        assertEquals(test.toString(),toString(expected));
        assertTrue(!test.checkTie());
    }

    @Test
    public void test_tie_vertW() {
        IGameBoard test = factory(3, 3, 3);
        char expected[][] = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x', 0);
        expected[0][0] = 'x';
        test.placeToken('x', 0);
        expected[1][0] = 'x';
        test.placeToken('x', 0);
        expected[2][0] = 'x';
        assertEquals(test.toString(), toString(expected));
        assertTrue(!test.checkTie());
    }

    @Test
    public void test_tie() {
        IGameBoard test = factory(3, 3, 3);
        char expected[][] = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x', 0);
        expected[0][0] = 'x';
        test.placeToken('o', 1);
        expected[0][1] = 'o';
        test.placeToken('x', 2);
        expected[0][2] = 'x';
        test.placeToken('o', 0);
        expected[1][0] = 'o';
        test.placeToken('r', 1);
        expected[1][1] = 'r';
        test.placeToken('o', 2);
        expected[1][2] = 'o';
        test.placeToken('x', 0);
        expected[2][0] = 'x';
        test.placeToken('o', 1);
        expected[2][1] = 'o';
        test.placeToken('x', 2);
        expected[2][2] = 'x';
        assertEquals(test.toString(), toString(expected));
        assertTrue(test.checkTie());
    }

    @Test
    public void test_pos_empty(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                assertTrue(test.whatsAtPos(i,j) == ' ');
            }
        }
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_pos_left(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('r',0);
        expected[0][0] = 'r';
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_pos_right(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('r',2);
        expected[0][2] = 'r';
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_pos_full(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                test.placeToken('x',col);
                expected[row][col] = 'x';
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                assertTrue(test.whatsAtPos(i,j) == 'x');
            }
        }
        assertEquals(test.toString(),toString(expected));
    }

    //3 more whats at pos

    @Test
    public void test_pos_place_all(){
        IGameBoard test = factory(5,5,3);
        char expected[][] = new char[5][5];
        char place = 'a';
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = place;
                test.placeToken(place,col);
                place++;
            }
        }
        place = 'a';
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                assertTrue(test.whatsAtPos(i,j) == place);
                place++;
            }
        }
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_pos_left_uneven(){
        IGameBoard test = factory(3,5,3);
        char expected[][] = new char[3][5];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        assertTrue(test.whatsAtPos(0,0) == 'x');
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_pos_right_uneven(){
        IGameBoard test = factory(3,5,3);
        char expected[][] = new char[3][5];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 5; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',4);
        expected[0][4] = 'x';
        assertTrue(test.whatsAtPos(0,4) == 'x');
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_token_left(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_token_right(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',2);
        expected[0][2] = 'x';
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_token_middle(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',1);
        expected[0][1] = 'x';
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_token_two(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',1);
        expected[0][1] = 'x';
        test.placeToken('x',1);
        expected[1][1] = 'x';
        assertEquals(test.toString(),toString(expected));
    }

    @Test
    public void test_token_top_left(){
        IGameBoard test = factory(3,3,3);
        char expected[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                expected[row][col] = ' ';
            }
        }
        test.placeToken('x',0);
        expected[0][0] = 'x';
        test.placeToken('x',0);
        expected[1][0] = 'x';
        test.placeToken('x',0);
        expected[2][0] = 'x';
        assertEquals(test.toString(),toString(expected));
    }
}
