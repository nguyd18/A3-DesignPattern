package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MazeLoaderTests {
    
    /**
     * Test to see if the wall is correctly identified
     */
    @Test
    public void testIsWall() {
        Maze maze = new Maze();
        maze.initialize(10, 10);
        maze.fillRow(0, "##########");
        maze.fillRow(1, "#        #");
        maze.fillRow(2, "#        #");
        maze.fillRow(3, "#        #");
        maze.fillRow(4, "#        #");
        maze.fillRow(5, "#        #");
        maze.fillRow(6, "#        #");
        maze.fillRow(7, "#        #");
        maze.fillRow(8, "#        #");
        maze.fillRow(9, "##########");

        assertTrue(maze.isWall(0, 0), "Expected a wall at (0, 0)");
        assertFalse(maze.isWall(1, 1), "Expected no wall at (1, 1)");
    }

    /**
     * Test to see if the maze loader loads the maze correctly
     */
    @Test
    public void testMazeLoader() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/small.maz.txt");
        assertNotNull(maze, "Maze should not be null");
        assertFalse(maze.isWall(8, 0), "Entry should not be a wall");
        assertFalse(maze.isWall(5, 10), "Exit should not be a wall");
    }

    /**
     * Test to see if maze knows the start point is
     */
    @Test
    public void testMazeStartPoint() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/small.maz.txt");
        int[] actual = maze.getEntry();
        int[] expected = {8, 0};
        assertArrayEquals(expected, actual, "The start point is incorrect");  
    }

    /**
     * Test to see if maze knows the exit point is
     */
    @Test
    public void testMazeExitPoint() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/small.maz.txt");
        int[] actual = maze.getExit();
        int[] expected = {5, 10};
        assertArrayEquals(expected, actual, "The exit point is incorrect");  
    }

}
