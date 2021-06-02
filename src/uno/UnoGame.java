package uno;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UnoGame
{
    UnoDeck Deck;
    UnoCard CurrentCard;
    private ArrayList<UnoPlayer> playersArray = new ArrayList<>();
    private int currentPlayer;
    boolean gameDirection; //clockwise by default

    private Color validColor;
    private Value validValue;

    public UnoGame(int numOfPlayers, ArrayList<String> names)
    {
        this.Deck = new UnoDeck();
        CurrentCard = Deck.drawCard();
        this.currentPlayer = 0;
        this.gameDirection = true; //clockwise by default
        for (int i = 0; i < numOfPlayers; i++)
            playersArray.add(new UnoPlayer(names.get(i)));
        for (int i = 0 ; i < numOfPlayers ; i++) { //iterating through players array and giving them cards
            for(int j = 0 ; j < 7 ; j++) { //each player gets 7 cards at the beginning
                playersArray.get(i).giveCard(Deck.drawCard()); //give one card to current player
            }
        }
    }

    public void gameLoop(UnoGame game) {

        UnoCard card = Deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();

        if(card.getValue() == Value.Wild) {
            gameLoop(game);
        }

        if(card.getValue() == Value.drawTwo || card.getValue() == Value.colorChange) {
            gameLoop(game);
        }

        if(CurrentCard.getValue() == Value.Skip) {
            System.out.println("Player was skipped");
            if(gameDirection == false) {
                currentPlayer = (currentPlayer + 1) % playersArray.size();
            }
            else if(gameDirection) {
                currentPlayer = (currentPlayer - 1) % playersArray.size();
            }
        }

        if (card.getValue() == Value.Reverse) {
            if(CurrentCard.getValue() == Value.Reverse) {
                this.gameDirection = !this.gameDirection;
                System.out.println("Game direction was changed");
                currentPlayer = playersArray.size() - 1;
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

    public UnoPlayer getCurrentPlayer() { return this.playersArray.get(currentPlayer); }

    public boolean cardValidation(UnoCard card) { return card.getColor() == validColor || card.getValue() == validValue; }

    public void changePlayer()
    {
        if(gameDirection) currentPlayer++;
        else currentPlayer--;

        if(currentPlayer>playersArray.size()) currentPlayer=0;
        if(currentPlayer<0) currentPlayer=playersArray.size();
    }

    public void performSkip()
    {

        this.changePlayer();
        playersArray.get(currentPlayer).searchCard(new UnoCard(Color.Special,Value.Skip));
        this.changePlayer();
    }

    public void performDrawTwo()
    {
        this.changePlayer();
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
    }

    public void performReverse()
    {
        gameDirection=!gameDirection;
    }

    public void performColorChange()
    {
//        CurrentCard.changeColor(playersArray[currentPlayer].pickColor());
    }

    public void performWild()
    {
        this.performColorChange();

        currentPlayer++;
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
    }



    public boolean performSpecialAction(UnoCard playedCard)
    {
        if(playedCard.getValue() == Value.drawTwo || playedCard.getValue() == Value.Reverse || playedCard.getValue() == Value.Skip)
        {
            if(playedCard.getColor() != this.CurrentCard.getColor())
                return false;
        }

        if(playedCard.getValue()==Value.Skip)
        {
            CurrentCard=playedCard;
            this.performSkip();
            return true;
        }

        if(playedCard.getValue()==Value.drawTwo)
        {
            CurrentCard=playedCard;
            this.performDrawTwo();
            return true;
        }

        if(playedCard.getValue()==Value.Reverse)
        {
            CurrentCard=playedCard;
            this.performReverse();
            return true;
        }

        if(playedCard.getValue()==Value.colorChange)
        {
            CurrentCard=playedCard;
            this.performColorChange();
            return true;
        }

        if(playedCard.getValue()==Value.Wild)
        {
            CurrentCard=playedCard;
            this.performWild();
            return true;
        }

        return true;
    }

    public boolean performAction(UnoCard playedCard)
    {
        if(playedCard.getColor() == Color.Special) {
            if(!performSpecialAction(playedCard))
                {return false;}
            else return true;

        }

        if(playedCard.getColor()!=this.CurrentCard.getColor() && playedCard.getValue()!=this.CurrentCard.getValue())
            {return false;}

//        playersArray.get(currentPlayer).removeCard();//WIP

        CurrentCard = playedCard;

        return true;
    }

    public ArrayList<UnoCard> getCurrentPlayerHand()
    {
        return playersArray.get(currentPlayer).getHand();
    }

    public void gameState() {

    }
}
