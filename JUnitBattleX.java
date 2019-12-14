import static org.junit.Assert.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class JUnitBattleX
{
    private Location loc1 = new Location(0,0);
    private Location loc2 = new Location(2,2);
    private AircraftCarrier AC = new AircraftCarrier(loc2);
    private Submarine sub = new Submarine(loc2);
    private Location loc3 = new Location(0,2);
    private Destroyer des = new Destroyer(loc3);
    
    
    //Testing Location
    @Test
    public void locationConstructorTest()
    {
        assertEquals(0, loc1.getX());
        assertEquals(0, loc1.getY());
    }
    
    @Test
    public void locationGetXTest()
    {
        assertEquals(0, loc1.getX());
    }
    
    @Test
    public void locationGetYTest()
    {
        assertEquals(0, loc1.getY());
    }
    
    @Test
    public void locationSetXTest()
    {
        loc1.setX( 1 );
        assertEquals(1, loc1.getX());
    }
    
    @Test
    public void locationSetYTest()
    {
        loc1.setY( 1 );
        assertEquals(1, loc1.getY());
    }
    
    @Test
    public void locationIncrementXTest()
    {
        loc2.incrementX();
        assertEquals(3, loc2.getX());
    }
    
    @Test
    public void locationIncrementYTest()
    {
        loc2.incrementY();
        assertEquals(3, loc2.getY());
    }
    
    @Test
    public void locationDecrementXTest()
    {
        loc2.decrementX();
        assertEquals(1, loc2.getX());
    }
    
    @Test
    public void locationDecrementYTest()
    {
        loc2.decrementY();
        assertEquals(1, loc2.getY());
    }
    
    @Test
    public void locationGetDistanceTest()
    {
        assertEquals(4,loc1.getDistance( loc2 ));
    }
    
    @Test
    public void locationEqualsTest()
    {
        loc2.setX( 0 );
        loc2.setY( 0 );
        assertTrue(loc1.equals( loc2 ));
    }
    
    //Testing Submarine
    
    @Test
    public void subHealthTest()
    {
        assertEquals(3, sub.health());
    }
    
    @Test
    public void subGotHitTest()
    {
        sub.gotHit();
        assertEquals(2, sub.health());
    }
    
    
    @Test
    public void subRangeTest()
    {
        assertEquals(3, sub.range());
    }
    
    @Test
    public void subLengthTest()
    {
        assertEquals(3, sub.getLength());
    }
    
    @Test
    public void carrierHealthTest()
    {
        assertEquals(4, AC.health());
    }
    
    @Test
    public void carrierGotHitTest()
    {
        AC.gotHit();
        assertEquals(3, AC.health());
    }
    
    @Test
    public void carrierRangeTest()
    {
        assertEquals(4, AC.range());
    }
    
    @Test
    public void carrierLengthTest()
    {
        assertEquals(4, AC.getLength());
    }
    
  //Testing Destroyer
    
    @Test
    public void desHealthTest()
    {
        assertEquals(2, des.health());
    }
    
    @Test
    public void desGotHitTest()
    {
        des.gotHit();
        assertEquals(1, des.health());
    }
    
    @Test
    public void desRangeTest()
    {
        assertEquals(2, des.range());
    }
    
    @Test
    public void desLengthTest()
    {
        assertEquals(2, des.getLength());
    }
    
//    public static junit.framework.Test suite()
//    {
//        return new JUnit4TestAdapter( JUnitBattleX.class );
//    }
//    
//    public static void main( String args[] )
//    {
//        org.junit.runner.JUnitCore.main( "JUnitBattleX" );
//    }
}
