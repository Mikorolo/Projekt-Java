package UnoGUI;

import javafx.scene.control.TextField;
import uno.UnoPlayer;

public class ScoreLabel extends TextField
{
    ScoreLabel(String player)
    {
        super();
        this.setText(player);
        this.setEditable(false);
    }
}