package ca.mcmaster.se2aa4.mazerunner;

/**
 * Abstract class representing a command in the maze navigation system
 */
public abstract class Command {

    public Navigator navigator;

    public Command(Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Abstract method to execute the command
     */
    public abstract void execute();
}
