# Battleship

A command-line implementation of the classic Battleship game in Java, featuring an intelligent AI opponent with advanced hunting algorithms.

## Description

This is a terminal-based Battleship game where you compete against an AI opponent on a 10x10 grid. The game includes 5 ships of varying lengths (2-6 units) and features a smart AI that uses hunt and target modes to efficiently locate and sink your fleet.


## How to Play

### Setup
1. Clone the repository
2. Compile all Java files
3. Run the game

```bash
# Clone
git clone https://github.com/Antkuz17/Battleship.git
cd Battleship

# Compile
javac *.java

# Run
java Main
```

### Gameplay
1. **Ship Placement** - You'll be prompted to place each of your 5 ships
   - Enter a starting coordinate (e.g., "A1", "B5")
   - Press 'R' to rotate the ship orientation
   - Press any other key to confirm placement

2. **Battle Phase** - Take turns with the AI
   - Enter coordinates to fire (e.g., "C3")
   - Hit all segments of a ship to sink it
   - First player to sink all enemy ships wins

## Game Logic

### AI Strategy
The AI operates in two modes:

**Hunt Mode** - When no ships are currently being targeted:
- Generates random coordinates
- Validates positions could fit remaining ships (wont target a position if the only ship alive wont fit)
- Avoids previously shot locations

**Target Mode** - After scoring a hit:
- Systematically checks adjacent cells (up, down, left, right)
- Establishes firing direction after second hit
- Continues in established direction until ship is sunk
- Returns to hunt mode after sinking a ship

### Ship Placement Validation
- Ships cannot overlap
- Ships cannot go out of bounds
- Adjacent cells are marked as invalid to ensure proper spacing
- System validates all four directions (up, down, left, right) before allowing placement

## Code Structure

- **Main.java** - Entry point
- **Game.java** - Main game loop and win condition logic
- **Player.java** - Abstract player class
- **HumanPlayer.java** - Handles user input and shooting
- **AIPlayer.java** - AI logic for hunt/target modes
- **Ship.java** - Ship placement and sunk status tracking
- **Grid.java** - 10x10 game board representation
- **Cell.java** - Individual grid cell state (ship, shot, placeable)
- **Utils.java** - Helper methods for coordinate translation and validation


## Requirements

- Java 8 or higher
- Command line / Terminal

## License

MIT License - Feel free to use and modify

## Author

Anton Kuzmichev
