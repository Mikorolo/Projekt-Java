package UnoGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uno.UnoPlayer;
import java.util.ArrayList;


public class UnoHotSeatSetupController
{
    ArrayList<String> playerArr = new ArrayList<>();

    @FXML
    TextField userNameField;

    @FXML
    ComboBox playerListBox;

    @FXML
    protected void handleCancel(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("UnoMainMenu.fxml"));
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void handleAddPlayer()
    {
        String newPlayer =userNameField.getText();
        playerArr.add(newPlayer);
        playerListBox.getItems().add(newPlayer);
    }

    @FXML
    protected void handleRemovePlayer()
    {
        String name=(String)playerListBox.getValue();
        playerListBox.getItems().remove(name);
        playerArr.remove(name);
    }

    @FXML
    protected void handleStartGame(ActionEvent event) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnoGameInterface.fxml"));
        Parent root = loader.load();
        UnoGameController ctrl = loader.getController();
        ctrl.setupGame(playerArr);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

}
