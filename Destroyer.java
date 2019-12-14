
/**
 *  The Destroyer is a large sized ship with two movements, two health
 *  points, and two movements.
 *
 *  @author  Daniel Huang
 *  @version May 28, 2019
 *  @author  Period: 5
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: 5
 */
public class Destroyer implements Ship {
    
    private final int range = 2;
    private final int length = 2;
    private int HP = 2;
    private Location[] shiploc;
    
    /**
     * Constructs a Destroyer object based on one location. Builds forward from
     * where that location is.
     * 
     * @param loc location to start creation
     */
    public Destroyer(Location loc) {
       
        int x = loc.getX() + 1;
        shiploc = new Location[length];
        Location p2 = new Location(x, loc.getY());
        shiploc[0] = loc;
        shiploc[1] = p2;
    }
    
    /**
     * Moves the Destroyer forward by incrementing its X-value.
     */
    public void moveForward() {
        
        for(int i = 0; i < length; i++) {
            shiploc[i].incrementX();
        }
    }
    
    /**
     * Moves the Destroyer forward by decrementing its X-value.
     */
    public void moveBackward() {
        for(int i = 0; i < length; i++) {
            shiploc[i].decrementX();
        }
    }
    
    /**
     * Moves the Destroyer forward by incrementing its Y-value.
     */
    public void moveRight() {
        for (int i = 0; i < length; i++) {
            shiploc[i].incrementY();
        }
    }
    
    /**
     * Moves the Destroyer forward by decrementing its Y-value.
     */
    public void moveLeft() {
        for (int i = 0; i < length; i++) {
            shiploc[i].decrementY();
        }
    }
    
    /**
     * Returns the effective health of Destroyer
     * @return health of Destroyer
     */
    public int health() {
        return HP;
    }
    
    /**
     * decreases the health of the ship by 1 after it is hit.
     */
    public void gotHit() {
        HP--;
    }
    
    /**
     *Gets the locations that the Destroyer is in
     * @return array of Location objects
     */
    public Location[] getLocations() {
        return shiploc;
    }
    
    /**
     * Gets the attacking range for the Destroyer
     * @return range 
     */
    public int range() {
        return range;
    }
    
    /**
     * Gets the length of the Destroyer.
     * @return length
     */
    public int getLength() {
        return length;
    }
}
