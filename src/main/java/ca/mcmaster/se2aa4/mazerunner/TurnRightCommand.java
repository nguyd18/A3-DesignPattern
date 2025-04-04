package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand extends Command {

    public TurnRightCommand(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void execute() {
        navigator.turnRight();
    }
    
}
