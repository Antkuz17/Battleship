import java.util.Scanner;

public class Ship {

    private int length;

    public boolean isSunk;
    private String vector; // From the initial pose does the ship point left right up down
    private String initPos; // The start position around which the ship is rotated
    private int[] arrCoords;

    // private boolean againstRightWall = false;
    // private boolean againstLeftWall = false;
    // private boolean againstTopWall = false;
    // private boolean againstBottomWall = false;

    public Ship(int length) {
        this.length = length;
        this.isSunk = false;
        this.vector = "down";
    }

    public Scanner input = new Scanner(System.in);

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

        String potentialPos = input.nextLine(); // Potential position still needs to be checked for validity

        int[] coords = Utils.translation(potentialPos);

        // Checks whether or not the coordinate is on the grid, if its a valid
        // coordinate, and if the ship would fit in one of 4 poses
        while (!Utils.isOnGrid(potentialPos) || Utils.posFits(grid, length, potentialPos) == 0) {
            System.out.println("Not a valid position try again");
            System.out.println("Where do you want the " + (length - 1) + " ship to start?: ");
            potentialPos = input.nextLine();
            coords = Utils.translation(potentialPos);
        }

        // Setting global variable for initPos
        initPos = potentialPos;
        arrCoords = coords;


        // Setting the start coord as having a ship and not being able to place ships on
        // it
        grid.getCell(coords[0], coords[1]).setHasShip(true);
        grid.getCell(coords[0], coords[1]).setShipPlacable(false);

        String userAnswer = "R";

