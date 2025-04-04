package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathFinderTests {

    private MazeLoader loader;
    
    /**
     * Test to see if the path finder finds the correct path to the straight maze
     */
    @Test
    public void solveStraightMaze() {
        loader = new MazeLoader();
        Maze maze = loader.load("./examples/straight.maz.txt");
        MazeSolver solver = new PathFinder(maze);
        String expectedPath = "4F";
        String actualPath = solver.findPath();
        assertEquals(expectedPath, actualPath);
    }

    /**
     * Test to see if the path finder finds the correct path to the tiny maze
     */
    @Test
    public void solveTinyMaze() {
        loader = new MazeLoader();
        Maze maze = loader.load("./examples/tiny.maz.txt");
        MazeSolver solver = new PathFinder(maze);
        String expectedPath = "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F";
        String actualPath = solver.findPath();
        assertEquals(expectedPath, actualPath);
    }

    /**
     * Test to see if the path finder finds the correct path to the small maze
     */
    @Test
    public void solveSmallMaze() {
        loader = new MazeLoader();
        Maze maze = loader.load("./examples/small.maz.txt");
        MazeSolver solver = new PathFinder(maze);
        String expectedPath = "F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F";
        String actualPath = solver.findPath();
        assertEquals(expectedPath, actualPath);
    }

}
