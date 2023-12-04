import java.util.ArrayList;

public class Maze implements IMaze {
    private char[][] maze = null;
    private final ArrayList<int[]> exits;
    private int maxRecursionDepth;

    // default constructor
    public Maze(char[][] maze) {
        if (maze == null || maze.length == 0 || maze.length != maze[0].length) {
            throw new IllegalArgumentException();
        }
        this.maze = maze;
        this.exits = new ArrayList<int[]>();
        this.maxRecursionDepth = 0;
    }
    public boolean locateExits(final int row, final int col, int depth) {
        if (depth > maxRecursionDepth) {
            maxRecursionDepth = depth;
        }
        if (depth == 0) {
            maze[row][col] = 'S';
        }
        if (maze[row][col] == '*' || maze[row][col] == '.' || maze[row][col] == 'X') {
            return false;
        }
        depth++;
        if (row == 0 || row == maze.length - 1 || col == 0 || col == maze[0].length - 1) {
            int[] exitC = {row, col};
            exits.add(exitC);
            maze[row][col] = 'X';
            return true;
        }

        if (maze[row][col] != 'S') {
            maze[row][col] = '.';
        }

        boolean foundExit = false;
        for (int[] dir : directions) {
            foundExit |= locateExits(row + dir[0], col + dir[1], depth);
        }

        return foundExit;
    }

    public ArrayList<int[]> getListOfExits() {
        return exits;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public char[][] getMaze() {
        return this.maze;
    }

    /**
     * This method prints the entire maze in the console for debugging.
     */
    public void dumpMaze() {
        System.out.println();
        for (char[] chars : maze) {
            System.out.println(chars);
        }
        System.out.print("Exits: ");
        for (int[] exit : exits) {
            System.out.print("(" + exit[0] + ", " + exit[1] + ")");
        }
        System.out.println();

    }

    // This is used to explore the directions in order: east, southeast, south, southwest, west, northwest, north, northeast
    private static final int[][] directions = {
            {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
    };
}