
/**
 *  Location class with X and Y coordinates. Location objects are used by ships
 *  for their location and numerically associated with GUI JButtons.
 *
 *  @author  Daniel Huang
 *  @version May 27, 2019
 *  @author  Period: 5
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: none
 */
public class Location
{
    private int xValue;
    private int yValue;
    private static final int xMax = 12;
    private static final int yMax = 12;
    
    /**
     * constructor for a Location Object
     * 
     * @param x int value of x coordinate
     * @param y int value of y coordinate
     */
    public Location(int x, int y) {
        /*if (x > 12 || y > 12)
        {
            throw new 
        }*/
        xValue = x;
        yValue = y;
    }
    
    
    /**
     * Gets the x-value of a location object
     * @return int x
     */
    public int getX()
    {
        return xValue;
    }
    
    /**
     * Gets the y-value of a location object
     * @return int y
     */
    public int getY()
    {
        return yValue;
    }
    
    /**
     * sets the x-value of a location object to a number
     * @param num new x-value
     */
    public void setX(int num)
    {
        xValue = num;    }
    
    /**
     * set the y-value of a location object to a number
     * @param num new y-value
     */
    public void setY(int num)
    {
        yValue = num;
    }
    
    /**
     * increases the x-value by 1
     */
    public void incrementX()
    {
        xValue++;
    }
    
    /**
     * increases the y-value by 1
     */
    public void incrementY()
    {
        yValue++;
    }
    
    /**
     * decreases the x-value by 1
     */
    public void decrementX()
    {
        xValue--;
    }
    
    /**
     * decreases the y-value by 1
     */
    public void decrementY()
    {
        yValue--;
    }
    
    /**
     * Returns the distance between to location objects. Distance is calculated
     * by adding the absolute values of the difference between the objects' 
     * x-values and y-values.
     * @param loc 2nd location to compare
     * @return distance between two location objects
     */
    public int getDistance(Location loc) {
        int xdist = Math.abs( xValue - loc.getX() );
        int ydist = Math.abs( yValue - loc.getY() );
        return xdist + ydist;
    }
    
    /**
     * Equals method for location. Locations are equal if their x-value and 
     * y-value are the same number.
     * @param loc 2nd location to compare
     * @return boolean true if equal or false if not equal
     */
    public boolean equals(Location loc) {
        return this.xValue == loc.getX() && this.yValue == loc.getY();
    }
}
