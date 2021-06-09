package uno;


import javafx.scene.control.ChoiceDialog;
import java.util.ArrayList;
import java.util.List;


public class UnoGame
{
    int cardCounter=0;
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
        while(CurrentCard.getValueIndex()>Value.Nine.ordinal())
            {
                CurrentCard = Deck.drawCard();
            }
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


    public void drawCard()
    {
        UnoCard drawnCard;
        do
        {
            drawnCard= Deck.drawCard();
            playersArray.get(currentPlayer).giveCard(drawnCard);
        }while(cardValidation(drawnCard)==false);
    }


    public void updateValid()
    {
        validColor = CurrentCard.getColor();
        validValue = CurrentCard.getValue();
    }


    public void playCard(UnoCard playedCard) throws Exception
    {
        if(playedCard.getValue().ordinal()<Value.Reverse.ordinal())
        {
            CurrentCard = playedCard;
            playersArray.get(currentPlayer).removeCard(playedCard);
        }
        else if(playedCard.getValue() == Value.Reverse)
        {
            CurrentCard = playedCard;
            playersArray.get(currentPlayer).removeCard(playedCard);
            performReverse();
        }
        else if(playedCard.getValue() == Value.colorChange)
        {
            try {
                playersArray.get(currentPlayer).removeCard(playedCard);
                performColorChange();
            }catch (CancelColorException x)
            {
                playersArray.get(currentPlayer).giveCard(new UnoCard(Color.Special,Value.colorChange));
                throw new Exception();
            }
        }
        else if(playedCard.getValue() == Value.Wild)
        {
            try {
                playersArray.get(currentPlayer).removeCard(playedCard);
                performWild();
            }catch (CancelColorException x)
            {
                playersArray.get(currentPlayer).giveCard(new UnoCard(Color.Special,Value.Wild));
                throw new Exception();
            }
        }
        else if(playedCard.getValue() == Value.drawTwo)
        {
            CurrentCard = playedCard;
            playersArray.get(currentPlayer).removeCard(playedCard);
            performDrawTwo();
        }
        else
        {
            CurrentCard = playedCard;
            performSkip();
            playersArray.get(currentPlayer).removeCard(playedCard);
        }
    }


    public ArrayList<UnoPlayer> getPlayersArray()
    {
        return playersArray;
    }

    public UnoCard getCurrentCard()
    {
        return this.CurrentCard;
    }


    public int getCurrentPlayerIndex()
    {
        return currentPlayer;
    }


    //Getting top card
    public UnoCard getTop() {
        return new UnoCard(validColor, validValue);
    }


    public UnoPlayer getCurrentPlayer() { return this.playersArray.get(currentPlayer); }


    public boolean cardValidation(UnoCard card)
    {
        return  card.getColor() == validColor ||
                card.getValue() == validValue ||
                card.getColor() == Color.Special;
    }


    public void changePlayer()
    {
        if(gameDirection) currentPlayer++;
        else currentPlayer--;

        if(currentPlayer>(playersArray.size()-1)) currentPlayer=0;
        if(currentPlayer<0) currentPlayer=(playersArray.size()-1);
    }


    public void performSkip()
    {
        changePlayer();
        changePlayer();
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
        changePlayer();
    }


    public void performColorChange() throws CancelColorException
    {
            CurrentCard.changeColor(getColorChoice());
    }


    public void performWild() throws CancelColorException
    {

        this.performColorChange();

        this.changePlayer();

        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
        playersArray.get(currentPlayer).giveCard(Deck.drawCard());
    }


    public Color getColorChoice() throws CancelColorException {
        ChoiceDialog<String> dialog;
        List<String> list = new ArrayList<>();

        list.add("Blue");
        list.add("Green");
        list.add("Red");
        list.add("Yellow");

        dialog = new ChoiceDialog<>("Blue", list);
        dialog.setTitle("Choose color");
        dialog.setHeaderText("Choose color");
        dialog.setContentText("Choose color:");

        dialog.showAndWait();

        switch (dialog.getSelectedItem())
        {
            case "Blue":
                return Color.Blue;
            case "Green":
                return Color.Green;
            case "Red":
                return Color.Red;
            case "Yellow":
                return Color.Yellow;
        }
        throw new CancelColorException();
    }

    public void incrementCounter()
    {
        cardCounter++;
    }

    public int getCounter()
    {
        return cardCounter;
    }

    public ArrayList<UnoCard> getCurrentPlayerHand()
    {
        return playersArray.get(currentPlayer).getHand();
    }

}
