package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerObserver implements Observer{
    
    private static final Logger logger = LogManager.getLogger();

    /**
     * Method to update the observer with the action taken, position, and direction
     */
    @Override
    public void update(String action, int[] position, Direction direction) {
        logger.trace("Action: " + action + " | Position: (" + position[0] + ", " + position[1] + ") | Direction: " + direction);
    }
}
