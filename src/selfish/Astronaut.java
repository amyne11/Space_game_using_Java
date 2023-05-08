package selfish;
import selfish.deck.Oxygen;
import selfish.deck.Card;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.random.*;
import selfish.deck.SpaceDeck;
import selfish.deck.GameDeck;
import java.util.ArrayList;

public class Astronaut {
    private String name;
    private GameEngine game;
    private List<Card> actions;
    private List<Oxygen> oxygens;
    private Collection<Card> track;
    private List<Astronaut> corpses = new ArrayList<>();


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

    // Astronaut.java
// ...

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
        gameEngine.getGameDiscard().discard(smallestOxygen);
    }

    return oxygenRemaining();
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
    return lastCard instanceof SolarFlare;
}

// ...
public List<Astronaut> getAllPlayers() {
    List<Astronaut> allPlayers = new ArrayList<>(activePlayers);
    allPlayers.addAll(corpses);
    return allPlayers;
}

public int getFullPlayerCount() {
    return activePlayers.size() + corpses.size();
}

public void killPlayer(Astronaut player) {
    if (!activePlayers.remove(player)) {
        throw new IllegalStateException("Player not found in active players.");
    }
    corpses.add(player);
}

    
    
}
