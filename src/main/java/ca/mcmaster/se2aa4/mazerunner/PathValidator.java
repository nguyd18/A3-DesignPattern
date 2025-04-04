package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathValidator {

    private Navigator navigator;
    private int[] end_position;
    private Maze maze;
    private static final Logger logger = LogManager.getLogger();

    /**
     * Constructor method to initialize instance variables
     * 
     * @param maze the maze object
     */
    public PathValidator(Maze maze) {
        this.maze = maze;
        navigator = new Navigator(maze.getEntry(), Direction.EAST);
        navigator.addObserver(new LoggerObserver());
        this.end_position = maze.getExit();
    }

    /**
     * Check if the path is a valid solution to the given maze
     * 
     * @param path the path argument passed by the user
     * @return a result message: "correct path" or "incorrect path"
     */
    public String validatePath(String path) {

        Command moveForward = new MoveForwardCommand(navigator);
        Command turnRight = new TurnRightCommand(navigator);
        Command turnLeft = new TurnLeftCommand(navigator);

        logger.trace("**** Input path: " + path);
        String canonical_path = PathFormatter.convertToCanonical(path).replaceAll("\\s", "");
        logger.trace("**** Factorized to canonical: " + canonical_path);

        for (int i = 0; i < canonical_path.length(); i++) {
            char current_char = canonical_path.charAt(i);
            if (current_char == 'F') {
                // see if it can move forward
                if (canMove(navigator.getDirection())) {
                    moveForward.execute();
                }
                else {
                    // if it sees that it can hit the wall, break
                    break;
                }

            }
            else if (current_char == 'R') {
                turnRight.execute();
            }
            else {
                turnLeft.execute();
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
            if (maze.isWall(navigator.getPosition()[0] - 1, navigator.getPosition()[1])) {
                return false;
            }
        }
        else if (d == Direction.EAST) {
            if (maze.isWall(navigator.getPosition()[0], navigator.getPosition()[1] + 1)) {
                return false;
            }
        }
        else if (d == Direction.SOUTH) {
            if (maze.isWall(navigator.getPosition()[0] + 1, navigator.getPosition()[1])) {
                return false;
            }
        }
        else if (d == Direction.WEST) {
            if (maze.isWall(navigator.getPosition()[0], navigator.getPosition()[1] - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if the validator is at the end of the maze, false if it is not
     */
    private boolean isAtEnd() {
        if (navigator.getPosition()[0] == end_position[0] && navigator.getPosition()[1] == end_position[1]) {
            return true;
        }
        return false;
    }
}
