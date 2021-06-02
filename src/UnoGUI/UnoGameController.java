package UnoGUI;


import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import uno.UnoCard;
import uno.UnoGame;
import java.util.ArrayList;
import java.util.Iterator;


public class UnoGameController
{
    UnoGame game;

    @FXML
    HBox obrazki;

    @FXML
    HBox center;

    protected void setupGame(ArrayList<String> players) throws Exception
    {
        game = new UnoGame(players.size(), players);
        UnoCardButton.loadAssets();
        generateHand(game.getCurrentPlayerHand());

    }

    protected void generateHand(ArrayList<UnoCard> currentPlayerHand)
    {
        Iterator<UnoCard> it = currentPlayerHand.iterator();
        while (it.hasNext())
        {
            obrazki.getChildren().add(new UnoCardButton(it.next()));
        }
    }

}
