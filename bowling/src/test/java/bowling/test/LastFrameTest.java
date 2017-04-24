package bowling.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import bowling.Frame;
import bowling.LastFrameStrategy;
import bowling.RollListenerManager;

public class LastFrameTest{

    private LastFrameStrategy frameStrategy;

    private Frame frame;

    @Before
    public void setup(){
        frameStrategy = new LastFrameStrategy();
        frame = new Frame( frameStrategy, mock( RollListenerManager.class ) );
    }

    @Test
    public void scoreTest1(){
        scoreShouldBeAddedCorrect( 10, 3, 6, 19 );
    }

    @Test
    public void scoreTest2(){
        scoreShouldBeAddedCorrect( 10, 0, 0, 10 );
    }

    @Test
    public void scoreTest3(){
        scoreShouldBeAddedCorrect( 10, 10, 10, 30 );
    }

    @Test
    public void scoreTest4(){
        scoreShouldBeAddedCorrect( 3, null, null, 3 );
    }

    private void scoreShouldBeAddedCorrect( int roll1, Integer roll2, Integer roll3, int score ){
        frame.addRoll( roll1 );
        if( roll2 != null ) frame.addRoll( roll2 );
        if( roll3 != null ) frame.addRoll( roll3 );
        assertThat( frame.getScore(), is( score ) );
    }

    @Test
    public void shouldNotFinishedWithEmptyFrame(){
        assertFalse( frame.isFinished() );
    }

    @Test
    public void shouldNotFinishedAfterStrike(){
        frame.addRoll( 10 );
        assertFalse( frame.isFinished() );
    }

    @Test
    public void shouldNotFinishedAfterStrikeAndOneMore(){
        frame.addRoll( 10 );
        frame.addRoll( 3 );
        assertFalse( frame.isFinished() );
    }

    @Test
    public void shouldNotFinishedAfterSpare(){
        frame.addRoll( 3 );
        frame.addRoll( 7 );
        assertFalse( frame.isFinished() );
    }

    @Test
    public void shouldFinishedAfterSpareAndOneMore(){
        frame.addRoll( 3 );
        frame.addRoll( 7 );
        frame.addRoll( 8 );
        assertTrue( frame.isFinished() );
    }

    @Test
    public void shouldFinishedAfterStrikeAndTwoMore(){
        frame.addRoll( 10 );
        frame.addRoll( 7 );
        frame.addRoll( 8 );
        assertTrue( frame.isFinished() );
    }

    @Test
    public void shouldFinishedAfterTwoRolls(){
        frame.addRoll( 3 );
        frame.addRoll( 5 );
        assertTrue( frame.isFinished() );
    }

    @Test
    public void shouldNotFinishedAfterOneRoll(){
        frame.addRoll( 7 );
        assertFalse( frame.isFinished() );
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowFinishedException(){
        frame.addRoll( 3 );
        frame.addRoll( 5 );
        // throws exception
        frame.addRoll( 1 );
    }
}
