import java.util.*;

/**
 *  The gameboard class represents a gameboard with two players that control
 *  ships.
 *
 *  @author  Daniel Huang
 *  @version May 28, 2019
 *  @author  Period: 5
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: none
 */
public class Gameboard
{
    private static final int length = 12;
    
    private Player p1;
    private NPC npc;
    private LinkedList<Ship> playerShips;
    private LinkedList<Ship> npcShips;
    private Gui x;
    private boolean shipsInit;
    //private ArrayList<Location> taken;

    
    /**
     * No args constructor for Gameboard
     * begins  the game by prompting user for starting spot for the ship
     */
    public Gameboard() {
        shipsInit = false; 
        x = new Gui(this);
        Scanner scan = new Scanner(System.in);
  
        System.out.println("Enter starting position for AircraftCarrier"); 
        int intee = -1;
        while(0 > intee || intee > 11) {
            while (!scan.hasNextInt()) {
                System.out.println( "Please print a number from 0 - 11." );
                scan.next();          
            }
            intee = scan.nextInt();
            if (intee < 0 || intee > 11) {
                System.out.println( "Input out of bounds. Please try again." );
            }
        }
        Location l1 = new Location(0, intee);
        System.out.println("Enter starting position for Submarine"); 
        int intee2 = -1;
        do {
            while (!scan.hasNextInt()) {
                System.out.println( "Please print a number from 0 - 11." );
                scan.next();          
            }
            intee2 = scan.nextInt();
            if (intee2 == intee) {
                System.out.println( "Overlapping with another ship. Please try again." );
            }
            if (intee2 < 0 || 11 < intee2) {
                System.out.println( "Input out of bounds. Please try again." );
            }
        }while( intee2 < 0 || 11 < intee2 || intee == intee2);
        
        Location l2 = new Location(0, intee2);
        System.out.println("Enter starting position for Destroyer");
        int intee3 = -1;
        do {
            while (!scan.hasNextInt()) {
                System.out.println( "Please print a number from 0 - 11." );
                scan.next();          
            } 
            intee3 = scan.nextInt();
            if (intee3 == intee2 || intee3 == intee) {
                System.out.println( "Overlapping with another ship. Please try again." );
            }
            if (intee3 < 0 || 11 < intee3) {
                System.out.println( "Input out of bounds. Please try again." );
            }
        }while( intee3 < 0 || 11 < intee3 || intee3 == intee2 || intee3 == intee);
        
        Location l3 = new Location(0, intee3);
        p1 = new Player(l1, l2, l3);
        playerShips = p1.getMyShips();
        Ship ac = p1.getAircraftCarrier();
        for (Location lo : ac.getLocations()) {
            int val = lo.getX();
            int val2 = lo.getY();
            x.getButton( val, val2, true );
        }
        
        Ship sb = p1.getSubmarine();
        for (Location lo : sb.getLocations()) {
            int val = lo.getX();
            int val2 = lo.getY();
            x.getButton( val, val2, true );
        }
        
        Ship ds = p1.getDestroyer();
        for (Location lo : ds.getLocations()) {
            int val = lo.getX();
            int val2 = lo.getY();
            x.getButton( val, val2, true );
        }
        
        npc = new NPC(this);
        npcShips = npc.getMyShips();
        shipsInit = true;
        
        //taken = new ArrayList<Location>();
        getTaken();
        
        
        //p1.move( this, x );
        
    }
       
    
    /**
     * checks if a location is taken by another ship
     * @param loc Location to check
     * @return true if taken by another ship, false if not
     */
    public boolean isFull(Location loc) {
        for (int i = 0; i < playerShips.size(); i++)
        {
            Location[] lens = playerShips.get( i ).getLocations();
            for ( Location x : lens) {
                if (x.equals( loc )) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * returns the ship in a certain location
     * @param loc Location to check
     * @return ship object if the location is taken, null if no ship
     */
    public Ship shipInLocation(Location loc) {
        Ship xx = null;
        for (Ship sh : npcShips) {
            for(Location ls : sh.getLocations()) {
                if (ls.equals( loc )) {
                    xx = sh;
                }
            }
        }
        return xx;
    }
    
    /**
     * Getter method
     * Gets the LinkedList of enemy ships
     * @return npcShips
     */
    public LinkedList<Ship> getNPCShips() {
        return npcShips;                  
    }
    
    /**
     * Getter method
     * Gets the LinkedList of the player's ships
     * @return playerShips
     */
    public LinkedList<Ship> getplayerShips() {
        return playerShips;
    }
    
    /**
     * returns the length of the gameboard
     * @return length
     */
    public int length() {
        return length;
    }
    
    /**
     * Getter method
     * gets the gui that the gameboard is using
     * @return x the gui
     */
    public Gui getGUI() {
        return x;
    }
    
    /**
     * Getter methods
     * Gets the shipsInit instance variable
     * @return true if ships have been initialized, false if not
     */
    public boolean getShipsInit() {
        return shipsInit;
    }
    
    /**
     * returns an arraylist of all taken locations on the gameboard at that 
     * moment
     * @return taken new LinkedList of locations
     */
    public ArrayList<Location> getTaken(){
        ArrayList<Location> taken = new ArrayList<Location>();
        for (Ship s : playerShips) {
            for(Location l : s.getLocations()) {
                taken.add(l);
            }
        }
        
        for (Ship s : npcShips) {
            for(Location l : s.getLocations()) {
                taken.add( l );
            }
        }
        return taken;
    }
    
    /**
     * Getter method gets the Player
     * @return p1
     */
    public Player getPlayer() {
        return p1;
    }
    
    /**
     * Getter method gets the opposing player
     * @return npc
     */
    public NPC getNPC() {
        return npc;
    }
    
}
