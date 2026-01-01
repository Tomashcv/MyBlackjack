package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRound {

    @SuppressWarnings("ClassCanBeRecord")
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

    private Deck deck;
    private Hand dealer;

    private List<Hand> playerHands;
    private int activeHandIndex;

    private boolean roundOver;
    private boolean firstDecisionCurHand;
    private boolean splitHappened;

    public void start(){

        this.deck = new Deck();
        this.dealer = new Hand();
        this.playerHands = new ArrayList<>();
        this.playerHands.add(new Hand());
        this.activeHandIndex = 0;
        this.roundOver = false;
        this.firstDecisionCurHand = true;
        this.splitHappened = false;

        Hand h = getPlayer();
        h.addCard(deck.draw());
        dealer.addCard(deck.draw());
        h.addCard(deck.draw());
        dealer.addCard(deck.draw());

        if (h.isBlackJack()){
            roundOver = true;
        } else if (dealer.isBlackJack()){
            roundOver = true;
        }
    }

    public Card dealerUpCard(){
        return dealer.getCards().getFirst();
    }

    public Hand getDealer(){
        return dealer;
    }

    public List<Hand> getPlayerHands() {
        return playerHands;
    }

    public int getActiveHandIndex(){
        return activeHandIndex;
    }

    public int getHandCount() {
        return playerHands.size();
    }

    public Hand getPlayer(){
        return playerHands.get(activeHandIndex);
    }

    public boolean isRoundOver() {
        return roundOver;
    }

    public void hit(){
        if (roundOver){
            return;
        }
        Hand h = getPlayer();
        h.addCard(deck.draw());
        firstDecisionCurHand = false;

        if (h.getTotal() > 21){
            advanceHandOrDealer();
        }
    }

    public List<DealerDraw> stand(){
        if (roundOver){
            return new ArrayList<>();
        }
        return advanceHandOrDealer();
    }

    public boolean canDoubleDown() {
        return firstDecisionCurHand && !roundOver;
    }

    public List<DealerDraw> DoubleDown(){
        if (!canDoubleDown()){
            return new ArrayList<>();
        }
        Hand h = getPlayer();
        h.addCard(deck.draw());
        firstDecisionCurHand = false;

        return advanceHandOrDealer();
    }

    public boolean canSplit(){
        if (roundOver){
            return false;
        }

        if (!firstDecisionCurHand){
            return false;
        }

        if (playerHands.size() != 1){
            return false;
        }

        Hand h = playerHands.getFirst();
        if (h.cardCount() != 2){
            return false;
        }

        int v1 = h.getCard(0).getValue();
        int v2 = h.getCard(1).getValue();

        return v1 == v2;
    }

    public void split(){
        if (!canSplit()){
            return;
        }

        Hand original = playerHands.getFirst();

        Card c1 = original.getCard(0);
        Card c2 = original.removeCard(1);

        Hand h1 = new Hand();
        Hand h2 = new Hand();

        h1.addCard(c1);
        h2.addCard(c2);

        h1.addCard(deck.draw());
        h2.addCard(deck.draw());

        playerHands.clear();

        playerHands.add(h1);
        playerHands.add(h2);

        splitHappened = true;
        activeHandIndex = 0;
        firstDecisionCurHand = true;
    }


    private List<DealerDraw> advanceHandOrDealer(){

        if (activeHandIndex < playerHands.size() - 1){
            activeHandIndex += 1;
            firstDecisionCurHand = true;
            return new ArrayList<>();
        }

        List<DealerDraw> events = dealerPlay();
        roundOver = true;
        return events;
    }

    private List<DealerDraw> dealerPlay() {
        List<DealerDraw> dealerCards = new ArrayList<>();

        while (dealer.getTotal() < 17) {
            Card c = deck.draw();
            dealer.addCard(c);
            dealerCards.add(new DealerDraw(c, dealer.getTotal()));
        }

        return dealerCards;
    }

    public Result getResultForHand(int index){
        if (!roundOver){
            return Result.NOT_FINISHED;
        }

        Hand h = playerHands.get(index);

        if (!splitHappened && playerHands.size() == 1) {

            if (h.isBlackJack() && dealer.isBlackJack()){
                return Result.PUSH;
            }

            if (dealer.isBlackJack()) {
                return Result.DEALER_WIN;
            }

            if (h.isBlackJack()) {
                return Result.PLAYER_BLACKJACK;
            }
        }

        if (h.getTotal() > 21){
            return Result.PLAYER_BUST;
        }

        if (dealer.getTotal() > 21){
            return Result.DEALER_BUST;
        }

        if (h.getTotal() > dealer.getTotal()){
            return Result.PLAYER_WIN;
        }

        if (dealer.getTotal() > h.getTotal()){
            return Result.DEALER_WIN;
        }

        return Result.PUSH;
    }
}
