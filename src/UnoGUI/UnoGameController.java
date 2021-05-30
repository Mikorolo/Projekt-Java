package UnoGUI;


import uno.UnoGame;
import java.util.ArrayList;


public class UnoGameController
{
    ArrayList<String> nameList;
    UnoGame game;

    protected void setupGame(ArrayList<String> players)
    {
        game = new UnoGame(players.size());
        nameList = players;
    }

}
