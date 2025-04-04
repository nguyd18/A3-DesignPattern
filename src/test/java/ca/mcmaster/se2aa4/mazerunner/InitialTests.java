package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InitialTests {
    
    /**
     * Test to see if the canonical path converts to a factorized path
     */
    @Test
    public void canonicalToFactorized() {
        String canonicalPath = "F F F F F F F F F F F";
        String expectedFactorizedPath = "11F";
        String actualFactorizedPath = PathFormatter.factorizedPath(canonicalPath);
        assertEquals(expectedFactorizedPath, actualFactorizedPath, "The path factorization is incorrect!");
    }

    /**
     * Test to see if the factorized path converts to a canonical path
     */
    @Test
    public void factorizedToCanonical() {
        String factorizedPath = "6F 2R L F";
        String expectedCanonicalPath = "FFFFFF RR L F";
        String actualCanonicalPath = PathFormatter.convertToCanonical(factorizedPath);
        assertEquals(expectedCanonicalPath, actualCanonicalPath, "The path conversion is incorrect!");
    }

    /**
     * Test turning left
     */
    @Test
    public void testTurnLeft() {
        Direction direction = Direction.NORTH;
        Direction expectedDirection = Direction.WEST;
        Direction actualDirection = direction.turnLeft();
        assertEquals(expectedDirection, actualDirection);
    }

    /**
     * Test turning right
     */
    @Test
    public void testTurnRight() {
        Direction direction = Direction.NORTH;
        Direction expectedDirection = Direction.EAST;
        Direction actualDirection = direction.turnRight();
        assertEquals(expectedDirection, actualDirection);
    }

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

    /**
     * Test to see if the path finder finds the correct path to the small maze
     */
    @Test
    public void solveSmallMaze() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/small.maz.txt");
        MazeSolver solver = new PathFinder(maze);
        String expectedPath = "F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F";
        String actualPath = solver.findPath();
        assertEquals(expectedPath, actualPath);
    }

    /**
     * Test to see if the path validator will validate the correct path to the small maze
     */
    @Test
    public void validateSmallMaze() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/small.maz.txt");
        PathValidator validator = new PathValidator(maze);
        String path = "F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F";
        String expectedValidation = "correct path";
        String actualValidation = validator.validatePath(path);
        assertEquals(expectedValidation, actualValidation);
    }
}