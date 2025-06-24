
// Length
// ship initial pose
// hits 
// Death
import java.util.Scanner;

public class Ship {
    public Scanner input = new Scanner(System.in);

    private int length;
    private Coord initPos; // where the ship start
    private boolean isSunk;
    private String vector; // From the initial pose does the ship point left right up down

    private boolean rightValid = true;
    private boolean leftValid = true;
    private boolean upValid = true;
    private boolean downValid = true;
    private int possiblePose;

    public Ship(int length) {
        this.length = length;
        this.initPos = null;
        this.isSunk = false;
        this.vector = "down";
    }

    public void placeShip(Grid grid) {
        System.out.println("Where do you want the " + (length - 1) + " ship to start?: ");
        String potentialPos = input.nextLine(); // Potential pos still needs to be checked for validity
        Coord initPos = translation(potentialPos); // Translates from String "A1" to coordinate notation 0,0
        while (!isOnGridNotOnOtherShip(initPos, grid)) {
            System.out.println("Not a valid position try again");
            System.out.println("Where do you want the " + (length - 1) + " ship to start?: ");
            potentialPos = input.nextLine();
            initPos = translation(potentialPos);
        }
        boolean answer = initPosValid(initPos);
        System.out.println("initPoseValid" + answer);

        // Validading ship against take squares

        initPos.setHasShip(true);
        initPos.setShipPlacable(false);
        grid.grid[initPos.getRow()][initPos.getCol()] = initPos;
        // Adding the ships coords
        String userAnswer = "R";
        if (possiblePose == 1) {
            userAnswer = "";
        }

        grid.drawGrid();
        while (userAnswer.equals("R")) {
            if (rightValid) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow()][initPos.getCol() + i].setHasShip(true);
                }
                grid.drawGrid();
                System.out.print("Enter R to rotate ship(if not click anyother key): ");
                userAnswer = input.nextLine();
                if (!userAnswer.equalsIgnoreCase("R")) {
                    vector = "right";
                    for (int i = 1; i < (length + 3); i++) {
                        grid.grid[initPos.getRow()][initPos.getCol() - 2 + i].setShipPlacable(false);
                        grid.grid[initPos.getRow() + 1][initPos.getCol() - 2 + i].setShipPlacable(false);
                        grid.grid[initPos.getRow() - 1][initPos.getCol() - 2 + i].setShipPlacable(false);
                    }
                    break;
                }
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow()][initPos.getCol() + i].setHasShip(false);
                }
            }
            if (downValid) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow() + i][initPos.getCol()].setHasShip(true);
                }
                grid.drawGrid();
                System.out.print("Enter R to rotate ship(if not click anyother key): ");
                userAnswer = input.nextLine();
                if (!userAnswer.equalsIgnoreCase("R")) {
                    vector = "down";
                    for (int i = 1; i < (length + 3); i++) {
                        grid.grid[initPos.getRow() - 2 + i][initPos.getCol()].setShipPlacable(false);
                        grid.grid[initPos.getRow() - 2 + i][initPos.getCol() + 1].setShipPlacable(false);
                        grid.grid[initPos.getRow() - 2 + i][initPos.getCol() - 1].setShipPlacable(false);
                    }
                    break;
                }
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow() + i][initPos.getCol()].setHasShip(false);
                }
            }
            if (leftValid) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow()][initPos.getCol() - i].setHasShip(true);
                }
                grid.drawGrid();
                System.out.print("Enter R to rotate ship(if not click anyother key): ");
                userAnswer = input.nextLine();
                if (!userAnswer.equalsIgnoreCase("R")) {
                    vector = "left";
                    for (int i = 1; i < (length + 3); i++) {
                        grid.grid[initPos.getRow()][initPos.getCol() + 2 - i].setShipPlacable(false);
                        grid.grid[initPos.getRow() + 1][initPos.getCol() + 2 - i].setShipPlacable(false);
                        grid.grid[initPos.getRow() - 1][initPos.getCol() + 2 - i].setShipPlacable(false);
                    }
                    break;
                }
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow()][initPos.getCol() - i].setHasShip(false);
                }
            }
            if (upValid) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow() - i][initPos.getCol()].setHasShip(true);
                }
                grid.drawGrid();
                System.out.print("Enter R to rotate ship(if not click anyother key): ");
                userAnswer = input.nextLine();
                if (!userAnswer.equalsIgnoreCase("R")) {
                    vector = "up";
                    for (int i = 1; i < (length + 3); i++) {
                        grid.grid[initPos.getRow() + 2 - i][initPos.getCol()].setShipPlacable(false);
                        grid.grid[initPos.getRow() + 2 - i][initPos.getCol() + 1].setShipPlacable(false);
                        grid.grid[initPos.getRow() + 2 - i][initPos.getCol() - 1].setShipPlacable(false);
                    }
                    break;
                }
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow() - i][initPos.getCol()].setHasShip(false);
                }
            }
        }
    }

    /**
     * Returns whether or not a position is a valid one
     * 
     * Valid - At least one orientation in that initial pose will result in a legal
     * position
     * 
     * Logic- Checks above and below the initpose to make sure that is a point where
     * the ship would fit borders
     * 
     * @return boolean value of true if its placeable and false if not
     */
    public boolean initPosValid(Coord pose) {
        possiblePose = 0;

        if ((pose.getRow() + (length - 1)) > 9) {
            downValid = false;
        } else {
            possiblePose += 1;
        }
        if ((pose.getRow() - (length - 1)) < 0) {
            upValid = false;
        } else {
            possiblePose += 1;
        }
        if ((pose.getCol() - (length - 1)) < 0) {
            leftValid = false;
        } else {
            possiblePose += 1;
        }
        if ((pose.getCol() + (length - 1)) > 9) {
            rightValid = false;
        } else {
            possiblePose += 1;
        }
        if (downValid == false && upValid == false && leftValid == false && rightValid == false) {
            return false;
        }
        System.out.println("Right Valid: " + rightValid);
        System.out.println("Left Valid: " + leftValid);
        System.out.println("Up Valid: " + upValid);
        System.out.println("Down Valid" + downValid);
        return true;
    }

    /**
     * Returns a true or false depending on whether the given coordinate is on the grid and isnt on a invalid square
     * 
     * @return Return true if on grid and false if the coord is off grid
     */
    public boolean isOnGridNotOnOtherShip(Coord coord, Grid grid) {
        if (coord.getCol() > 9 || coord.getCol() < 0 || coord.getRow() > 9 || coord.getRow() < 0
                || grid.grid[coord.getRow()][coord.getCol()].getShipPlaceble() == false) {
            return false;
        }
        return true;
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

}
