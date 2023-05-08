package selfish.deck;
import selfish.GameException;
public class SpaceDeck extends Deck {
    // attributes, constructors, methods
    private static final long serialVersionUID = 1L;

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

    public SpaceDeck(String path){
        super();
        try {
            loadCards(path);
        } catch (GameException e) {
            System.err.println("Error loading SpaceDeck cards: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
