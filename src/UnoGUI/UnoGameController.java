package UnoGUI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import klient.Klient;
import klient.UserScore;
import uno.UnoCard;
import uno.UnoGame;
import uno.UnoPlayer;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class UnoGameController
{
    UnoGame game;
    int currentPlayerIndex;
    boolean madeMove = true;


    @FXML
    HBox obrazki;


    @FXML
    HBox center;


    @FXML
    VBox graczeInfo;


    @FXML
    Button passTurn;


    @FXML
    Button iWinButton;


    protected void setupGame(ArrayList<String> players)
    {
        game = new UnoGame(players.size(), players);
        currentPlayerIndex =game.getCurrentPlayerIndex();
        UnoCardButton.loadAssets();
        refreshScreen();
    }


    protected void refreshScreen()
    {
        UnoCardButton tempCard;
        ArrayList<UnoCard> currentPlayerHand = game.getCurrentPlayerHand();
        Iterator<UnoCard> itHand = currentPlayerHand.iterator();
        Iterator<UnoPlayer> itName = game.getPlayersArray().iterator();

        iWinButton.setDisable(!(game.getCurrentPlayerHand().size()==0));
        passTurn.setDisable(madeMove);
        obrazki.getChildren().clear();
        center.getChildren().clear();
        graczeInfo.getChildren().clear();

        while (itHand.hasNext())
        {
            tempCard = new UnoCardButton(itHand.next());
            tempCard.setOnAction(this::playCard);
            obrazki.getChildren().add(tempCard);
        }
        while (itName.hasNext())
        {
            graczeInfo.getChildren().add(new PlayerLabel(itName.next()));
        }
        center.getChildren().add(new UnoCardButton(game.getCurrentCard()));

    }


    protected void playCard(ActionEvent curEvent)
    {
        UnoCard playedCard = ((UnoCardButton)curEvent.getSource()).getCard();
        game.updateValid();

        if(!game.cardValidation(playedCard))
        {
            return;//add pop-up message "invalid move"
        }
        else try{game.playCard(playedCard);}
        catch (Exception x){refreshScreen();}

        madeMove=false;
        refreshScreen();
    }


    @FXML
    protected void clickDraw()
    {
        game.drawCard();
        refreshScreen();
    }

    @FXML
    protected void handlePassTurn()
    {
        madeMove=true;
        game.changePlayer();
        refreshScreen();
    }


    @FXML
    protected void handleUno() throws IOException
    {
        Alert unoAlert = new Alert(Alert.AlertType.INFORMATION);
        unoAlert.setTitle("UNO!");
        unoAlert.setHeaderText("UNO!");
        unoAlert.setContentText(game.getCurrentPlayer().getPlayerName()+" won the game!");

        Klient aaa = new Klient();
        aaa.establishConnection("127.0.0.1",6666);
        aaa.sendMessege(new UserScore(game.getCurrentPlayer().getPlayerName(),game.getCounter()));

        unoAlert.showAndWait();
    }
}