package UnoGUI;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uno.UnoGame;
import java.io.FileInputStream;
import java.util.ArrayList;


public class UnoGameController
{
    UnoGame game;
    ArrayList<Image> assets = new ArrayList<>();

    @FXML
    HBox obrazki;

    protected void setupGame(ArrayList<String> players) throws Exception
    {
        game = new UnoGame(players.size(), players);
        this.loadAssets();
        for(int i=0; i<10; i++)
            obrazki.getChildren().add(new ImageView(assets.get(i)));
    }

    private void loadAssets() throws Exception
    {
        FileInputStream input;

        for (int i=0; i<10; i++)
        {
            input = new FileInputStream(("E:\\Projekt-Java\\assets\\Blue_" + i + ".png"));
            assets.add(new Image(input));
        }
    }


}
