package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

/**
 * Navigator class to manage the position and direction of the navigator in the maze
 */
public class Navigator {
    private int[] current_position;
    private Direction current_direction;
    private final List<Observer> observers = new ArrayList<>();

    public Navigator(int[] start_position, Direction start_direction) {
        current_position = start_position;
        current_direction = start_direction;
    }

    /**
     * Method to add an observer to the navigator
     * @param observer The observer to be added
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Method to notify all observers of an action taken
     * @param action The action taken by the navigator
     */
    private void notifyObservers(String action) {
        for (Observer observer : observers) {
            observer.update(action, current_position, current_direction);
        }
    }

    /**
     * Move the navigator forward in the current direction
     */
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

    /**
     * Checks if the navigator can move by checking its adjacent cells
     * 
     * @param maze the maze object
     * @param d the direction the navigator is facing
     * @return true if can move, false if there is a wall in the way
     */
    public boolean canMove(Maze maze, Direction d) {
        int row = current_position[0];
        int col = current_position[1];
        if (d == Direction.NORTH) {
            if (maze.isWall(row - 1, col)) {
                return false;
            }
        }
        else if (d == Direction.EAST) {
            if (maze.isWall(row, col + 1)) {
                return false;
            }
        }
        else if (d == Direction.SOUTH) {
            if (maze.isWall(row + 1, col)) {
                return false;
            }
        }
        else if (d == Direction.WEST) {
            if (maze.isWall(row, col - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the navigator is at the end of the maze
     * 
     * @param maze the maze object
     * @return true if the finder is at the end of the maze, false if it is not
     */
    public boolean isAtEnd(Maze maze) {
        int exit_row = maze.getExit()[0];
        int exit_col = maze.getExit()[1];
        if (current_position[0] == exit_row && current_position[1] == exit_col) {
            return true;
        }
        return false;
    }

    /**
     * Turn the navigator left
     */
    public void turnLeft() {
        current_direction = current_direction.turnLeft();
        notifyObservers("turnLeft");
    }

    /**
     * Turn the navigator right
     */
    public void turnRight() {
        current_direction = current_direction.turnRight();
        notifyObservers("turnRight");
    }

    /**
     * @return The current position of the navigator
     */
    public int[] getPosition() {
        return current_position;
    }

    /**
     * @return The current direction of the navigator
     */
    public Direction getDirection() {
        return current_direction;
    }

    /**
     * @return The direction to the left of the current direction
     */
    public Direction getLeftDirection() {
        return current_direction.getLeftDirection(current_direction);
    }

    /**
     * @return The direction to the right of the current direction
     */
    public Direction getRightDirection() {
        return current_direction.getRightDirection(current_direction);
    }

    /**
     * @param d Set the current direction of the navigator to this direction
     */
    public void setDirection(Direction d) {
        this.current_direction = d;
    }
}
