package selfish.deck;


// Oxygen.java
public class Oxygen extends Card {
    private static final long serialVersionUID = 3L;

    private int value;

    public Oxygen(int value) {
        super("Oxygen", "Oxygen card with a value of " + value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + value + ")";
    }
}

