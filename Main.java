import java.util.Scanner;

public class Main {
    public Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Grid grid = new Grid();
        // System.out.println("Welcome to battleship, here is the grid:");
        // grid.drawGrid();

        // // Placing all User ships
        // System.out.println("You have 5 ships of different lengths (length 2-6)");
        // Ship ship1 = new Ship(2);
        // ship1.placeShip(grid);

        // Ship ship2 = new Ship(3);
        // ship2.placeShip(grid);

        // Ship ship3 = new Ship(4);
        // ship3.placeShip(grid);

        // Ship ship4 = new Ship(5);
        // ship4.placeShip(grid);

        // Ship ship5 = new Ship(6);
        // ship5.placeShip(grid);

        // Placing AI Ships
        Grid aiGrid = new Grid(); // Ai's grid object
        System.out.println("AI Grid: "); // will be deleted later just for trouble shooting
        Ship aiShip1 = new Ship(2);
        aiShip1.placeAiShip(aiGrid);
        // Ship aiShip2 = new Ship(3);
        // aiShip2.placeAiShip(aiGrid);
        // Ship aiShip3 = new Ship(4);
        // aiShip3.placeAiShip(aiGrid);
        // Ship aiShip4 = new Ship(5);
        // aiShip4.placeAiShip(aiGrid);
        // Ship aiShip5 = new Ship(6);
        // aiShip5.placeAiShip(aiGrid);

    }

}
