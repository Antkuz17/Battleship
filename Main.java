import java.util.Scanner;
public class Main {
    public Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println("Welcome to battleship, here is the grid:");
        grid.drawGrid();
        System.out.println("You have 5 ships of different lengths (length 2-6)");
        Ship ship1 = new Ship(2);
        ship1.placeShip(grid);
        grid.drawGrid();
        Ship ship2 = new Ship(3);
        ship2.placeShip(grid);
        grid.drawGrid();
        Ship ship3 = new Ship(4);
        ship3.placeShip(grid);
        grid.drawGrid();
        Ship ship4 = new Ship(5);
        ship4.placeShip(grid);
        grid.drawGrid();
        Ship ship5 = new Ship(6);
        ship5.placeShip(grid);
        grid.drawGrid();
    }
}
