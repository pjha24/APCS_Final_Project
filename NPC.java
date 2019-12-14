
import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 
 * This is the class that controls the NPC actions NPC extends player class and
 * controls starting actions, movement actions, attacking actions, and includes
 * helper methods for these actions.
 *
 * @author Jacob Lee
 * @version May 20, 2019
 * @author Period: 5
 * @author Assignment: Final Project
 *
 * @author Sources:
 */
public class NPC extends Player
{
    private Gameboard board;

    private static int Height = 12;

    private Gui gg;

    private int count;
    
    


    /**
     * Constructor for the NPC
     * 
     * @param x
     *            the game board that is being played on
     */
    public NPC( Gameboard x )
    {

        super( startLocation( Height / 3 + 1, Height * 2 / 3, true, 4 ),
            startLocation( 0, Height / 3, true, 3 ),
            startLocation( Height * 2 / 3, Height, true, 2 ) );

        Ship ac = this.getAircraftCarrier();
        for ( Location lo : ac.getLocations() )
        {
            int val = lo.getX();
            int val2 = lo.getY();
            x.getGUI().getButton( val, val2, false );
        }

        Ship sb = this.getSubmarine();
        for ( Location lo : sb.getLocations() )
        {
            int val = lo.getX();
            int val2 = lo.getY();
            x.getGUI().getButton( val, val2, false );
        }

        Ship ds = this.getDestroyer();
        for ( Location lo : ds.getLocations() )
        {
            int val = lo.getX();
            int val2 = lo.getY();
            x.getGUI().getButton( val, val2, false );
        }
        board = x;
        gg = board.getGUI();
    }


    /**
     * 
     * The method that will randomize a start location between bounds
     * 
     * @param start
     *            the start bound
     * @param end
     *            the end bound
     * @param mine
     *            whether or not I'm initializing a location for my ship or an
     *            enemy ship
     * @return
     */
    private static Location startLocation(
        int start,
        int end,
        boolean mine,
        int length )
    {
        int x = (int)( Math.random() * ( end - start ) + start );
        int y;
        if ( !mine )
        {
            y = 0;
        }
        else
        {
            y = Height - length;
        }
        return new Location( y, x );
    }


