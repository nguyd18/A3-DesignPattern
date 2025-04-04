package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * MazeLoader class is responsible for loading the maze from a file
 * and initializing the maze object with the data from the file
 */
public class MazeLoader {
    
    private static final Logger logger = LogManager.getLogger();

    /**
     * Load the maze from a file
     * 
     * @param file_path File path name of the maze
     * @return the maze object
     */
    public Maze load(String file_path) {
        Maze maze = new Maze();
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            int row_count = 0;
            int col_count = 0;
            while ((line = reader.readLine()) != null) {
                row_count++;
                col_count = Math.max(col_count, line.length());
            }

            maze.initialize(row_count, col_count);
        } catch (Exception e) {
            logger.error("Error loading maze: " + e.getMessage());
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                maze.fillRow(row, line);
                row++;
            }
        } catch (Exception e) {
            logger.error("Error reading maze content: " + e.getMessage());
        }
        
        logger.info("** Maze loaded successfully.");
        return maze;
    }
}
