/* Jeremy Holloway / Damion Anderson
 * CPSC-2150-001
 * Lab 08
 * 10/26/2018
 */
package cpsc2150.Speedster;

import org.junit.Test;
import static org.junit.Assert.*;

public class DirectionalSpeedsterTest {

    //This test will check the basic functionality of the constructor
    @Test
    public void test_constructor(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        assertTrue(test.getCurXPos() == 0);
        assertTrue(test.getCurYPos() == 0);
        assertTrue(test.getAverageSpeed() == 0);
        assertTrue(test.getTotalDistance() == 0);
        assertTrue(test.getTotalTime() == 0);
    }

    //This test will make sure positive vertical changes are occurring
    @Test
    public void test_vertical_change_up(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0, 10, 5);
        assertTrue(test.getCurYPos() == 10);
    }

    //This test will check if negative vertical changes are occurring
    @Test
    public void test_vertical_down(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(1,-5,3);
        assertTrue(test.getCurYPos() == -5);
    }

    //this test will make sure time changes are occurring
    @Test
    public void test_adding_time(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,0, 5);
        assertTrue(test.getTotalTime() == 5);
    }

    //This test ensures decimal changes will occur
    @Test
    public void test_decimal_movement(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0.5,0.5,2);
        test.addTravel(1.6,1.9,4);
        test.addTravel(10.3,18.2,4);
        assertTrue(test.getCurXPos() == 10.3);
        assertTrue(test.getCurYPos() == 18.2);
    }

    //This test will check if I can addtravel to the starting position
    @Test
    public void test_travel_to_origin(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,0,0);
        assertTrue(test.getCurYPos() == 0);
        assertTrue(test.getCurXPos() == 0);
    }

    //This test will check if it can add travel that goes from right to left
    @Test
    public void test_right_to_left(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(2,1,3);
        test.addTravel(-2,1,3);
        assertTrue(test.getCurXPos() == -2);
        assertTrue(test.getCurYPos() == 1);
    }

    //This test will check if it can add travel that goes from right to left
    @Test
    public void test_left_to_right(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(-2,1,3);
        test.addTravel(2,1,3);
        assertTrue(test.getCurYPos() == 1);
        assertTrue(test.getCurXPos() == 2);
    }

    //This test will check if add travel is occurring in a negative diagonal direction
    @Test
    public void test_negative_diagonal(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(2,-5, 10);
        assertTrue(test.getCurXPos() == 2);
        assertTrue(test.getCurYPos() == -5);
    }

    //This test will check if add travel is occurring in a positive diagonal direction
    @Test
    public void test_positive_diagonal(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(4,5,5);
        assertTrue(test.getCurXPos() == 4);
        assertTrue(test.getCurYPos() == 5);
    }

    //This test is to check the normal functionality of test
    @Test
    public void test_normal_travel(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(2, 4, 3);
        assertTrue(test.getCurXPos() == 2);
        assertTrue(test.getCurYPos() == 4);
        assertTrue(test.getTotalTime() == 3);
    }

    //This test is to make sure calls to current position won't add distance
    @Test
    public void test_distance_to_origin(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,0,2);
        assertTrue(test.getTotalDistance() == 0);
    }

    //This test will check if distance calculations are occurring for negative directions
    @Test
    public void test_negative_direction(){
        double expected = 8.94427190999916;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(-4,-8, 3);
        assertEquals(expected, test.getTotalDistance(),0.00001);
    }

    //This test will check if distance calculations are occurring for positive directions
    @Test
    public void test_positive_direction(){
        double expected = 8.94427190999916;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(4,8,3);
        assertEquals(expected, test.getTotalDistance(), 0.000000001);
    }

    //This test will check if small distance calculations are occurring
    @Test
    public void test_small_distances(){
        double expected = 24.678322665996085;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0.5,0.5,4);
        test.addTravel(4.5,5.8, 5);
        test.addTravel(-8.1,-6.1,10);
        assertEquals(expected, test.getTotalDistance(), 0.000000001);
    }

    //This test is a multiple call normal use distance calculation check
    @Test
    public void test_total_distance() {
        double expected_distance = 16.194173437483137;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(3, 4, 3);
        test.addTravel(2,8,2);
        test.addTravel(1,1,4);
        assertTrue(test.getCurXPos() == 1);
        assertTrue(test.getCurYPos() == 1);
        assertEquals(expected_distance, test.getTotalDistance(), 0.0000000001);
        assertTrue(test.getTotalTime() == 9);
    }

    //This test is to make sure calls to current position won't add net distance
    @Test
    public void test_net_distance_to_origin(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,0,2);
        assertTrue(test.getNetDistance() == 0);
    }

    //This test will check if net distance calculations are occurring for negative directions
    @Test
    public void test_net_negative_direction(){
        double expected = 8.94427190999916;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(-4,-8, 3);
        assertEquals(expected, test.getNetDistance(),0.00001);
    }

    //This test will check if net distance calculations are occurring for positive directions
    @Test
    public void test_net_positive_direction(){
        double expected = 8.94427190999916;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(4,8,3);
        assertEquals(expected, test.getNetDistance(), 0.000000001);
    }

    //This test will check if small net distance calculations are occurring
    @Test
    public void test_net_small_distances(){
        double expected = 10.140019723846695;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0.5,0.5,4);
        test.addTravel(4.5,5.8, 5);
        test.addTravel(-8.1,-6.1,10);
        assertEquals(expected, test.getNetDistance(), 0.000000001);
    }

    //This test will add multiple travel points and check for a net calculation
    @Test
    public void test_net_distance(){
        double expected_distance = 1.4142135623730951;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(3, 4, 3);
        test.addTravel(2,8,2);
        test.addTravel(1,1,4);
        assertTrue(test.getCurXPos() == 1);
        assertTrue(test.getCurYPos() == 1);
        assertEquals(expected_distance, test.getNetDistance(), 0.0000000001);
        assertTrue(test.getTotalTime() == 9);
    }

    //This test will check if net horizontal speeds are calculated
    @Test
    public void test_net_horiz_speed(){
        double expected = 2;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(10,0,5);
        assertEquals(expected, test.getNetSpeed(), 0.0000001);
    }

    //This test will check if net vertical speeds are calculated
    @Test
    public void test_net_vert_speed(){
        double expected = 3;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,15,5);
        assertEquals(expected, test.getNetSpeed(), 0.000001);
    }

    //This test will check if decimal net speeds are calculated
    @Test
    public void test_net_small_speed(){
        double expected = 2.0829996149450745;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(10.4,7.3,6.1);
        assertEquals(expected, test.getNetSpeed(), 0.00001);
    }

    //This test will will check if a net speed calculation is made to the current position
    @Test
    public void test_net_no_move(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,0,0);
        assertTrue(test.getNetSpeed() == 0);
    }

    //This test will check the net speed calculation for multiple travel points
    @Test
    public void test_net_multipoint_speed(){
        double expected = 1.60210312325;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(4,8,9);
        test.addTravel(-3,5,4);
        test.addTravel(4,-8,5);
        test.addTravel(-2,-6,5.5);
        assertEquals(expected, test.getNetSpeed(), 0.000001);
    }

    //This test will check if horizontal speeds are calculated
    @Test
    public void test_avg_horiz_speed(){
        double expected = 2;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(10,0,5);
        assertEquals(expected, test.getAverageSpeed(), 0.0000001);
    }

    //This test will check if vertical speeds are calculated
    @Test
    public void test_avg_vert_speed(){
        double expected = 3;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,15,5);
        assertEquals(expected, test.getAverageSpeed(), 0.000001);
    }

    //This test will check if decimal speeds are calculated
    @Test
    public void test_avg_small_speed(){
        double expected = 2.0829996149450745;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(10.4,7.3,6.1);
        assertEquals(expected, test.getAverageSpeed(), 0.00001);
    }

    @Test
    public void test_avg_no_move(){
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(0,0,0);
        assertTrue(test.getAverageSpeed() == 0);
    }

    @Test
    public void test_avg_multipoint_speed(){
        double expected = 1.60210312325;
        DirectionalSpeedster test = new DirectionalSpeedster();
        test.addTravel(4,8,9);
        test.addTravel(-3,5,4);
        test.addTravel(4,-8,5);
        test.addTravel(-2,-6,5.5);
        assertEquals(expected, test.getAverageSpeed(), 0.000001);
    }
}
