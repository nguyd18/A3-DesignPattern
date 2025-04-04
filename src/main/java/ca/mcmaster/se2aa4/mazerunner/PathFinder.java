package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder implements MazeSolver{

    private Navigator navigator;
    private int[] end_position;
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
        this.end_position = maze.getExit();
        this.canonical_path = new StringBuffer();
        navigator = new Navigator(maze.getEntry(), Direction.EAST);
    }

    /**
     * Finds the solution path to the maze
     */
    @Override
    public String findPath() {

        Command command;

        logger.info("** Trying to solve the maze");
        logger.trace("**** Current Position. Row: " + navigator.getPosition()[0] + " Col: " + navigator.getPosition()[1]);

        while (!isAtEnd()) {
            logger.trace("**** Current Direction: " + navigator.getDirection());

            // Check the diagonal cell (front-right)
            if (canMove(navigator.getDirection()) && canMove(navigator.getDirection().getRightDirection(navigator.getDirection()))) {
                navigator.turnRight();
                // moveForward();
                command = new MoveForwardCommand(navigator);
                command.execute();
                logger.trace("**** Moved forward and turned right to: (" + navigator.getPosition()[0] + ", " + navigator.getPosition()[1] + ")");
                logger.trace("**** R F");
                canonical_path.append(" R F");
            }

            // Check the cell in front
            if (canMove(navigator.getDirection())) {
                // moveForward();
                command = new MoveForwardCommand(navigator);
                command.execute();
                logger.trace("**** Moved forward to: (" + navigator.getPosition()[0] + ", " + navigator.getPosition()[1] + ")");
                logger.trace("**** F");
                canonical_path.append("F");
                continue;
            }

            // Check the cell to the right
            if (canMove(navigator.getDirection().getRightDirection(navigator.getDirection()))) {
                navigator.turnRight();
                // moveForward();
                command = new MoveForwardCommand(navigator);
                command.execute();
                logger.trace("**** Turned right and moved forward to: (" + navigator.getPosition()[0] + ", " + navigator.getPosition()[1] + ")");
                logger.trace("**** R F");
                canonical_path.append(" R F");
                continue;
            }

            // Check the cell to the left
            if (canMove(navigator.getDirection().getLeftDirection(navigator.getDirection()))) {
                navigator.turnLeft();
                // moveForward();
                command = new MoveForwardCommand(navigator);
                command.execute();
                logger.trace("**** Turned left and moved forward to: (" + navigator.getPosition()[0] + ", " + navigator.getPosition()[1] + ")");
                logger.trace(" L F");
                canonical_path.append(" L F");
                continue;
            }

            // If it reaches a dead end
            navigator.turnLeft();
            navigator.turnLeft();
            // moveForward();
            command = new MoveForwardCommand(navigator);
            command.execute();
            logger.trace("**** Dead end. Turned around and moved forward to: (" + navigator.getPosition()[0] + ", " + navigator.getPosition()[1] + ")");
            logger.trace(" L L F");
            canonical_path.append(" L L F");
        }
        logger.info("** Maze has been solved!");
        // System.out.println(PathFormatter.factorizedPath(canonical_path.toString()));
        return PathFormatter.factorizedPath(canonical_path.toString());
    }

    /**
     * Checks if the finder can move by checking its adjacent cells
     * 
     * @param d the direction the finder is facing
     * @return true if can move, false if there is a wall in the way
     */
    private boolean canMove(Direction d) {
        int[] current_position = navigator.getPosition();
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
     * Moves the finder forward to the next cell
     */
    private void moveForward() {
        
    }

    /**
     * @return true if the finder is at the end of the maze, false if it is not
     */
    private boolean isAtEnd() {
        if (navigator.getPosition()[0] == end_position[0] && navigator.getPosition()[1] == end_position[1]) {
            return true;
        }
        return false;
    }
}
