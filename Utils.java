import java.util.Scanner;

public class Utils {

    public static Scanner input = new Scanner(System.in);

    /**
     * Translates a user input into grid coords
     * 
     * For example A1 is turned into 0,0 or J10 is turned into 9,9
     * 
     * @param pos The user string input like "A1"
     * @return An integer array of two numbers
     */

    public static int[] translation(String pos) {
        char firstLetter = pos.charAt(0);
        int firstNumber = Character.getNumericValue(firstLetter) - 10;
        pos = pos.substring(1);
        int secondNumber;
        if (Integer.parseInt(pos) == 10) {
            secondNumber = 9;
        } else {
            secondNumber = Character.getNumericValue(pos.charAt(0)) - 1;
        }
        int[] Coords = { firstNumber, secondNumber };
        return Coords;
    }

    /**
     * Generates a random coordinate on the grid
     * 
     * @return String in the form of A1 or B2 or G10
     */
    public static String generateRandCoord() {
        int randX = (int) (Math.random() * 10);
        int randY = (int) (Math.random() * 10);
        String coord = Coord.numToLetter(randX) + (randY + 1);
        return coord;
    }

    // /**
    // * Generates a random set of coords
    // *
    // * ex (6,2) (0,9) (7,1)
    // *
    // * @return These numbers as a two element array
    // */
    // public int[] genRandCoord(){
    // int[] nums = new int[2];
    // for(int i =0; i< 2; i++){
    // int random = (int) (Math.random() * 10);
    // nums[i] = random;
    // }
    // return nums;
    // }
    // /**
    // * Gets a coordinate from the user in the from A1. Does validation to ensure
    // that the inputed coordinate is on the grid
    // * @return String version of the coordinate that has been validated
    // */
    // public String getCoord(){
    // Boolean valid = true;
    // String Coord;
    // while(true){
    // System.out.print("Enter a coordinate: ");
    // Coord = input.nextLine();
    // if(Coord.charAt(0))
    // }
    // }

    /**
     * Given a String coordinate, this method tells you whether or not it is on the
     * grid
     * Checks the length of the string given as well as the number and letter of the
     * coord
     * 
     * @param coord The coordinate that needs validating
     * @return True if the coordinate is on the grid and false if not
     */
    public static boolean isOnGrid(String coord) {

        // Checking the length of the given coordinate
        if (coord == null || coord.length() < 2 || coord.length() > 3) {
            return false;
        }

        // Checking that the first character is on the axis
        if (coord.charAt(0) < 'A' || coord.charAt(0) > 'J') {
            return false;
        }

        // Checking that the number at the end is not greater than 10
        String numPart = coord.substring(1);
        try {
            int num = Integer.parseInt(numPart);
            if (num < 1 || num > 10) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;

    }

    /**
     * Returns a boolean value depending on whether a ship would fit at the given
     * coord. This method
     * will check whether given a start coord, a ship would fit in any direction
     * (up, down, left, right)
     * Takes borders and other ships into account.
     * Assumes that the initial position is on the board
     * 
     * @param grid   The grid that is being checked
     * @param Length The length of the ship
     * @param coord  The coordinate where the ship stars
     * @return Returns a true value if the ship would fit and false if it doesnt
     */
    public static boolean posFits(Grid grid, int length, String coord) {

        int[] arrayCoords = Utils.translation(coord); // Takes string and converts it to an array of 2 coords

        int row = arrayCoords[0];
        int col = arrayCoords[1];

        Boolean rightValid = true;
        Boolean upValid = true;
        Boolean downValid = true;
        Boolean leftValid = true;

        int directionCounter = 0; // If = 4 then all 4 directions are invalid

        // Checks the validity of each direciton with regard to the borders
        // Add's the length of the ship to the row and coloum and checks whether the
        // result exceeds the bounds
        if (col + (length - 1) > 9) {
            rightValid = false;
            directionCounter++;
        }
        if (row - (length - 1) < 0) {
            upValid = false;
            directionCounter++;
        }
        if (col - (length - 1) < 0) {
            leftValid = false;
            directionCounter++;
        }
        if (col + (length - 1) > 9) {
            downValid = false;
            directionCounter++;
        }

        // If all four directions dont fit return false
        if (directionCounter == 4) {
            return false;
        }


        // Checking for intersections with other ships using for loops to iterate that
        // many times in all directions

        // Checking right direction
        if (rightValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.getCell(row, col + i).getShipPlaceable()) {
                    rightValid = false;
                }
            }
        }

        // Checking up direction
        if (upValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.getCell(row - i, col).getShipPlaceable()) {
                    upValid = false;
                }
            }
        }

        // Checking down direction
        if (downValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.getCell(row + i, col).getShipPlaceable()) {
                    downValid = false;
                }
            }
        }

        // Checking left direction
        if (leftValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.getCell(row, col - i).getShipPlaceable()) {
                    leftValid = false;
                }
            }
        }

        // If all directions are invalid return false
        if(!leftValid && !rightValid && !upValid && !downValid){
            return false;
        }

        System.out.println("Right: " + rightValid);
        System.out.println("Left: " + leftValid);
        System.out.println("Down: " + downValid);
        System.out.println("Up: " + upValid);

        return true;
    }

    // }
    // // Check down direction
    // if (downValid) {
    // for (int i = 0; i < length; i++) {
    // if (!grid.grid[row + i][col].getShipPlaceble()) {
    // downValid = false;
    // possiblePose -= 1;
    // break;
    // }
    // }
    // }
    // // Check left direction
    // if (leftValid) {
    // for (int i = 0; i < length; i++) {
    // if (!grid.grid[row][col - i].getShipPlaceble()) {
    // leftValid = false;
    // possiblePose -= 1;
    // break;
    // }
    // }
    // }
    // if (downValid == false && upValid == false && leftValid == false &&
    // rightValid == false) {
    // return false;
    // }
    // System.out.println("Right Valid: " + rightValid);
    // System.out.println("Left Valid: " + leftValid);
    // System.out.println("Up Valid: " + upValid);
    // System.out.println("Down Valid" + downValid);
    // return true;
    // }

}
