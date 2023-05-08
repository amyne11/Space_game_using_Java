package selfish.deck;
import selfish.GameException;
public class GameDeck extends Deck {
    private static final long serialVersionUID = 1L;

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

    public GameDeck(String path){
        super();
        try {
            loadCards(path);
        } catch (GameException e) {
            System.err.println("Error loading GameDeck cards: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
