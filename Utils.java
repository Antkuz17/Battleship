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
        String coord = numToLetter(randX) + (randY + 1);
        return coord;
    }


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
     * Returns a int value depending on whether a ship would fit at the given
     * coord. This method
     * will check whether given a start coord, a ship would fit in any direction
     * (up, down, left, right)
     * Takes borders and other ships into account.
     * Assumes that the initial position is on the board. And checks if the initial pose is a valid one
     * Returns 0 if no poses, or 1-4 depending on how many poses work
     * 
     * @param grid   The grid that is being checked
     * @param Length The length of the ship
     * @param coord  The coordinate where the ship stars
     * @return Returns a int value if depending on the number of valid positions
     */
    public static int posFits(Grid grid, int length, String coord) {

        int[] arrayCoords = Utils.translation(coord); // Takes string and converts it to an array of 2 coords

        int row = arrayCoords[0];
        int col = arrayCoords[1];

        Boolean rightValid = true;
        Boolean upValid = true;
        Boolean downValid = true;
        Boolean leftValid = true;

        int directionCounter = 0; // If = 4 then all 4 directions are invalid\

        // If the initial position cant have a ship on it, return false
        if(!grid.getCell(row, col).getShipPlaceable()){
            return 0;
        }

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
        if (row + (length - 1) > 9) {
            downValid = false;
            directionCounter++;
        }

        // If all four directions dont fit return false
        if (directionCounter == 4) {
            return 0;
        }

        // Checking for intersections with other ships using for loops to iterate that
        // many times in all directions

        // Checking right direction
        if (rightValid) {
            for (int i = 1; i < length; i++) {
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
        if (!leftValid && !rightValid && !upValid && !downValid) {
            return 0;
        }

        int counter = 0;
        if (rightValid) {
            counter++;
        }
        if (leftValid) {
            counter++;
        }
        if (upValid) {
            counter++;
        }
        if (downValid) {
            counter++;
        }

        return counter;
    }

    /**
     * Gives a boolean of whether the right direction would fit the ship given the
     * starting coordinate
     * 
     * @param grid   The grid being placed on
     * @param length Length of ship
     * @param coord  Start coordinate of the ship
     * @return True if it does fit and false if it doesnt
     */
    public static Boolean rightValid(Grid grid, int length, String coord) {

        int[] arrayCoords = Utils.translation(coord); // Takes string and converts it to an array of 2 coords

        int row = arrayCoords[0];
        int col = arrayCoords[1];
        try{
        for (int i = 1; i < length; i++) {
            if (!grid.getCell(row, col + i).getShipPlaceable()) {
                return false;
            }
        }
    }catch (ArrayIndexOutOfBoundsException e){
        return false;
    }
        return true;
    }

    /**
     * Gives a boolean of whether the left direction would fit the ship given the
     * starting coordinate
     * 
     * @param grid   The grid being placed on
     * @param length Length of ship
     * @param coord  Start coordinate of the ship
     * @return True if it does fit and false if it doesnt
     */
    public static Boolean leftValid(Grid grid, int length, String coord) {

        int[] arrayCoords = Utils.translation(coord); // Takes string and converts it to an array of 2 coords

        int row = arrayCoords[0];
        int col = arrayCoords[1];
        try{
        for (int i = 1; i < length; i++) {
            if (!grid.getCell(row, col - i).getShipPlaceable()) {
                return false;
            }
        }
    }  catch(ArrayIndexOutOfBoundsException e){
        return false;
    }
        return true;
    }

    /**
     * Gives a boolean of whether the up direction would fit the ship given the
     * starting coordinate
     * 
     * @param grid   The grid being placed on
     * @param length Length of ship
     * @param coord  Start coordinate of the ship
     * @return True if it does fit and false if it doesnt
     */
    public static Boolean upValid(Grid grid, int length, String coord) {

        int[] arrayCoords = Utils.translation(coord); // Takes string and converts it to an array of 2 coords

        int row = arrayCoords[0];
        int col = arrayCoords[1];
        try{
        for (int i = 1; i < length; i++) {
            if (!grid.getCell(row - i, col).getShipPlaceable()) {
                return false;
            }
        }
    } catch (ArrayIndexOutOfBoundsException e){
        return false;
    }
        return true;
    }

    /**
     * Gives a boolean of whether the down direction would fit the ship given the
     * starting coordinate
     * 
     * @param grid   The grid being placed on
     * @param length Length of ship
     * @param coord  Start coordinate of the ship
     * @return True if it does fit and false if it doesnt
     */
    public static Boolean downValid(Grid grid, int length, String coord) {

        int[] arrayCoords = Utils.translation(coord); // Takes string and converts it to an array of 2 coords

        int row = arrayCoords[0];
        int col = arrayCoords[1];
        try{
        for (int i = 1; i < length; i++) {
            if (!grid.getCell(row + i, col).getShipPlaceable()) {
                return false;
            }
        }
    }catch (ArrayIndexOutOfBoundsException e){
        return false;
    }
        return true;
    }

    // Gets user input for a coordinate
    // Used so that only one scanner exists accross all classes to avoid input issues
    public static String getCoord(){
        return input.nextLine();
    }

    /**
     * Takes a letter and translates that letter into a corresponding number
     * 
     * @param letter The letter being translated (ex. "A")
     * @return Corresponding number which in this case would be 1
     */
    public static String numToLetter(int num) {
        switch (num) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            default:
                return "?";
        }
    }

    /**
     * Takes a letter and translates that letter into a corresponding number (0–9)
     * 
     * @param letter The letter being translated (ex. "A")
     * @return Corresponding number (0 for A, 1 for B, ..., 9 for J)
     */
    public int letterToNum(String letter) {
        switch (letter.toUpperCase()) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            default:
                return -1; // Invalid input
        }
    }
}
