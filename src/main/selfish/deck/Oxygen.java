package selfish.deck;
import java.io.Serializable;
/**
 * The Oxygen class represents an oxygen card in the game.
 */
public class Oxygen extends Card implements Serializable{
    private int value;
    private static final long serialVersionUID = 1234567897L;

    /**
     * Constructs an Oxygen card with the specified value.
     * @param value the value of the oxygen card
     */
    public Oxygen(int value) {
        super("Oxygen", "This is an Oxygen card with value " + value);
        this.value = value;
    }

    /**
     * Returns the value of the oxygen card.
     * @return the value of the oxygen card
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a string representation of the oxygen card.
     * @return a string representation of the oxygen card
     */
    @Override
    public String toString() {
        return super.toString() + ", Value: " + value;
    }
}