package selfish.deck;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import selfish.GameException;
import java.util.Collections;

public abstract class Deck implements Serializable {
    private static final long serialVersionUID = 4L;
    // attributes, constructors, methods
    protected List<Card> cards;

    // Empty constructor
    public Deck() {
        cards = new ArrayList<>();
        discards = new ArrayList<>();
    }

    // Empty loadCards method
    protected List<Card> loadCards (String path) throws GameException{
        // Add your implementation later
        List<Card> loadedCards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Process each line, create Card objects
                String[] parts = line.split(",");
                String cardName = parts[0].trim();
                String cardDescription = parts[1].trim();
                int cardCount = Integer.parseInt(parts[2].trim());

                for (int i = 0; i < cardCount; i++) {
                    loadedCards.add(new Card(cardName, cardDescription));
                }
            }
        }catch (IOException e) {
            throw new GameException("Error reading cards from file: " + path, e);
        } catch (NumberFormatException e) {
            throw new GameException("Invalid card count format in file: " + path, e);
        }
        return loadedCards;
    }
    public Card drawCard() {
        if (cards.isEmpty()) {
            mergeDecks();
        }
        return cards.remove(0);
    }
    public void discardCard(Card card) {
        discards.add(card);
    }
    public void mergeDecks() {
        cards.addAll(discards);
        discards.clear();
        Collections.shuffle(cards);
    }
    

}
