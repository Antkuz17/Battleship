public class AIPlayer extends Player{
    
    Boolean huntMode = true; // If AI has no hits, hunt mode means semi random shots (wont shoot if the ship cant be there)
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
    public void aiShoot(Grid enemyGrid, Grid shotsGrid) {
        
        if(huntMode){
            // Generate random coord
            Coord guessCoord = translation(generateRandCoord()); // Random guess by the AI
            if(guessCoord.getwasShot()){

            }
        }
        

    }

    /**
     * Generates a random coordinate on the grid
     * 
     * @return String in the form of A1 or B2 or G10
     */
    public String generateRandCoord() {
        int randX = (int) (Math.random() * 10);
        int randY = (int) (Math.random() * 10);
        String coord = Coord.numToLetter(randX) + (randY + 1);
        return coord;
    }

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
        Coord initPos = new Coord(firstNumber, secondNumber);
        return initPos;
    }
}
