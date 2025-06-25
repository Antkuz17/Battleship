// Class representing a single position on the grid
public class Coord {
    private int row;
    private int col;
    private String coordinate;
    private boolean hasShip;
    private boolean wasShot;
    private boolean shipPlaceable = true;

    public Coord(int row, int col){ // Constructor
        this.row = row;
        this.col = col;

        coordinate = Integer.toString(row) + col;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public void setWasShot(boolean wasShot) {
        this.wasShot = wasShot;
    }

    public void setShipPlacable(boolean shipPlaceable){
        this.shipPlaceable = shipPlaceable;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean gethasShip() {
        return hasShip;
    }

    public boolean getwasShot() {
        return wasShot;
    }
    
    public boolean getShipPlaceble() {
        return shipPlaceable;
    }
    @Override
    public String toString() {
        if(hasShip){
            return " S ";
        }
        return "   "; 
    }

    /**
     * Takes a letter and translates that letter into a corresponding number
     * 
     * @param letter The letter being translated (ex. "A")
     * @return Corresponding number which in this case would be 1
     */
    public String numToLetter(int num) {
        switch (num) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            default:
                return "?";
        }
    }  
    
    /**
     * Takes a letter and translates that letter into a corresponding number (0–9)
     * 
     * @param letter The letter being translated (ex. "A")
     * @return Corresponding number (0 for A, 1 for B, ..., 9 for J)
     */
    public int letterToNum(String letter) {
        switch (letter.toUpperCase()) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            default:
                return -1; // Invalid input
        }
    }

}
