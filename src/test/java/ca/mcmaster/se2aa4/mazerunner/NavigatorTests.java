package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NavigatorTests {
    
    private Navigator navigator;

    /**
     * Test to see if the navigator moves forward
     */
    @Test
    public void testMoveForward() {
        navigator = new Navigator(new int[]{3, 3}, Direction.EAST);
        navigator.moveForward();
        int[] expectedPosition = {3, 4};
        int[] actualPosition = navigator.getPosition();
        assertArrayEquals(expectedPosition, actualPosition);

        navigator.turnLeft(); // Turn to face North
        navigator.moveForward();
        expectedPosition = new int[]{2, 4};
        actualPosition = navigator.getPosition();
        assertArrayEquals(expectedPosition, actualPosition);

        navigator.turnLeft(); // Turn to face West
        navigator.moveForward();
        expectedPosition = new int[]{2, 3};
        actualPosition = navigator.getPosition();
        assertArrayEquals(expectedPosition, actualPosition);

        navigator.turnLeft(); // Turn to face South
        navigator.moveForward();
        expectedPosition = new int[]{3, 3};
        actualPosition = navigator.getPosition();
        assertArrayEquals(expectedPosition, actualPosition);
    }

    /**
     * Test to see if the navigator turns left
     */
    @Test
    public void testTurnLeft() {
        navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
        navigator.turnLeft();
        Direction expectedDirection = Direction.WEST;
        Direction actualDirection = navigator.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }

    /**
     * Test to see if the navigator turns right
     */
    @Test
    public void testTurnRight() {
        navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
        navigator.turnRight();
        Direction expectedDirection = Direction.EAST;
        Direction actualDirection = navigator.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }

    /**
     * Test to see if the navigator returns the left direction 
     * without turning left
     */
    @Test
    public void testGetLeftDirection() {
        navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
        Direction expectedDirection = Direction.WEST;
        Direction actualDirection = navigator.getLeftDirection();
        assertEquals(expectedDirection, actualDirection);
        assertEquals(Direction.NORTH, navigator.getDirection());
    }

    /**
     * Test to see if the navigator returns the right direction
     * without turning right
     */
    @Test
    public void testGetRightDirection() {
        navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
        Direction expectedDirection = Direction.EAST;
        Direction actualDirection = navigator.getRightDirection();
        assertEquals(expectedDirection, actualDirection);
        assertEquals(Direction.NORTH, navigator.getDirection());
    }

    /**
     * Test the method for setting the direction
     */
    @Test
    public void testSettingDirection() {
        navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
        navigator.setDirection(Direction.SOUTH);
        Direction expectedDirection = Direction.SOUTH;
        Direction actualDirection = navigator.getDirection();
        assertEquals(expectedDirection, actualDirection);
    }
}