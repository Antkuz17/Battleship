// Class representing a single position on the grid
public class Coord {
    private int row;
    private int col;
    private String coordinate;
    private boolean hasShip;
    private boolean wasShot;

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
    @Override
    public String toString() {
        return " ~ "; 
    }

    /**
     * Takes a letter and translates that letter into a corresponding number
     * 
     * @param letter The letter being translated (ex. "A")
     * @return Corresponding number
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
}
