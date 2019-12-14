import java.awt.Color
;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;


import javax.swing.*;

/**
*  GUI class extends JFrame and adds a grid of JButtons to a JPanel in the JFrame
 *  Each button is associated with a row and column number that is used to calculate distance and set locations of starting ships
 *  @author  Paritosh Jha
 *  @version May 28, 2019
 *  @author  Period: 5
 *  @author  Assignment: CS Final
 *
 *  @author  Sources: TODO

 */
public class Gui extends JFrame
{
    private static final int rc = 12;
    private Gameboard board;
    private List<JButton> allButtons = new ArrayList<JButton>();
    private int count = 0;
    private boolean flash = false;
    
//    public static void main( String args[] )
//    {
//        new Gui();
//        
//    }
        
    /**
     *   @param x 
     *          using gameboard in order to access its methods and maintain the game on one screen
     */ 
    public Gui(Gameboard x)
    {
        setSize(1000,1000);
        setResizable(false);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        add(makePanel());
        setVisible(true);
        
       // for (int i = 0; i < allButtons.size();i++)
        {
         //   System.out.println(allButtons.get(i).);
        }
        
       board = x;
    }
    
    public JPanel makePanel()
    {
        JPanel panel = new JPanel(new GridLayout(rc, rc));
        
        for (int row = 0; row < rc; row++)
        {
            for (int col = 0; col< rc; col++)
            {
                JButton button = makeButton(row,col);
                panel.add(button);
                allButtons.add(button);
            }
        }
        
        return panel;
        
    }
     
    /**
    * iterates through calling makeButton in order to associate a button with a row,column location
    * adds every button being made into an ArrayList that we use in other methods to access certain buttons
    * 
    * @param r row of button
    * @param c column of button
    * @return  Jpanel that is arranged on screen using prebuilt gridLayout

    */
    public JButton makeButton(final int r, final int c)
    {
        final JButton location = new JButton();
        
        location.addActionListener(new ActionListener()
        {
            @Override

            public void actionPerformed(ActionEvent e) 
            {
               // getLocation() make getter method to return location of each button when clicked
               // System.out.println(r + " " + c);
                
                //location.setText(r + " " + c);
               // getLocation(r,c);
                //isFull(getLocation(r,c), board);
                
                if (board.getShipsInit() && board.getPlayer().getCheckAttack())
                {
                    
                    if (isFull(getLocation(r,c), board) && isInRange(getLocation(r,c), board.getPlayer().getAttackingShip() ) )
                    {
                        System.out.println( "You hit a ship at : " + r + ", " + c);
                        
                        board.getPlayer().setCheckAttack( false );
                        flash = true;
                        final Timer timer = new Timer();
                        final Timer timer2 = new Timer();
                        TimerTask task = new TimerTask() {
                            public void run()
                            {
                                count++;
                                if (count >= 7)
                                {
                                    timer.cancel();
                                    timer.purge();
                                    
                                    
                                }   
                                location.setBackground( Color.orange );
                                location.setOpaque( true );
                                
                                
                            }
                        };
                        TimerTask task2 = new TimerTask() {
                                public void run()
                                {
                                    count++;
                                    if (count >= 7 )
                                    {
                                        timer.cancel();
                                        timer.purge();
                                           
                                    }
                                   // location.setBackground( Color.MAGENTA );
                                    location.setOpaque( true );
                                    location.setBackground(Color.black);
                                    
                                }
                                
                        };
                        timer.schedule( task, 1000, 1000 );
                        timer.schedule( task2, 500, 1000 );
                        count = 0;
                       
                        
                        flash = false;

                        TimerTask task3 = new TimerTask() {
                            public void run()
                            {
                                Ship use = board.shipInLocation(getLocation(r, c));
                                use.gotHit();
                                if (board.getNPC().isDead( use ) ) 
                                {
                                    for (Location l : use.getLocations()) {
                                        int val = l.getX();
                                        int val2 = l.getY();
                                        changeColor(val, val2);
                                        //board.updateTaken(val, val2);
                                            
                                    }
                                    board.getNPCShips().remove( use );
                                    System.out.println( "Aye! You sunk one!" );

                                }
                               
                                
                                
                            }
                        };
                        timer2.schedule(task3, 4200);
                        System.out.println( "Press [Enter] to continue" );
                        
                        
                        
                        
                        board.getNPC().subtractHP();
                        //System.out.println( board.getNPC().getHP() );
                        board.getPlayer().setCheckAttack( false );
                    }
                    else if (!isInRange(getLocation(r,c), board.getPlayer().getAttackingShip()))
                    {
                        flash = false;
                        System.out.println( "You're attacking out of range. You've wasted a turn!" );
                        board.getPlayer().setCheckAttack( false );
                        System.out.println( "Press [Enter] to continue" );


                    }
                    else {
                        flash = false;
                        System.out.println( "You missed." );
                        board.getPlayer().setCheckAttack( false );
                        System.out.println( "Press [Enter] to continue" );

                    }
              
                    
                    
                
                    
                }
                
            }

        });
       
        
       
        return location;
        
    }
    
