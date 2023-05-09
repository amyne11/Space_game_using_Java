package selfish;

import selfish.deck.Oxygen;
import selfish.deck.Card;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.io.Serializable;

/**
 * Represents an Astronaut in the game, including their hand, track, and game state.
 */
public class Astronaut implements Comparable<Astronaut> {
    private String name;
    private List<Card> actions = new ArrayList<>();
    private List<Oxygen> oxygens = new ArrayList<>();
    private List<Card> track = new ArrayList<>();
    private GameEngine game;
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new Astronaut with the specified name.
     *
     * @param name the name of the astronaut
     */
    public Astronaut(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the astronaut.
     *
     * @return the name of the astronaut
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets a list of all cards in the astronaut's hand.
     *
     * @return a list of all cards in the astronaut's hand
     */
    public List<Card> getHand() {
        List<Card> hand = new ArrayList<>();
        hand.addAll(actions);
        hand.addAll(oxygens);
        return hand;
    }

    /**
     * Removes and returns a card with the specified name from the astronaut's hand.
     *
     * @param cardName the name of the card to remove
     * @return the removed card, or null if the card was not found
     */
    public Card removeCardWithName(String cardName) {
        Card foundCard = null;
        for (Iterator<Card> iterator = getHand().iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getName().equals(cardName)) {
                foundCard = card;
                iterator.remove();
                break;
            }
        }
        return foundCard;
    }

    /**
     * Gets the distance of the astronaut from the ship, based on the size of their track.
     *
     * @return the distance of the astronaut from the ship
     */
    public int distanceFromShip() {
        return track.size();
    }

    /**
     * Determines if the astronaut is alive, based on their remaining oxygen.
     *
     * @return true if the astronaut is alive, false otherwise
     */
    public boolean isAlive() {
        return !oxygens.isEmpty();
    }

    /**
     * Makes the astronaut breathe by removing the smallest oxygen card and discarding it.
     *
     * @param gameEngine the game engine for the current game
     * @return the total amount of oxygen remaining after breathing
     */
    public int breathe(GameEngine gameEngine) {
        if (!isAlive()) {
            throw new IllegalStateException("Cannot breathe if the player is already dead.");
        }

        Oxygen smallestOxygen = oxygens.stream().min(Comparator.comparingInt(Oxygen::getValue)).orElse(null);

        if (smallestOxygen != null) {
            oxygens.remove(smallestOxygen);
            gameEngine.discardOxygen(smallestOxygen);
        }

        return oxygenRemaining();
    }

    /**
     * Gets the total amount of oxygen remaining for the astronaut.
     *
     * @return the total amount of oxygen remaining
     */
    public int oxygenRemaining() {
        return oxygens.stream().mapToInt(Oxygen::getValue).sum();
    }

        /**
     * Steals a random action card from the astronaut's hand.
     *
     * @return the stolen action card
     * @throws IllegalStateException if the astronaut has no action cards to steal
     */
    public Card steal() {
        if (actions.isEmpty()) {
            throw new IllegalStateException("No action cards to steal.");
        }

        int randomIndex = new Random().nextInt(actions.size());
        Card stolenCard = actions.get(randomIndex);
        actions.remove(randomIndex);

        return stolenCard;
    }

    /**
     * Determines if the astronaut has the Melted Eyeballs status effect, based on the last card in their track.
     *
     * @return true if the astronaut has the Melted Eyeballs status effect, false otherwise
     */
    public boolean hasMeltedEyeballs() {
        if (track.isEmpty()) {
            return false;
        }

        Card lastCard = track.get(track.size() - 1);
        return lastCard.getName().equals("SolarFlare");
    }

    /**
     * Adds the specified card to the astronaut's hand.
     *
     * @param card the card to add
     */
    public void addToHand(Card card) {
        if (card instanceof Oxygen) {
            oxygens.add((Oxygen) card);
        } else {
            actions.add(card);
        }
    }

    /**
     * Gets a string representation of the astronaut's hand.
     *
     * @return a string representation of the astronaut's hand
     */
    public String getHandStr() {
        StringBuilder sb = new StringBuilder();
        for (Card card : actions) {
            sb.append(card.getName()).append("\n");
        }
        for (Oxygen oxygen : oxygens) {
            sb.append(oxygen.getName()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Gets a string representation of the astronaut's actions, including their names and descriptions.
     *
     * @return a string representation of the astronaut's actions
     */
    public String getActionsStr() {
        StringBuilder sb = new StringBuilder();
        for (Card card : actions) {
            sb.append(card.getName()).append(": ").append(card.getDescription()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Astronaut other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Adds the specified card to the astronaut's track.
     *
     * @param card the card to add
     */
    public void addToTrack(Card card) {
        track.add(card);
    }

    /**
     * Gets the list of cards in the astronaut's track.
     *
     * @return the list of cards in the astronaut's track
     */
    public List<Card> getTrack() {
        return track;
    }
}


    
