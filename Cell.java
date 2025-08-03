/**
 * This class represents a single cell on the battleship grid
 * 
 * <p>
 * Contains vital information like the state of the cell (i.e. has ship or was shot). 
 * </p>
 */

public class Cell {
    private boolean hasShip = false;
    private boolean wasShot = false;
    private boolean shipPlaceable = true;

    // No constuctor necessary since each cell object has the same start fields and are only changed later

    /**
     * Tells whether this cell has a ship
     * @return True if yes and false if no
     */
    public Boolean gethasShip(){
        return hasShip;
    }

    /**
     * Tells whether this cell has been shot
     * 
     * @return True if yes and false if no
     */
    public Boolean getWasShot() {
        return wasShot;
    }

    /**
     * Tells whether this cell can have a ship placed on it
     * 
     * @return True if yes and false if no
     */
    public Boolean getShipPlaceable() {
        return shipPlaceable;
    }
    
    /**
     * Sets whether a cell has a ship
     * @param hasShip Boolean value of true or false
     */
    public void setHasShip(Boolean hasShip){
        this.hasShip = hasShip;
    }

    /**
     * Sets whether a cell has been shot
     * 
     * @param wasShot Boolean value of true or false
     */
    public void setWasShot(Boolean wasShot) {
        this.wasShot = wasShot;
    }

    /**
     * Sets whether a cell can have a ship placed on it
     * 
     * @param shipPlaceable Boolean value of true or false
     */
    public void setShipPlacable(Boolean shipPlaceable) {
        this.shipPlaceable = shipPlaceable;
    }

    /**
     * If the cell has seen no actions, it will return a 3 space blank
     * If the cell has been shot but no ship, then it will return M meaning Miss
     * If the cell has been shot and there was a ship, then it will return H meaning Hit
     * If the cell has a ship then is will return S meaning Ship
     * 
     */
    @Override
    public String toString(){
        if (hasShip && wasShot) {
            return " H ";
        }
        if (hasShip) {
            return " S ";
        }
        if (wasShot) {
            return " M ";
        }

        return "   ";
    }
    
}
