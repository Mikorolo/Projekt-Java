package UnoGUI;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class MainMenuController
{

    @FXML
    Label test;

    @FXML
    Button exitButtonMain;

    @FXML
    protected void handleHotSeatOnAction(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UnoGameSetup.fxml")));
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    @FXML
    protected void handleExit()
    {
        Platform.exit();
    }


    @FXML
    protected void handleHall(ActionEvent event)throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnoHallOfFame.fxml"));
        Parent root = loader.load();
        HallController ctrl = loader.getController();
        ctrl.setup();
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}
