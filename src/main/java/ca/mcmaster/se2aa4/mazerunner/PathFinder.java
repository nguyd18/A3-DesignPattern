package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder {
    private int[] current_position;
    private int[] end_position;
    private Direction current_direction;
    private Maze maze;
    private StringBuffer path;

    public PathFinder(String file_path) {
        maze = new Maze();
        maze.loadMaze(file_path);

        current_position = new int[2];
        current_position = maze.getEntry();

        end_position = new int[2];
        end_position = maze.getExit();


        current_direction = Direction.EAST;
        path = new StringBuffer();
    }

    /**
     * Solve the maze
     */
    public void solveMaze() {
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
        if (current_direction == Direction.NORTH) {
            if (maze.isWall(current_position[0] - 1, current_position[1])) {
                return false;
            }
        }
        else if (current_direction == Direction.EAST) {
            if (maze.isWall(current_position[0], current_position[1] + 1)) {
                return false;
            }
        }
        else if (current_direction == Direction.SOUTH) {
            if (maze.isWall(current_position[0] + 1, current_position[1])) {
                return false;
            }
        }
        else if (current_direction == Direction.WEST) {
            if (maze.isWall(current_position[0], current_position[1] - 1)) {
                return false;
            }
        }
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
        if (current_position[0] == end_position[0] && current_position[1] == end_position[1]) {
            return true;
        }
        return false;
    }

    /**
     * Generates the correct path of the maze (in canonical form)
     */
    public void generatePath(Maze maze_obj) {
        System.out.println("FFFF");
    }

}
