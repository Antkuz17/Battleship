import java.util.Scanner;

public class Main {
    public Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        
        Grid grid = new Grid(); // contains the players ships and enemy shots
        Grid playerShotsGrid = new Grid(); // Contains the players shots
        Grid aiGrid = new Grid(); // Contains the ai ships and enemy shots
        Grid aiShotsGrid = new Grid(); // Contains the ai's shots

        System.out.println("Welcome to battleship, here is the grid:");
        grid.drawGrid();

        // Placing all User ships
        System.out.println("You have 5 ships of different lengths (length 2-6)");
        Ship ship1 = new Ship(2);
        ship1.placeShip(grid);

        Ship ship2 = new Ship(3);
        ship2.placeShip(grid);

        Ship ship3 = new Ship(4);
        ship3.placeShip(grid);

        Ship ship4 = new Ship(5);
        ship4.placeShip(grid);

        Ship ship5 = new Ship(6);
        ship5.placeShip(grid);

        // Placing AI Ships
        Ship aiShip1 = new Ship(2);
        aiShip1.placeAiShip(aiGrid);
        Ship aiShip2 = new Ship(3);
        aiShip2.placeAiShip(aiGrid);
        aiGrid.drawGrid();
        Ship aiShip3 = new Ship(4);
        aiShip3.placeAiShip(aiGrid);
        Ship aiShip4 = new Ship(5);
        aiShip4.placeAiShip(aiGrid);
        Ship aiShip5 = new Ship(6);
        aiShip5.placeAiShip(aiGrid);

        System.out.println("AI Grid: "); // will be deleted later just for trouble shooting
        aiGrid.drawGrid();

        // Main gaime loop
        // Keeps running till either all of the players ships or the AI's ships are destroyed
        boolean playerShipsDestroyed = false;
        boolean aiShipsDestroyed = false;
        while (!playerShipsDestroyed && !aiShipsDestroyed){
            aiGrid.Shoot(aiGrid, playerShotsGrid); // Player shooting
            System.out.println("Players Grid:");
            grid.drawGrid();
            System.out.println("Players Shots Grid:");
            playerShotsGrid.drawGrid();
            System.out.println("Ais Grid Grid:");
            aiGrid.drawGrid();
            System.out.println("Ais shots Grid:");
            aiShotsGrid.drawGrid();


        }

    }

}
