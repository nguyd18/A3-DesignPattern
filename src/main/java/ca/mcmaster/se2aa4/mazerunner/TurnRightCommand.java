package ca.mcmaster.se2aa4.mazerunner;

/**
 * Command to turn the navigator right in the maze
 */
public class TurnRightCommand extends Command {

    public TurnRightCommand(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void execute() {
        navigator.turnRight();
    }
    
}
