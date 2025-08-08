public class HumanPlayer extends Player {

    /**
     * Responsible for handling the player shooting.
     * Works by having the player enter a coordinate and then it checks that the given coord
     * is on the grid and hasnt already been shot
     * @param enemyGrid The enemy grid with all their ships
     * @param shotsGrid The players own shots grid
     */
    public void shoot(Grid enemyGrid, Grid shotsGrid){

        // Getting user input
        System.out.println("Where would you like to shoot?");
        String coord = Utils.getCoord();

        int[] arrCoords = Utils.translation(coord); 

        // Keep reprompting until the inputed coord hasnt been shot and is on grd
        while(!Utils.isOnGrid(coord) || shotsGrid.getCell(arrCoords[0], arrCoords[1]).getWasShot()){
            System.out.print("Invalid input, try again. Where would you like to shoot");
            coord = Utils.getCoord();
            arrCoords = Utils.translation(coord);
        }

        // Set the coordinate that was shot at to was shot on both grids
        shotsGrid.getCell(arrCoords[0], arrCoords[1]).setWasShot(true);
        enemyGrid.getCell(arrCoords[0], arrCoords[1]).setWasShot(true);

        // If there was a hit, print to terminal
        if(enemyGrid.getCell(arrCoords[0], arrCoords[1]).gethasShip()){
            System.out.println("Thats a hit");
            shotsGrid.getCell(arrCoords[0], arrCoords[1]).setHasShip(true); // Set so that when displaying the grid, it shows a H and not a M
        }
    }
}
