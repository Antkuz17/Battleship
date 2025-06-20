public class Grid {
    private String[][] grid = new String[11][11]; // Size is set as 11 x 11 grid outer grid is used for Letter + numbers
    
    public Grid(){
        grid[0][0] = "0";
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for(int i = 1; i < 11; i++){
            grid[i][0] = letters[i-1];
        }
        String[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
        for (int i = 1; i < 11; i++) {
            grid[0][i] = numbers[i - 1];
        }
        
    }
    public void printGrid(){
        for(int i =0; i < 11; i++){
            System.out.println("");
            for(int z = 0; z<11; z++){
                System.out.print("[" + grid[i][z] + "]");
            }
        }

    }
}
