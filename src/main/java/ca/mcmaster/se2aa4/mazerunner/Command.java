package ca.mcmaster.se2aa4.mazerunner;

public abstract class Command {

    public Navigator navigator;

    public Command(Navigator navigator) {
        this.navigator = navigator;
    }

    public abstract void execute();
}
