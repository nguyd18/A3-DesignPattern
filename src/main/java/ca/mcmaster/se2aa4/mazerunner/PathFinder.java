package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder {

    private int[] current_position;
    private int[] end_position;
    private Direction current_direction;
    private Maze maze;
    private StringBuffer canonical_path;
    private static final Logger logger = LogManager.getLogger();

    /**
     * Constructor method to initialize instance variables
     * 
     * @param maze the maze object
     */
    public PathFinder(Maze maze) {
        this.maze = maze;
        this.current_position = maze.getEntry();
        this.end_position = maze.getExit();
        this.current_direction = Direction.EAST;
        this.canonical_path = new StringBuffer();
    }

    /**
     * Finds the solution path to the maze
     */
    public void findPath() {
        logger.info("** Trying to solve the maze");
        logger.trace("**** Current Position. Row: " + current_position[0] + " Col: " + current_position[1]);

        while (!isAtEnd()) {
            logger.trace("**** Current Direction: " + current_direction);

            // Check the diagonal cell (front-right)
            if (canMove(current_direction) && canMove(getRightDirection(current_direction))) {
                current_direction = current_direction.turnRight();
                moveForward();
                logger.trace("**** Moved forward and turned right to: (" + current_position[0] + ", " + current_position[1] + ")");
                logger.trace("**** R F");
                canonical_path.append(" R F");
            }

            // Check the cell in front
            if (canMove(current_direction)) {
                moveForward();
                logger.trace("**** Moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
                logger.trace("**** F");
                canonical_path.append("F");
                continue;
            }

            // Check the cell to the right
            if (canMove(getRightDirection(current_direction))) {
                current_direction = current_direction.turnRight();
                moveForward();
                logger.trace("**** Turned right and moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
                logger.trace("**** R F");
                canonical_path.append(" R F");
                continue;
            }

            // Check the cell to the left
            if (canMove(getLeftDirection(current_direction))) {
                current_direction = current_direction.turnLeft();
                moveForward();
                logger.trace("**** Turned left and moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
                logger.trace(" L F");
                canonical_path.append(" L F");
                continue;
            }

            // If it reaches a dead end
            current_direction = current_direction.turnLeft();
            current_direction = current_direction.turnLeft();
            moveForward();
            logger.trace("**** Dead end. Turned around and moved forward to: (" + current_position[0] + ", " + current_position[1] + ")");
            logger.trace(" L L F");
            canonical_path.append(" L L F");
        }
        logger.info("** Maze has been solved!");
        System.out.println(canonical_path.toString());
    }

    /**
     * Converts the canonical path into a factorized path
     * 
     * @param input_path the input canonical path
     * @return the factorized path
     */
    public String factorizedPath(String input_path) {
        if (input_path.length() == 0) {
            return "";
        }

        String path = input_path.replaceAll("\\s", "");
        logger.trace("**** Path without whitespaces: " + path);
        StringBuffer factorized = new StringBuffer();
        char current_char = path.charAt(0);
        int count = 1;
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == current_char) {
                count++;
            }
            else {
                if (count > 1) {
                    factorized.append(count);
                }
                factorized.append(current_char);
                current_char = path.charAt(i);
                count = 1;
                factorized.append(" ");
            }
        }
        
        if (count > 1) {
            factorized.append(count);
        }
        factorized.append(current_char);
        return factorized.toString();
    }

    /**
     * Get the right adjacent cell of the finder without turning right
     * 
     * @param d current direction of the finder
     * @return the direction that results of looking right
     */
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

    /**
     * Get the left adjacent cell of the finder without turning left
     * 
     * @param d current direction of the finder
     * @return the direction that results of looking left
     */
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

    /**
     * Checks if the finder can move by checking its adjacent cells
     * 
     * @param d the direction the finder is facing
     * @return true if can move, false if there is a wall in the way
     */
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

    /**
     * Moves the finder forward to the next cell
     */
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

    /**
     * @return true if the finder is at the end of the maze, false if it is not
     */
    private boolean isAtEnd() {
        if (current_position[0] == end_position[0] && current_position[1] == end_position[1]) {
            return true;
        }
        return false;
    }
}
