package selfish;
import selfish.deck.Oxygen;
import selfish.deck.Card;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.io.Serializable;



public class Astronaut implements Comparable<Astronaut>{
    private String name;
    private List<Card> actions = new ArrayList<>();
    private List<Oxygen> oxygens = new ArrayList<>();
    private List<Card> track = new ArrayList<>();
    private GameEngine game;
    private static final long serialVersionUID = 1L;

    public Astronaut(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Card> getHand() {
        List<Card> hand = new ArrayList<>();
        hand.addAll(actions);
        hand.addAll(oxygens);
        return hand;
    }

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

    public int distanceFromShip() {
        return track.size();
    }

    public boolean isAlive() {
        return !oxygens.isEmpty();
    }

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
    

    public int oxygenRemaining() {
        return oxygens.stream().mapToInt(Oxygen::getValue).sum();
    }

    public Card steal() {
        if (actions.isEmpty()) {
            throw new IllegalStateException("No action cards to steal.");
        }

        int randomIndex = new Random().nextInt(actions.size());
        Card stolenCard = actions.get(randomIndex);
        actions.remove(randomIndex);

        return stolenCard;
    }

    public boolean hasMeltedEyeballs() {
        if (track.isEmpty()) {
            return false;
        }

        Card lastCard = track.get(track.size() - 1);
        return lastCard.getName().equals("SolarFlare");
    }

    public void addToHand(Card card) {
        if (card instanceof Oxygen) {
            oxygens.add((Oxygen) card);
        } else {
            actions.add(card);
        }
    }

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

    public void addToTrack(Card card) {
        track.add(card);
    }

    public List<Card> getTrack() {
        return track;
    }
}
