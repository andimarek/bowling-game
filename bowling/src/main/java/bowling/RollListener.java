package bowling;

/**
 * Interface for listener, which are informed, when a new roll was played.
 */
public interface RollListener{

    /**
     * A new roll with the provided value was played.
     * 
     * @param rollValue
     */
    public void newRoll( int rollValue );

}
