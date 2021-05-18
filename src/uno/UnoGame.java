package uno;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UnoGame
{
    UnoDeck Deck;
    UnoCard CurrentCard;
    private UnoPlayer[]playersArray;
    private int currentPlayer;
    boolean gameDirection; //clockwise by default
    private ArrayList<ArrayList<UnoCard>> playerHand;

    private UnoCard.Color validColor;
    private UnoCard.Value validValue;

    public UnoGame(int numOfPlayers)
    {
        this.Deck = new UnoDeck();
        CurrentCard = Deck.drawCard();
        this.currentPlayer = 0;
        this.gameDirection = true; //clockwise by default
        playersArray = new UnoPlayer[numOfPlayers];
        for (int i = 0 ; i < numOfPlayers ; i++) { //iterating through players array and giving them cards
            for(int j = 0 ; i < 7 ; j++) { //each player gets 7 cards at the beginning
                playersArray[currentPlayer].giveCard(Deck.drawCard()); //give one card to current player
            }
        }
    }

    public void gameLoop(UnoGame game) {

        UnoCard card = Deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

//        if(CurrentCard.getColor() == UnoCard.Color.Special) {
//            CurrentCard = Deck.drawCard();
//        }
//        if(CurrentCard.getValue() == UnoCard.Value.Skip) {
//            CurrentCard = Deck.drawCard();
//        }

        if(card.getValue() == UnoCard.Value.Wild) {
            gameLoop(game);
        }

        if(card.getValue() == UnoCard.Value.drawTwo || card.getValue() == UnoCard.Value.colorChange) {
            gameLoop(game);
        }

        if(CurrentCard.getValue() == UnoCard.Value.Skip) {
            System.out.println("Player was skipped");
            if(gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playersArray.length;
            }
            else if(gameDirection) {
                currentPlayer = (currentPlayer - 1) % playersArray.length;
            }
        }

        if (card.getValue() == UnoCard.Value.Reverse) {
            if(CurrentCard.getValue() == UnoCard.Value.Reverse) {
                this.gameDirection = !this.gameDirection;
                System.out.println("Game direction was changed");
                currentPlayer = playersArray.length - 1;
            }
        }
        Deck.drawCard();
    }

    //Getting top card
    public UnoCard getTop() {
        return new UnoCard(validColor, validValue);
    }

    //Getting top card image
    public ImageIcon getImage() {
        return new ImageIcon(validColor + "-" + validValue + ".jpg");
    }

    public boolean isGameOver() {
        for (UnoPlayer player : this.playersArray) {
//            if(emptyHand(player)) {
//                return true;
//            }
        }
        return false;
    }

    public UnoPlayer getCurrentPlayer() {
        return this.playersArray[this.currentPlayer];
    }

    public ArrayList<UnoCard> getPlayerHand(String playerID) {
        int index = Arrays.asList(playersArray).indexOf(playerID);
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String playerID) {
        return getPlayerHand(playerID).size();
    }

    public boolean emptyHand(String playerID) {
        return getPlayerHand(playerID).isEmpty();
    }

    public boolean cardValidation(UnoCard card) {
        return card.getColor() == validColor || card.getValue() == validValue;
    }

    public void gameState() {
        if(CurrentCard.getValue() == UnoCard.Value.Skip) {
            System.out.println("Player was skipped");
        }
        if(CurrentCard.getValue() == UnoCard.Value.drawTwo) {
            System.out.println("Player must draw 2 cards");
        }
        if(CurrentCard.getValue() == UnoCard.Value.colorChange) {
            System.out.println("Current color was changed");
        }
        if(CurrentCard.getValue() == UnoCard.Value.Reverse) {
            this.gameDirection = !this.gameDirection;
            System.out.println("Game direction was changed");
        }
        if(CurrentCard.getValue() == UnoCard.Value.Wild) {
            System.out.println("Color was changed and next player draws 4 cards");
        }
    }
}
