package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand extends Command {

    public TurnLeftCommand(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void execute() {
        navigator.turnLeft();
    }
    
}
