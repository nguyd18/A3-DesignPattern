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
        logger.trace("**** Current Position. Row: " + current_position[0] + " Col: " + current_position[1]);
        while (!isAtEnd()) {
            logger.trace("**** Current Direction: " + current_direction);
            // Check the diagonal cell (front-right)
            if (canMove(current_direction) && canMove(getRightDirection(current_direction))) {
                turnRight();
                moveForward();
                logger.trace("**** Moved forward and turned right to: (" + current_position[0] + ", " + current_position[1] + ")");
            }

            // Check the cell in front
            if (canMove(current_direction)) {
                moveForward();
                logger.trace("**** Moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
                continue;
            }
            // Check the cell to the right
            if (canMove(getRightDirection(current_direction))) {
                turnRight();
                moveForward();
                logger.trace("**** Turned right and moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
                continue;
            }

            // Check the cell to the left
            if (canMove(getLeftDirection(current_direction))) {
                turnLeft();
                moveForward();
                logger.trace("**** Turned left and moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
                continue;
            }

            // Dead end
            turnLeft();
            turnLeft();
            moveForward();
            logger.trace("**** Dead end. Turned around and moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
        }
        logger.info("** Maze has been solved!");
    }

    private Direction getRightDirection(Direction d) {
        if (d == Direction.NORTH) {
            return Direction.EAST;
        }
        else if (d == Direction.EAST) {
            return Direction.SOUTH;
        }
        else if (d == Direction.SOUTH) {
            return Direction.WEST;
        }
        else {
            return Direction.NORTH;
        }
    }

    private Direction getLeftDirection(Direction d) {
        if (d == Direction.NORTH) {
            return Direction.WEST;
        }
        else if (d == Direction.WEST) {
            return Direction.SOUTH;
        }
        else if (d == Direction.SOUTH) {
            return Direction.EAST;
        }
        else {
            return Direction.NORTH;
        }
    }

    private boolean canMove(Direction d) {
        if (d == Direction.NORTH) {
            if (maze.isWall(current_position[0] - 1, current_position[1])) {
                return false;
            }
        }
        else if (d == Direction.EAST) {
            if (maze.isWall(current_position[0], current_position[1] + 1)) {
                return false;
            }
        }
        else if (d == Direction.SOUTH) {
            if (maze.isWall(current_position[0] + 1, current_position[1])) {
                return false;
            }
        }
        else if (d == Direction.WEST) {
            if (maze.isWall(current_position[0], current_position[1] - 1)) {
                return false;
            }
        }
        return true;
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
