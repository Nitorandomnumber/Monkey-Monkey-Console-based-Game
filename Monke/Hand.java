package Monke;

import java.util.ArrayList;

public class Hand {
    private final ArrayList<String> hand = new ArrayList<>();

    public ArrayList<String> getHand() {
        return hand;
    }

    public void addCard(String hand) {
        this.hand.add(hand);
    }

    public void removeCard(int index) {
        hand.remove(index);
    }

    public void clear() {
        hand.clear();
    }

    public void addAll(ArrayList<String> hand) {
        this.hand.addAll(hand);
    }
}
