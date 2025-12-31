package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;
    private int total;
    private int softAces;

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

    public boolean isBlackJack(){
        return total == 21 && cards.size() == 2;
    }

    public List<Card> getCards(){
        return cards;
    }

    public String toString(){
        return cards.toString() + " (total = " + total + ")";
    }
}
