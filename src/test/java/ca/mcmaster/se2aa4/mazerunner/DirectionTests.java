package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTests {
    
    /**
     * Test turning left
     */
    @Test
    public void testTurnLeft() {
        Direction direction = Direction.NORTH;
        assertEquals(Direction.WEST, direction.turnLeft());
        assertEquals(Direction.SOUTH, direction.turnLeft().turnLeft());
        assertEquals(Direction.EAST, direction.turnLeft().turnLeft().turnLeft());
    }

    /**
     * Test turning right
     */
    @Test
    public void testTurnRight() {
        Direction direction = Direction.NORTH;
        assertEquals(Direction.EAST, direction.turnRight());
        assertEquals(Direction.SOUTH, direction.turnRight().turnRight());
        assertEquals(Direction.WEST, direction.turnRight().turnRight().turnRight());
    }
}