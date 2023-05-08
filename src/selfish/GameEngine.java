package selfish;
import java.util.Random;
import selfish.deck.GameDeck;
import selfish.deck.SpaceDeck;
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import selfish.Astronaut;
import selfish.deck.Card;
import selfish.deck.Oxygen;

public class GameEngine implements Serializable{
    // GameEngine implementation will go here
    private static final long serialVersionUID = 2L;
    private ArrayList<Astronaut> activePlayers;
    private Astronaut currentPlayer;
private GameDeck gameDeck;
private GameDeck gameDiscard;
private SpaceDeck spaceDeck;
private SpaceDeck spaceDiscard;

public GameEngine() {
    this(new Random().nextLong());
    activePlayers = new ArrayList<>();
}
public GameEngine(long seed) {
    gameDeck = new GameDeck("Desktop/COMP16421/comp16412-coursework-2__y54754ae/io/ActionsCards.txt");
    gameDiscard = new GameDeck("Desktop/COMP16421/comp16412-coursework-2__y54754ae/io/ActionsCards.txt");
    spaceDeck = new SpaceDeck("Desktop/COMP16421/comp16412-coursework-2__y54754ae/io/SpaceCards.txt");
    spaceDiscard = new SpaceDeck("Desktop/COMP16421/comp16412-coursework-2__y54754ae/io/SpaceCards.txt");
}
public GameDeck getGameDeck() {
    return gameDeck;
}

public GameDeck getGameDiscard() {
    return gameDiscard;
}

public SpaceDeck getSpaceDeck() {
    return spaceDeck;
}

public SpaceDeck getSpaceDiscard() {
    return spaceDiscard;
}

public void saveState(String filePath) throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
        out.writeObject(this);
    }
}

public static GameEngine loadState(String filePath) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
        return (GameEngine) in.readObject();
    }
}
public void addPlayer(String playerName) {
    Astronaut newPlayer = new Astronaut(playerName);
    activePlayers.add(newPlayer);
}
public Astronaut getCurrentPlayer() {
    return currentPlayer;
}

public ArrayList<Astronaut> getActivePlayers() {
    return activePlayers;
}
public void startTurn() {
    if (activePlayers.size() > 0) {
        currentPlayer = activePlayers.get(0);
    }
}

public void endTurn() {
    if (activePlayers.size() > 1) {
        Astronaut removedPlayer = activePlayers.remove(0);
        activePlayers.add(removedPlayer);
        currentPlayer = activePlayers.get(0);
    }
}
public void startGame() {
    gameDeck.mergeDecks();
    spaceDeck.mergeDecks();
}

public void splitOxygen() {
    Astronaut currentPlayer = getCurrentPlayer();

    // Check if the currentPlayer has the Oxygen card with a value of 2
    boolean hasDoubleOxygen = currentPlayer.getHand().stream().anyMatch(card -> card instanceof Oxygen && ((Oxygen) card).getValue() == 2);

    if (!hasDoubleOxygen) {
        throw new IllegalStateException("The current player does not have an Oxygen card with a value of 2.");
    }

    // Remove the double Oxygen card from the currentPlayer's hand
    Card doubleOxygenCard = currentPlayer.getHand().stream().filter(card -> card instanceof Oxygen && ((Oxygen) card).getValue() == 2).findFirst().orElse(null);
    currentPlayer.getHand().remove(doubleOxygenCard);

    // Create two single Oxygen cards
    Oxygen singleOxygen1 = new Oxygen(1);
    Oxygen singleOxygen2 = new Oxygen(1);

    // Add the single Oxygen cards to the currentPlayer's hand
    currentPlayer.addToHand(singleOxygen1);
    currentPlayer.addToHand(singleOxygen2);
}












}

