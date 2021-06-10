package uno;

import java.util.ArrayList;
import java.util.Objects;

public class UnoPlayer
{


    ArrayList<UnoCard> playerHand = new ArrayList<>();
    String playerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnoPlayer unoPlayer = (UnoPlayer) o;
        return Objects.equals(playerName, unoPlayer.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

    public UnoPlayer(String name)
    {
        playerName = name;
    }

    public String getPlayerName()
    {
        return this.playerName;
    }

    public void giveCard(UnoCard card) {
        playerHand.add(card);
    }

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

    public ArrayList<UnoCard> getHand()
    {
        return playerHand;
    }
}
