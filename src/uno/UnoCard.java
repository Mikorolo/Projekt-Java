package uno;

import java.util.Objects;

public class UnoCard {
    enum Color {
        Blue, Green, Red, Yellow, Special;
        private static final Color[] cardColors = Color.values();
        public static Color getColor(int i)
        {
            return Color.cardColors[i];
        }
    }
    enum Value {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Reverse, drawTwo, Skip, colorChange, Wild;
        private static final Value[] cardValues = Value.values();
        public static Value getValue(int i)
        {
            return Value.cardValues[i];
        }
    }

    private Color color;
    private final Value value;

    public UnoCard (final Color color, final Value value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnoCard unoCard = (UnoCard) o;
        return color == unoCard.color && value == unoCard.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }

    public Color getColor() {
        return this.color;
    }

    public Value getValue() {
        return this.value;
    }

    public void changeColor(UnoCard.Color a)
    {
        this.color = a;
    }

    public  void  handleSpecial(){}

}
