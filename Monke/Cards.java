package Monke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Cards {
    private String hiddenCard;
    private String lastCard;
    private static final ArrayList<String> discarded = new ArrayList<>();
    private static final ArrayList<String> tempBin = new ArrayList<>();
    private static String[][] DECK;

    public Cards() {
        hiddenCard = "";
        lastCard = "";
        DECK = new String[][]{
                {"A♡", "2♡", "3♡", "4♡", "5♡", "6♡", "7♡", "8♡", "9♡", "10♡", "K♡", "Q♡", "J♡"}, //index[0][n] | Hearts ♥
                {"A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "K♦", "Q♦", "J♦"}, //index[1][n] | Diamonds ♦
                {"A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "K♠", "Q♠", "J♠"}, //index[2][n] | Clubs ♣
                {"A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "K♣", "Q♣", "J♣"}, //index[3][n] | Spades ♠
                {"Hearts", "Diamonds", "Clubs", "Spades"} //index[4][n] | Suit names
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

    public ArrayList<String> getDeckList() {
        return new ArrayList<>(Arrays.asList(mergeArrays(DECK[1], DECK[0], DECK[2], DECK[3])));
    }

    public void setLastCard(String lastCard) {
        this.lastCard = lastCard;
    }

    public void setHiddenCard(ArrayList<String> deck) {
        var rand = Main.random(deck.size(), 0);
        this.hiddenCard = deck.get(rand);
        deck.remove(rand);
    }

    private static String[] mergeArrays(String[]... arrays) {//Merges multiple arrays into one.
        return Stream.of(arrays)
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }

    public ArrayList<String> getDiscarded() {
        return discarded;
    }

    public ArrayList<String> getTempBin() {
        return tempBin;
    }

    public List<String> cancelPairs(ArrayList<String> list) {
        List<String> arrList = new ArrayList<>(list);
        Collections.sort(arrList);
        list.clear();
        tempBin.clear();
        for (int i = 0; i <= arrList.size() - 1; i++) {
            for (int j = i + 1; j < arrList.size(); j++) {
                if ((arrList.get(i).charAt(0) == (arrList.get(j).charAt(0)) && i != j)) {
                    tempBin.add(arrList.get(i));
                    discarded.add(arrList.get(i));
                    tempBin.add(arrList.get(i + 1));
                    discarded.add(arrList.get(i + 1));
                    arrList.remove(arrList.get(i));
                    arrList.remove(arrList.get(i));
                }
            }
        }
        for (int i = 0; i <= arrList.size() - 1; i++) {
            for (int j = i + 1; j < arrList.size(); j++) {
                if ((arrList.get(i).charAt(0) == (arrList.get(j).charAt(0)) && i != j)) {
                    tempBin.add(arrList.get(i));
                    discarded.add(arrList.get(i));
                    tempBin.add(arrList.get(i + 1));
                    discarded.add(arrList.get(i + 1));
                    arrList.remove(arrList.get(i));
                    arrList.remove(arrList.get(i));
                }
            }
        }
        if (tempBin.size() == 0) {
            new Animations().noPairsAnim();
        } else {
            new Animations().cancelProgressAnim();
        }
        return arrList;
    }

}
