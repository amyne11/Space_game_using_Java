package selfish.deck;
import java.io.Serializable;
public class Card implements Serializable, Comparable <Card> {
    private String name;
    private String description;
    private static final long serialVersionUID = 1234567890L;
    
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
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return name;
    }
    @Override
    public int compareTo(Card other) {
        return this.toString().compareTo(other.toString());
    }
}