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
