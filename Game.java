/**
 * The class that runs the main game loop
 * 
 * <p>
 * It handles the initialization of player and AI grids, placement of ships for
 * both
 * the player and the AI, and the main gameplay loop where players take turns
 * shooting.
 * The game continues until all ships of either the player or the AI are
 * destroyed.
 * </p>
 */

public class Game {

    private HumanPlayer humanPlayer = new HumanPlayer(); // The user playing battleship
    private AIPlayer AIPlayer = new AIPlayer(); // The AI playing against the user

    public void start() {
        System.out.println("Welcome to battleship, here is the grid:");
        humanPlayer.myGrid.drawGrid();

        // Placing all the players ships
        System.out.println("You have 5 ships of different lengths (length 2-6)");

        humanPlayer.ship1.placeShip(humanPlayer.myGrid);
        humanPlayer.ship2.placeShip(humanPlayer.myGrid);
        humanPlayer.ship3.placeShip(humanPlayer.myGrid);
        humanPlayer.ship4.placeShip(humanPlayer.myGrid);
        humanPlayer.ship5.placeShip(humanPlayer.myGrid);

        // Placing all the AI's ships
        AIPlayer.ship1.placeAiShip(AIPlayer.myGrid);
        AIPlayer.ship2.placeAiShip(AIPlayer.myGrid);
        AIPlayer.ship3.placeAiShip(AIPlayer.myGrid);
        AIPlayer.ship4.placeAiShip(AIPlayer.myGrid);
        AIPlayer.ship5.placeAiShip(AIPlayer.myGrid);
        
        AIPlayer.myGrid.drawGrid(); // For trouble shooting


        // Main gaime loop
        // Keeps running till either all of the players ships or the AI's ships are destroyed
        boolean playerShipsDestroyed = false;
        boolean aiShipsDestroyed = false;
        while (!playerShipsDestroyed && !aiShipsDestroyed) {
            // Player shooting
            humanPlayer.shoot(AIPlayer.myGrid, humanPlayer.shotsGrid);

            // AI shooting
            AIPlayer.shoot(humanPlayer.myGrid, AIPlayer.shotsGrid);
            

            
        // All of the grids shown for testing purposes
        System.out.println("Players Grid:");
        humanPlayer.myGrid.drawGrid();
        System.out.println("Players Shots Grid:");
        humanPlayer.shotsGrid.drawGrid();
        System.out.println("Ais Grid Grid:");
        AIPlayer.myGrid.drawGrid();
        System.out.println("Ais shots Grid:");
        AIPlayer.shotsGrid.drawGrid();

        // grid.playerShoot(grid, aiShotsGrid);

        // }

    }

}
}