package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * A bowling game. A new Object is a new game, played through the addRoll method. To start a new game, a new Object must be created.
 */
public class BowlingGame{

    private List<Frame> frames = new ArrayList<>();

    private static final int MAX_FRAME_COUNT = 10;

    private final NormalFrameStrategy normalFrameStrategy = new NormalFrameStrategy();
    private final LastFrameStrategy lastFrameStrategy = new LastFrameStrategy();

    private final RollListenerManagerImpl rollListenerManager = new RollListenerManagerImpl();

    /**
     * A new game.
     */
    public BowlingGame(){
        createNormalFrame();
    }

    /**
     * If the game is finished. The game is finished when all 10 frames are finished.
     * 
     * @return
     */
    public boolean isFinished(){
        return frames.size() == MAX_FRAME_COUNT && frames.get( frames.size() - 1 ).isFinished();
    }

    /**
     * Adding a roll to the game. Only allowed if the game ist not finished.
     * 
     * @param rollValue
     * @throws IllegalStateException
     *         If the game is finished.
     */
    public void addRoll( int rollValue ){
        if( isFinished() ) throw new IllegalStateException( "Game is finished" );
        Frame currentFrame = getCurrentFrame();

        rollListenerManager.saveListenersToInform();
        currentFrame.addRoll( rollValue );
        rollListenerManager.fireNewRoll( rollValue );
    }

    /**
     * Returns the current frame. Ensures that the frame is not finished and not null.
     * 
     * @return
     */
    private Frame getCurrentFrame(){
        Frame frame = frames.get( frames.size() - 1 );
        if( !frame.isFinished() ) return frame;

        // The current Frame is finished -> Create new one
        if( frames.size() == MAX_FRAME_COUNT - 1 ){
            Frame lastFrame = createLastFrame();
            return lastFrame;
        }
        return createNormalFrame();
    }

    private Frame createLastFrame(){
        Frame lastFrame = new Frame( lastFrameStrategy, rollListenerManager );
        frames.add( lastFrame );
        return lastFrame;
    }

    private Frame createNormalFrame(){
        Frame frame = new Frame( normalFrameStrategy, rollListenerManager );
        frames.add( frame );
        return frame;
    }

    /**
     * The current total score of the game: The sum of the scores of the frames.
     * 
     * @return
     */
    public int getScore(){
        int score = 0;
        for( Frame frame : frames ){
            score += frame.getScore();
        }
        return score;
    }

    /**
     * Returns the current frames for this game.  
     * 
     * @return
     */
    public ArrayList<Frame> getFrames(){
        return new ArrayList<>( frames );
    }

    /**
     * Manages a list of RollListeners: Listeners can be added and removed and a new event can be fired.
     *
     */
    private static class RollListenerManagerImpl implements RollListenerManager{

        private final List<RollListener> listeners = new ArrayList<>();
        private List<RollListener> listenersToInform;

        @Override
        public void addNewListener( RollListener toAdd ){
            listeners.add( toAdd );
        }

        @Override
        public void removeListener( RollListener toRemove ){
            listeners.remove( toRemove );
        }

        public void saveListenersToInform(){
            listenersToInform = new ArrayList<>( listeners );
        }

        public void fireNewRoll( int rollValue ){
            for( RollListener rollListener : listenersToInform ){
                rollListener.newRoll( rollValue );
            }
        }

    }

}
