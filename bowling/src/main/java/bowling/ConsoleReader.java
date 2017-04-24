package bowling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleReader{

    /**
     * Reads the rolls for a bowling game for sdt in and prints the result after finished.
     * 
     * @param args
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException{

        BowlingGame game = new BowlingGame();
        System.out.println( "Starting a new bowling game:" );
        while( !game.isFinished() ){
            System.out.println( "Please enter the next roll:" );
            int rollValue = readRollValue();
            game.addRoll( rollValue );
        }

    

        ArrayList<Frame> frames = game.getFrames();
        int count = 1;
        for( Frame frame : frames ){
            System.out.println( "Frame " + count++ + ": " );
            System.out.println( "Rolles: " + frame.getRolls() );
            System.out.println( "Score: " + frame.getScore() );
            System.out.println( "" );
        }
        System.out.println( "Game finished: Total score: " + game.getScore() );
    }

    private static int readRollValue() throws IOException{
        while( true ){
            try{
                BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
                int result = Integer.parseInt( br.readLine() );
                if( result < 0 || result > 10 ){
                    System.out.println( "Error: Roll must be between 0<= roll <= 10" );
                    continue;
                }
                return result;
            }
            catch( NumberFormatException e ){
                System.out.println( "Error: Roll must be a integer between 0<= roll <= 10 " );
            }
        }

    }
}
