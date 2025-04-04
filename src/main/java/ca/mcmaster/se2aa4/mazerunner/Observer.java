package ca.mcmaster.se2aa4.mazerunner;

public interface Observer {
    public void update(String action, int[] position, Direction direction);
}
