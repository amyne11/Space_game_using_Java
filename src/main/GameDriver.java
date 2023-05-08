import selfish.GameEngine;
import java.util.Scanner;
import java.io.File;
import selfish.deck.GameDeck;
import selfish.deck.SpaceDeck;
import java.io.FileNotFoundException;
import java.io.IOException;
import selfish.deck.Card;
import selfish.Astronaut;

public class GameDriver {
    public static String centreString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public GameDriver() {
    }

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        File file = new File("Desktop/COMP16412/comp16412-coursework-2__y54754ae/io/art.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Artwork file not found.");
            return;
        }
        String artwork = "";
        while (scanner.hasNextLine()) {
            artwork += scanner.nextLine() + "\n";
        }
        scanner.close();
        System.out.println(artwork);

        // ...

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Add player");
            System.out.println("2. Start game");
            System.out.println("3. Load game");
            System.out.println("4. Save game");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.print("Enter player's name: ");
                String playerName = scanner.nextLine();
                gameEngine.addPlayer(playerName);
            } else if (choice == 2) {
                gameEngine.startGame();

                // Cycle through players, draw a card, and breathe
                while (!gameEngine.gameOver()) {
                    Astronaut currentPlayer = gameEngine.getCurrentPlayer();
                
                    if (currentPlayer.isAlive()) {
                        currentPlayer.addToHand(gameEngine.draw());
                        currentPlayer.breathe(gameEngine);
                
                        System.out.println(currentPlayer.getName() + "'s turn");
                        System.out.println("Hand: ");
                        System.out.println(currentPlayer.getHandStr());
                        System.out.println("Actions: ");
                        System.out.println(currentPlayer.getActionsStr());
                        // You can add more code here to allow the player to play cards and target other players
                    }
                
                    if (!currentPlayer.isAlive()) {
                        System.out.println(currentPlayer + " has died.");
                        gameEngine.killPlayer(currentPlayer);
                    }
                
                    gameEngine.endTurn();
                }

                Astronaut winner = gameEngine.getWinner();
                if (winner != null) {
                    System.out.println(winner + " has won the game!");
                } else {
                    System.out.println("All players are dead. Game over.");
                }

            } else if (choice == 3) {
                // Load game
                System.out.print("Enter the file path to load the game: ");
                String filePath = scanner.nextLine();
                try {
                    gameEngine = GameEngine.loadState(filePath);
                    System.out.println("Game loaded successfully.");
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error loading game: " + e.getMessage());
                }
            } else if (choice == 4) {
                // Save game
                System.out.print("Enter the file path to save the game: ");
                String filePath = scanner.nextLine();
                try {
                    gameEngine.saveState(filePath);
                    System.out.println("Game saved successfully.");
                } catch (IOException e) {
                    System.err.println("Error saving game: " + e.getMessage());
                }
            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}