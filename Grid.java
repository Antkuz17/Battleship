import java.util.ArrayList;
import java.util.Scanner;

public class Grid {
    public Scanner input = new Scanner(System.in);

    public Coord[][] grid = new Coord[10][10]; // Size is set as 11 x 11 grid outer grid is used for Letter + numbers

    public Grid() { // Constructor for grid object, sets every index to a coord object
        for (int i = 0; i < 10; i++) {
            for (int z = 0; z < 10; z++) {
                grid[i][z] = new Coord(i, z);
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
/**
 * 
 * @param grid The grid being shot at 
 */

    // In here we need to do a number of things
    // 1. update the shots for the player on the player shot board
    // 2. update the shots for the ai on the ai board
    public void Shoot(Grid enemyGrid, Grid shotsGrid){
        System.out.println("Where would you like to shoot?");
        String answer = input.nextLine();
        Coord shotCoord = translation(answer);

        enemyGrid.grid[shotCoord.getRow()][shotCoord.getCol()].setWasShot(true);
        shotsGrid.grid[shotCoord.getRow()][shotCoord.getCol()].setWasShot(true);




        
    }

    /**
     * Translates a user input into grid coords
     * 
     * For example A1 is turned into 0,0 or J10 is turned into 9,9
     * 
     * @param pos The user string input like "A1"
     * @return An coordinate object with the translated coords
     */

    public Coord translation(String pos) {
        char firstLetter = pos.charAt(0);
        int firstNumber = Character.getNumericValue(firstLetter) - 10;
        int secondNumber;
        if (Integer.parseInt(pos.substring(1)) > 10) {
            secondNumber = 11;
        } else if (Integer.parseInt(pos.substring(1)) == 10) {
            secondNumber = 9;
        } else {
            secondNumber = Character.getNumericValue(pos.charAt(1)) - 1;
        }
        System.out.println("First Number: " + firstNumber);
        System.out.println("Second Number: " + secondNumber);
        Coord initPos = new Coord(firstNumber, secondNumber);
        return initPos;
    }

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
