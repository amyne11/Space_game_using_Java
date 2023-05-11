package selfish.deck;

import selfish.GameException;
import java.util.*;
import java.io.Serializable;

/**
 * The SpaceDeck class represents a deck of space cards.
 * It extends the abstract Deck class.
 */
public class SpaceDeck extends  Deck implements Serializable{

    public static final String BLANK_SPACE_CARD = "Blank Space";
    public static final String METEOROID_CARD = "Meteoroid";
    public static final String WORMHOLE_CARD = "Wormhole";
    public static final String SOLAR_FLARE_CARD = "Solar Flare";
    public static final String GRAVITATIONAL_ANOMALY_CARD = "Gravitational Anomaly";
    public static final String ASTEROID_FIELD_CARD="Asteroid field";
    public static final String COSMIC_RADIATION_CARD="Cosmic radiation";
    public static final String HYPERSPACE_CARD="Hyperspace";
    public static final String USEFUL_JUNK_CARD="Useful junk";
    public static final String MYSTERIOUS_NEBULA_CARD="Mysterious Nebula";

    private static final long serialVersionUID = 1234567896L; // replace with actual serialVersionUID
    /**
     * Constructs a SpaceDeck with an empty list of cards.
     */
    public SpaceDeck() {
        // default constructor
    }
    /**
     * Constructs a SpaceDeck and loads cards from a file at the specified path.
     * @param path the path to the file containing card data
     */
    public SpaceDeck(String path) {
        try {
            List<Card> loadedCards = loadCards(path);
            // TODO: you need to implement how to store these cards
        } catch (GameException e) {
            // handle the exception
        }
    }


    
}
