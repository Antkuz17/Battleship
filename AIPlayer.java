public class AIPlayer extends Player {

    Boolean huntMode = true;
    // If AI has no hits, hunt mode means semi random shots (wont shoot if the ship
    // cant be there)
    // Done by cross refrencing the currently active ships

    Boolean targetMode = false; // If AI got a hit, target mode uses logic to sink it

    Coord pointOfInterest = null; // This is the coordinate position where there is a ship

    // States whether the ship in question may fit vertically or horizontally
    Boolean vertPossible = true;
    Boolean horizPossible = true;

    // Current ships alive of the user (SL2 = Ship Length 2)

    Boolean SL2 = true;
    Boolean SL3 = true;
    Boolean SL4 = true;
    Boolean SL5 = true;
    Boolean SL6 = true;

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

        String coord = Utils.generateRandCoord();

        int[] arrCoords = Utils.translation(coord);

        // Hunt mode means that the AI does not know the location of the next ship
        if(huntMode){
            // Keep regenerating until a valid coordinate is created
            // Valid means a. hasnt been shot already, b. could fit a ship thats still alive
            while (shotsGrid.getCell(arrCoords[0], arrCoords[1]).getWasShot() || ) {
                coord = Utils.generateRandCoord();
                arrCoords = Utils.translation(coord);
            }

            shotsGrid.getCell(arrCoords[0], arrCoords[1]).setWasShot(true);
            enemyGrid.getCell(arrCoords[0], arrCoords[1]).setWasShot(true);
        }


}

    
}