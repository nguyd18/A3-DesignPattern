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
     * Test to see if the path finder finds the correct path to the straight maze
     */
    @Test
    public void solveStraightMaze() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/straight.maz.txt");
        MazeSolver solver = new PathFinder(maze);
        String expectedPath = "4F";
        String actualPath = solver.findPath();
        assertEquals(expectedPath, actualPath);
    }

    /**
     * Test to see if the path validator will validate the correct path to the straight maze
     */
    @Test
    public void validateStraightMaze() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/straight.maz.txt");
        PathValidator validator = new PathValidator(maze);
        String path = "4F";
        String expectedValidation = "correct path";
        String actualValidation = validator.validatePath(path);
        assertEquals(expectedValidation, actualValidation);
    }

    /**
     * Test to see if the path finder finds the correct path to the tiny maze
     */
    @Test
    public void solveTinyMaze() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/tiny.maz.txt");
        MazeSolver solver = new PathFinder(maze);
        String expectedPath = "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F";
        String actualPath = solver.findPath();
        assertEquals(expectedPath, actualPath);
    }

    /**
     * Test to see if the path validator will validate the correct path to the tiny maze
     */
    @Test
    public void validateTinyMaze() {
        MazeLoader loader = new MazeLoader();
        Maze maze = loader.load("./examples/tiny.maz.txt");
        PathValidator validator = new PathValidator(maze);
        String path = "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F";
        String expectedValidation = "correct path";
        String actualValidation = validator.validatePath(path);
        assertEquals(expectedValidation, actualValidation);
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