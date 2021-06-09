package UnoGUI;

import javafx.scene.control.TextField;
import uno.UnoPlayer;

public class PlayerLabel extends TextField
{
    PlayerLabel(UnoPlayer player)
    {
        super();
        this.setText(player.getPlayerName()+" - "+player.getHand().size());
        this.setEditable(false);
    }
}
