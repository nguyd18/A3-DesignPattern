package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Maze class represents a maze as a 2D character array
 * and provides methods to load the maze from a file,
 * find the entrance and exit, and check if a cell is a wall
 */
public class Maze {
    
    private static final Logger logger = LogManager.getLogger();
    private char[][] maze_array;
    private int row_count;
    private int col_count;
    private int[] entry;
    private int[] exit;

    /**
     * Initializes a 2D character array to store the maze 
     * 
     * @param rows the amount of rows in the maze
     * @param cols the amount of columns in the maze
     */
    public void initialize(int rows, int cols) {
        this.row_count = rows;
        this.col_count = cols;
        maze_array = new char[row_count][col_count];
    }

    /**
     * Stores the maze one row at a time
     * if the row is incomplete, it will automatically fill in with whitespaces
     * 
     * @param row_index the current row of the maze
     * @param line the row of the maze consisting of spaces and '#'
     */
    public void fillRow(int row_index, String line) {
        for (int col_index = 0; col_index < line.length(); col_index++) {
            maze_array[row_index][col_index] = line.charAt(col_index);
        }
        for (int col_index = line.length(); col_index < col_count; col_index++) {
            maze_array[row_index][col_index] = ' ';
        }
    }

    /**
     * Load the maze from a file
     * 
     * @param file_path path to the maze file
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
                for (int col_index = line.length(); col_index < col_count; col_index++) {
                    maze_array[row_index][col_index] = ' ';
                }
                row_index++;
            }
            logger.info("** Maze loaded successfully. Size: " + row_count + "x" + col_count);
            reader.close();

        } catch(FileNotFoundException e) {
            logger.error("INVALID FILE PATH");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Find the entrance of the maze
     * 
     * @return coordinates of the entrance format (row, col)
     */
    public int[] getEntry() {
        int entry_row = 0;
        int entry_col = 0;
        for (int i = 0; i < row_count; i++) {
            if (maze_array[i][entry_col] == ' ') {
                entry_row = i;
                entry = new int[]{entry_row, entry_col};
                break;
            }
        }
        logger.info("** Found entrance. Row: " + entry[0] + " Col: " + entry[1]);
        return entry;
    }

    /**
     * Find the exit of the maze
     * 
     * @return coordinates of the exit
     */
    public int[] getExit() {
        int exit_row = 0;
        int exit_col = col_count - 1;
        for (int i = 0; i < row_count; i++) {
            if (maze_array[i][exit_col] == ' ') {
                exit_row = i;
                exit = new int[]{exit_row, exit_col};
                break;
            }
        }
        logger.info("** Found exit. Row: " + exit[0] + " Col: " + exit[1]);
        return exit;
    }

    /**
     * Sees if the cell is a wall
     * 
     * @param row row of the cell
     * @param col column of the cell
     * @return true if the cell is a wall, false if the cell is not a wall
     */
    public boolean isWall(int row, int col) {
        if (row < 0 || row >= row_count || col < 0 || col >= col_count) {
            return true;
        }
        return maze_array[row][col] == '#';
    }
}
