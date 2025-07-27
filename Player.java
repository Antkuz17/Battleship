public abstract class Player {
    
    protected Grid myGrid = new Grid();   // This players grid with their own ships and the shots that the enemy has taken at them
    protected Grid shotsGrid  = new Grid();  // A grid containing this players own personal shots against the other

    // All of the ships that each player will have
    protected Ship ship1 = new Ship(2);
    protected Ship ship2 = new Ship(3);
    protected Ship ship3 = new Ship(4);
    protected Ship ship4 = new Ship(5);
    protected Ship ship5 = new Ship(6);



}
