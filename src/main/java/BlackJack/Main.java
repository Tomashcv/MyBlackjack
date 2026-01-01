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
            System.out.println("Player: " + round.getPlayer());
            System.out.println(" ");

            boolean running = true;
            while (running){

                Result res = round.getResult();
                if (res != Result.NOT_FINISHED){
                    break;
                }

                if (firstDecision){
                    System.out.println("Please choose an option {h,s,d,q}");
                } else {
                    System.out.println("Please choose an option {h,s,q}");
                }
                System.out.println("Hit - h");
                System.out.println("Stand - s");
                if (firstDecision){
                    System.out.println("Double - d");
                }
                System.out.println("Quit - q");
                System.out.println("Choice: ");

                String input = scanner.nextLine().trim();

                if (input.equals("h")){

                    System.out.println("Hit");
                    round.hit();
                    System.out.println("Player: " + round.getPlayer());
                    firstDecision = false;

                } else if (input.equals("s")){

                    System.out.println("Stand");
                    System.out.println("Dealer: " + round.getDealer());
                    List<Card> drawn = round.stand();
                    for (Card c : drawn) {
                        System.out.println("Dealer draws: " + c + " -> total " + round.getDealer().getTotal());
                    }
                    running = false;

                } else if (input.equals("d") && firstDecision) {

                    System.out.println("Double");
                    System.out.println("Dealer: " + round.getDealer());

                    List<Card> drawn = round.DoubleDown();

                    System.out.println("Player: " + round.getPlayer());

                    for (Card c : drawn) {
                        System.out.println("Dealer draws: " + c + " -> total " + round.getDealer().getTotal());
                    }
                    running = false;

                } else if (input.equals("d")){
                    System.out.println("Double its only allowed in the first decision");
                }
                else if (input.equals("q")){
                    System.out.println("Quitting...");
                    running = false;
                    appOn = false;
                } else {
                    System.out.println("Invalid Option");
                }
            }

            Result finalRes = round.getResult();

            System.out.println("Final");
            System.out.println("Dealer: " + round.getDealer());
            System.out.println("Player: " + round.getPlayer());

            if (finalRes == Result.PLAYER_BUST) {
                System.out.println("You have busted. You lose");
            } else if (finalRes == Result.DEALER_BUST) {
                System.out.println("Dealer has busted. You win");
            } else if (finalRes == Result.PLAYER_BLACKJACK) {
                System.out.println("You have Blackjack! You win");
            } else if (finalRes == Result.PLAYER_WIN) {
                System.out.println("You win");
            } else if (finalRes == Result.DEALER_WIN) {
                System.out.println("You lose");
            } else if (finalRes == Result.PUSH) {
                System.out.println("It's a push");
            } else {
                System.out.println("Round ended");
            }

            System.out.println("Do you Want to play another Round[yes/no]?");
            String input = scanner.nextLine().trim();

            if (input.equals("yes")){

                System.out.println("   ");
                System.out.println("   ");
                System.out.println("   ");
                System.out.println("New Game");
                System.out.println("   ");

            } else if (input.equals("no")){
                System.out.println("Quitting...");
                appOn = false;
            } else {
                System.out.println("Invalid Option");
            }

        }
        scanner.close();
    }
}
