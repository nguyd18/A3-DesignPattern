package ca.mcmaster.se2aa4.mazerunner;
import java.util.*;

public class Navigator {
    private int[] current_position;
    private Direction current_direction;
    private final List<Observer> observers = new ArrayList<>();

    public Navigator(int[] start_position, Direction start_direction) {
        current_position = start_position;
        current_direction = start_direction;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(String action) {
        for (Observer observer : observers) {
            observer.update(action, current_position, current_direction);
        }
    }

    public void moveForward() {
        if (current_direction == Direction.NORTH) {
            current_position[0]--;
        } else if (current_direction == Direction.EAST) {
            current_position[1]++;
        } else if (current_direction == Direction.SOUTH) {
            current_position[0]++;
        } else if (current_direction == Direction.WEST) {
            current_position[1]--;
        }
        notifyObservers("moveForward");
    }

    public void turnLeft() {
        current_direction = current_direction.turnLeft();
        notifyObservers("turnLeft");
    }

    public void turnRight() {
        current_direction = current_direction.turnRight();
        notifyObservers("turnRight");
    }

    public int[] getPosition() {
        return current_position;
    }

    public Direction getDirection() {
        return current_direction;
    }

    public Direction getLeftDirection() {
        return current_direction.getLeftDirection(current_direction);
    }

    public Direction getRightDirection() {
        return current_direction.getRightDirection(current_direction);
    }

    public void setDirection(Direction d) {
        this.current_direction = d;
    }
}
