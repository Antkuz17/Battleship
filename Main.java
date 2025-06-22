public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        System.out.println("Welcome to battleship, here is the grid:");
        grid.drawGrid();
        System.out.println("You have 5 ships of different lengths (length 2-6)");
        for(int i = 0; i < 5; i++){
            //grid.placeShip();
        }

    }
}
