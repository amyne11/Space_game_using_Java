<<<<<<< HEAD
package src.selfish.deck;
import selfish.GameException;
public class GameDeck extends Deck {
    private static final long serialVersionUID = 5L;
=======
package selfish.deck;

import selfish.GameException;
import java.util.*;
import java.io.Serializable;


/**
 * The GameDeck class represents a deck of game cards.
 * It extends the abstract Deck class.
 */

public class GameDeck extends Deck implements Serializable{
>>>>>>> new

    public static final String OXYGEN_CARD = "Oxygen";
    public static final String LASER_BLAST_CARD = "Laser Blast";
    public static final String OXYGEN_SIPHON_CARD = "Oxygen Siphon";
    public static final String ROCKET_BOOSTER_CARD = "Rocket Booster";
    public static final String SHIELD_CARD = "Shield";
    public static final String HACK_SUIT_CARD="Hack suit";
    public static final String HOLE_IN_SUIT_CARD="Hole in suit";
    public static final String OXYGEN_1_CARD="Oxygen(1)";
    public static final String OXYGEN_2_CARD="Oxygen(2)";
    public static final String TETHER_CARD="Tether";
    public static final String TRACTOR_BEAM_CARD="Tractor beam";
<<<<<<< HEAD

    public GameDeck(String path){
        super();
        try {
            loadCards(path);
        } catch (GameException e) {
            System.err.println("Error loading GameDeck cards: " + e.getMessage());
            e.printStackTrace();
        }

    }



    public void discard(Card card) {
        // Add the card to the discard pile
        cards.add(card);
    }
=======
    private static final long serialVersionUID = 1234567895L; // replace with actual serialVersionUID

    /**
     * Constructs a GameDeck with an empty list of cards.
     */
    public GameDeck() {
        // default constructor
    }

    /**
     * Constructs a GameDeck and loads cards from a file at the specified path.
     * @param path the path to the file containing card data
     */
    public GameDeck(String path) {
        try {
            List<Card> loadedCards = loadCards(path);
            // TODO: you need to implement how to store these cards
        } catch (GameException e) {
            // handle the exception
        }
    }

    
>>>>>>> new
}
