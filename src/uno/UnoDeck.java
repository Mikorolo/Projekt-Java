package uno;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UnoDeck {
    private HashMap<UnoCard.Color, UnoCard.Value> deck;
    private int numOfCards;

    public UnoDeck() {
        deck = new HashMap<UnoCard.Color, UnoCard.Value>();
    }

    public void fillDeck() {
        UnoCard newCard;
        UnoCard.Value[] values = UnoCard.Value.values();
        numOfCards = 0;

        for (int i = 1 ; i < values.length - 5 ; i++) {
            UnoCard.Value value = values[i];
            deck.put(UnoCard.Color.Blue, value);
        }
    }
}
