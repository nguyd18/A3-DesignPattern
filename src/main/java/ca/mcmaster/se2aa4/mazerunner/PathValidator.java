package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathValidator {

    private int[] current_position;
    private int[] end_position;
    private Direction current_direction;
    private Maze maze;
    private static final Logger logger = LogManager.getLogger();

    /**
     * Constructor method to initialize instance variables
     * 
     * @param maze the maze object
     */
    public PathValidator(Maze maze) {
        this.maze = maze;
        this.current_position = maze.getEntry();
        this.end_position = maze.getExit();
        this.current_direction = Direction.EAST;
    }

    /**
     * Check if the path is a valid solution to the given maze
     * 
     * @param path the path argument passed by the user
     * @return a result message: "correct path" or "incorrect path"
     */
    public String validatePath(String path) {
        logger.trace("**** Input path: " + path);
        logger.trace("**** Factorized to canonical: " + PathFormatter.convertToCanonical(path));
        String canonical_path = PathFormatter.convertToCanonical(path).replaceAll("\\s", "");

        for (int i = 0; i < canonical_path.length(); i++) {
            char current_char = canonical_path.charAt(i);
            if (current_char == 'F') {
                // see if it can move forward
                if (canMove(current_direction)) {
                    moveForward();
                }
                else {
                    // if it sees that it can hit the wall, break
                    break;
                }

            }
            else if (current_char == 'R') {
                current_direction = current_direction.turnRight();
            }
            else {
                current_direction = current_direction.turnLeft();
            }
        }

        if (isAtEnd()) {
            return "correct path";
        }
        return "incorrect path";
    }

    /**
     * Checks if the validator can move by checking its adjacent cells
     * 
     * @param d the direction the validator is facing
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
     * Moves the validator forward to the next cell
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
     * @return true if the validator is at the end of the maze, false if it is not
     */
    private boolean isAtEnd() {
        if (current_position[0] == end_position[0] && current_position[1] == end_position[1]) {
            return true;
        }
        return false;
    }
}
