package ca.mcmaster.se2aa4.mazerunner;

/**
 * Command to turn the navigator left in the maze
 */
public class TurnLeftCommand extends Command {

    public TurnLeftCommand(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void execute() {
        navigator.turnLeft();
    }
    
}
