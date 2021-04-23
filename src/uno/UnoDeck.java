package uno;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class UnoDeck {
    private UnoCard[] Deck;
    public int top = -1;

    public UnoDeck()
    {
        this.Deck = new UnoCard[108];
        fillDeck();
        List<UnoCard> buff = Arrays.asList(this.Deck);
        Collections.shuffle(buff);
        buff.toArray(this.Deck);
    }

    public void fillDeck() {
        UnoCard newCard;
        UnoCard.Value[] values = UnoCard.Value.values();
        UnoCard.Color[] colors = UnoCard.Color.values();
        int numOfCards = 0;

        for (int i = 1 ; i < values.length-2 ; i++)
        {
            for(int j = 0; j < colors.length-1; j++)
            {
                this.Deck[numOfCards++] = new UnoCard(colors[j], values[i]);
                this.Deck[numOfCards++] = new UnoCard(colors[j], values[i]);
            }
        }

        for(int i = 0;i < colors.length-1;i++)
        {
            this.Deck[numOfCards++] = new UnoCard(colors[i],UnoCard.Value.getValue(0));
        }

        for(int i = 13; i < values.length; i++)
        {
            for (int j = 0; j<4; j++)
            {
                this.Deck[numOfCards++] = new UnoCard(UnoCard.Color.getColor(4), values[i]);
            }
        }
    }

    public UnoCard drawCard() {
        top++;
        if (top > Deck.length) {
            List<UnoCard> buff = Arrays.asList(this.Deck);
            Collections.shuffle(buff);
            buff.toArray(this.Deck);
            this.top = 0;
        }
        return Deck[top];
    }
}
