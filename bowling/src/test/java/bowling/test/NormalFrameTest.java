package bowling.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import bowling.Frame;
import bowling.NormalFrameStrategy;
import bowling.RollListener;
import bowling.RollListenerManager;

public class NormalFrameTest{

    private RollListenerManager rollListenerManager;
    private NormalFrameStrategy frameStrategy;
    private Frame frame;

    @Before
    public void setup(){
        rollListenerManager = mock( RollListenerManager.class );
        frameStrategy = new NormalFrameStrategy();
        frame = new Frame( frameStrategy, rollListenerManager );
    }

    @Test
    public void shouldNotFninishedWithoutRoll(){
        assertFalse( frame.isFinished() );
    }
    
    @Test
    public void listenerIsAddedAfterStrike() {
        frame.addRoll( 10 );
        verify( rollListenerManager ).addNewListener( Mockito.any( RollListener.class ) );
    }
    
    @Test
    public void listenerIsAddedAfterSpar() {
        frame.addRoll( 7 );
        frame.addRoll( 3 );
        verify( rollListenerManager ).addNewListener( Mockito.any( RollListener.class ) );
    }

    @Test
    public void shouldNotFninishedAfterOneRoll(){
        frame.addRoll( 7 );
        assertFalse( frame.isFinished() );
    }

    @Test
    public void shouldFinishedAfterStrike(){
        frame.addRoll( 10 );
        assertTrue( frame.isFinished() );
    }

    @Test
    public void shouldFinishedAfterTwoRolls(){
        frame.addRoll( 3 );
        frame.addRoll( 7 );
        assertTrue( frame.isFinished() );
    }

}
