package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * A frame in bowling game. A strategy for calculating the score and determining when the frame is finished must be provided.
 */
public class Frame{

    private List<Integer> rolls = new ArrayList<>();
    private int score;
    private final FrameStrategy frameStrategy;
    private final RollListenerManager rollListenerManager;

    public Frame( FrameStrategy frameStrategy, RollListenerManager rollListenerManager ){
        this.frameStrategy = frameStrategy;
        this.rollListenerManager = rollListenerManager;
    }

    /**
     * Adds a new Roll. Only allowed, when its not finished.
     * @param roll
     * @throws IllegalStateException, when already finished
     */
    public void addRoll( int roll ){
        if( isFinished() ) throw new IllegalStateException( "Frame is finished" );
        rolls.add( roll );
        frameStrategy.newRollAdded( this, roll, rollListenerManager );
    }

    public boolean isFinished(){
        return frameStrategy.isFinished( this );

    }

    public void addScore( int toAdd ){
        this.score += toAdd;
    }

    public int getScore(){
        return score;
    }

    public List<Integer> getRolls(){
        return new ArrayList<>( rolls );
    }

    public int getRollCount(){
        return rolls.size();
    }

    @Override
    public String toString(){
        return "Frame: " + rolls + ", score:" + score + ", finished: " + isFinished();
    }

}
