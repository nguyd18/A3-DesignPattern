package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder {
    private int[] current_position;
    private int[] end_position;
    private Direction current_direction;
    private Maze maze;
    private StringBuffer path;

    /**
     * Solve the maze
     */
    public void solveMaze(Maze maze_obj) {
        // System.out.println("Solving maze...");

        while (!isAtEnd()) {
            if (canMoveForward()) {
                moveForward();
            } else {
                if (canTurnRight()) {
                    turnRight();
                } else {
                    if (canTurnLeft()) {
                        turnLeft();
                    } else {
                        turnLeft();
                        turnLeft();
                    }
                }
            }
        }
    }

    private boolean canMoveForward() {
        // Implement logic to check if you can move forward
        return true;
    }

    private void moveForward() {
        if (current_direction == Direction.NORTH) {
            current_position[0]--;
        }
        else if (current_direction == Direction.EAST) {
            current_position[1]++;
        }
        else if (current_direction == Direction.SOUTH) {
            current_position[0]++;
        }
        else if (current_direction == Direction.WEST) {
            current_position[1]--;
        }
    }

    private void turnRight() {
        if (current_direction == Direction.NORTH) {
            current_direction = Direction.EAST;
        }
        else if (current_direction == Direction.EAST) {
            current_direction = Direction.SOUTH;
        }
        else if (current_direction == Direction.SOUTH) {
            current_direction = Direction.WEST;
        }
        else if (current_direction == Direction.WEST) {
            current_direction = Direction.NORTH;
        }
    }

    private void turnLeft() {
        if (current_direction == Direction.NORTH) {
            current_direction = Direction.WEST;
        }
        else if (current_direction == Direction.WEST) {
            current_direction = Direction.SOUTH;
        }
        else if (current_direction == Direction.SOUTH) {
            current_direction = Direction.EAST;
        }
        else if (current_direction == Direction.EAST) {
            current_direction = Direction.NORTH;
        }
    }

    private boolean isAtEnd() {
        // Implement logic to check if you have reached the end of the maze
    }

    /**
     * Generates the correct path of the maze (in canonical form)
     */
    public void generatePath(Maze maze_obj) {
        System.out.println("FFFF");
    }

}
