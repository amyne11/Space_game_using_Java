package selfish.deck;
import java.util.*;
import selfish.GameException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * The abstract Deck class represents a deck of cards in the game.
 */
public abstract class Deck implements Serializable {
    private LinkedList<Card> cards;
    private static final long serialVersionUID = 1234567891L;

    /**
     * Constructs a Deck with an empty list of cards.
     */
    protected Deck() {
        this.cards = new LinkedList<>();
    }
    /**
 * Loads cards from a file at the specified path.
 * @param path the path to the file containing card data
 * @return a collection of cards loaded from the file
 * @throws GameException if an error occurs while reading the file
 */

     protected static Collection<Card> loadCards(String path) throws GameException {
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
    

     protected static Card[] stringToCards(String str) {
        // Assuming the string is formatted as "CardName;CardDescription;Quantity"
        String[] parts = str.split(";");
        String cardName = parts[0].trim();
        String cardDescription = parts[1].trim();
        int quantity = Integer.parseInt(parts[2].trim());
    
        Card[] cards = new Card[quantity];
        // add new Card objects to the array
        for (int i = 0; i < quantity; i++) {
            cards[i] = new Card(cardName, cardDescription);
        }
    
        return cards;
    }
    
    /**
     * Adds a card to the top of the deck.
     * @param card the card to add to the deck
     * @return the new size of the deck
     */
    public int add(Card card) {
        
        this.cards.addFirst(card); // add at the top
        return this.cards.size();
    }

    /**
     * Adds a list of cards to the top of the deck.
     * @param cards the cards to add to the deck
     * @return the new size of the deck
     */
    protected int add(Collection<Card> cards) {
        this.cards.addAll(0, cards);  // add at the top
        return this.cards.size();
    }

    /**
 * Draws a card from the deck.
 * @return the drawn card, or null if the deck is empty
 */
public Card draw() {
    if (this.cards.isEmpty()) {
        return null;
    }
    return this.cards.removeLast(); // draw from the deck
}
public Iterator<Card> getIterator() {
    return this.cards.iterator();
}


/**
 * Removes a card from the deck.
 * @param card the card to remove from the deck
 */
public void remove(Card card) {
     this.cards.remove(card); // remove the card if it exists
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
    /**
 * Merges the specified deck into this deck.
 * @param other the deck to merge into this deck
 * @throws GameException if an error occurs while drawing cards from the other deck
 */
public void merge(Deck other) throws GameException {
    while (other.size() > 0) {
        this.add(other.draw());
    }
}
public List<Card> getCards() {
    return new ArrayList<>(cards);
}
}


