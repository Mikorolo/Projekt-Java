package UnoGUI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uno.UnoCard;
import uno.Color;
import uno.Value;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class UnoCardButton extends Button
{
    UnoCard thisButt;
    static Image[][] cardImages = new Image[5][15];
    ImageView cardGraphic;


    public UnoCard getCard()
    {
        return thisButt;
    }


    public UnoCardButton(UnoCard card)
    {
        super();
        cardGraphic = new ImageView(cardImages[card.getColorIndex()][card.getValueIndex()]);
        thisButt=card;
        this.setGraphic(cardGraphic);
    }


    public static void loadAssets()
    {
        String[] colors ={"Blue", "Green", "Red", "Yellow","Special"};
        String[] values ={"0","1","2","3","4","5","6","7","8","9","Reverse","DrawTwo","Skip"};

        for(int i=0;i<13;i++)
        {
            for(int j=0;j<4;j++)
            {
                try
                {
                    cardImages[j][i] = new Image(new FileInputStream("assets\\" + colors[j] + "_" + values[i] + ".png"));
                }catch(FileNotFoundException x)
                {
                    x.printStackTrace();
                }
                try
                {
                cardImages[Color.Special.ordinal()][Value.Wild.ordinal()]=new Image(new FileInputStream("assets\\Wild.png"));
                cardImages[Color.Special.ordinal()][Value.colorChange.ordinal()]=new Image(new FileInputStream("assets\\Color_Change.png"));
                }catch(FileNotFoundException x)
                {
                    x.printStackTrace();
                }
            }
        }
    }
}
