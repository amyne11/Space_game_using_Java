package selfish.deck;
import java.io.Serializable;
/**
 * The Oxygen class represents an oxygen card in the game.
 */
public class Oxygen extends Card implements Serializable {
    private int value;
    private static final long serialVersionUID = 1234567893L;
    
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
    public void setValue(int value) {
        this.value = value;
    }
    /**
 * Provides a String representation of the Oxygen object.
 *
 * @return A String in the format "Oxygen(value)", where "value" is replaced by the actual value of the Oxygen object.
 */
    @Override
    public String toString() {
        return "Oxygen(" + value + ")";
}
    }
