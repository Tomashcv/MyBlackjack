package BlackJack;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        boolean appOn = true;
        while (appOn){

            BlackJackRound round = new BlackJackRound();

            boolean firstDecision = true;

            round.start();

            System.out.println("   ");
            System.out.println("BlackJack Started");
            System.out.println("   ");

            System.out.println("Dealer shows: " + round.dealerUpCard() + " + [hidden]");
            System.out.println("Hand " + (round.getActiveHandIndex() + 1) + "/" + round.getHandCount());
            System.out.println("Player: " + round.getPlayer());
            System.out.println(" ");

            boolean running = true;
            while (running){

                if (round.isRoundOver()) {
                    break;
                }

                if (firstDecision && round.canSplit()){
                    System.out.println("Please choose an option {h,s,d,p,q}");
                } else if (firstDecision && !round.canSplit()){
                    System.out.println("Please choose an option {h,s,d,q}");
                } else {
                    System.out.println("Please choose an option {h,s,q}");
                }

                System.out.println("Hit - h");
                System.out.println("Stand - s");
                if (round.canDoubleDown()){
                    System.out.println("Double - d");
                }
                if (round.canSplit()) {
                    System.out.println("Split - p");
                }
                System.out.println("Quit - q");
                System.out.println("Choice: ");

                String input = scanner.nextLine().trim().toLowerCase();

                int beforeHandIndex = round.getActiveHandIndex();

                if (input.equals("h")){

                    System.out.println("Hit");
                    round.hit();
                    System.out.println("Player: " + round.getPlayer());
                    firstDecision = false;

                    if (round.getActiveHandIndex() != beforeHandIndex) {
                        firstDecision = true;
                        System.out.println("Hand " + (round.getActiveHandIndex() + 1) + "/" + round.getHandCount());
                        System.out.println("Player: " + round.getPlayer());
                        System.out.println(" ");
                    }

                } else if (input.equals("s")){

                    System.out.println("Stand");
                    System.out.println("Dealer: " + round.getDealer());

                    List<BlackJackRound.DealerDraw> drawn = round.stand();

                    if (drawn.isEmpty() && !round.isRoundOver()) {
                        firstDecision = true;
                        System.out.println("Hand " + (round.getActiveHandIndex() + 1) + "/" + round.getHandCount());
                        System.out.println("Player: " + round.getPlayer());
                        System.out.println(" ");
                        continue;
                    }

                    for (BlackJackRound.DealerDraw c : drawn) {
                        try { Thread.sleep(500); } catch (InterruptedException ex) { }
                        System.out.println("Dealer draws: " + c.getCard() + " -> total " + c.getTotalAfter());
                    }

                    running = false;

                } else if (input.equals("d")) {

                    if (!round.canDoubleDown()) {
                        System.out.println("Double only allowed in the first decision");
                    } else {
                        System.out.println("DoubleDown");
                        System.out.println("Dealer: " + round.getDealer());

                        List<BlackJackRound.DealerDraw> drawn = round.DoubleDown();

                        System.out.println("Player: " + round.getPlayerHands().get(round.getActiveHandIndex()));

                        if (drawn.isEmpty() && !round.isRoundOver()) {
                            firstDecision = true;
                            System.out.println("Hand " + (round.getActiveHandIndex() + 1) + "/" + round.getHandCount());
                            System.out.println("Player: " + round.getPlayer());
                            System.out.println(" ");
                            continue;
                        }

                        for (BlackJackRound.DealerDraw c : drawn) {
                            try { Thread.sleep(500); } catch (InterruptedException ex) { }
                            System.out.println("Dealer draws: " + c.getCard() + " -> total " + c.getTotalAfter());
                        }

                        running = false;
                    }

                } else if (input.equals("p")){
                    if (!round.canSplit()) {
                        System.out.println("Split is not allowed with different cards.");
                    } else {
                        round.split();
                        firstDecision = true;
                        System.out.println("SPLIT feito!");
                        System.out.println("Hand 1: " + round.getPlayerHands().get(0));
                        System.out.println("Hand 2: " + round.getPlayerHands().get(1));
                    }
                }

                else if (input.equals("q")){
                    System.out.println("Quitting...");
                    running = false;
                    appOn = false;
                } else {
                    System.out.println("Invalid Option");
                }
            }

            System.out.println("Final");
            System.out.println("Dealer: " + round.getDealer());

            for (int i = 0; i < round.getHandCount(); i++) {
                Result res = round.getResultForHand(i);
                System.out.println("Player hand " + (i + 1) + ": " + round.getPlayerHands().get(i));

                if (res == Result.PLAYER_BUST) {
                    System.out.println("Hand " + (i + 1) + ": busted (lose)");
                } else if (res == Result.DEALER_BUST) {
                    System.out.println("Hand " + (i + 1) + ": dealer busted (win)");
                } else if (res == Result.PLAYER_BLACKJACK) {
                    System.out.println("Hand " + (i + 1) + ": blackjack (win)");
                } else if (res == Result.PLAYER_WIN) {
                    System.out.println("Hand " + (i + 1) + ": win");
                } else if (res == Result.DEALER_WIN) {
                    System.out.println("Hand " + (i + 1) + ": lose");
                } else if (res == Result.PUSH) {
                    System.out.println("Hand " + (i + 1) + ": push");
                }
            }

            if (!appOn) {
                break;
            }

            System.out.println("Do you Want to play another Round[yes/no]?");
            String input = scanner.nextLine().trim().toLowerCase();

            while (!input.equals("yes") && !input.equals("no")) {
                System.out.println("Invalid Option. Type yes or no:");
                input = scanner.nextLine().trim().toLowerCase();
            }

            if (input.equals("yes")){

                System.out.println("   ");
                System.out.println("   ");
                System.out.println("   ");
                System.out.println("New Game");
                System.out.println("   ");

            } else {
                System.out.println("Quitting...");
                appOn = false;
            }
        }
        scanner.close();
    }
}
