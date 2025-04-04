package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathValidatorTests {
    
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
