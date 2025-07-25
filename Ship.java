
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

    private boolean againstRightWall = false;
    private boolean againstLeftWall = false;
    private boolean againstTopWall = false;
    private boolean againstBottomWall = false;

    public Ship(int length) {
        this.length = length;
        this.initPos = null;
        this.isSunk = false;
        this.vector = "down";
    }

    /**
     * Places ship on the provided grid by prompting the user for a starting
     * position
     * and allowing them to rotate the ship to a valid orientation.
     * <p>
     * The method ensures the ship is placed only on valid, unoccupied squares and
     * does not overlap with other ships or go out of bounds. It updates the grid to
     * mark the
     * ship'sposition and adjacent squares as unplaceable for other ships. The user
     * can
     * rotate the ship to different valid orientations before finalizing the
     * placement.
     * This method will also take into account that a position which seems valid but
     * would not
     * fit the ship is an invalid pose and not let you place a ship there.
     *
     * @param grid The object on which the ship will be placed.
     */
    public void placeShip(Grid grid) {
        System.out.println("Where do you want the " + (length - 1) + " ship to start?: ");
        String potentialPos = input.nextLine(); // Potential pos still needs to be checked for validity
        Coord initPos = translation(potentialPos); // Translates from String "A1" to a coord object

        while (!isOnGridNotOnOtherShip(initPos, grid) || !initPosFits(initPos, grid)) { // Keep reprompting till user
                                                                                        // inputs a valid square
            System.out.println("Not a valid position try again");
            System.out.println("Where do you want the " + (length - 1) + " ship to start?: ");
            potentialPos = input.nextLine();
            initPos = translation(potentialPos); // Translates the string to a coord object
        }

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
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = initPos.getCol() + i -1;
                        int topRow = initPos.getRow() + 1;
                        int bottomRow = initPos.getRow() - 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.grid[initPos.getRow()][targetCol].setShipPlacable(false);

                            if (topRow <= 9) {
                                grid.grid[topRow][targetCol].setShipPlacable(false);
                            }

                            if (bottomRow >= 0) {
                                grid.grid[bottomRow][targetCol].setShipPlacable(false);
                            }
                        }
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
                        int targetRow = initPos.getRow() - 2 + i;
                        int leftCol = initPos.getCol() - 1;
                        int rightCol = initPos.getCol() + 1;
                        if (targetRow >= 0 && targetRow < grid.grid.length) {
                            grid.grid[targetRow][initPos.getCol()].setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.grid[targetRow][leftCol].setShipPlacable(false);
                            }

                            if (rightCol < 9) {
                                grid.grid[targetRow][rightCol].setShipPlacable(false);
                            }
                        }
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
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = initPos.getCol() - length + i;
                        int topRow = initPos.getRow() + 1;
                        int bottomRow = initPos.getRow() - 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.grid[initPos.getRow()][targetCol].setShipPlacable(false);

                            if (topRow <= 9) {
                                grid.grid[topRow][targetCol].setShipPlacable(false);
                            }

                            if (bottomRow >= 0) {
                                grid.grid[bottomRow][targetCol].setShipPlacable(false);
                            }
                        }
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
                    for (int i = 0; i < (length + 2); i++) {
                        int targetRow = initPos.getRow() - i +1;
                        int leftCol = initPos.getCol() - 1;
                        int rightCol = initPos.getCol() + 1;

                        if (targetRow >= 0 && targetRow <= 9) {
                            grid.grid[targetRow][initPos.getCol()].setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.grid[targetRow][leftCol].setShipPlacable(false);
                            }

                            if (rightCol <= 9) {
                                grid.grid[targetRow][rightCol].setShipPlacable(false);
                            }
                        }
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
     * Sets the wall boundaries for a given position initial pose
     * 
     * Used to avoid indexout of bounds errors when setting adjacent squares to
     * invalid
     * 
     * @param pose
     */
    public void setWallBounds(Coord pose) {
        if (pose.getCol() == 0) {
            againstLeftWall = true;
        }
        if (pose.getCol() == 9) {
            againstRightWall = true;
        }
        if (pose.getRow() == 0) {
            againstTopWall = true;
        }
        if (pose.getRow() == 0) {
            againstBottomWall = true;
        }
    }

    /**
     * Returns whether or not a position is a valid one
     * 
     * Valid - At least one orientation in that initial pose will result in a legal
     * position
     * 
     * Logic- Checks above, below, left, and right of the initpose to make sure that
     * is a point where
     * the ship would fit
     * 
     * Takes borders and other ships into account
     * 
     * @return boolean value of true if its placeable and false if not
     */
    public boolean initPosFits(Coord pose, Grid grid) {
        rightValid = true;
        leftValid = true;
        upValid = true;
        downValid = true;

        int row = pose.getRow();
        int col = pose.getCol();

        possiblePose = 0;

        // Checks that the ship fits the bounds of the grid
        if (row + (length - 1) > 9) {
            downValid = false;
        } 
        if (row - (length - 1) < 0) {
            upValid = false;
        } 
        if (col - (length - 1) < 0) {
            leftValid = false;
        } 
        if (col + (length - 1) > 9) {
            rightValid = false;
        } 

        // Check right direction
        if (rightValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.grid[row][col + i].getShipPlaceble()) {
                    rightValid = false;
                    possiblePose -= 1;
                }
            }
        }
        // Check up direction
        if (upValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.grid[row - i][col].getShipPlaceble()) {
                    upValid = false;
                    possiblePose -= 1;
                    break;
                }
            }
        }
        // Check down direction
        if (downValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.grid[row + i][col].getShipPlaceble()) {
                    downValid = false;
                    possiblePose -= 1;
                    break;
                }
            }
        }
        // Check left direction
        if (leftValid) {
            for (int i = 0; i < length; i++) {
                if (!grid.grid[row][col - i].getShipPlaceble()) {
                    leftValid = false;
                    possiblePose -= 1;
                    break;
                }
            }
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
     * Returns a true or false depending on whether the given coordinate is on the
     * grid and isnt on a invalid square
     * For example if a ship is at A1, A2 is an invalid square
     * 
     * This method will consider only that the init square is in bounds and its not
     * on an invalid square
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
    /**
     * Will add a ship to the Ai's grid 
     * 
     * Init pos is randomized until a valid pose is found then it will randomly add the ship in a random position
     * @param grid
     */
    public void placeAiShip(Grid grid){
        int[] arrayCoord = genRandCoord();
        int x = arrayCoord[0];
        int y = arrayCoord[1];
        Coord initPos = new Coord(x, y);
        while (!isOnGridNotOnOtherShip(initPos, grid) || !initPosFits(initPos, grid)) { // Keep regenerating till valid pose 
            //System.out.println("initPosFits: " + initPosFits(initPos, grid));
            //System.out.println("isOnGridNotOnOtherShip" + isOnGridNotOnOtherShip(initPos,grid));
            arrayCoord = genRandCoord();
            x = arrayCoord[0];
            y = arrayCoord[1];

            initPos = new Coord(x, y);
        }

        
        initPos.setHasShip(true);
        initPos.setShipPlacable(false);

        // Setting the initial pose thats been validated as the initpos on the grid
        grid.grid[initPos.getRow()][initPos.getCol()] = initPos;


        while (true) {
            int random = (int) (Math.random() * (4 - 1 + 1)) + 1;
            if (rightValid && random == 1) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow()][initPos.getCol() + i].setHasShip(true);
                }
    
                    vector = "right";
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = initPos.getCol() + i - 1;
                        int topRow = initPos.getRow() + 1;
                        int bottomRow = initPos.getRow() - 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.grid[initPos.getRow()][targetCol].setShipPlacable(false);

                            if (topRow <= 9) {
                                grid.grid[topRow][targetCol].setShipPlacable(false);
                            }

                            if (bottomRow >= 0) {
                                grid.grid[bottomRow][targetCol].setShipPlacable(false);
                            }
                        }
                    }
                    break;
            }
            if (downValid && random ==2) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow() + i][initPos.getCol()].setHasShip(true);
                }
               
                    vector = "down";
                    for (int i = 1; i < (length + 3); i++) {
                        int targetRow = initPos.getRow() - 2 + i;
                        int leftCol = initPos.getCol() - 1;
                        int rightCol = initPos.getCol() + 1;
                        if (targetRow >= 0 && targetRow < grid.grid.length) {
                            grid.grid[targetRow][initPos.getCol()].setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.grid[targetRow][leftCol].setShipPlacable(false);
                            }

                            if (rightCol < 9) {
                                grid.grid[targetRow][rightCol].setShipPlacable(false);
                            }
                        }
                    }
                    break;
                }

            if (leftValid && random == 3) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow()][initPos.getCol() - i].setHasShip(true);
                }

                    vector = "left";
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = initPos.getCol() - length + i;
                        int topRow = initPos.getRow() + 1;
                        int bottomRow = initPos.getRow() - 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.grid[initPos.getRow()][targetCol].setShipPlacable(false);

                            if (topRow <= 9) {
                                grid.grid[topRow][targetCol].setShipPlacable(false);
                            }

                            if (bottomRow >= 0) {
                                grid.grid[bottomRow][targetCol].setShipPlacable(false);
                            }
                        }
                    }
                    break;
                }
            if (upValid && random == 4) {
                for (int i = 1; i < (length); i++) {
                    grid.grid[initPos.getRow() - i][initPos.getCol()].setHasShip(true);
                }

                    vector = "up";
                    for (int i = 0; i < (length + 2); i++) {
                        int targetRow = initPos.getRow() - i + 1;
                        int leftCol = initPos.getCol() - 1;
                        int rightCol = initPos.getCol() + 1;

                        if (targetRow >= 0 && targetRow <= 9) {
                            grid.grid[targetRow][initPos.getCol()].setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.grid[targetRow][leftCol].setShipPlacable(false);
                            }

                            if (rightCol <= 9) {
                                grid.grid[targetRow][rightCol].setShipPlacable(false);
                            }
                        }
                    }
                    break;
                }

            }
        }

    
    /**
     * Generates a random set of coords 
     * 
     * ex (6,2) (0,9) (7,1)
     * 
     * @return These numbers as a two element array
     */
    public int[] genRandCoord(){
        int[] nums = new int[2];
        for(int i =0; i< 2; i++){
            int random = (int) (Math.random() * 10);
            nums[i] = random;
        }
        return nums;
    }

}
