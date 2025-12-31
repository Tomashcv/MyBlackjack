package BlackJack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        boolean appOn = true;
        while (appOn){
        Deck deck = new Deck();

        Hand dealer = new Hand();
        Hand player = new Hand();

        System.out.println("   ");

        System.out.println("BlackJack Started");

        System.out.println("   ");

        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
        System.out.println("Dealer shows: " + dealer.getCards().getFirst() + " + [hidden]");

        player.addCard(deck.draw());
        player.addCard(deck.draw());
        if (player.isBlackJack()){
            System.out.println("You have Blackjack");
            if (dealer.getCards().getFirst().getValue() == 10 || dealer.getCards().getFirst().getValue() == 11){
                System.out.println("Dealer: " + dealer);
                if (dealer.isBlackJack()){
                    System.out.println("It's a push");
                    break;
                } else {
                    System.out.println("You win");
                    break;
                }
            }
        }
        System.out.println("Player: " + player);

        System.out.println(" ");


            boolean gameOn = true;
            boolean running = true;
            while (running){

                System.out.println("Please choose an option {1,2,3,4}");
                System.out.println("1.Hit");
                System.out.println("2.Stand");
                System.out.println("3.Double");
                System.out.println("4.Quit");
                System.out.println("Choice: ");

                String input = scanner.nextLine().trim();

                if (input.equals("1")){
                    System.out.println("Hit");

                    player.addCard(deck.draw());
                    System.out.println("Player: " + player);

                    if (player.getTotal() > 21){
                        System.out.println("You have busted You Lose");
                        gameOn = false;
                        break;
                    }
                } else if (input.equals("2")){

                    System.out.println("Stand");

                    System.out.println("Dealer: " + dealer);

                    while (dealer.getTotal() < 17){

                        dealer.addCard(deck.draw());
                        System.out.println("Dealer: " + dealer);
                    }
                    if (dealer.getTotal() > 21){
                        gameOn = false;
                        System.out.println("Dealer has Busted You Win");
                        break;
                    }
                    break;
                } else if (input.equals("3")) {
                    System.out.println("Double");

                    player.addCard(deck.draw());
                    System.out.println("Player: " + player);

                    if (player.getTotal() > 21){
                        System.out.println("You have busted You Lose");
                        gameOn = false;
                        break;
                    } else {
                        while (dealer.getTotal() < 17){
                            dealer.addCard(deck.draw());
                            System.out.println("Dealer: " + dealer);
                        }
                        if (dealer.getTotal() > 21){
                            gameOn = false;
                            System.out.println("Dealer has Busted You Win");
                            break;
                        }
                        break;
                    }
                } else if (input.equals("4")){
                    System.out.println("Quitting...");
                    gameOn = false;
                    running = false;
                    appOn = false;
                } else {
                    System.out.println("Invalid Option");
                }
            }

            if(gameOn){
                System.out.println("Final");

                System.out.println("Dealer: " + dealer);

                System.out.println("Player: " + player);
            }

            if (dealer.getTotal() > player.getTotal() && gameOn){
                System.out.println("You lose");
            } else if (dealer.getTotal() == player.getTotal() && gameOn){
                System.out.println("It's a push");
            } else if (dealer.getTotal() < player.getTotal() && gameOn){
                System.out.println("You win");
            }

            System.out.println("   ");
            System.out.println("   ");
            System.out.println("   ");
            System.out.println("New Game");
            System.out.println("   ");
        }
        scanner.close();
    }
}
