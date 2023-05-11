package selfish.deck;
<<<<<<< HEAD
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import selfish.GameException;
import java.util.Collections;

public abstract class Deck implements Serializable {
    private static final long serialVersionUID = 4L;
    // attributes, constructors, methods
    protected List<Card> cards;
    protected List<Card> discards;

    // Empty constructor
    public Deck() {
        cards = new ArrayList<>();
        discards = new ArrayList<>();
    }

    // Empty loadCards method
    protected List<Card> loadCards (String path) throws GameException{
        // Add your implementation later
        List<Card> loadedCards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Process each line, create Card objects
                String[] parts = line.split(",");
                String cardName = parts[0].trim();
                String cardDescription = parts[1].trim();
                int cardCount = Integer.parseInt(parts[2].trim());

                for (int i = 0; i < cardCount; i++) {
                    loadedCards.add(new Card(cardName, cardDescription));
                }
            }
        }catch (IOException e) {
            throw new GameException("Error reading cards from file: " + path, e);
        } catch (NumberFormatException e) {
            throw new GameException("Invalid card count format in file: " + path, e);
        }
        return loadedCards;
    }
    public Card drawCard() {
        if (cards.isEmpty()) {
            mergeDecks();
        }
        return cards.remove(0);
    }
    public void discardCard(Card card) {
        discards.add(card);
    }
    public void mergeDecks() {
        cards.addAll(discards);
        discards.clear();
        Collections.shuffle(cards);
    }
    

}
=======
import java.util.*;
import selfish.GameException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * The abstract Deck class represents a deck of cards in the game.
 */
public abstract class Deck implements Serializable{
    protected List<Card> cards;
    private static final long serialVersionUID = 1234567894L; // assuming 1L, you should replace it with actual value

    /**
     * Constructs a Deck with an empty list of cards.
     */
    protected Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Loads cards from a file at the specified path.
     * @param path the path to the file containing card data
     * @return a list of cards loaded from the file
     * @throws GameException if an error occurs while reading the file
     */

    protected List<Card> loadCards(String path) throws GameException {
        List<Card> cards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // skip the header line
            while ((line = br.readLine()) != null) {
                // assuming each line is formatted as "CardName;CardDescription;Quantity"
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    throw new GameException("Invalid line format: " + line);
                }
                String cardName = parts[0].trim();
                String cardDescription = parts[1].trim();
                int quantity;
                try {
                    quantity = Integer.parseInt(parts[2].trim());
                } catch (NumberFormatException e) {
                    throw new GameException("Invalid quantity format: " + parts[2].trim(), e);
                }
                // add new Card objects to the list
                for (int i = 0; i < quantity; i++) {
                    cards.add(new Card(cardName, cardDescription));
                }
            }
        } catch (IOException e) {
            throw new GameException("Error reading the cards file", e);
        }
        return cards;
    }

    /**
     * Converts a string to a list of cards. This method is not yet implemented.
     * @param str the string to convert to cards
     * @return a list of cards
     */
    

    protected List<Card> stringToCards(String str) {
        // TODO: implement converting string to cards
        return null;
    }
    /**
     * Adds a card to the top of the deck.
     * @param card the card to add to the deck
     * @return the new size of the deck
     */
    public int add(Card card) {
        this.cards.add(0, card); // add at the beginning for LIFO logic
        return this.cards.size();
    }
    /**
     * Adds a list of cards to the top of the deck.
     * @param cards the cards to add to the deck
     * @return the new size of the deck
     */
    protected int add(List<Card> cards) {
        this.cards.addAll(0, cards); // add at the beginning for LIFO logic
        return this.cards.size();
    }
    /**
     * Draws a card from the deck. This method is not yet implemented.
     * @return the drawn card
     */
    public Card draw() {
        // TODO: implement draw logic
        return null;
    }
    /**
     * Removes a card from the deck. This method is not yet implemented.
     * @param card the card to remove from the deck
     */
    public void remove(Card card) {
        // TODO: implement remove logic
    }
    /**
     * Shuffles the deck using the specified Random object.
     * @param random the Random object to use for shuffling the deck
     */
    public void shuffle(Random random) {
        Collections.shuffle(this.cards, random);
    }
    /**
     * Returns the current size of the deck.
     * @return the size of the deck
     */
    public int size() {
        return this.cards.size();
    }
}

>>>>>>> new
