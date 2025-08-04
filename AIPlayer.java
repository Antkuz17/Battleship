import java.util.ArrayList;

public class AIPlayer extends Player {

    Boolean huntMode = true; // Means the AI does not know where the next ship is so it guesses

    Boolean targetMode = false; // If AI got a hit, target mode uses logic to sink it

    String pointOfInterest = null; // This is the coordinate position where there is a ship

    // States whether the ship in question may fit vertically or horizontally
    Boolean vertPossible = true;
    Boolean horizPossible = true;

    // Current ships alive of the user (SL2 = Ship Length 2)
    Boolean SL2 = true;
    Boolean SL3 = true;
    Boolean SL4 = true;
    Boolean SL5 = true;
    Boolean SL6 = true;

    String lastHit = null; // Last hit made by the AI
    String direction = null; // Up down left right
    ArrayList<String> hitCoords = new ArrayList<>(); // Tracks all AI hits, used to tell when all ships have been sunk

    /**
     * Deals with all the logic surrounding the the ai's shooting
     * First an initial position is generated randomly. Then there is a shot and if
     * theres a ship there, it is regarded as a point of interest.
     * The AI then cross refrences the current ships that are yet to be sunk with
     * whether or not they could fit. After that it guesses up, down, left, right
     * until the ship is destroyed. Only then does it begin to generate random
     * positions
     * 
     * @param enemyGrid The grid of the player
     * @param shotsGrid The grid that tracks the AI's shots
     */
    public void shoot(Grid enemyGrid, Grid shotsGrid) {
        String coord;
        int[] arrCoords;

        // Hunt mode means that the AI does not know the location of the next ship and
        // makes educated guess
        if (huntMode) {

            // Generating a random coordinate
            coord = Utils.generateRandCoord();
            arrCoords = Utils.translation(coord);

            // Keep regenerating until a valid coordinate is created
            // Valid means a. hasnt been shot already, b. could fit a ship thats still alive
            while (shotsGrid.getCell(arrCoords[0], arrCoords[1]).getWasShot()
                    || Utils.posFits(shotsGrid, smallestShip(), coord) == 0) {
                // Needs changing later, not complete ending logic
                if (smallestShip() == -1) {
                    System.out.println("All ships have been sunk");
                    break;
                }
                coord = Utils.generateRandCoord();
                arrCoords = Utils.translation(coord);
            }

            // Setting the cells on the ai shots grid and the enemy grid to was shot
            shotsGrid.getCell(arrCoords[0], arrCoords[1]).setWasShot(true);
            enemyGrid.getCell(arrCoords[0], arrCoords[1]).setWasShot(true);

            // Important logic for the target mode that tells the AI that this direction is
            // invalid
            // In other words, no ship could have been placed there so no point in shooting
            // at it
            shotsGrid.getCell(arrCoords[0], arrCoords[1]).setShipPlacable(false);

            System.out.println("AI shoots at: " + coord);

            // If there was a hit, we are now in target mode and that coordinate is a point
            // of interest
            if (enemyGrid.getCell(arrCoords[0], arrCoords[1]).gethasShip()) {
                System.out.println("AI hit a ship!");
                huntMode = false;
                targetMode = true;
                pointOfInterest = coord;
                lastHit = coord;
                hitCoords.add(coord);
            } else {
                System.out.println("AI missed");
            }
            // Once a shots made, stop the shooting
            return;
        }

        // Target mode means the AI got a hit and will use logic to sink the ship
        // Until the ship is sunk it will remain in target mode
        if (targetMode) {
            coord = null;

            // Get coordinates for lastHit
            int[] lastHitCoords = Utils.translation(lastHit);

            // If we have established a direction, continue in that direction
            if (direction != null) {
                switch (direction) {
                    case "up":
                        if (lastHitCoords[0] > 0) {
                            coord = Coord.numToLetter(lastHitCoords[0] - 1) + (lastHitCoords[1] + 1);
                        }
                        break;
                    case "down":
                        if (lastHitCoords[0] < 9) {
                            coord = Coord.numToLetter(lastHitCoords[0] + 1) + (lastHitCoords[1] + 1);
                        }
                        break;
                    case "left":
                        if (lastHitCoords[1] > 0) {
                            coord = Coord.numToLetter(lastHitCoords[0]) + lastHitCoords[1];
                        }
                        break;
                    case "right":
                        if (lastHitCoords[1] < 9) {
                            coord = Coord.numToLetter(lastHitCoords[0]) + (lastHitCoords[1] + 2);
                        }
                        break;
                }

                // Check if coordinate is valid
                if (coord != null) {
                    int[] testCoords = Utils.translation(coord);
                    if (shotsGrid.getCell(testCoords[0], testCoords[1]).getWasShot()) {
                        coord = null; // Already shot, need to try opposite direction
                    }
                }
            }

            // If no direction established or current direction failed, try all directions
            // from point of interest
            if (coord == null) {
                int[] poiCoords = Utils.translation(pointOfInterest);

                // Try up
                if (poiCoords[0] > 0) {
                    String testCoord = Coord.numToLetter(poiCoords[0] - 1) + (poiCoords[1] + 1);
                    int[] testArr = Utils.translation(testCoord);
                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                        coord = testCoord;
                    }
                }

                // Try down if up didn't work
                if (coord == null && poiCoords[0] < 9) {
                    String testCoord = Coord.numToLetter(poiCoords[0] + 1) + (poiCoords[1] + 1);
                    int[] testArr = Utils.translation(testCoord);
                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                        coord = testCoord;
                    }
                }

                // Try left if up and down didn't work
                if (coord == null && poiCoords[1] > 0) {
                    String testCoord = Coord.numToLetter(poiCoords[0]) + poiCoords[1];
                    int[] testArr = Utils.translation(testCoord);
                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                        coord = testCoord;
                    }
                }

