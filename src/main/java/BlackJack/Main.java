package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Random random = new Random();

        HandTotal dealer = new HandTotal();
        HandTotal player = new HandTotal();

        List<Integer> cards = new ArrayList<>();

        System.out.println("BlackJack Started");

        int i = 2;
        while (i < 12) {
            cards.add(i);
            i++;
        }

        System.out.println(cards);

        dealer.addCard(draw(random, cards));
        System.out.println("Dealer Value: " + dealer.getTotal());

        player.addCard(draw(random, cards));
        player.addCard(draw(random, cards));
        System.out.println("Player Value: " + player.getTotal());

        System.out.println(" ");
        System.out.println("Hit or Stand");

        Scanner scanner = new Scanner(System.in);

        boolean gameOn = true;
        boolean running = true;
        while (running){

            System.out.println("1.Hit");
            System.out.println("2.Stand");
            System.out.println("3.Quit");
            System.out.println("Escolha: ");

            String input = scanner.nextLine().trim();


            if (input.equals("1")){
                System.out.println("Hit");

                player.addCard(draw(random, cards));
                System.out.println("Player Value: " + player.getTotal());

                if (player.getTotal() > 21){
                    System.out.println("You have busted You Lose");
                    gameOn = false;
                    break;
                }
            } else if (input.equals("2")){

                System.out.println("Stand");

                dealer.addCard(draw(random, cards));
                System.out.println("Dealer Value: " + dealer.getTotal());

                while (dealer.getTotal() < 17){

                    dealer.addCard(draw(random, cards));
                    System.out.println("Dealer Value: " + dealer.getTotal());
                }
                if (dealer.getTotal() > 21){
                    gameOn = false;
                    System.out.println("Dealer has Busted You Win");
                    break;
                }
                break;
            } else if (input.equals("3")){
                System.out.println("Quitting");
                gameOn = false;
                running = false;
            } else {
                System.out.println("Invalid Option");
            }
        }
        scanner.close();

        if(gameOn){
            System.out.println("Final");

            System.out.println("Dealer Value: ");
            System.out.println(dealer.getTotal());

            System.out.println("Player Value: ");
            System.out.println(player.getTotal());
        }

        if (dealer.getTotal() > player.getTotal() && gameOn){
            System.out.println("You lose");
        } else if (dealer.getTotal() == player.getTotal() && gameOn){
            System.out.println("It's a push");
        } else if (dealer.getTotal() < player.getTotal() && gameOn){
            System.out.println("You win");
        }
    }

    private static int draw(Random random, List<Integer> cards) {
        return cards.get(random.nextInt(cards.size()));
    }
}
