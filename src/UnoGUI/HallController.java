package UnoGUI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import klient.Klient;
import klient.UserScore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class HallController
{
    @FXML
    VBox graczeInfo;


    @FXML
    protected void handleCancel(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("UnoMainMenu.fxml"));
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


    protected void setup() throws IOException
    {
        Klient lista = new Klient();
        lista.establishConnection("127.0.0.1",6666);

        try{lista.requestRanking();}
            catch(ClassNotFoundException x){x.printStackTrace();}
        ArrayList<UserScore> fameList = lista.getScores();

        Iterator<UserScore> itName = fameList.iterator();

        while (itName.hasNext())
        {
            graczeInfo.getChildren().add(new ScoreLabel(itName.next().getScore()));
        }
    }
}
