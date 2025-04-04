package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand extends Command {

    public MoveForwardCommand(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void execute() {
        navigator.moveForward();
    }
    
}
