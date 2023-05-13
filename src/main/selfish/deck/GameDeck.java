package selfish.deck;

import selfish.GameException;
import java.util.*;
import java.io.Serializable;


/**
 * The GameDeck class represents a deck of game cards.
 * It extends the abstract Deck class.
 */

 public class GameDeck extends Deck {
    /**
 * The constant identifier for the "Hack suit" card.
 */
public static final String HACK_SUIT = "Hack suit";

/**
 * The constant identifier for the "Hole in suit" card.
 */
public static final String HOLE_IN_SUIT = "Hole in suit";

/**
 * The constant identifier for the "Laser blast" card.
 */
public static final String LASER_BLAST = "Laser blast";

/**
 * The constant identifier for the "Oxygen" card.
 */
public static final String OXYGEN = "Oxygen";

/**
 * The constant identifier for the "Oxygen(1)" card.
 */
public static final String OXYGEN_1 = "Oxygen(1)";

/**
 * The constant identifier for the "Oxygen(2)" card.
 */
public static final String OXYGEN_2 = "Oxygen(2)";

/**
 * The constant identifier for the "Oxygen siphon" card.
 */
public static final String OXYGEN_SIPHON = "Oxygen siphon";

/**
 * The constant identifier for the "Rocket booster" card.
 */
public static final String ROCKET_BOOSTER = "Rocket booster";

/**
 * The constant identifier for the "Shield" card.
 */
public static final String SHIELD = "Shield";

/**
 * The constant identifier for the "Thether" card.
 */
public static final String TETHER = "Tether";

/**
 * The constant identifier for the "Tractor beam" card.
 */
public static final String TRACTOR_BEAM = "Tractor beam";

    private static final long serialVersionUID = 1234567892L;

    /**
     * Constructs a GameDeck with an empty list of cards.
     */
    public GameDeck() {
        // default constructor
        super();
    }

    /**
     * Constructs a GameDeck and loads cards from a file at the specified path.
     * @param path the path to the file containing card data
     */
    public GameDeck(String path) {
        try {
            Collection<Card> loadedCards = loadCards(path);
            for (Card card : loadedCards) {
                this.add(card);
            }
        } catch (GameException e) {
            // handle the exception
        }
    }


    public Oxygen drawOxygen(int value) {
        Oxygen foundOxygen = null;
        Iterator<Card> iterator = this.getIterator();
    
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card instanceof Oxygen && ((Oxygen) card).getValue() == value) {
                foundOxygen = (Oxygen) card;
                iterator.remove();
                break;
            }
        }
        return foundOxygen;
    }
    public Oxygen[] splitOxygen(Oxygen dbl) {
        if(dbl.getValue() != 2) {
            return null;
        }
        return new Oxygen[] {new Oxygen(1), new Oxygen(1)};
    }
}