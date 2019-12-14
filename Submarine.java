
/**
 *  The Submarine is a medium sized ship with three movements, three health
 *  points, and three movements.
 *
 *  @author  Daniel Huang
 *  @version May 28, 2019
 *  @author  Period: 5
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: none
 */
public class Submarine implements Ship
{

    private final int range = 3;

    private final int length = 3;

    private int HP = 3;

    private Location[] shiploc;


    /**
     * Constructs a Submarine object based on one location. Builds forward from
     * where that location is.
     * 
     * @param loc location to start creation
     */
    public Submarine( Location loc )
    {

        int x = loc.getX();
        Location p2 = new Location( x + 1, loc.getY() );
        Location p3 = new Location( x + 2, loc.getY() );

        shiploc = new Location[length];
        shiploc[0] = loc;
        shiploc[1] = p2;
        shiploc[2] = p3;

    }


    /**
     * Moves the submarine forward by incrementing its X-value.
     */
    public void moveForward()
    {

        for ( int i = 0; i < length; i++ )
        {
            shiploc[i].incrementX();
        }
    }

    /**
     * Moves the ship backward by decrementing its X-value.
     */
    public void moveBackward()
    {
        for ( int i = 0; i < length; i++ )
        {
            shiploc[i].decrementX();
        }
    }

    /**
     * Moves the ship right by incrementing its Y-value.
     */
    public void moveRight()
    {
        for ( int i = 0; i < length; i++ )
        {
            shiploc[i].incrementY();
        }
    }

    /**
     * Moves the ship left by decrementing its Y-value.
     */
    public void moveLeft()
    {
        for ( int i = 0; i < length; i++ )
        {
            shiploc[i].decrementY();
        }
    }


    /**
     * Returns the effective health of submarine
     * @return health of submarine
     */
    public int health()
    {
        return HP;
    }

    /**
     * decreases the health of the submarine by 1 after it is hit.
     */
    public void gotHit()
    {
        HP--;
    }

    /**
     *Gets the locations that the submarine is in
     * @return array of Location objects
     */
    public Location[] getLocations()
    {
        return shiploc;
    }

    /**
     * Gets the attacking range for the ship
     * @return range 
     */
    public int range()
    {
        return range;
    }

    
    /**
     * Gets the length of the submarine.
     * @return length
     */
    public int getLength() {
        return length;
    }
}