                // Try right if all others failed
                if (coord == null && poiCoords[1] < 9) {
                    String testCoord = Coord.numToLetter(poiCoords[0]) + (poiCoords[1] + 2);
                    int[] testArr = Utils.translation(testCoord);
                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                        coord = testCoord;
                    }
                }

                // If we had a direction and it failed, try opposite direction from original
                // point
                if (coord == null && direction != null) {
                    String oppositeDir = null;
                    switch (direction) {
                        case "up":
                            oppositeDir = "down";
                            break;
                        case "down":
                            oppositeDir = "up";
                            break;
                        case "left":
                            oppositeDir = "right";
                            break;
                        case "right":
                            oppositeDir = "left";
                            break;
                    }

                    if (oppositeDir != null) {
                        switch (oppositeDir) {
                            case "up":
                                if (poiCoords[0] > 0) {
                                    String testCoord = Coord.numToLetter(poiCoords[0] - 1) + (poiCoords[1] + 1);
                                    int[] testArr = Utils.translation(testCoord);
                                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                                        coord = testCoord;
                                        direction = oppositeDir;
                                        lastHit = pointOfInterest;
                                    }
                                }
                                break;
                            case "down":
                                if (poiCoords[0] < 9) {
                                    String testCoord = Coord.numToLetter(poiCoords[0] + 1) + (poiCoords[1] + 1);
                                    int[] testArr = Utils.translation(testCoord);
                                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                                        coord = testCoord;
                                        direction = oppositeDir;
                                        lastHit = pointOfInterest;
                                    }
                                }
                                break;
                            case "left":
                                if (poiCoords[1] > 0) {
                                    String testCoord = Coord.numToLetter(poiCoords[0]) + poiCoords[1];
                                    int[] testArr = Utils.translation(testCoord);
                                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                                        coord = testCoord;
                                        direction = oppositeDir;
                                        lastHit = pointOfInterest;
                                    }
                                }
                                break;
                            case "right":
                                if (poiCoords[1] < 9) {
                                    String testCoord = Coord.numToLetter(poiCoords[0]) + (poiCoords[1] + 2);
                                    int[] testArr = Utils.translation(testCoord);
                                    if (!shotsGrid.getCell(testArr[0], testArr[1]).getWasShot()) {
                                        coord = testCoord;
                                        direction = oppositeDir;
                                        lastHit = pointOfInterest;
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Method will return the length of the smallest ship currently alive
     * 
     * If no ships are alive, return -1
     * 
     * @return An int value of the smallest ship length
     */
    public int smallestShip() {
        if (SL2) {
            return 2;
        } else if (SL3) {
            return 3;
        } else if (SL4) {
            return 4;
        } else if (SL5) {
            return 5;
        } else if (SL6) {
            return 6;
        }
        return -1;
    }



}