        // Will keep rotating until the user inputs not R
        while (userAnswer.equals("R")) {

            if (Utils.rightValid(grid, length, potentialPos)) { // If the right direction is valid

                // Make the ship on the grid, adding the coordinates the grid
                for (int i = 1; i < (length); i++) {
                    grid.getCell(coords[0], coords[1] + i).setHasShip(true);
                    grid.getCell(coords[0], coords[1] + i).setShipPlacable(false);
                }

                // Draws the grid for the user without the invalid squares (troubleshooting
                // purposes)
                System.out.println("Grid with just ship:");
                grid.drawGrid();

                System.out.print("Enter R to rotate ship(if not click anyother key): ");
                userAnswer = input.nextLine();

                // If the user does not enter R, then they dont want to rotate the ship and this
                // will be the final position
                if (!userAnswer.equalsIgnoreCase("R")) {
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = coords[1] + i - 1;
                        int topRow = coords[0] - 1;
                        int bottomRow = coords[0] + 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.getCell(coords[0], targetCol).setShipPlacable(false);

                            if (topRow >= 0) { // Fixed: check >= 0 for top row
                                grid.getCell(topRow, targetCol).setShipPlacable(false);
                            }

                            if (bottomRow <= 9) { // Fixed: check <= 9 for bottom row
                                grid.getCell(bottomRow, targetCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "right";
                    System.out.println("Grid with invalid squares");
                    grid.drawGrid();
                    break;
                }

                // If they do enter R, then they dont want this position and so the program will
                // set the coordinates back to normal
                for (int i = 1; i < (length); i++) {
                    grid.getCell(coords[0], coords[1] + i).setHasShip(false);
                    grid.getCell(coords[0], coords[1] + i).setShipPlacable(true);
                }

            }

            // Down direction
            if (Utils.downValid(grid, length, potentialPos)) {

                for (int i = 1; i < length; i++) {
                    grid.getCell(coords[0] + i, coords[1]).setHasShip(true);
                    grid.getCell(coords[0] + i, coords[1]).setShipPlacable(false);
                }

                System.out.println("Grid with just ship:");
                grid.drawGrid();

                System.out.print("Enter R to rotate ship(if not click any other key): ");
                userAnswer = input.nextLine();
                if (!userAnswer.equalsIgnoreCase("R")) {
                    for (int i = 0; i < (length + 2); i++) {
                        int targetRow = coords[0] + i - 1; // Going down from start position
                        int leftCol = coords[1] - 1;
                        int rightCol = coords[1] + 1;

                        if (targetRow >= 0 && targetRow <= 9) {
                            grid.getCell(targetRow, coords[1]).setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.getCell(targetRow, leftCol).setShipPlacable(false);
                            }

                            if (rightCol <= 9) {
                                grid.getCell(targetRow, rightCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "down";
                    System.out.println("Grid with invalid squares");
                    grid.drawGrid();
                    break;
                }
                for (int i = 1; i < length; i++) {
                    grid.getCell(coords[0] + i, coords[1]).setHasShip(false);
                    grid.getCell(coords[0] + i, coords[1]).setShipPlacable(true);
                }
            }

            // Left direction
            if (Utils.leftValid(grid, length, potentialPos)) {

                for (int i = 1; i < length; i++) {
                    grid.getCell(coords[0], coords[1] - i).setHasShip(true);
                    grid.getCell(coords[0], coords[1] - i).setShipPlacable(false);
                }

                System.out.println("Grid with just ship:");
                grid.drawGrid();

                System.out.print("Enter R to rotate ship(if not click any other key): ");
                userAnswer = input.nextLine();
                if (!userAnswer.equalsIgnoreCase("R")) {
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = coords[1] - i + 1; // Going left from start position
                        int topRow = coords[0] - 1;
                        int bottomRow = coords[0] + 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.getCell(coords[0], targetCol).setShipPlacable(false);

                            if (topRow >= 0) {
                                grid.getCell(topRow, targetCol).setShipPlacable(false);
                            }

                            if (bottomRow <= 9) {
                                grid.getCell(bottomRow, targetCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "left";
                    System.out.println("Grid with invalid squares");
                    grid.drawGrid();
                    break;
                }
                for (int i = 1; i < length; i++) {
                    grid.getCell(coords[0], coords[1] - i).setHasShip(false);
                    grid.getCell(coords[0], coords[1] - i).setShipPlacable(true);
                }
            }

            // Up Direciton
            if (Utils.upValid(grid, length, potentialPos)) {

                for (int i = 1; i < length; i++) {
                    grid.getCell(coords[0] - i, coords[1]).setHasShip(true);
                    grid.getCell(coords[0] - i, coords[1]).setShipPlacable(false);
                }

                System.out.println("Grid with just ship:");
                grid.drawGrid();

                System.out.print("Enter R to rotate ship(if not click any other key): ");
                userAnswer = input.nextLine();
                if (!userAnswer.equalsIgnoreCase("R")) {
                    for (int i = 0; i < (length + 2); i++) {
                        int targetRow = coords[0] - i + 1; // Going up from start position
                        int leftCol = coords[1] - 1;
                        int rightCol = coords[1] + 1;

                        if (targetRow >= 0 && targetRow <= 9) {
                            grid.getCell(targetRow, coords[1]).setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.getCell(targetRow, leftCol).setShipPlacable(false);
                            }

                            if (rightCol <= 9) {
                                grid.getCell(targetRow, rightCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "up";
                    System.out.println("Grid with invalid squares");
                    grid.drawGrid();
                    break;
                }
                for (int i = 1; i < length; i++) {
                    grid.getCell(coords[0] - i, coords[1]).setHasShip(false);
                    grid.getCell(coords[0] - i, coords[1]).setShipPlacable(true);
                }
            }

        }
    }

    /**
     * Used to place ships on the AI's grid. Works by generating random coords.
     * Then once you have one, checks if its a valid coordinate and if it is, place
     * the ship there in a random direction
     * 
     * @param grid The AI's own ship grid
     */
    public void placeAiShip(Grid grid) {

        String potentialPos = Utils.generateRandCoord(); // Potential position still needs to be checked for validity

        int[] coords = Utils.translation(potentialPos); // Turns the potential pose to a set of ints

        // Checks whether or not the coordinate is on the grid, if its a valid
        // coordinate, and if the ship would fit in one of 4 positions
        while (!Utils.isOnGrid(potentialPos) || Utils.posFits(grid, length, potentialPos) == 0) {
            potentialPos = Utils.generateRandCoord();
            coords = Utils.translation(potentialPos);
        }

        // Setting the start coord as having a ship and not being able to place ships on
        // it
        grid.getCell(coords[0], coords[1]).setHasShip(true);
        grid.getCell(coords[0], coords[1]).setShipPlacable(false);


        // Will keep rotating until the user inputs not R
        while (true) {
            // Used to randomize a direction for the ship
            int num = (int) (Math.random() * 4) + 1;

            if (num == 1) {
                if (Utils.rightValid(grid, length, potentialPos)) { // If the right direction is valid
                    // Make the ship on the grid, adding the coordinates the grid
                    for (int i = 1; i < (length); i++) {
                        grid.getCell(coords[0], coords[1] + i).setHasShip(true);
                        grid.getCell(coords[0], coords[1] + i).setShipPlacable(false);
                    }
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = coords[1] + i - 1;
                        int topRow = coords[0] - 1;
                        int bottomRow = coords[0] + 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.getCell(coords[0], targetCol).setShipPlacable(false);

                            if (topRow >= 0) {
                                grid.getCell(topRow, targetCol).setShipPlacable(false);
                            }

                            if (bottomRow <= 9) {
                                grid.getCell(bottomRow, targetCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "right";
                    break;
                }
            }

            if (num == 2) {
                if (Utils.downValid(grid, length, potentialPos)) { // If the down direction is valid
                    // Make the ship on the grid, adding the coordinates the grid
                    for (int i = 1; i < (length); i++) {
                        grid.getCell(coords[0] + i, coords[1]).setHasShip(true);
                        grid.getCell(coords[0] + i, coords[1]).setShipPlacable(false);
                    }
                    for (int i = 0; i < (length + 2); i++) {
                        int targetRow = coords[0] + i - 1;
                        int leftCol = coords[1] - 1;
                        int rightCol = coords[1] + 1;

                        if (targetRow >= 0 && targetRow <= 9) {
                            grid.getCell(targetRow, coords[1]).setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.getCell(targetRow, leftCol).setShipPlacable(false);
                            }

                            if (rightCol <= 9) {
                                grid.getCell(targetRow, rightCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "down";
                    break;
                }
            }

            if (num == 3) {
                if (Utils.leftValid(grid, length, potentialPos)) { // If the left direction is valid
                    // Make the ship on the grid, adding the coordinates the grid
                    for (int i = 1; i < (length); i++) {
                        grid.getCell(coords[0], coords[1] - i).setHasShip(true);
                        grid.getCell(coords[0], coords[1] - i).setShipPlacable(false);
                    }
                    for (int i = 0; i < (length + 2); i++) {
                        int targetCol = coords[1] - i + 1;
                        int topRow = coords[0] - 1;
                        int bottomRow = coords[0] + 1;

                        if (targetCol >= 0 && targetCol <= 9) {
                            grid.getCell(coords[0], targetCol).setShipPlacable(false);

                            if (topRow >= 0) {
                                grid.getCell(topRow, targetCol).setShipPlacable(false);
                            }

                            if (bottomRow <= 9) {
                                grid.getCell(bottomRow, targetCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "left";
                    break;
                }
            }

            if (num == 4) {
                if (Utils.upValid(grid, length, potentialPos)) { // If the up direction is valid
                    // Make the ship on the grid, adding the coordinates the grid
                    for (int i = 1; i < (length); i++) {
                        grid.getCell(coords[0] - i, coords[1]).setHasShip(true);
                        grid.getCell(coords[0] - i, coords[1]).setShipPlacable(false);
                    }
                    for (int i = 0; i < (length + 2); i++) {
                        int targetRow = coords[0] - i + 1;
                        int leftCol = coords[1] - 1;
                        int rightCol = coords[1] + 1;

                        if (targetRow >= 0 && targetRow <= 9) {
                            grid.getCell(targetRow, coords[1]).setShipPlacable(false);

                            if (leftCol >= 0) {
                                grid.getCell(targetRow, leftCol).setShipPlacable(false);
                            }

                            if (rightCol <= 9) {
                                grid.getCell(targetRow, rightCol).setShipPlacable(false);
                            }
                        }
                    }
                    vector = "up";
                    break;
                }
            }
        }

    }

    /**
     * Returns whether this ship is completely sunk
     * A ship is sunk when all of its positions on the grid have been shot
     * 
     * @param grid The grid to check (should be the grid where this ship was placed)
     * @return True if all parts of the ship have been hit, false otherwise
     */
    public Boolean getIsSunk(Grid grid) {
        if (initPos == null) {
            return false; // Ship was never placed
        }

        int startRow = arrCoords[0];
        int startCol = arrCoords[1];

        // Check if the starting position has been shot
        if (!grid.getCell(startRow, startCol).getWasShot()) {
            return false; // Starting position not hit, ship not sunk
        }

        // Check all other positions of the ship based on its direction
        switch (vector) {
            case "right":
                for (int i = 1; i < length; i++) {
                    if (!grid.getCell(startRow, startCol + i).getWasShot()) {
                        return false; // Found an unhit part
                    }
                }
                break;

            case "down":
                for (int i = 1; i < length; i++) {
                    if (!grid.getCell(startRow + i, startCol).getWasShot()) {
                        return false; // Found an unhit part
                    }
                }
                break;

            case "left":
                for (int i = 1; i < length; i++) {
                    if (!grid.getCell(startRow, startCol - i).getWasShot()) {
                        return false; // Found an unhit part
                    }
                }
                break;

            case "up":
                for (int i = 1; i < length; i++) {
                    if (!grid.getCell(startRow - i, startCol).getWasShot()) {
                        return false; // Found an unhit part
                    }
                }
                break;

            default:
                // If vector is not set properly, assume ship is not sunk
                return false;
        }

        // If we get here, all parts of the ship have been hit
        isSunk = true;
        return true;
    }

}
