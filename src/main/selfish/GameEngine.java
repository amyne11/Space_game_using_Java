package selfish;
import selfish.deck.*;
import java.util.*;
import java.io.*;

public class GameEngine implements Serializable {
    private Collection<Astronaut> activePlayers;
    private List<Astronaut> corpses;
    private Astronaut currentPlayer;
    private boolean hasStarted;
    private Random random;
    private GameDeck gameDeck;
    private GameDeck gameDiscard;
    private SpaceDeck spaceDeck;
    private SpaceDeck spaceDiscard;
    private Iterator<Astronaut> playerIterator;
    private static final long serialVersionUID = 1234567896L;
    private static GameEngine instance;
    
    
    /**
 * Default constructor for the GameEngine class. Initializes the player iterator.
 *
 * @throws GameException if there is an issue initializing the game.
 */
    private GameEngine() throws GameException {
        // constructor details
        playerIterator = activePlayers.iterator();
    }

    
    /**
 * Constructor for the GameEngine class. Initializes the player iterator, seed for random, game deck and space deck.
 *
 * @param seed the seed for the random number generator.
 * @param GameDeckpath the file path of the game deck.
 * @param SpaceDeckpath the file path of the space deck.
 * @throws GameException if there is an issue initializing the game.
 */
    public GameEngine(long seed, String GameDeckpath , String SpaceDeckpath ) throws GameException {
        // constructor details
        playerIterator = activePlayers.iterator();
        this.random=new Random(seed);
        this.gameDeck= new GameDeck(GameDeckpath);
        this.spaceDeck= new SpaceDeck(SpaceDeckpath);
    }
    public static GameEngine getInstance() {
        if (instance == null) {
            try {
                instance = new GameEngine();
            } catch (GameException e) {
                // Handle exception, for example print stack trace or log it
                e.printStackTrace();
                // Consider whether returning null, throwing a RuntimeException, or other handling is appropriate here.
                return null;
            }
        }
        return instance;
    }
    
    
    /**
 * Adds a player to the game.
 *
 * @param player the name of the player to be added.
 * @return the new number of active players in the game.
 */
    public int addPlayer(String player) {
        Astronaut newPlayer = new Astronaut(player, this);
        this.activePlayers.add(newPlayer);
        return this.activePlayers.size();
    }

    
    /**
 * Ends the current player's turn and updates the current player to the next player in the sequence.
 *
 * @return the index (or position) of the new current player in the list of active players.
 */
    public int endTurn() {
        if (!playerIterator.hasNext()) {
            playerIterator = activePlayers.iterator(); // Reset the iterator when we reach the end
        }
        currentPlayer = playerIterator.next();
        
        // Find index (or position) of current player
        int index = 0;
        for (Astronaut astronaut : activePlayers) {
            if (astronaut == currentPlayer) {
                break;
            }
            index++;
        }
        return index;
    }
    /**
 * Checks if the game is over.
 *
 * @return true if the game is over (only one or no active players), false otherwise.
 */
    public boolean gameOver() {
        return activePlayers.size() <= 1;
    }
    /**
 * Gets a list of all players in the game, both active and eliminated.
 *
 * @return a list containing all players.
 */
public List<Astronaut> getAllPlayers() {
    List<Astronaut> allPlayers = new ArrayList<>(activePlayers);
    allPlayers.addAll(corpses);
    return allPlayers;
}

    
    /**
     * Returns the total number of players in the game, both active and deceased.
     *
     * @return The total number of players.
     */
    public int getFullPlayerCount() {
        return activePlayers.size() + corpses.size();
    }
    
    /**
     * Returns the game deck.
     * @return the game deck
     */
    public GameDeck getGameDeck() {
        return this.gameDeck;
    }
    
     /**
     * Returns the discard game deck.
     * @return the discard game deck
     */
    public GameDeck getGameDiscard() {
        return this.gameDiscard;
    }
    
    /**
     * Returns the space deck.
     * @return the space deck
     */
    public SpaceDeck getSpaceDeck() {
        return this.spaceDeck;
    }
    
