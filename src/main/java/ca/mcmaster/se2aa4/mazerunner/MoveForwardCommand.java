package ca.mcmaster.se2aa4.mazerunner;

/**
 * Command to move the navigator forward in the maze
 */
public class MoveForwardCommand extends Command {

    public MoveForwardCommand(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void execute() {
        navigator.moveForward();
    }
    
}
