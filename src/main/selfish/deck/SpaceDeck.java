package selfish.deck;

import selfish.GameException;
import java.util.*;
import java.io.Serializable;

/**
 * The SpaceDeck class represents a deck of space cards.
 * It extends the abstract Deck class.
 */
public class SpaceDeck extends Deck {
    /**
 * The constant identifier for the "Asteroid field" card.
 */
public static final String ASTEROID_FIELD = "Asteroid field";

/**
 * The constant identifier for the "Blank space" card.
 */
public static final String BLANK_SPACE = "Blank space";

/**
 * The constant identifier for the "Cosmic radiation" card.
 */
public static final String COSMIC_RADIATION = "Cosmic radiation";

/**
 * The constant identifier for the "Gravitational anomaly" card.
 */
public static final String GRAVITATIONAL_ANOMALY = "Gravitational anomaly";

/**
 * The constant identifier for the "Hyperspace" card.
 */
public static final String HYPERSPACE = "Hyperspace";

/**
 * The constant identifier for the "Meteoroid" card.
 */
public static final String METEOROID = "Meteoroid";

/**
 * The constant identifier for the "Mysterious nebula" card.
 */
public static final String MYSTERIOUS_NEBULA = "Mysterious nebula";

/**
 * The constant identifier for the "Solar flare" card.
 */
public static final String SOLAR_FLARE = "Solar flare";

/**
 * The constant identifier for the "Useful junk" card.
 */
public static final String USEFUL_JUNK = "Useful junk";

/**
 * The constant identifier for the "Wormhole" card.
 */
public static final String WORMHOLE = "Wormhole";

    private static final long serialVersionUID = 1234567894L;

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
            Collection<Card> loadedCards = loadCards(path);
            // TODO: you need to implement how to store these cards
        } catch (GameException e) {
            // handle the exception
        }
    }
}