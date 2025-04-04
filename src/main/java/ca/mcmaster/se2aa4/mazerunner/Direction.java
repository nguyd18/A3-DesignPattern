package ca.mcmaster.se2aa4.mazerunner;

/**
 * Enum representing the four cardinal directions
 */
public enum Direction {
    
    NORTH, EAST, SOUTH, WEST;

    /**
     * @return the direction that results from turning right
     */
    public Direction turnRight() {
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return this;
        }
    }

    /**
     * @return the direction that results from turning left
     */
    public Direction turnLeft() {
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                return this;
        }
    }

    /**
     * Get the right adjacent cell of the finder without turning right
     * 
     * @param d current direction of the finder
     * @return the direction that results of looking right
     */
    public Direction getRightDirection(Direction d) {
        if (d == Direction.NORTH) {
            return Direction.EAST;
        }
        else if (d == Direction.EAST) {
            return Direction.SOUTH;
        }
        else if (d == Direction.SOUTH) {
            return Direction.WEST;
        }
        else {
            return Direction.NORTH;
        }
    }

    /**
     * Get the left adjacent cell of the finder without turning left
     * 
     * @param d current direction of the finder
     * @return the direction that results of looking left
     */
    public Direction getLeftDirection(Direction d) {
        if (d == Direction.NORTH) {
            return Direction.WEST;
        }
        else if (d == Direction.WEST) {
            return Direction.SOUTH;
        }
        else if (d == Direction.SOUTH) {
            return Direction.EAST;
        }
        else {
            return Direction.NORTH;
        }
    }
}