package uno;

public enum Color {
    Blue, Green, Red, Yellow,Special;
    private static final Color[] cardColors = Color.values();
    public static Color getColor(int i)
    {
        return Color.cardColors[i];
    }
}
