package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();
    private char[][] maze_array;

    /**
     * Load the maze from a file
     */
    public void loadMaze(String file_path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_path));
            String line;
            int row_count = 0;
            int col_count = 0;

            while ((line = reader.readLine()) != null) {
                row_count++;
                col_count = Math.max(col_count, line.length()); // In case if the maze is not rectangular
            }
            maze_array = new char[row_count][col_count];
            reader.close();

            reader = new BufferedReader(new FileReader(file_path));
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    maze_array[row][col] = line.charAt(col);
                }
            }
            logger.info("** Maze loaded successfully");
            reader.close();

        } catch (Exception e) {
            logger.error("INVALID FILE PATH");
        }
    }

    /**
     * Find the entrance of the maze
     */
    public void findEntry() {

    }

    /**
     * Find the exit of the maze
     */
    public void findExit() {

    }

    /**
     * Print the maze on to the terminal
     */
    public void printMaze() {

    }
}
