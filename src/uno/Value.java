package uno;

public enum Value {
    Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Reverse, drawTwo, Skip, colorChange, Wild;
    private static final Value[] cardValues = Value.values();
    public static Value getValue(int i)
    {
        return Value.cardValues[i];
    }
}
