package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    protected int total;
    protected int softAces;

    public Hand(){
        cards = new ArrayList<Card>();
        total = 0;
        softAces = 0;
    }

    public void addCard(Card card){

        cards.add(card);
        total += card.getValue();

        if (card.getRank() == Rank.ACE){
            softAces += 1;
        }

        while (total > 21 && softAces > 0){
            total -= 10;
            softAces -= 1;
        }
    }

    public int getTotal(){
        return total;
    }
}
