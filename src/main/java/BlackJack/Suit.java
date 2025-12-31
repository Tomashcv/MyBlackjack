package BlackJack;

public enum Suit {
    CLUBS("♣"),
    DIAMONDS("♦"),
    SPADES("♠"),
    HEARTS("♥");

    private final String output;

    Suit(String output){
        this.output = output;
    }

    public String getOutput(){
        return output;
    }
}
