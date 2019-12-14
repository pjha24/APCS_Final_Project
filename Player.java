import java.util.*;


/**
 *  The Player class represents a player with multiple ships on that he can move or
 *  attack with each turn.
 *  The Player class operates with use of a scanner object.
 *  
 *  
 *
 *  @author  Daniel Huang
 *  @version May 28, 2019
 *  @author  Period: none
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: none
 */
public class Player
{
    private int HP = 9;
    private boolean checkAttack;
    private LinkedList<Ship> myShips;
    private Ship attackingShip;
    private boolean enemycheck;

    /**
     * Constructor for a player object
     * 
     * @param l1 Creates an AircraftCarrier object with this location
     * @param l2 Creates an Submarine object with this location
     * @param l3 Creates an Destroyer object with this location
     */
    public Player( Location l1, Location l2, Location l3 )
    {
        Ship ac = new AircraftCarrier( l1 );
        Ship sb = new Submarine( l2 );
        Ship dd = new Destroyer( l3 );
        myShips = new LinkedList<Ship>();
        myShips.add( ac );
        myShips.add( sb );
        myShips.add( dd );
        attackingShip = null;
        enemycheck = true;
    }


    /**
     * The player selects a location to attack on the gameboard
     * @param loc Location to be attacked
     * @param g gameboard that this location is at
     * @return true if hits an enemy ship, false if doesn't hit
     */
    public boolean attack( Location loc, Gameboard g) {
        for ( Ship s : g.getNPCShips() ) {
            for ( Location l : s.getLocations() )
            {
                if(l.equals( loc )) {
                    s.gotHit();
                    return true;
                }            
            }
        }
        return false;
    }
    

