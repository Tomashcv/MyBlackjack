package BlackJack;

public enum Rank {

    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7,"7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "T"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ACE(11, "A");

    private final int value;
    private final String output;

    Rank(int value, String output){
        this.value = value;
        this.output = output;
    }

    public int getValue(){
        return value;
    }

    public String getOutput(){
        return output;
    }
}
