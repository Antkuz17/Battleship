public class Grid {
    private String[][] grid = new String[11][11]; // Size is set as 11 x 11 grid outer grid is used for Letter + numbers

    public Grid() { // Constructor for grid object
        for (int i = 0; i < 11; i++) {
            for (int z = 0; z < 11; z++) {
                grid[i][z] = "   ";
            }
        }
        grid[0][0] = " ";
        String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        for (int i = 1; i < 11; i++) {
            grid[i][0] = letters[i - 1];
        }
        String[] numbers = { " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 ", " 10 " };
        for (int i = 1; i < 11; i++) {
            grid[0][i] = numbers[i - 1];
        }

    }

    /**
     * Prints the grid to terminal using nested for loops
     * 
     * Each cell is " ~ "
     */
    public void printGrid() {
        for (int i = 0; i < 11; i++) {
            System.out.println("");
            for (int z = 0; z < 11; z++) {
                if (z== 10){
                    System.out.print("|" + grid[i][z] + "|");
                }
                else{
                System.out.print("|" + grid[i][z]);
                }
            }
        }
    }
    /**
     * Places a missed marker "~" at a position on the board
     * 
     * @param x The number shot at
     * @param y The letter shot at
     */
    public void placeMissMarker(String x, String y){
        String yNum = letterToNumTrans(y);
        int xcoord = Integer.parseInt(x);
        xcoord += 1;
        int ycoord = Integer.parseInt(yNum);
        ycoord += 1;
        grid[ycoord -1][xcoord -1] = " ~ ";
    }

    /**
     * Takes a letter and translates that letter into a corresponding number
     * 
     * @param letter The letter being translated (ex. "A")
     * @return Corresponding number
     */
    public String letterToNumTrans(String letter){
        switch(letter){
            case("A"):
                return "1";
            case ("B"):
                return "2";
            case ("C"):
                return "3";
            case ("D"):
                return "4";
            case ("E"):
                return "5";
            case ("F"):
                return "6";
            case ("G"):
                return "7";
            case ("H"):
                return "8";
            case ("I"):
                return "9";
            case ("J"):
                return "10";
            default:
                return "-1";
        }
    }
}
