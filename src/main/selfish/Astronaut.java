package selfish;
import selfish.deck.*;
import java.util.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.stream.Collectors;
/**
 * The Astronaut class represents an astronaut in the game.
 */
public class Astronaut implements Serializable , Comparable<Astronaut>{
    private GameEngine game;
    private List<Card> actions;
    private List<Oxygen> oxygens;
    private String name;
    private Collection<Card> track;
    

    private static final long serialVersionUID = 1234567895L;

    /**
     * Constructs an Astronaut with the specified name and game.
     * @param name the name of the astronaut
     * @param game the game engine
     */
    public Astronaut(String name, GameEngine game) {
        this.name = name;
        this.game = game;
        this.actions = new ArrayList<>();
        this.oxygens = new ArrayList<>();
        this.track = new ArrayList<>();
    }

    /**
     * Adds a card to the astronaut's hand.
     *
     * @param card The card to add.
     */
    public void addToHand(Card card) {
        if (card instanceof Oxygen) {
            oxygens.add((Oxygen) card);
        } else {
            actions.add(card);
        }
    }

    /**
     * Adds the specified card to the astronaut's track.
     *
     * @param card the card to add
     */
    public void addToTrack(Card card) {
        track.add(card);
    }
    


    public int breathe() {
        for (int i = 0; i < oxygens.size(); i++) {
            Oxygen oxygen = oxygens.get(i);
            if (oxygen.getValue() > 1) {
                oxygen.setValue(oxygen.getValue() - 1);
                return getOxygenCount();
            } else if (oxygen.getValue() == 1) {
                oxygens.remove(oxygen);
                return getOxygenCount();
            }
        }
        throw new RuntimeException("No oxygen left!");
    }
    
    private int getOxygenCount() {
        int total = 0;
        for (Oxygen oxygen : oxygens) {
            total += oxygen.getValue();
        }
        return total;
    }
    
    
    
    /**
 * Returns the distance from the ship. This is a placeholder implementation and
 * needs to be replaced with the actual calculation.
 *
 * @return The distance from the ship.
 */
public int distanceFromShip() {
    return 6 - track.size();
}


    
    /**
     * Returns the astronaut's action cards.
     *
     * @return A list of action cards.
     */
    public List<Card> getActions() {
        actions.sort(Comparator.comparing(Card::getDescription));
        return new ArrayList<>(actions);
    }
    
    

    

    /**
     * Returns all cards in the astronaut's hand.
     *
     * @return A list of all cards.
     */
    public List<Card> getHand() {
        List<Card> hand = new ArrayList<>();
        hand.addAll(actions);
        hand.addAll(oxygens);
        hand.sort(Comparator.comparing(Card::getDescription));
        return hand;
    }
    

    public String getActionsStr(boolean enumerate, boolean excludeShields) {
        StringBuilder actionsStr = new StringBuilder();
        Map<String, Integer> actionCounts = new HashMap<>();
        char index = 'A';
    
        for (Card card : actions) {
            if (!(card instanceof Oxygen)) {
                if (excludeShields && card.getDescription().equals("Shield")) {
                    continue;
                }
                actionCounts.put(card.getDescription(), actionCounts.getOrDefault(card.getDescription(), 0) + 1);
            }
        }
    
        for (Map.Entry<String, Integer> entry : actionCounts.entrySet()) {
            if (actionsStr.length() > 0) {
                actionsStr.append(", ");
            }
            if (enumerate) {
                actionsStr.append('[').append(index++).append("] ");
            }
            actionsStr.append(entry.getValue()).append("x ").append(entry.getKey());
        }
        
        return actionsStr.toString();
    }
    
    


    /**
     * Gets the list of cards in the astronaut's track.
     *
     * @return the list of cards in the astronaut's track
     */
    public Collection<Card> getTrack() {
        return track;
    }
    


