
/**
 *  The ship interface specifies the methods needed for ship movement and
 *  attacking.
 *
 *  @author  Daniel Huang
 *  @version May 27, 2019
 *  @author  Period: 5
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: none
 */
public interface Ship
{
    
    /**
     * Moves the ship forward by incrementing its X-value.
     */
    void moveForward();
    
    /**
     * Moves the ship backward by decrementing its X-value
     */
    void moveBackward();
    
    /**
     * Moves the ship right by incrementing its Y-value.
     */
    void moveRight();
    
    /**
     * Moves the ship left by decrementing its Y-value.
     */
    void moveLeft();
    
    /**
     * Returns the effective health of ship
     * @return health ship
     */
    int health();
    
    /**
     * decreases the health of the ship by 1 after it is hit.
     */
    void gotHit();
    
    /**
     *Gets the locations that the ship is in
     * @return array of Location objects    
     */
    Location[] getLocations();

    /**
     * Gets the attacking range for the ship
     * @return range 
     */
    int range();
    
    /**
     * Gets the length of the ship.
     * @return length
     */
    int getLength();
}
