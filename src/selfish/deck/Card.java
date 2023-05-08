package selfish.deck;

// Card.java
import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 7L;

    private String name;
    private String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}

