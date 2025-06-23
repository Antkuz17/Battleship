// Length
// ship initial pose
// hits 
// Death
import java.util.Scanner;

public class Ship {
    public Scanner input = new Scanner(System.in);

    private int length;
    private Coord initPos; // where the ship start
    private boolean isSunk;
    private String vector; // From the initial pose does the ship point left right up down

    public Ship(int length){
        this.length = length;
        this.initPos = null;
        this.isSunk = false;
        this.vector = null;
    }

    public void placeShip(){
        System.out.println("Where do you want the " + (length -1) + " ship to start?: ");
        String potentialPos = input.nextLine(); // Potential pos still needs to be checked for validity
        Coord initPos = translation(potentialPos); // Translates from String "A1" to coordinate notation 0,0
        while (!isOnGrid(initPos)) {
             System.out.println("Not on the grid try again");
             System.out.println("Where do you want the " + (length - 1) + " ship to start?: ");
             potentialPos = input.nextLine();
             initPos = translation(potentialPos);
        }
        boolean answer = initPosValid(initPos);
        System.out.println(answer);



    }

    /**
     * Returns whether or not a position is a valid one
     * 
     * Valid - At least one orientation in that initial pose will result in a legal position
     * 
     * Logic- Checks above and below the initpose to make sure that is a point where the ship would fit
     * 
     * @return boolean value of true if its placeable and false if not
     */
    public boolean initPosValid(Coord pose){ 
        boolean rightValid = true;
        boolean leftValid = true;
        boolean upValid = true;
        boolean downValid = true;

        if((pose.getRow() + (length-1)) > 9){
            downValid = false;
        }
        if ((pose.getRow() - (length - 1)) < 0) {
            upValid = false;
        }
        if ((pose.getCol() - (length - 1)) < 0) {
            leftValid = false;
        }
        if ((pose.getCol() + (length - 1)) > 9) {
            rightValid = false;
        }
        if(downValid == false && upValid == false && leftValid == false && rightValid == false){
            return false;
        }
        System.out.println("Right Valid: " + rightValid);
        System.out.println("Left Valid: " + leftValid);
        System.out.println("Up Valid: " + upValid);
        System.out.println("Down Valid" + downValid);
        return true;
    }
    /**
     * Returns a true or false depending on whether the given coordinate is on the grd
     * @return Return true if on grid and false if the coord is off grid
     */
    public boolean isOnGrid(Coord coord){
        if (coord.getCol() > 9 || coord.getCol() < 0 || coord.getRow() > 9 || coord.getRow() < 0){
            return false;
        }
        return true;
    }
    /**
     * Translates a user input into grid coords
     * 
     * For example A1 is turned into 0,0 or J10 is turned into 9,9
     * @param pos The user string input like "A1"
     * @return An coordinate object with the translated coords
     */

    public Coord translation(String pos){
        char firstLetter = pos.charAt(0);
        int firstNumber = Character.getNumericValue(firstLetter) - 10;
        int secondNumber;
        if (Integer.parseInt(pos.substring(1)) > 10) {
            secondNumber = 11;
        } 
        else if (Integer.parseInt(pos.substring(1)) == 10){
            secondNumber =9;
        }
        else {
            secondNumber = Character.getNumericValue(pos.charAt(1)) - 1;
        }
        System.out.println("First Number: " + firstNumber);
        System.out.println("Second Number: " + secondNumber);
        Coord initPos = new Coord(firstNumber, secondNumber);
        return initPos;
    }


}
