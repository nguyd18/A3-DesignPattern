package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();
    private char[][] maze_array;
    private int row_count;
    private int col_count;
    private int entry_row;
    private int entry_col;
    private int exit_row;
    private int exit_col;

    /**
     * Load the maze from a file
     */
    public void loadMaze(String file_path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_path));
            String line;
            row_count = 0;
            col_count = 0;

            while ((line = reader.readLine()) != null) {
                row_count++;
                col_count = Math.max(col_count, line.length()); // In case if the maze is not rectangular
            }
            maze_array = new char[row_count][col_count];
            reader.close();

            reader = new BufferedReader(new FileReader(file_path));
            int row_index = 0;
            while ((line = reader.readLine()) != null) {
                for (int col_index = 0; col_index < line.length(); col_index++) {
                    maze_array[row_index][col_index] = line.charAt(col_index);
                }
                row_index++;
            }
            logger.info("** Maze loaded successfully. Size: " + row_count + "x" + col_count);
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
        for (int i = 0; i < row_count; i++) {
            for (int j = 0; j < col_count; j++) {
                System.out.print(maze_array[i][j]);
            }
            System.out.println();
        }
    }
}
