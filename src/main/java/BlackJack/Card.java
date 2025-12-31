package BlackJack;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue(){
        return rank.getValue();
    }

    public String getOutput(){
        return rank.getOutput();
    }

    public Rank getRank(){
        return rank;
    }

    public Suit getSuit(){
        return suit;
    }

    @Override
    public String toString(){
        return rank.getOutput() + suit.getOutput();
    }
}
