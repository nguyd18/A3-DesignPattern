package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class PathFinder {
    private int[] current_position;
    private int[] end_position;
    private Direction current_direction;
    private Maze maze;
    private StringBuffer path;
    private static final Logger logger = LogManager.getLogger();

    public PathFinder(String file_path) {
        maze = new Maze();
        maze.loadMaze(file_path);

        current_position = new int[2];
        current_position = maze.getEntry();

        end_position = new int[2];
        end_position = maze.getExit();


        current_direction = Direction.EAST;
        path = new StringBuffer();
    }

    /**
     * Solve the maze
     */
    public void solveMaze() {
        logger.info("** Trying to solve the maze");
        logger.trace("**** Current Position: (" + current_position[0] + ", " + current_position[1] + ")");
        while (!isAtEnd()) {
            if (!canMoveForward()) {
                // Adjust direction according to your desired priority: forward, right, left, backward.
                // For example, try turning right, then left, then backwards until a move is available.
                if (!tryChangeDirection()) {
                    logger.error("No moves available. Maze might be unsolvable.");
                    break;
                }
            }
            // Now that we (hopefully) have a clear path, move forward.
            moveForward();
            logger.trace("Moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
        }
        if (isAtEnd()) {
            logger.info("** Maze has been solved!");
        }
    }
    
    // Helper method to change direction until a move is available.
    private boolean tryChangeDirection() {
        // Try turning right.
        turnRight();
        if (canMoveForward()) return true;
        // If right is blocked, try left from original direction.
        turnLeft(); // This undoes the right turn.
        turnLeft(); // Now we’re turned left.
        if (canMoveForward()) return true;
        // As a last resort, turn to face backward.
        turnLeft(); // This results in the backward direction relative to original.
        return canMoveForward();
    }  

    private void moveForward() {
        if (current_direction == Direction.NORTH) {
            current_position[0]--;
        }
        else if (current_direction == Direction.EAST) {
            current_position[1]++;
        }
        else if (current_direction == Direction.SOUTH) {
            current_position[0]++;
        }
        else if (current_direction == Direction.WEST) {
            current_position[1]--;
        }
    }

    private void turnRight() {
        if (current_direction == Direction.NORTH) {
            current_direction = Direction.EAST;
        }
        else if (current_direction == Direction.EAST) {
            current_direction = Direction.SOUTH;
        }
        else if (current_direction == Direction.SOUTH) {
            current_direction = Direction.WEST;
        }
        else if (current_direction == Direction.WEST) {
            current_direction = Direction.NORTH;
        }
    }

    private void turnLeft() {
        if (current_direction == Direction.NORTH) {
            current_direction = Direction.WEST;
        }
        else if (current_direction == Direction.WEST) {
            current_direction = Direction.SOUTH;
        }
        else if (current_direction == Direction.SOUTH) {
            current_direction = Direction.EAST;
        }
        else if (current_direction == Direction.EAST) {
            current_direction = Direction.NORTH;
        }
    }

    /**
     * @return True if you can move forward, false if you cannot
     */
    private boolean canMoveForward() {
        if (current_direction == Direction.NORTH) {
            if (maze.isWall(current_position[0] - 1, current_position[1])) {
                return false;
            }
        }
        else if (current_direction == Direction.EAST) {
            if (maze.isWall(current_position[0], current_position[1] + 1)) {
                return false;
            }
        }
        else if (current_direction == Direction.SOUTH) {
            if (maze.isWall(current_position[0] + 1, current_position[1])) {
                return false;
            }
        }
        else if (current_direction == Direction.WEST) {
            if (maze.isWall(current_position[0], current_position[1] - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAtEnd() {
        if (current_position[0] == end_position[0] && current_position[1] == end_position[1]) {
            return true;
        }
        return false;
    }

    /**
     * Generates the correct path of the maze (in canonical form)
     */
    public void generatePath(Maze maze_obj) {
        System.out.println("FFFF");
    }

}
