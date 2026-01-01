package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRound {

    private Deck deck;
    private Hand dealer;
    private Hand player;
    private boolean roundOver;

    public void start(){

        this.deck = new Deck();
        this.dealer = new Hand();
        this.player = new Hand();
        this.roundOver = false;

        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    public Card dealerUpCard(){
        return dealer.getCards().getFirst();
    }

    public Hand getDealer(){
        return dealer;
    }

    public Hand getPlayer(){
        return player;
    }

    public void hit(){
        player.addCard(deck.draw());
        if (player.getTotal() > 21){
            roundOver = true;
        }
    }

    public List<Card> stand(){
        return dealerPlay();
    }

    public List<Card> DoubleDown(){
        player.addCard(deck.draw());
        return dealerPlay();
    }

    public Result getResult(){
        if (!roundOver){
            if (player.isBlackJack()){
                if (dealer.getCards().getFirst().getValue() == 10 || dealer.getCards().getFirst().getValue() == 11){
                    if (dealer.isBlackJack()){
                        return Result.PUSH;
                    }
                    return Result.PLAYER_BLACKJACK;
                }
            } else {
                return Result.NOT_FINISHED;
            }
        }
        if (player.getTotal() > 21){
            return Result.PLAYER_BUST;
        }
        if (dealer.getTotal() > 21){
            return Result.DEALER_BUST;
        }
        if (player.getTotal() > dealer.getTotal()){
            return Result.PLAYER_WIN;
        }
        if (dealer.getTotal() > player.getTotal()){
            return Result.DEALER_WIN;
        }
        return Result.PUSH;
    }

    private List<Card> dealerPlay(){
        List<Card> dealerCards = new ArrayList<>();

        while (dealer.getTotal() < 17){
            Card c = deck.draw();
            dealer.addCard(c);
            dealerCards.add(c);
        }

        roundOver = true;
        return dealerCards;
    }
}
