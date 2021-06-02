package uno;

import java.util.Objects;

public class UnoCard {


    private Color color;
    private final Value value;


    public UnoCard (final Color color, final Value value)
    {
        this.color = color;
        this.value = value;
    }

    public int getColorIndex()
    {
        return color.ordinal();
    }

    public int getValueIndex()
    {
        return value.ordinal();
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

    public void changeColor(Color a)
    {
        this.color = a;
    }

    public  void  handleSpecial(){}

}
