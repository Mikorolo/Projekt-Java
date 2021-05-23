package uno;
import java.util.ArrayList;

public class UnoPlayer {
    ArrayList<UnoCard> playerHand = new ArrayList<UnoCard>();

    public void giveCard(UnoCard card) {
        playerHand.add(card);
    };

    public boolean removeCard(UnoCard toRM)
    {
        if(playerHand.remove(toRM))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeCard(int toRM)
    {
        try
        {
            playerHand.remove(toRM);
            return true;
        }
        catch (IndexOutOfBoundsException x)
        {
            return false;
        }
        }

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

    public boolean cardExists(UnoCard card)
    {

        if(playerHand.contains(card))
        {
            return true;
        }
        else return false;

    }
}
