/* Jeremy Holloway / Damion Anderson
 * CPSC-2150-001
 * Lab 08
 * 10/26/2018
 */
package cpsc2150.Speedster;

public class DirectionalSpeedster implements IDirectionalSpeedster{

    private double totalDistance;
    private double totalTime;
    private double avgSpeed;
    private double curXPos;
    private double curYPos;

    public DirectionalSpeedster(){
        curXPos = 0;
        curYPos = 0;
        totalDistance = 0;
        totalTime = 0;
        avgSpeed = 0;
    }

    public void addTravel(double xChange, double yChange, double time) {
        totalDistance += Math.sqrt(Math.pow((xChange - curXPos), 2) + Math.pow((yChange - curYPos), 2));
        totalTime += time;
        curXPos = xChange;
        curYPos = yChange;
        if (xChange == 0 && yChange == 0) {
            avgSpeed = 0;
        } else {
            avgSpeed = totalDistance / totalTime;
        }
    }

    public double getTotalDistance(){
        return totalDistance;
    }

    public double getTotalTime(){
        return totalTime;
    }

    public double getAverageSpeed(){
        return avgSpeed;
    }

    public double getCurXPos(){
        return curXPos;
    }

    public double getCurYPos(){
        return curYPos;
    }
}
