
public class Grid {

    public Cell[][] grid = new Cell[10][10]; // 10x10 grid of cell objects

    public Grid() { // Constructor for grid object, sets every index to a cell object
        for (int i = 0; i < 10; i++) {
            for (int z = 0; z < 10; z++) {
                grid[i][z] = new Cell();
            }
        }
    }

    /**
     * Prints the grid to terminal using nested for loops and the print horizontal
     * line method
     */
    public void drawGrid() {
        System.out.println("");
        System.out.print("    1   2   3   4   5   6   7   8   9   10");
        System.out.println("");
        printHorizontalLine();
        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int z = 0; z < 10; z++) {
                System.out.print("|");
                System.out.print(grid[i][z]);
            }
            System.out.println("|");
            if (i < 10) { // Drawing the lines in between
                printHorizontalLine();
            }
        }
    }

    /**
     * Prints a line of iterating dashes and crosses thats used to draw the grid
     */
    public static void printHorizontalLine() {
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print("+");
            for (int j = 0; j < 3; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+"); // End of line
    }

    /**
     * Returns the cell object at a certain location. Fixes the issue of having to
     * call the 2D array within the grid class
     * 
     * @param row The row needed (0-9)
     * @param col The column needed (0-9)
     * @return  Cell object at that location
     */
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

}
