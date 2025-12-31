package BlackJack;

public class HandTotal {

    protected int total;
    protected int softAces;

    public HandTotal(){
        total = 0;
        softAces = 0;
    }

    public void addCard(int value){
        total += value;

        if (value == 11){
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
}
