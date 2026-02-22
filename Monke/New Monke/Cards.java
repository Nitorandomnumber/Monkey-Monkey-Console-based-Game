package Monke;

import java.util.Random;
import java.util.Scanner;

public class Cards {
    private String hiddenCard;
    private String lastCard;
    private static String[] discarded = new String[52];
    private static String[] tempBin = new String[52];
    private static String[][] DECK;

    public Cards() {
        hiddenCard = "";
        lastCard = "";
        DECK = new String[][]{
                {"Aظآة", "2ظآة", "3ظآة", "4ظآة", "5ظآة", "6ظآة", "7ظآة", "8ظآة", "9ظآة", "10ظآة", "Kظآة", "Qظآة", "Jظآة"},
                {"Aظآخ", "2ظآخ", "3ظآخ", "4ظآخ", "5ظآخ", "6ظآخ", "7ظآخ", "8ظآخ", "9ظآخ", "10ظآخ", "Kظآخ", "Qظآخ", "Jظآخ"},
                {"Aظآب", "2ظآب", "3ظآب", "4ظآب", "5ظآب", "6ظآب", "7ظآب", "8ظآب", "9ظآب", "10ظآب", "Kظآب", "Qظآب", "Jظآب"},
                {"Aظآث", "2ظآث", "3ظآث", "4ظآث", "5ظآث", "6ظآث", "7ظآث", "8ظآث", "9ظآث", "10ظآث", "Kظآث", "Qظآث", "Jظآث"},
                {"Hearts", "Diamonds", "Clubs", "Spades"}
        };
    }

    public String[][] getDECK() {
        return DECK;
    }

    public String getHiddenCard() {
        return hiddenCard;
    }

    public String getLastCard() {
        return lastCard;
    }

    public String[] getDeckList() {
        String[] deckList = new String[52];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                deckList[index++] = DECK[i][j];
            }
        }
        return deckList;
    }

    public void setLastCard(String lastCard) {
        this.lastCard = lastCard;
    }

    public void setHiddenCard(String[] deck) {
        Random rand = new Random();
        int randIndex = rand.nextInt(deck.length);
        this.hiddenCard = deck[randIndex];
        deck[randIndex] = null; // Remove the card
    }

    public String[] getDiscarded() {
        return discarded;
    }

    public String[] getTempBin() {
        return tempBin;
    }

    public String[] cancelPairs(String[] list) {
        String[] arrList = Arrays.copyOf(list, list.length);
        Arrays.sort(arrList);
        int newSize = 0;
        for (int i = 0; i < arrList.length - 1; i++) {
            if (arrList[i] != null && arrList[i].charAt(0) == arrList[i + 1].charAt(0)) {
                tempBin[tempBin.length] = arrList[i];
                discarded[discarded.length] = arrList[i];
                tempBin[tempBin.length] = arrList[i + 1];
                discarded[discarded.length] = arrList[i + 1];
                arrList[i] = null;
                arrList[i + 1] = null;
                newSize += 2;
            }
        }
        String[] result = new String[arrList.length - newSize];
        int index = 0;
        for (String card : arrList) {
            if (card != null) {
                result[index++] = card;
            }
        }
        return result;
    }
}