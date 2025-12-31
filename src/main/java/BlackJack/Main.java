package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Random random = new Random();

        List<Integer> cards = new ArrayList<>();

        System.out.println("BlackJack Started");

        int i = 2;
        while (i < 12) {
            cards.add(i);
            i++;
        }

        int bound = cards.size();

        System.out.println(cards);

        System.out.println("Dealer Value: ");
        int dealerValue = cards.get(random.nextInt(bound));
        System.out.println(dealerValue);

        System.out.println("Player Value: ");
        int playerValue = cards.get(random.nextInt(bound));
        playerValue += cards.get(random.nextInt(bound));

        if (playerValue == 22){
            playerValue = 12;
        }

        System.out.println(playerValue);

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
                playerValue += cards.get(random.nextInt(bound));
                System.out.println("Player Value: ");
                System.out.println(playerValue);
                if (playerValue > 21){
                    System.out.println("You have busted You Lose");
                    gameOn = false;
                    break;
                }
            } else if (input.equals("2")){
                System.out.println("Stand");
                dealerValue += cards.get(random.nextInt(bound));
                System.out.println("Dealer Value: ");
                System.out.println(dealerValue);
                while (dealerValue < 17){
                    dealerValue += cards.get(random.nextInt(bound));
                    System.out.println("Dealer Value");
                    System.out.println(dealerValue);
                }
                if (dealerValue > 21){
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
            System.out.println(dealerValue);

            System.out.println("Player Value: ");
            System.out.println(playerValue);
        }

        if (dealerValue > playerValue && gameOn){
            System.out.println("You lose");
        } else if (dealerValue == playerValue && gameOn){
            System.out.println("It's a push");
        } else if (dealerValue < playerValue && gameOn){
            System.out.println("You win");
        }
    }

    /*public Main(Random random){
        this.random = random;
    }

    public int getCards(){
        int number = random.nextInt(10);
        return number;
    }*/
}
