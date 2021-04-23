package uno;
import java.util.ArrayList;

public class UnoPlayer {
    ArrayList<UnoCard> playerHand = new ArrayList<UnoCard>();

    public void giveCard(UnoCard card) {
        playerHand.add(card);
    };
}
