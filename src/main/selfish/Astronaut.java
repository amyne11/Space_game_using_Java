package selfish;

import selfish.deck.Oxygen;
import selfish.deck.Card;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.io.Serializable;
import java.util.Collection;


/**
 * Represents an Astronaut in the game, including their hand, track, and game state.
 */
public class Astronaut implements Serializable {
    private GameEngine game;
    private List<Card> actions = new ArrayList<>();
    private List<Oxygen> oxygens = new ArrayList<>();
    private String name;
    private Collection<Card> track = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public Astronaut(String name, GameEngine game) {
        this.name = name;
        this.game = game;
    }

    public void addToHand(Card card) {
        if (card instanceof Oxygen) {
            oxygens.add((Oxygen) card);
        } else {
            actions.add(card);
        }
    }

    public void addToTrack(Card card) {
        track.add(card);
    }

    public int breathe() {
        if (oxygens.size() > 0) {
            game.addToGameDiscard(oxygens.remove(0));
        }
        return oxygens.size();
    }

    public int distanceFromShip() {
        return track.size();
    }

    public List<Card> getActions() {
        return new ArrayList<>(actions);
    }

    public String getActionsStr(boolean enumerated, boolean excludeShields) {
        // TODO: Need clarification. What does enumerated and excludeShields mean here?
    }

    public List<Card> getHand() {
        List<Card> hand = new ArrayList<>();
        hand.addAll(actions);
        hand.addAll(oxygens);
        return hand;
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

    public Collection<Card> getTrack() {
        return new ArrayList<>(track);
    }

    public void hack(Card card) {
        this.actions.remove(card);
        this.oxygens.remove(card);
    }

    public Card hack(String cardName) {
        for (Card card : actions) {
            if (card.toString().equals(cardName)) {
                actions.remove(card);
                return card;
            }
        }
        for (Oxygen oxygen : oxygens) {
            if (oxygen.toString().equals(cardName)) {
                oxygens.remove(oxygen);
                return oxygen;
            }
        }
        return null;
    }
    

    public int hasCard(String cardName) {
        int count = 0;
        for (Card card : actions) {
            if (card.toString().equals(cardName)) {
                count++;
            }
        }
        for (Oxygen oxygen : oxygens) {
            if (oxygen.toString().equals(cardName)) {
                count++;
            }
        }
        return count;
    }
    

    public boolean hasMeltedEyeballs() {
        if (!(track instanceof List) || track.isEmpty()) {
            return false;
        }
    
        // If track is a List, this will work.
        // return ((List<Card>) track).get(track.size() - 1).toString().equals("SolarFlare");
    
        // Alternative approach with Iterator.
        Iterator<Card> iter = track.iterator();
        Card lastElement = iter.next();
        while (iter.hasNext()) {
            lastElement = iter.next();
        }
        return lastElement.toString().equals("SolarFlare");
    }
    

    public boolean hasWon() {
        return this.distanceFromShip() == 0 && this.isAlive();
    }

    public boolean isAlive() {
        return !oxygens.isEmpty();
    }

    public Card laserBlast() {
        if (track.isEmpty()) {
            return null;
        }
        Iterator<Card> iter = track.iterator();
        Card lastElement = iter.next();
        while (iter.hasNext()) {
            lastElement = iter.next();
        }
        iter.remove();
        return lastElement;
    }
    

    public int oxygenRemaining() {
        return oxygens.stream().mapToInt(Oxygen::getValue).sum();
    }

    public Card peekAtTrack() {
        if (track.isEmpty()) {
            return null;
        }
        Iterator<Card> iter = track.iterator();
        Card lastElement = iter.next();
        while (iter.hasNext()) {
            lastElement = iter.next();
        }
        return lastElement;
    }
    

    public Oxygen siphon() {
        for (Oxygen oxygen : this.oxygens) {
            if (oxygen.getValue() == 1) {
                this.oxygens.remove(oxygen);
                return oxygen;
            }
        }
        return null;
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

    public void swapTrack(Astronaut swapee) {
        Collection<Card> temp = this.track;
        this.track = swapee.track;
        swapee.track = temp;
    }

    @Override
    public String toString() {
        return name;
    }
}
