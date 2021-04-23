package uno;

import java.util.Stack;
import java.util.List;

public class UnoHost
{


    List<UnoClient> ClientList;
    int currentPlayerId;
    UnoCard[] HostHand;

    public Boolean ValidateMove(UnoMove CurrMove)
    {
        return true;
    }

    public void UnlockPlayer()
    {

    }

    public void LockPlayer()
    {

    }
}