    /**
     * 
     * Checks whether any ships are in range
     * 
     * @param mine
     *            the ship that is attacking
     * @param enemyLocation
     *            all the enemy locations
     * @return whether or not any ships in range
     * 
     */
    private boolean canAttack(
        Ship mine,
        ArrayList<Location> enemyLocation )
    {
        for ( Location x : mine.getLocations() )
        {
            for ( Location y : enemyLocation )
            {
                if ( x.getDistance( y ) <= mine.range() )
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 
     * returns a list of the ships that the given ship can attack
     * 
     * @param mine
     *            the ship that can attack
     * @return a list of the ships that can attack
     */
    private ArrayList<Ship> attackableShips( Ship mine )
    {
        ArrayList<Ship> attackable = new ArrayList<Ship>();
        for ( Location x : mine.getLocations() )
        {
            for ( Ship enemy : board.getplayerShips() )
            {
                for ( Location y : enemy.getLocations() )
                {
                    if ( x.getDistance( y ) <= mine.range()
                        && !attackable.contains( enemy ) )
                    {
                        attackable.add( enemy );
                    }
                }
            }
        }
        return attackable;
    }


    /**
     * 
     * moves to lowest health ship prioritizing 
     * 
     * @param ship
     *            my ship
     */
    private void move( Ship ship )
    {
        Ship enemy = null;
        int health = 10;
        for ( Ship e : board.getplayerShips() )
        {
            
            if ( e.health() < health  )
            {
                health = e.health();
                enemy = e;
            }
        }
        Location[] closest = new Location[2];
        int distance = 200;
        for ( Location mineLoc : ship.getLocations() )
        {
            for ( Location enemyLoc : enemy.getLocations() )
            {
                if ( mineLoc.getDistance( enemyLoc ) < distance )
                {
                    distance = mineLoc.getDistance( enemyLoc );
                    closest[0] = mineLoc;
                    closest[1] = enemyLoc;
                }
            }
        }
        int moveX = closest[0].getX() - closest[1].getX();
        int moveY = closest[0].getY() - closest[1].getY();

        int range = ship.range();
        if ( Math.abs( moveX ) + Math.abs( moveY ) < range )
            range = Math.abs( moveX ) + Math.abs( moveY ) - 1;
        for ( int row = 0; row < range; row++ )
        {
            if ( moveX > 0 )
            {
                if ( !moveBackward( ship ) )
                {
                    if ( !moveLeft( ship ) )
                    {
                        moveRight( ship );
                        moveY++;
                    }
                    else
                    {
                        moveY--;
                    }
                }
                else {
                    moveX--;
                }
            }
            else if ( moveX < 0 )
            {
                if ( !moveForward( ship ) )
                {
                    if ( !moveRight( ship ) )
                    {
                        moveLeft( ship );
                        moveY--;
                    }
                    else
                    {
                        moveY++;
                    }
                }
                else
                {
                    moveX++;
                }
            }
            if(moveX == 0)
            {
                if ( moveY > 0 )
                {
                    if ( !moveLeft( ship ) )
                    {
                        moveBackward( ship );
                        moveX--;
                    }
                    else
                    {
                        moveY--;
                    }
                        
                }
    
                else if ( moveY < 0 )
                {
                    if ( !moveRight( ship ) )
                    {
                        moveBackward( ship );
                        moveX--;
                    }
                    else {
                        moveY++;
                    }
                }
            }
            

        }
    }

    
    private boolean moveLeft( Ship ship )
    {
        {
            /*
             * boolean empty = true; for ( Location lo : ship.getLocations() ) {
             * lo.decrementY(); if (board.getTaken().contains(lo )); { empty =
             * false; } } if(empty) {
             */
            Location[] temp = new Location[ship.getLocations().length];
            for ( int k = 0; k < temp.length; k++ )
            {
                temp[k] = new Location( ship.getLocations()[k].getX(),
                    ship.getLocations()[k].getY() );
            }

            for ( int k = 0; k < temp.length; k++ )
            {
                temp[k].decrementY();
            }
            boolean check = true;
            for ( int k = 0; k < temp.length; k++ )
            {
                for ( Location sss : board.getTaken() )
                {
                    if ( temp[k].equals( sss ) )
                    {
                        check = false;
                    }
                }
                if ( temp[k].getY() < 0 )
                {
                    check = false;
                }
            }

            if ( check )
            {
                for ( Location lo : ship.getLocations() )
                {
                    int val = lo.getX();
                    int val2 = lo.getY();
                    gg.redo( val, val2 );
                }
                ship.moveLeft();
                for ( Location lo : ship.getLocations() )
                {
                    int val = lo.getX();
                    int val2 = lo.getY();
                    gg.getButton( val, val2, false );
                }
                return true;
            }
            else
                return false;
        }
    }


    private boolean moveRight( Ship ship )
    {
        /*
         * boolean empty = true; for ( Location lo : ship.getLocations() ) {
         * lo.incrementY(); if (board.getTaken().contains(lo )); { empty =
         * false; } } if(empty) {
         */
        Location[] temp = new Location[ship.getLocations().length];
        for ( int k = 0; k < temp.length; k++ )
        {
            temp[k] = new Location( ship.getLocations()[k].getX(),
                ship.getLocations()[k].getY() );
        }

        for ( int k = 0; k < temp.length; k++ )
        {
            temp[k].incrementY();
        }
        boolean check = true;
        for ( int k = 0; k < temp.length; k++ )
        {
            for ( Location sss : board.getTaken() )
            {
                // for(Location lc : sss.getLocations()) {
                if ( temp[k].equals( sss ) )
                {
                    check = false;
                }
            }
            if ( temp[k].getY() > 11 )
            {
                check = false;
            }
        }
        if ( check )
        {
            for ( Location lo : ship.getLocations() )
            {
                int val = lo.getX();
                int val2 = lo.getY();
                gg.redo( val, val2 );
            }
            ship.moveRight();
            for ( Location lo : ship.getLocations() )
            {
                int val = lo.getX();
                int val2 = lo.getY();
                gg.getButton( val, val2, false );
            }
            return true;

        }
        else
            return false;

    }


    private boolean moveForward( Ship ship )
    {
        Location[] temp = new Location[ship.getLocations().length];
        temp[temp.length - 1] = new Location(
            ship.getLocations()[temp.length - 1].getX(),
            ship.getLocations()[temp.length - 1].getY() );
        temp[temp.length - 1].incrementX();
        boolean check = true;
        for ( Location lc : board.getTaken() )
        {
            if ( temp[temp.length - 1].equals( lc ) )
            {
                check = false;
            }
        }
        if ( temp[temp.length - 1].getX() > 11 )
        {
            check = false;
        }
        if ( check )
        {
            for ( Location lo : ship.getLocations() )
            {
                int val = lo.getX();
                int val2 = lo.getY();
                gg.redo( val, val2 );
            }
            ship.moveForward();
            for ( Location lo : ship.getLocations() )
            {
                int val = lo.getX();
                int val2 = lo.getY();
                gg.getButton( val, val2, false );
            }
            return true;
        }
        else
            return false;
    }


    /**
     * 
     * Moves backward one (forward for the npc ship)
     * @param ship the ship that is moving
     * @return whether or not the ship could move
     */
    private boolean moveBackward( Ship ship )
    {
        Location[] temp = new Location[ship.getLocations().length];
        temp[0] = new Location( ship.getLocations()[0].getX(),
            ship.getLocations()[0].getY() );
        temp[0].decrementX();
        boolean check = true;
        for ( Location lc : board.getTaken() )
        {
            if ( temp[0].equals( lc ) )
            {
                check = false;
            }
        }
        if ( temp[0].getX() < 0 )
        {
            check = false;
        }
        if ( check )
        {
            for ( Location lo : ship.getLocations() )
            {
                int val = lo.getX();
                int val2 = lo.getY();
                gg.redo( val, val2 );
            }
            ship.moveBackward();
            for ( Location lo : ship.getLocations() )
            {
                int val = lo.getX();
                int val2 = lo.getY();
                gg.getButton( val, val2, false );
            }
            return true;
        }
        else
            return false;
    }

    /**
     * 
     * Updates all the enemy locations 
     * @return an arrayList of all the enemy locations
     */
    private ArrayList<Location> enemyLocations()
    {
        ArrayList<Location> eL = new ArrayList<Location>();
        for ( Ship enemyShip : board.getplayerShips() )
        {
            for ( Location x : enemyShip.getLocations() )
            {
                eL.add( x );
            }
        }
        return eL;
    }

    /**
     * 
     * completes a round for npc
     */
    public void round()
    {
        ArrayList<Ship>[] attackable = new ArrayList[board.getNPCShips()
            .size()];
        int[] enemy = new int[3];
        
        
        for ( int i = 0; i < board.getNPCShips().size(); i++ )
        {
            if ( canAttack( board.getNPCShips().get( i ),
                enemyLocations() ) )
            {
                attackable[i] = attackableShips(
                    board.getNPCShips().get( i ) );

                for ( Ship x : attackable[i] )
                {
                    if ( x.range() == 4 )
                    {
                        enemy[0]++;
                    }
                    else if ( x.range() == 3 )
                    {
                        enemy[1]++;
                    }
                    else if ( x.range() == 2 )
                    {
                        enemy[2]++;
                    }
                }

            }
        }

        for ( int i = 0; i < board.getNPCShips().size(); i++ )
        {
            if(board.getplayerShips().isEmpty())
            {
                System.out.println("You lost to a robot");
                break;
            }
            int wait = 0;
            if ( canAttack( board.getNPCShips().get( i ),
                enemyLocations() ) )
            {
                attackable[i] = attackableShips(
                    board.getNPCShips().get( i ) );
            }
            else
            {
                attackable[i] = null;
                
            }
            if ( attackable[i] == null )
            {
                move( board.getNPCShips().get( i ) );
                wait = 1;
            }
            else
            {
                wait = 5;
                Ship toBeAttacked = null;
                for ( int x = 0; x < board.getplayerShips().size(); x++ )
                {
                    Ship s = board.getplayerShips().get( x );
                    int y = board.getplayerShips().indexOf( s );
                    if ( s.health() > 0 && s.health() <= enemy[y] )
                    {
                        toBeAttacked = s;
                    }
                }
                if ( toBeAttacked == null )
                {
                    int health = 10;
                    for ( Ship x : attackable[i] )
                    {
                        if ( health > x.health() )
                        {
                            health = x.health();
                            toBeAttacked = x;
                        }
                    }
                }
                toBeAttacked.gotHit();
                enemy[board.getplayerShips().indexOf( toBeAttacked )]--;
                System.out.println("You got hit");
                board.getPlayer().subtractHP();
                
                if ( board.getPlayer().isDead( toBeAttacked ) )
                {
                    board.getplayerShips().remove( toBeAttacked );
                    System.out.println( "You got sunk." );
                }
                final Timer timer = new Timer();
                final Timer timer2 = new Timer();
                final Location[] list = toBeAttacked.getLocations();
                TimerTask task = new TimerTask()
                {
                    public void run()
                    {
                        count++;
                        if ( count >= 7 )
                        {
                            timer.cancel();
                            timer.purge();

                        }
                        for ( Location loc : list )
                        {
                            gg.getButton( loc.getX(), loc.getY() )
                                .setBackground( Color.orange );
                            gg.getButton( loc.getX(), loc.getY() )
                                .setOpaque( true );
                        }

                    }
                };
                TimerTask task2 = new TimerTask()
                {
                    public void run()
                    {
                        count++;
                        if ( count >= 7 )
                        {
                            timer.cancel();
                            timer.purge();

                        }
                        // location.setBackground( Color.MAGENTA );
                        for ( Location loc : list )
                        {
                            gg.getButton( loc.getX(), loc.getY() )
                                .setOpaque( true );
                            gg.getButton( loc.getX(), loc.getY() )
                                .setBackground( Color.blue );
                        }

                    }

                };

                timer.schedule( task, 1000, 1000 );
                timer.schedule( task2, 500, 1000 );
                if ( board.getPlayer().isDead( toBeAttacked ) )
                {
                    TimerTask task3 = new TimerTask()
                    {
                        public void run()
                        {
                            for ( Location l : list )
                            {
                                int val = l.getX();
                                int val2 = l.getY();
                                gg.changeColor( val, val2 );
                                // board.updateTaken(val, val2);

                            }
                        }
                    };
                    timer2.schedule( task3, 4500 );
                    
                }
                count = 0;

                
            }
            try {
                TimeUnit.SECONDS.sleep( wait );
            }
            catch(InterruptedException ie) {
                
            }
        }
    }
}