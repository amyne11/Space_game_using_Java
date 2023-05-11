package selfish.deck;
import java.io.Serializable;
/**
 * The Card class represents a card in the game.
 */
public class Card implements Serializable{
    private String name;
    private String description;
    private static final long serialVersionUID = 1234567893L;

    /**
     * Constructs a Card with the specified name and description.
     * @param name the name of the card
     * @param description the description of the card
     */
    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the description of the card.
     * @return the description of the card
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the card.
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return "Card Name: " + name + ", Description: " + description;
    }
}
