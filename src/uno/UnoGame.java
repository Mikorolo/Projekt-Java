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

    public void changePlayer()
    {
        if(gameDirection) currentPlayer++;
        else currentPlayer--;

        if(currentPlayer>playersArray.length) currentPlayer=0;
        if(currentPlayer<0) currentPlayer=playersArray.length;
    }

    public void performSkip()
    {

        this.changePlayer();
        playersArray[currentPlayer].searchCard(new UnoCard(UnoCard.Color.Special,UnoCard.Value.Skip));
        this.changePlayer();
    }

    public void performDrawTwo()
    {
        this.changePlayer();
        playersArray[currentPlayer].giveCard(Deck.drawCard());
        playersArray[currentPlayer].giveCard(Deck.drawCard());
    }

    public void performReverse()
    {
        gameDirection=!gameDirection;
    }

    public void performColorChange()
    {
        CurrentCard.changeColor(playersArray[currentPlayer].pickColor());
    }

    public void performWild()
    {
        this.performColorChange();

        currentPlayer++;
        playersArray[currentPlayer].giveCard(Deck.drawCard());
        playersArray[currentPlayer].giveCard(Deck.drawCard());
        playersArray[currentPlayer].giveCard(Deck.drawCard());
        playersArray[currentPlayer].giveCard(Deck.drawCard());
    }

    public boolean performSpecialAction(UnoCard playedCard)
    {
        if(playedCard.getValue() == UnoCard.Value.drawTwo || playedCard.getValue() == UnoCard.Value.Reverse || playedCard.getValue() == UnoCard.Value.Skip)
        {
            if(playedCard.getColor() != this.CurrentCard.getColor())
                return false;
        }

        if(playedCard.getValue()==UnoCard.Value.Skip)
        {
            CurrentCard=playedCard;
            this.performSkip();
            return true;
        }

        if(playedCard.getValue()==UnoCard.Value.drawTwo)
        {
            CurrentCard=playedCard;
            this.performDrawTwo();
            return true;
        }

        if(playedCard.getValue()==UnoCard.Value.Reverse)
        {
            CurrentCard=playedCard;
            this.performReverse();
            return true;
        }

        if(playedCard.getValue()==UnoCard.Value.colorChange)
        {
            CurrentCard=playedCard;
            this.performColorChange();
            return true;
        }

        if(playedCard.getValue()==UnoCard.Value.Wild)
        {
            CurrentCard=playedCard;
            this.performWild();
            return true;
        }

        return true;
    }

    public boolean performAction(UnoCard playedCard)
    {
        if(playedCard.getColor() == UnoCard.Color.Special) {
            if(!performSpecialAction(playedCard))
                {return false;}
            else return true;

        }

        if(playedCard.getColor()!=this.CurrentCard.getColor() && playedCard.getValue()!=this.CurrentCard.getValue())
            {return false;}

        playersArray[currentPlayer].removeCard();//WIP

        CurrentCard = playedCard;

        return true;
    }

    public void gameState() {

    }
}