    /**
     * Able to access a JButton in the Grid given its row and column
     * @param row 
     *              row of button
     * @param col 
     *              column of button
     * @param x 
     *              used to determine if button should symbolize your ships or AI

     */
    public void getButton(int row, int col, boolean x)
    {
        int index = row * rc + col;
        JButton place = allButtons.get(index);
        if (x) {
            place.setBackground( Color.BLUE );
            place.setOpaque(true);
        }
        else {
            place.setBackground( Color.BLACK );
            place.setOpaque(true);
        }
        
        //return allButtons.get(index);
        
        
    }
    
    /**
     * returns a location of JButton to be called by methods in Gameboard
     * @param row
     *            row of button
     * @param col 
     *              column of button
     * @return 
     *           location

     */
    public Location getLocation(int row, int col)
    {
        Location z = new Location(row, col);
        //System.out.println(z.getX() + " " + z.getY());
        
        return z;
    }
    
    /**
     * iterates through an ArrayList of AI ships in order to determine if the location given has an opponent ship
    * @param loc 
    *          location object supplied through action listener
    * @param g 
    *          gameboard object
    * @return a 
    *          boolean true- if ship there

    */
    public boolean isFull(Location loc, Gameboard g)
    {
        boolean full = false;
        LinkedList<Ship> ships = g.getNPCShips();
        for (int i = 0; i < ships.size(); i++)
        {
            Location[] lens = ships.get( i ).getLocations();
            for ( Location x : lens) {
                if (x.equals( loc )) {
                    // System.out.println( "you hit a ship at : " + loc.getX() + ", " + loc.getY());
                     full = true;
                }
            }
        }
        return full;
        
    }
    
    /**
     * checks if location clicked is in range of the ship being selected
     * @param x 
     *          location from action listener
     * @param sh 
     *              ship being attacked
     * @return 
     *          a boolean

     */
    public boolean isInRange(Location x, Ship sh) {
        boolean r = false;
        for (Location l : sh.getLocations()) {
            if (x.getDistance( l ) <= sh.range()) {
                r = true;
            }
        }
        return r;
    }
    
    /**
     * after a ship moves location, this method turns their old location back to the original color of the board
     * @param row 
     *          row of button
     * @param col 
     *          column of button

     */
    public void redo(int row, int col)
    {
        int index = row * rc + col;
        JButton place = allButtons.get(index);
        place.setBackground( new JButton().getBackground() );
    }
    
    /**
     * After a ship is sunk, method takes location and turns it into color of original board
    * @param r 
    *          row of button
    * @param c 
    *          column of button

    */
    public void changeColor(int r, int c)
    {
        int index = r * rc + c;
        JButton place = allButtons.get(index);
        place.setBackground( new JButton().getBackground() );
        
    }
        
    /**
     * accesses the button in the arrayList of all JButtons 
   * @param row 
   *          row of button
   * @param col 
   *          column of button
   * @return 
   *          a JButton

   */
    public JButton getButton(int row, int col)
    {
        int index = row * rc + col;
        JButton place = allButtons.get( index );
        return place;
    }

    
    

    
}
// create a method that allows you to recognize a ship once it has been clicked
