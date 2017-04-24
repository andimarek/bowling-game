package bowling;

/**
 * Calculates the score for a frame and determines if the frame is finished.
 */
public interface FrameStrategy{

    /**
     * Called when a new roll is added to the frame.
     * 
     * @param frame
     * @param rollValue
     * @param rollListenerManager
     */
    void newRollAdded( Frame frame, int rollValue, RollListenerManager rollListenerManager );


    /**
     * If the frame is finished. If finished no more rolls can be added.
     * 
     * @return
     */
    boolean isFinished( Frame frame );
}
