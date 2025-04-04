package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CommandTests {
    
    private Navigator navigator;

    @BeforeEach
    public void setUp() {
        navigator = new Navigator(new int[]{2,2}, Direction.NORTH);
    }

    /**
     * Test to see if the MoveForwardCommand moves the navigator forward
     */
    @Test
    public void testMoveForwardCommand() {
        Command moveForward = new MoveForwardCommand(navigator);
        moveForward.execute();
        int[] expectedPosition = {1, 2};
        int[] actualPosition = navigator.getPosition();
        assertArrayEquals(expectedPosition, actualPosition);
    }

    /**
     * Test to see if the TurnLeftCommand turns the navigator left
     */
    @Test
    public void testTurnLeft() {
        Command turnLeft = new TurnLeftCommand(navigator);
        turnLeft.execute();
        assertEquals(Direction.WEST, navigator.getDirection());
        turnLeft.execute();
        assertEquals(Direction.SOUTH, navigator.getDirection());
    }

    /**
     * Test to see if the TurnRightCommand turns the navigator right
     */
    @Test
    public void testTurnRight() {
        Command turnRight = new TurnRightCommand(navigator);
        turnRight.execute();
        assertEquals(Direction.EAST, navigator.getDirection());
        turnRight.execute();
        assertEquals(Direction.SOUTH, navigator.getDirection());
    }
}
