package uno;
import java.util.ArrayList;

public class UnoPlayer {
    ArrayList<UnoCard> playerHand = new ArrayList<UnoCard>();

    public void giveCard(UnoCard card) {
        playerHand.add(card);
    };

    public boolean removeCard(){return true;}

    public int searchCard(UnoCard searched)
    {
        for(int i=0;i<playerHand.size();i++)
        {
            if(searched == playerHand.get(i))
            {
                return i;
            }
        }
        return -1;
    }
}
