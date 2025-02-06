package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathValidator {
    private int[] current_position;
    private int[] end_position;
    private Maze maze;
    private static final Logger logger = LogManager.getLogger();

    public PathValidator(String file_path) {
        maze = new Maze();
        maze.loadMaze(file_path);
    }

    public void validatePath(String path) {
        logger.trace("**** Input path: " + path);
        logger.trace("**** Factorized to canonical: " + convertToCanonical(path));

        // if (path.length() == 0) {
        //     return "incorrect path";
        // }

        // for (int i = 0; i < path.length(); i++) {

        // }
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
}
