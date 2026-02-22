package Monke;

public class Hand {
    private String[] hand = new String[13];
    private int size = 0;

    public String[] getHand() {
        return Arrays.copyOf(hand, size);
    }

    public void addCard(String card) {
        if (size < hand.length) {
            hand[size++] = card;
        }
    }

    public void removeCard(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                hand[i] = hand[i + 1];
            }
            hand[--size] = null;
        }
    }

    public void clear() {
        hand = new String[13];
        size = 0;
    }

    public void addAll(String[] cards) {
        for (String card : cards) {
            addCard(card);
        }
    }
}