
/**
 *  BattleX game is uses a Gameboard object to create a game where players move
 *  ships and try to defeat the enemy.
 *  The main class for the BattleX Application
 *
 *  @author  Daniel Huang
 *  @version May 28, 2019
 *  @author  Period: 5
 *  @author  Assignment: BattleX
 *
 *  @author  Sources: none
 */
public class BattleX
{
    /**
     * The main class for the BattleX application
     * @param args none
     */
    public static void main( String[] args ) {
        System.out.println( "Welcome to BattleX!" );
        System.out.println( "Read the rules before you begin" );
        System.out.println( "The boundaries of the map are 0 to 11." );
        System.out.println( "You can move or attack each ship depending on its type" );
        System.out.println( "Good luck Captain" );
        Gameboard x = new Gameboard();
        
        while(x.getPlayer().getHP() > 0 && x.getNPC().getHP() > 0) {
            x.getPlayer().move( x, x.getGUI() );
            x.getNPC().round();
            
        }
        
        if (x.getPlayer().getHP() == 0) {
            System.out.println( "Try again next time." );
            System.out.println( "Game Over" );
        }
        else //x.getNPC().getHP() ==  0
        {
            System.out.println( "Congratulations Captain!" );
            System.out.println( "Game Over" );
        }
    }
}
