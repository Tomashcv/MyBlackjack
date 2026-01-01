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

    public List<BlackJackRound.DealerDraw> stand(){
        return dealerPlay();
    }

    public List<BlackJackRound.DealerDraw> DoubleDown(){
        player.addCard(deck.draw());
        return dealerPlay();
    }

    public Result getResult(){
        if (!roundOver){
            if (player.isBlackJack()) {

                Card up = dealer.getCards().getFirst();
                boolean dealerMayHaveBJ = (up.getValue() == 10 || up.getValue() == 11);

                if (dealerMayHaveBJ && dealer.isBlackJack()) {
                    return Result.PUSH;
                }

                return Result.PLAYER_BLACKJACK;
            }
            return Result.NOT_FINISHED;
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

    public static class DealerDraw {
        private final Card card;
        private final int totalAfter;

        public DealerDraw(Card card, int totalAfter) {
            this.card = card;
            this.totalAfter = totalAfter;
        }

        public Card getCard() {
            return card;
        }

        public int getTotalAfter() {
            return totalAfter;
        }
    }


    private List<DealerDraw> dealerPlay() {
        List<DealerDraw> dealerCards = new ArrayList<>();

        while (dealer.getTotal() < 17) {
            Card c = deck.draw();
            dealer.addCard(c);
            dealerCards.add(new DealerDraw(c, dealer.getTotal()));
        }

        roundOver = true;
        return dealerCards;
    }
}
