package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NavigatorTests {
    
    /**
     * Test to see if the navigator turns left
     */
    @Test
    public void testTurnLeft() {
        Navigator navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
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
        Navigator navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
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
        Navigator navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
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
        Navigator navigator = new Navigator(new int[]{0, 0}, Direction.NORTH);
        Direction expectedDirection = Direction.EAST;
        Direction actualDirection = navigator.getRightDirection();
        assertEquals(expectedDirection, actualDirection);
        assertEquals(Direction.NORTH, navigator.getDirection());
    }
}