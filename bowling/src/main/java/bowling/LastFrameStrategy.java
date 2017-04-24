package bowling;

public class LastFrameStrategy implements FrameStrategy{

    private static final int PIN_COUNT = 10;

    @Override
    public void newRollAdded( Frame frame, int rollValue, RollListenerManager rollListenerManager ){
        frame.addScore( rollValue );
    }

    /**
     * Special rule for the last frame: A third roll is allowed, when the score is more than 9 after the first two.
     * 
     * @see bowling.FrameStrategy#isFinished(bowling.Frame)
     */
    @Override
    public boolean isFinished( Frame frame ){
        if( frame.getScore() >= PIN_COUNT && frame.getRollCount() < 3 ) return false;
        if( frame.getRollCount() < 2 ) return false;
        return true;
    }
}
