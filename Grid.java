

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
     * Prints the grid to terminal using nested for loops and the print horizontal line method
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

    /**
     * Prints a line of iterating dashes and crosses thats used to draw the grid
     */
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
    //  * Deals with the player shooting at the ai
    //  * First Position validation occurs making sure the shot is on the board and has not been shot yet
    //  * If valid, then positions are updated on all approriate boards
    //  * @param enemyGrid The grid of the person being shot at
    //  * @param shotsGrid Your own shots grid that monitors your shots
    //  */
    // public void playerShoot(Grid enemyGrid, Grid shotsGrid){
    //     System.out.println("Where would you like to shoot?");
    //     String answer = input.nextLine();
    //     Coord shotCoord = translation(answer);
    //     while(shotCoord.getCol() > 9 || shotCoord.getCol() < 0 || shotCoord.getRow() > 9 || shotCoord.getRow() < 0 || enemyGrid.grid[shotCoord
    //             .getRow()][shotCoord.getCol()].getwasShot()){ // Validates the pose is on board
    //         System.out.println("Shot lays outside grid try again");
    //         System.out.println("Where would you like to shoot?");
    //         answer = input.nextLine();
    //         shotCoord = translation(answer);
    //     }
    //     enemyGrid.grid[shotCoord.getRow()][shotCoord.getCol()].setWasShot(true);
    //     shotsGrid.grid[shotCoord.getRow()][shotCoord.getCol()].setWasShot(true);
    // }

}
