package ca.mcmaster.se2aa4.mazerunner;

/**
 * Observer interface for the observer pattern
 * This interface is used to update observers of changes in the subject
 */
public interface Observer {
    public void update(String action, int[] position, Direction direction);
}
