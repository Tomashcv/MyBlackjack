package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();

        for (Rank rank : Rank.values()){
            for (Suit suit : Suit.values()){
                    cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card draw(){
        return cards.removeFirst();
    }
}
