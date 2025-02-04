package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();
    private char[][] maze_array;
    private int row_count;
    private int col_count;
    private int[] entry;
    private int[] exit;

    /**
     * Load the maze from a file
     * 
     * @param file_path Path to the maze file
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
     * @return Coordinates of the entrance
     */
    public int[] getEntry() {
        int entry_col = 0;
        int entry_row = 0;
        for (int i = 0; i < row_count; i++) {
            if (maze_array[i][0] == ' ') {
                entry = new int[]{i, 0};
                break;
            }
        }
        logger.info("** Found entrance at cell: (" + entry_col + ", " + entry_row + ")");
        return entry;
    }

    /**
     * Find the exit of the maze
     * 
     * @return Coordinates of the exit
     */
    public int[] getExit() {
        int exit_col = 0;
        int exit_row = 0;
        for (int i = 0; i < row_count; i++) {
            if (maze_array[i][col_count - 1] == ' ') {
                exit = new int[]{i, col_count - 1};
                break;
            }
        }
        logger.info("** Found exit at cell: (" + exit_col + ", " + exit_row + ")");
        return exit;
    }

    public boolean isWall(int x, int y) {
        if (y < 0 || y >= row_count || x < 0 || x >= col_count) {
            return true;
        }
        return maze_array[y][x] == '#';
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

    public int getRowCount() {
        return row_count;
    }

    public int getColCount() {
        return col_count;
    }

    public char[][] getMaze() {
        return maze_array;
    }
}
