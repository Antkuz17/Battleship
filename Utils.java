public class Utils {
    /**
     * Translates a user input into grid coords
     * 
     * For example A1 is turned into 0,0 or J10 is turned into 9,9
     * 
     * @param pos The user string input like "A1"
     * @return An coordinate object with the translated coords
     */

    public static Coord translation(String pos) {
        char firstLetter = pos.charAt(0);
        int firstNumber = Character.getNumericValue(firstLetter) - 10;
        int secondNumber;
        if (Integer.parseInt(pos.substring(1)) > 10) {
            secondNumber = 11;
        } else if (Integer.parseInt(pos.substring(1)) == 10) {
            secondNumber = 9;
        } else {
            secondNumber = Character.getNumericValue(pos.charAt(1)) - 1;
        }
        Coord initPos = new Coord(firstNumber, secondNumber);
        return initPos;
    }


    /**
     * Generates a random coordinate on the grid
     * 
     * @return String in the form of A1 or B2 or G10
     */
    public static String generateRandCoord() {
        int randX = (int) (Math.random() * 10);
        int randY = (int) (Math.random() * 10);
        String coord = Coord.numToLetter(randX) + (randY + 1);
        return coord;
    }

    // /**
    // * Generates a random set of coords
    // *
    // * ex (6,2) (0,9) (7,1)
    // *
    // * @return These numbers as a two element array
    // */
    // public int[] genRandCoord(){
    // int[] nums = new int[2];
    // for(int i =0; i< 2; i++){
    // int random = (int) (Math.random() * 10);
    // nums[i] = random;
    // }
    // return nums;
    // }
    /**
     * Gets a coordinate from the user in the from A1. Does validation to ensure that the inputed coordinate is on the grid
     * @return
     */
    public String getCoord(){
        while(true){
            String Coord = input.nextLine();
            if()
        }
    }
}