    /**
     * Returns the discard space deck.
     * @return the discard space deck
     */
    public SpaceDeck getSpaceDiscard() {
        return this.spaceDiscard;
    }
    

    public void addCorpse(Astronaut astronaut) {
        corpses.add(astronaut);
      }
    /**
 * Gets the winner of the game.
 *
 * @return the winning player if the game is over and there is a winner, null otherwise.
 */
    public Astronaut getWinner() {
        if (gameOver() && !activePlayers.isEmpty()) {
            return activePlayers.iterator().next();
        }
        return null;
    }
    
    /**
 * Eliminates a player from the game.
 *
 * @param corpse the player to be eliminated.
 */
    public void killPlayer(Astronaut corpse) {
        try {
            if (activePlayers.remove(corpse)) {
                corpses.add(corpse);
                // If the current player is killed, we need to update the current player
                if (corpse == currentPlayer) {
                    endTurn();
                }
            } else {
                throw new GameException("Player not found in the list of active players");
            }
        } catch (GameException e) {
            System.err.println("Error killing player: " + e.getMessage());
        }
    }
    
    
    /**
     * Returns the current player.
     * @return the current player.
     */
    public Astronaut getCurrentPlayer() {
        return this.currentPlayer;
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
 * Merges the cards from the second deck into the first deck.
 *
 * @param deck1 The first deck.
 * @param deck2 The second deck.
 */
public void mergeDecks(Deck deck1, Deck deck2) {
    List<Card> cards1 = deck1.getCards();
    List<Card> cards2 = deck2.getCards();
    mergeLists(cards1, cards2);
    

    // Get the cards from the second deck
   // mergeLists(deck1,deck2);
    //deck1.addAll(deck2);
    //List<Card> deck2Cards = new ArrayList<>();
    /*while (true) {
        try {
            deck2Cards.add(deck2.draw());
        } catch (GameException e) {
            break; // Exit the loop if there are no more cards to draw
        }
    }
    
    // Add the cards to the first deck
   deck1.add(deck2Cards);*/
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
     * Splits a double oxygen into two single oxygen.
     * 
     * @param dbl the double oxygen to be split.
     * @return an array of two single oxygen.
     */
    public Oxygen[] splitOxygen(Oxygen dbl) {
        if (dbl.getValue()==2) {
            return new Oxygen[]{new Oxygen(1), new Oxygen(1)};
        } else {
            return new Oxygen[]{dbl};
        }
    }
    
    /**
 * Starts the game.
 * This method sets the first player in the list as the current player and deals 5 cards 
 * from the game deck to each player.
 * If there are not enough cards to deal, an error message is printed and the method returns early.
 * Finally, the game is marked as having started.
 *
 * @throws IllegalStateException If there are no players in the game or not enough cards in the deck.
 */
public void startGame() throws IllegalStateException {
    // Ensure there are players in the game
    if (this.activePlayers.isEmpty()) {
        throw new IllegalStateException("No players in the game.");
    }

    // Ensure there are enough cards in the deck
    if (this.gameDeck.size() < this.activePlayers.size() * 5) {
        throw new IllegalStateException("Not enough cards in the deck.");
    }

    // Set the first player as the current player
    this.currentPlayer = this.activePlayers.iterator().next();

    // Deal initial cards to each player
    for (Astronaut player : this.activePlayers) {
        for (int i = 0; i < 5; i++) {
            Card card = this.gameDeck.draw();
            player.addToHand(card);
        }
    }

    this.hasStarted = true;
}

    
    /**
     * Starts the turn for the current player.
     */
    public void startTurn() {
        if (this.currentPlayer == null && !this.activePlayers.isEmpty()) {
            this.currentPlayer = this.activePlayers.iterator().next();
        }
    }

    /**
 * Handles the travel of a specific astronaut.
 *
 * @param traveller the astronaut that is travelling.
 * @return the card that the astronaut landed on, null if not implemented yet.
 */
    public Card travel(Astronaut traveller) {
        // To be implemented
        return null;
    }
    public static void mergeLists(List<Card> list1, List<Card> list2) {
        list1.addAll(list2);
    }
}