package bowling;

/**
 * Responsible for managing a set of RollListener instances.
 */
public interface RollListenerManager{

    void addNewListener( RollListener toAdd );

    void removeListener( RollListener toRemove );
}
