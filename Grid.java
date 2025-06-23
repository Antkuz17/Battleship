import java.util.ArrayList;

public class Grid {
    public Coord[][] grid = new Coord[10][10]; // Size is set as 11 x 11 grid outer grid is used for Letter + numbers

    public ArrayList<Coord> takenSquares = new ArrayList<>();

    public Grid() { // Constructor for grid object, sets every index to a coord object
        for (int i = 0; i < 10; i++) {
            for (int z = 0; z < 10; z++) {
                grid[i][z] = new Coord(i, z + 1);
            }
        }
    }

    /**
     * Prints the grid to terminal using nested for loops
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
            if (i < 10){ // Drawing the lines in between
                printHorizontalLine();
            }
        }
    }

    public static void printHorizontalLine(){
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print("+");
            for (int j = 0; j < 3; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+"); // End of line
    }
    // /**
    // * Places a missed marker "~" at a position on the board
    // *
    // * @param x The number shot at
    // * @param y The letter shot at
    // */
    // public void placeMissMarker(String x, String y){
    // int ycoord = letterToNum(y);
    // int xcoord = Integer.parseInt(x);
    // xcoord += 1;
    // ycoord += 1;
    // grid[ycoord -1][xcoord -1] = " ~ ";
    // }

    // /**
    // * Places a hit marker "" at a position on the board
    // *
    // * @param x The number shot at
    // * @param y The letter shot at
    // */
    // public void placeHitMarker(String x, String y) {
    // int ycoord = letterToNum(y);
    // int xcoord = Integer.parseInt(x);
    // xcoord += 1;
    // ycoord += 1;
    // grid[ycoord - 1][xcoord - 1] = " X ";
    // }

    public void missed(int x, int y) {

    }

}
