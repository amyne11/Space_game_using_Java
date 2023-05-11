package selfish;

import java.io.Serializable;
import java.util.Random;
import selfish.deck.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEngine class manages the game decks and the random number generation.
 */
public class GameEngine  implements Serializable{
    private GameDeck gameDeck;
    private SpaceDeck spaceDeck;
    private GameDeck discardGameDeck;
    private SpaceDeck discardSpaceDeck;
    private Random random;
    private static final long serialVersionUID = 1234567891L;
    private List<Astronaut> activePlayers;
    private Astronaut currentPlayer;


    /**
     * Constructs a GameEngine with default parameters.
     * @throws GameException if an error occurs while initializing the game engine
     */
    public GameEngine() throws GameException {
        // constructor details
    }

    /**
     * Constructs a GameEngine with the specified seed for the Random object.
     * @param seed the seed for the Random object
     * @throws GameException if an error occurs while initializing the game engine with seed
     */
    public GameEngine(long seed) throws GameException {
        // constructor details
    }

    /**
     * Returns the game deck.
     * @return the game deck
     */
    public GameDeck getGameDeck() {
        return this.gameDeck;
    }

    /**
     * Returns the space deck.
     * @return the space deck
     */
    public SpaceDeck getSpaceDeck() {
        return this.spaceDeck;
    }

    /**
     * Returns the discard game deck.
     * @return the discard game deck
     */
    public GameDeck getDiscardGameDeck() {
        return this.discardGameDeck;
    }

    /**
     * Returns the discard space deck.
     * @return the discard space deck
     */
    public SpaceDeck getDiscardSpaceDeck() {
        return this.discardSpaceDeck;
    }

      /**
     * Saves the current state of the game to a file.
     * 
     * @param filePath the path of the file where the state is saved.
     * @throws IOException if an I/O error occurs when writing to the file.
     */
    public void saveState(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }

    /**
     * Loads the state of the game from a file.
     * 
     * @param filePath the path of the file from where the state is loaded.
     * @return the GameEngine object loaded from the file.
     * @throws IOException if an I/O error occurs when reading from the file.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */
    public static GameEngine loadState(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameEngine) ois.readObject();
        }
    }

    /**
     * Adds a new player to the game.
     * 
     * @param player the player to be added.
     */
    public void addPlayer(Astronaut player) {
        this.activePlayers.add(player);
    }

    /**
     * Returns the list of active players.
     * 
     * @return the list of active players.
     */
    public List<Astronaut> getActivePlayers() {
        return this.activePlayers;
    }

    /**
     * Starts the turn for the current player.
     */
    public void startTurn() {
        // Set the current player as the first active player initially
        if (this.currentPlayer == null) {
            this.currentPlayer = this.activePlayers.get(0);
        }
        // Implement the logic of a player's turn start
    }

    /**
     * Ends the turn for the current player and sets the next player as the current player.
     */
    public void endTurn() {
        // Implement the logic of a player's turn end
        // Update the current player to the next player in the active players list
        int currentIndex = this.activePlayers.indexOf(this.currentPlayer);
        if (currentIndex == this.activePlayers.size() - 1) {
            // If the current player is the last in the list, set the first player as the current player
            this.currentPlayer = this.activePlayers.get(0);
        } else {
            this.currentPlayer = this.activePlayers.get(currentIndex + 1);
        }
    }
}
