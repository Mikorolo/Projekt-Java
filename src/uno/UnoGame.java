package uno;
import javax.swing.plaf.ColorUIResource;

public class UnoGame
{
    UnoDeck Deck;
    UnoCard CurrentCard;
    private UnoPlayer[] playersArray;
    private int currentPlayer;
    boolean gameDirection; //clockwise by default

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

    public void start(UnoGame game) {
        if(CurrentCard.getColor() == UnoCard.Color.Special) {
            CurrentCard = Deck.drawCard();
        }
        if(CurrentCard.getValue() == UnoCard.Value.Skip) {
            CurrentCard = Deck.drawCard();
        }
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