    public void hack(Card card) {
        if (actions.contains(card)) {
            actions.remove(card);
        } else if (oxygens.contains(card)) {
            oxygens.remove(card);
        }
        // If the last oxygen card is removed, empty the hand
        if (oxygens.isEmpty()) {
            actions.clear();
        }
    }

    public void hack(String cardName) {
        if (cardName.startsWith("Oxygen")) {
            int oxygenValue = Integer.parseInt(cardName.replaceAll("\\D", ""));
            oxygens.removeIf(oxygen -> oxygen.getValue() == oxygenValue);
        } else {
            actions.removeIf(card -> card.getDescription().equals(card.getDescription()));
        }
        // If the last oxygen card is removed, empty the hand
        if (oxygens.isEmpty()) {
            actions.clear();
        }
    }
        /**
     * Checks if the astronaut has a card with a specific description.
     *
     * @param cardDescription The description of the card to check for.
     * @return 1 if the astronaut has the card, 0 otherwise.
     */
    public int hasCard(String cardDescription) {
        if (cardDescription.startsWith("Oxygen")) {
            int oxygenValue = Integer.parseInt(cardDescription.replaceAll("\\D", ""));
            return (int) oxygens.stream().filter(oxygen -> oxygen.getValue() == oxygenValue).count();
        } else {
            return (int) actions.stream().filter(card -> card.getDescription().equals(cardDescription)).count();
        }
    }
    public String getHandStr() {
        StringBuilder handStr = new StringBuilder();
        Map<String, Integer> cardCounts = new HashMap<>();
    
        for (Card card : actions) {
            cardCounts.put(card.toString(), cardCounts.getOrDefault(card.toString(), 0) + 1);
        }
    
        for (Oxygen oxygen : oxygens) {
            String key = "Oxygen(" + oxygen.getValue() + ")";
            cardCounts.put(key, cardCounts.getOrDefault(key, 0) + 1);
        }
    
        for (Map.Entry<String, Integer> entry : cardCounts.entrySet()) {
            if (handStr.length() > 0) {
                handStr.append(", ");
            }
            handStr.append(entry.getValue() > 1 ? entry.getValue() + "x " : "").append(entry.getKey());
        }
    
        return handStr.toString();
    }
    
    


    public boolean hasMeltedEyeballs() {
        /*if (track.isEmpty()) {
            return false;
        return lastCard.getName().equals("SolarFlare");*/
        return false;
    }


    public boolean hasWon() {
        // To be implemented
        return false;
    }

    /**
     * Checks if the astronaut is alive.
     *
     * @return true if the astronaut has any oxygen cards, false otherwise.
     */
    public boolean isAlive() {
        return !oxygens.isEmpty();
    }

    public Card laserBlast() {
        if (track.isEmpty()) {
            return null;
        } else {
            return ((ArrayList<Card>) track).remove(track.size() - 1);
        }
    }

    /**
     * Returns the total value of the astronaut's oxygen cards.
     *
     * @return The total oxygen remaining.
     */
    public int oxygenRemaining() {
        return oxygens.stream().mapToInt(Oxygen::getValue).sum();
    }


    public Card peekAtTrack() {
        if (track.isEmpty()) {
            return null;
        } else {
            return ((ArrayList<Card>) track).get(track.size() - 1);
        }
    }

    public Oxygen siphon() {
        // To be implemented
        return null;
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
        } else {
            return actions.remove(new Random().nextInt(actions.size()));
        }
    }



    public void swapTrack(Astronaut swapee) {
        Collection<Card> tempTrack = this.track;
        this.track = swapee.track;
        swapee.track = tempTrack;
    }

    /**
     * Returns a string representation of the astronaut.
     * @return a string representation of the astronaut
     */
    @Override
    public String toString() {
        return name + " with " + oxygenRemaining() + " oxygen";
    }
    @Override
    public int compareTo(Astronaut other) {
        return this.name.compareTo(other.name);
    }
}