    /**
     * Represents a turn  where the player can choose to attack or move. The
     * scanner will prompt the player to move or attack for each ship that is
     * alive. If player chooses to move, it will go thru a the specified number
     * of movements for each ship. If the player chooses to attack, he will be
     * prompted to click on the GUI.
     * 
     * @param g gameboard that is being played on
     * @param gg Gui that the player and board is linked to
     */
    public void move( Gameboard g, Gui gg )
    {
        
        Scanner scan = new Scanner( System.in );
        for ( Ship sh : myShips )
        {
            setAttackingShip(sh);
            int len = sh.getLength();
            if (len == 4 && enemyAlive(g)) {
                System.out.println( "\"move\" or \"attack\" with AircraftCarrier" );
            }
            else if (len ==  3 && enemyAlive(g)) {
                System.out.println( "\"move\" or \"attack\" with Submarine" );
            }
            else if (len == 2 && enemyAlive(g)) {
                System.out.println( "\"move\" or \"attack\" with AircraftCarrier" );
            }
            if (!enemyAlive(g)) {
                break;
            }
            String cmd = scan.next();
            cmd = cmd.toUpperCase();
            if ( cmd.equals( "MOVE" ) )
            {
                for ( int x = 0; x < sh.range(); x++ )
                {
                    System.out.println( "Which direction?" );
                    String str = scan.next();  
                    str = str.toUpperCase();
                    if ( str.equals( "RIGHT" ) )
                    {
                        
                        Location[] temp = new Location[sh.getLocations().length];
                        for(int k = 0; k < temp.length; k++) {
                            temp[k] = new Location(sh.getLocations()[k].getX(), sh.getLocations()[k].getY());
                        }
//                        temp[1] = new Location(sh.getLocations()[1].getX(), sh.getLocations()[1].getY());
//                        temp[2] = new Location(sh.getLocations()[2].getX(), sh.getLocations()[2].getY());
//                        temp[3] = new Location(sh.getLocations()[3].getX(), sh.getLocations()[3].getY());

                        for(int k = 0; k < temp.length; k++) {
                            
                            //System.out.println( temp[k].getX() + " " + temp[k].getY() );
                            //System.out.println( sh.getLocations()[k].getX() + " " + sh.getLocations()[k].getY() );

                            temp[k].incrementY();
                            //System.out.println( temp[k].getX() + " " + temp[k].getY() );
                            //System.out.println( sh.getLocations()[k].getX() + " " + sh.getLocations()[k].getY() );
                        }
                        boolean check = true;
                        for(int k = 0; k < temp.length ; k++) {
                            for (Location sss : g.getTaken()) {
                                //for(Location lc : sss.getLocations()) {
                                    if(temp[k].equals( sss )) {
                                        check = false;
                                    }
                            }
                            if (temp[k].getY() > 11) {
                                check = false;
                            }
                        }
                        
                        
                        
                        
                        
                        if (check)
                        {
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.redo( val, val2 );
                            }
                            sh.moveRight();
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.getButton( val, val2, true );
                            }
                        }
                        else
                        {
                            System.out.println( "Boy, you can see where you're going? Not a valid move." );
                        }
                        
                        
                        
                    }
                    else if ( str.equals( "LEFT" ) )
                    {
                        Location[] temp = new Location[sh.getLocations().length];
                        for(int k = 0; k < temp.length; k++) {
                            temp[k] = new Location(sh.getLocations()[k].getX(), sh.getLocations()[k].getY());
                        }
//                        temp[1] = new Location(sh.getLocations()[1].getX(), sh.getLocations()[1].getY());
//                        temp[2] = new Location(sh.getLocations()[2].getX(), sh.getLocations()[2].getY());
//                        temp[3] = new Location(sh.getLocations()[3].getX(), sh.getLocations()[3].getY());

                        for(int k = 0; k < temp.length; k++) {
                            
                            //System.out.println( temp[k].getX() + " " + temp[k].getY() );
                            //System.out.println( sh.getLocations()[k].getX() + " " + sh.getLocations()[k].getY() );

                            temp[k].decrementY();
                            //System.out.println( temp[k].getX() + " " + temp[k].getY() );
                            //System.out.println( sh.getLocations()[k].getX() + " " + sh.getLocations()[k].getY() );
                        }
                        boolean check = true;
                        for(int k = 0; k < temp.length ; k++) {
                            for (Location sss : g.getTaken()) {
                                if(temp[k].equals( sss )) {
                                        check = false;
                                }
                            }
                            if (temp[k].getY() < 0) {
                                check = false;
                            }
                        }
                        
                        if (check) {
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.redo( val, val2 );
                            }
                            sh.moveLeft();
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.getButton( val, val2, true );
                            }
                        }
                        else
                        {
                            System.out.println( "The fog is thick, you can't see... Can you? Not a valid move." );

                        }
                        
                    }
                    else if ( str.equals( "FORWARD" ) )
                    {
                        Location[] temp = new Location[sh.getLocations().length];
                        temp[temp.length-1] = new Location(sh.getLocations()[temp.length-1].getX(), sh.getLocations()[temp.length-1].getY());
                        temp[temp.length-1].incrementX();
                        boolean check = true;
                        for(Location lc : g.getTaken()) {
                            if(temp[temp.length - 1].equals( lc )) {
                                System.out
                                    .println( "Get some binoculars! This location is taken by another ship." );
                                check = false;          
                            }
                        }
                        if (temp[temp.length - 1].getX() > 11) {
                            System.out.println( "The world is flat! Can't move that way." );
                            check = false;
                    }
                        
                        if (check) {
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.redo( val, val2 );
                            }
                            sh.moveForward();
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.getButton( val, val2, true );
                            }
                        }
                        
                        
                    }
                    else if ( str.equals( "BACKWARD" ) )
                    {
                        Location[] temp = new Location[sh.getLocations().length];
                        temp[0] = new Location(sh.getLocations()[0].getX(), sh.getLocations()[0].getY());
                        temp[0].decrementX();
                        boolean check = true;
                        for(Location lc : g.getTaken()) {
                            if(temp[0].equals( lc )) {
                                System.out
                                    .println( "Get some binoculars! This location is taken by another ship." );
                                check = false;          
                            }
                        }
                        if (temp[0].getX() < 0) {
                            System.out.println( "The world is flat! Can't move that way." );
                            check = false;
                    }
                        if (check) {
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.redo( val, val2 );
                            }
                            sh.moveBackward();
                            for ( Location lo : sh.getLocations() )
                            {
                                int val = lo.getX();
                                int val2 = lo.getY();
                                gg.getButton( val, val2, true );
                            }
                        }
                        
                    }
                    else {
                        System.out.println( "Command not recognized. This movement has been skipped" );

                    }
                }
            }
            else if ( cmd.equals( "ATTACK" ) )
            {
                checkAttack = true;
//                System.out.println( "Enter row" );
//                int val = scan.nextInt();
//                System.out.println( "Enter col" );
//                int val2 = scan.nextInt();
//                Location spot = new Location( val, val2 );
//                System.out.println( attack(spot, g) );
                System.out.println( "Click where you would like to attack" );
                tocontinue();       
                
            }
            else {
                System.out.println( "Command not recognized. Your move has been skipped" );
            }
        }

    }
    
    /**
     *gets the AircraftCarrier from the player's ships
     * @return aircraftcarrier
     */
    public Ship getAircraftCarrier() {
        return myShips.get( 0 );
    }
    
    /**
     *gets the submarine from the player's ships
     * @return submarine
     */
    public Ship getSubmarine() {
        return myShips.get( 1 );
    }
    
    /**
     *gets the Destroyer from the player's ships
     * @return Destroyer
     */
    public Ship getDestroyer() {
        return myShips.get( 2 );
    }
    
    /**
     * Returns the LinkedList of all the Player's ship that are alive
     * @return myShips
     */
    public LinkedList<Ship> getMyShips(){
        return myShips;
    }
    
    /**
     * returns the total HP of the player
     * @return HP
     */
    public int getHP()
    {
        return HP;
    }
    
    /**
     * subtracts the player's HP by one
     */
    public void subtractHP() {
        HP--;
    }
    
    
    /**
     * checks  if the player is currently attacking
     * @return checkAttack
     */
    public boolean getCheckAttack()
    {
        return checkAttack;
    }
    
    
    /**
     * changes the checkAttack instance variable to boolean b
     * @param b boolean for new checkAttack
     */
    public void setCheckAttack(boolean b) {
        checkAttack = b;
    }
    
    /**
     * Checks if the chosen ship has 0 hp
     * @param bo the ship to check if dead
     * @return true if ship has 0 hp, false if ship is still alive
     */
    public boolean isDead(Ship bo) {
        boolean dog = false;
        if (bo.health() < 1) {
            dog = true;
            
            
        }
        return dog;
    }
    
    /**
     * returns the ship that is currently attacking within the move
     * @return ship object
     */
    public Ship getAttackingShip() {
        return attackingShip;
    }
    
    /**
     * sets the attacking ship to whichever ship's turn it is
     * @param x the current ship that is in it's move iteration
     */
    public void setAttackingShip(Ship x) {
        attackingShip = x;
    }
    
    /**
     * checks if the enemy is stil alive (has greater than 0 health)
     * @param ggg gameboard object
     * @return true if enemy is alive, false if enemy is dead.
     */
    public boolean enemyAlive(Gameboard ggg) {
        return (ggg.getNPC().getHP() > 0);
    }
    
    /**
     * prompts the user to press "enter" to continue
     */
    private void tocontinue()
    { 
           try
           {
               System.out.println( "Waiting..." );
               System.in.read();
               
           }  
           catch(Exception e)
           {}  
    }
}