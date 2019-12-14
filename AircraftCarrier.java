
/**
 *  The AircraftCarrier is a large sized ship with four movements, four health
 *  points, and four movements.
 *
 *  @author  Daniel Huang
 *  @version May 27, 2019
 *  @author  Period: 5
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: none
 */
public class AircraftCarrier implements Ship {
    
    private static final int range = 4;
    private final int length = 4;
    private int HP = 4;
    private Location[] shiploc;
    
    /**
     * Constructs a AircraftCarrier object based on one location. Builds forward\
     * from where that location is.
     * 
     * @param loc location to start creation
     */
    public AircraftCarrier(Location loc) {
        int x = loc.getX();
        Location p2 = new Location(x + 1, loc.getY());
        Location p3 = new Location(x + 2, loc.getY());
        Location p4 = new Location(x + 3, loc.getY());
        shiploc = new Location[length];
        shiploc[0] = loc;
        shiploc[1] = p2;
        shiploc[2] = p3;
        shiploc[3] = p4;
        
    }
    
    /**
     * Moves the ship forward by incrementing its X-value.
     */
    public void moveForward() {
        
        for(int i = 0; i < length; i++) {
            shiploc[i].incrementX();
        }
    }
    
    /**
     * Moves the ship backward by decrementing its X-value.
     */
    public void moveBackward() {
        for(int i = 0; i < length; i++) {
            shiploc[i].decrementX();
        }
    }
    
    /**
     * Moves the ship right by incrementing its Y-value.
     */
    public void moveRight() {
        for (int i = 0; i < length; i++) {
            shiploc[i].incrementY();
        }
    }
    
    /**
     * Moves the ship left by decrementing its Y-value.
     */
    public void moveLeft() {
        for (int i = 0; i < length; i++) {
            shiploc[i].decrementY();
        }
    }
    
    /**
     * Returns the effective health of AircraftCarrier
     * @return health of AircraftCarrier
     */
    public int health() {
        return HP;
    }
    
    /**
     * decreases the health of the AircraftCarrier by 1 after it is hit.
     */
    public void gotHit() {
        HP--;
    }
    
    /**
     *Gets the locations that the AircraftCarrier is in
     * @return array of Location objects
     */
    public Location[] getLocations() {
        return shiploc;
    }
    
    /**
     * Gets the attacking range for the ship
     * @return range 
     */
    public int range() {
        return range;
    }
    
    /**
     * Gets the length of the ship.
     * @return length
     */
    public int getLength() {
        return length;
    }
}
