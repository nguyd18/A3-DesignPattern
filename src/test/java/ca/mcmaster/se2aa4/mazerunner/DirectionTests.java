package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTests {
    
    private Direction direction;

    /**
     * Test turning left
     */
    @Test
    public void testTurnLeft() {
        direction = Direction.NORTH;
        assertEquals(Direction.WEST, direction.turnLeft());
        assertEquals(Direction.SOUTH, direction.turnLeft().turnLeft());
        assertEquals(Direction.EAST, direction.turnLeft().turnLeft().turnLeft());
    }

    /**
     * Test turning right
     */
    @Test
    public void testTurnRight() {
        direction = Direction.NORTH;
        assertEquals(Direction.EAST, direction.turnRight());
        assertEquals(Direction.SOUTH, direction.turnRight().turnRight());
        assertEquals(Direction.WEST, direction.turnRight().turnRight().turnRight());
    }

    /**
     * Test looking left without turning
     */
    @Test
    public void testLookingLeft() {
        direction = Direction.NORTH;
        assertEquals(Direction.WEST, direction.getLeftDirection(direction));
        direction = Direction.WEST;
        assertEquals(Direction.SOUTH, direction.getLeftDirection(direction));
        direction = Direction.SOUTH;
        assertEquals(Direction.EAST, direction.getLeftDirection(direction));
        direction = Direction.EAST;
        assertEquals(Direction.NORTH, direction.getLeftDirection(direction));
    }
}