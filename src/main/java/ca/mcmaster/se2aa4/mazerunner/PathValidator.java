package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathValidator {
    private int[] current_position;
    private int[] end_position;
    private Direction current_direction;
    private Maze maze;
    private static final Logger logger = LogManager.getLogger();

    public PathValidator(String file_path) {
        maze = new Maze();
        maze.loadMaze(file_path);

        current_position = new int[2];
        current_position = maze.getEntry();

        end_position = new int[2];
        end_position = maze.getExit();

        current_direction = Direction.EAST;
    }

    public String validatePath(String path) {
        logger.trace("**** Input path: " + path);
        logger.trace("**** Factorized to canonical: " + convertToCanonical(path));
        String canonical_path = convertToCanonical(path).replaceAll("\\s", "");

        for (int i = 0; i < canonical_path.length(); i++) {
            char current_char = canonical_path.charAt(i);
            if (current_char == 'F') {
                // see if it can move forward
                if (canMove(current_direction)) {
                    moveForward();
                }
                else {
                    // if it sees that it can hit the wall, break
                    // else move forward
                    break;
                }

            }
            else if (current_char == 'R') {
                // turn right
                turnRight();
            }
            else {
                // turn left
                turnLeft();
            }
        }

        if (isAtEnd()) {
            return "correct path";
        }
        return "incorrect path";
    }

    private String convertToCanonical(String path) {
        logger.trace("**** Checking if path is canonical");
        boolean isFactorized = false;
        for (char c : path.toCharArray()) {
            if (Character.isDigit(c)) {
                logger.trace("**** Path is factorized");
                isFactorized = true;
                break;
            }
        }
        if (isFactorized) {
            StringBuffer canonical_path = new StringBuffer();
            int count = 0;
            for (int i = 0; i < path.length(); i++) {
                char current_char = path.charAt(i);
                if (Character.isDigit(current_char)) {
                    count = count * 10 + (current_char - '0');
                    logger.trace("**** Read in a " + count);
                }
                else {
                    if (count == 0) {
                        count = 1;
                    }

                    for (int j = 0; j < count; j++) {
                        canonical_path.append(current_char);
                    }
                    count = 0;
                }
            }
            logger.trace("**** Path is now canonical");
            return canonical_path.toString();
        }
        else {
            logger.trace("**** Path is already canonical");
            return path;
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

    private boolean isAtEnd() {
        if (current_position[0] == end_position[0] && current_position[1] == end_position[1]) {
            return true;
        }
        return false;
    }
}
