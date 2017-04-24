package bowling.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import bowling.BowlingGame;

public class AcceptanceTest{

    private BowlingGame game = new BowlingGame();

    @Test
    public void gameShouldCalculateCorrectScore(){
        List<Integer> rollValues = exampleGame();
        for( int rollValue : rollValues ){
            game.addRoll( rollValue );
        }
        assertThat( game.isFinished(), is( true ) );

        int expectedEndScore = 133;
        assertThat( game.getScore(), is( expectedEndScore ) );
    }

    @Test
    public void gameShouldCalculateCorrectScore2(){
        List<Integer> rollValues = perfectGame();
        for( int rollValue : rollValues ){
            game.addRoll( rollValue );
        }
        assertThat( game.isFinished(), is( true ) );

        int perfectGameResult = 300;
        assertThat( game.getScore(), is( perfectGameResult ) );
    }

    private List<Integer> exampleGame(){
        List<Integer> result = Arrays.asList( 1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6 );
        return result;
    }

    private List<Integer> perfectGame(){
        List<Integer> result = Arrays.asList( 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 );
        return result;
    }

}
