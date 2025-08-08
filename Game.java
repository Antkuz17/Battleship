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

        while (true) {
            // Player shooting
            System.out.println("Players turn");
            humanPlayer.shoot(AIPlayer.myGrid, humanPlayer.shotsGrid);

            

            // Update AI's ship tracking for its hunting logic
            updateAIShipStatus();
            
            // Check if all AI ships are sunk
            if (areAllShipsSunk(AIPlayer)) {
                System.out.println("\nCongratulations! You won!");
                System.out.println("All enemy ships have been destroyed!");
                break;
            }

            // Check for individual ship sunk messages for player
            checkAndReportSunkShips(AIPlayer, "Enemy");

            // AI shooting
            System.out.println("AI's Turn");
            AIPlayer.shoot(humanPlayer.myGrid, AIPlayer.shotsGrid);

            // Check if all player ships are sunk
            if (areAllShipsSunk(humanPlayer)) {
                System.out.println("\nGame Over! AI wins!");
                System.out.println("All your ships have been destroyed!");
                break;
            }

            // Check for individual ship sunk messages for AI
            checkAndReportSunkShips(humanPlayer, "Your");

            // Display grids after each round
            displayGameStatus();
    }
}

    // Method to check if all ships of a player are sunk
    private boolean areAllShipsSunk(Player player) {
        return player.ship1.getIsSunk(player.myGrid) &&
               player.ship2.getIsSunk(player.myGrid) &&
               player.ship3.getIsSunk(player.myGrid) &&
               player.ship4.getIsSunk(player.myGrid) &&
               player.ship5.getIsSunk(player.myGrid);
    }

    // Method to update AI's ship tracking when ships are sunk
    private void updateAIShipStatus() {
        if (AIPlayer.ship1.getIsSunk(AIPlayer.myGrid)) {
            AIPlayer.SL2 = false;
        }
        if (AIPlayer.ship2.getIsSunk(AIPlayer.myGrid)) {
            AIPlayer.SL3 = false;
        }
        if (AIPlayer.ship3.getIsSunk(AIPlayer.myGrid)) {
            AIPlayer.SL4 = false;
        }
        if (AIPlayer.ship4.getIsSunk(AIPlayer.myGrid)) {
            AIPlayer.SL5 = false;
        }
        if (AIPlayer.ship5.getIsSunk(AIPlayer.myGrid)) {
            AIPlayer.SL6 = false;
        }
    }

    // Method to check and report when ships are sunk
    private void checkAndReportSunkShips(Player player, String owner) {
        if (player.ship1.getIsSunk(player.myGrid) && !player.ship1.isSunk) {
            System.out.println(owner + " ship of length 2 has been sunk");
            player.ship1.isSunk = true;
        }
        if (player.ship2.getIsSunk(player.myGrid) && !player.ship2.isSunk) {
            System.out.println(owner + " ship of length 3 has been sunk");
            player.ship2.isSunk = true;
        }
        if (player.ship3.getIsSunk(player.myGrid) && !player.ship3.isSunk) {
            System.out.println(owner + " ship of length 4 has been sunk");
            player.ship3.isSunk = true;
        }
        if (player.ship4.getIsSunk(player.myGrid) && !player.ship4.isSunk) {
            System.out.println(owner + " ship of length 5 has been sunk");
            player.ship4.isSunk = true;
        }
        if (player.ship5.getIsSunk(player.myGrid) && !player.ship5.isSunk) {
            System.out.println(owner + " ship of length 6 has been sunk");
            player.ship5.isSunk = true;
        }
    }

    // Gam status reporter
    private void displayGameStatus() {
        System.out.println("\n=== Current Game Status ===");
        System.out.println("Your Grid:");
        humanPlayer.myGrid.drawGrid();
        System.out.println("Your Shots Grid:");
        humanPlayer.shotsGrid.drawGrid();
        
        // Optional: Show AI grids for debugging (remove in final version)
        System.out.println("AI's Grid (Debug):");
        AIPlayer.myGrid.drawGrid();
        System.out.println("AI's Shots Grid:");
        AIPlayer.shotsGrid.drawGrid();
        System.out.println("========================\n");
    }


}