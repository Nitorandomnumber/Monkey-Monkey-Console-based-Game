package Monke;

import java.util.Arrays; // Add this line
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String line = "————————————————————————————————————————————————————————————————————————————————————————————————————————————————————";
    static Hand userHand = new Hand(); // Human player's hand
    static Hand cpuHand = new Hand();  // CPU 1's hand
    static Hand cpuHand1 = new Hand(); // CPU 2's hand
    static Hand cpuHand2 = new Hand(); // CPU 3's hand
    static Cards cards = new Cards();
    static Animations anim = new Animations();

    static String[] deckList;

    // CPU names
    static final String CPU1_NAME = "CPU1";
    static final String CPU2_NAME = "CPU2";
    static final String CPU3_NAME = "CPU3";

    public static void main(String[] args) {
        outer:
        while (true) {
            // Shuffle the deck
            deckList = cards.getDeckList();
            shuffleArray(deckList);

            // Hide one card
            cards.setHiddenCard(deckList);

            // Distribute cards
            int size = deckList.length;

            // User hand: 13 cards
            for (int i = 0; i < 13; i++) {
                userHand.addCard(deckList[i]);
            }

            // CPU1 hand: 13 cards
            for (int i = 13; i < 26; i++) {
                cpuHand.addCard(deckList[i]);
            }

            // CPU2 hand: 13 cards
            for (int i = 26; i < 39; i++) {
                cpuHand1.addCard(deckList[i]);
            }

            // CPU3 hand: 12 cards
            for (int i = 39; i < size; i++) {
                cpuHand2.addCard(deckList[i]);
            }

            anim.titleAnim();
            displayDeck();
            whoGoesFirst(isFirstToPick());

            // End part
            System.out.println(line);
            System.out.println();
            showAllRemoved();
            System.out.println();
            System.out.println(line);
            compareHiddenAndLastCard();
            System.out.println();
            System.out.println(line);
            System.out.println();
            System.out.print("Do you want to play again? Y/N :");
            char choice = scanner.next().toLowerCase().charAt(0);
            do {
                if (choice == 'n') {
                    System.out.println("\nSuccessfully exited the program. \nPlease come again!");
                    break outer;
                } else if (choice == 'y') {
                    System.out.println();
                    userHand.clear();
                    cpuHand.clear();
                    cpuHand1.clear();
                    cpuHand2.clear();
                    deckList = new String[52];
                    break;
                } else if (choice != '?' && choice != ' ') {
                    System.out.print("Is it a no or a yes? Please make up your mind:");
                    choice = scanner.next().toLowerCase().charAt(0);
                } else {
                    System.out.print("Is it a no or a yes? Please make up your mind:");
                    choice = scanner.next().toLowerCase().charAt(0);
                }
            } while (true);
        }
        System.exit(0);
    }

    private static void shuffleArray(String[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndex = rand.nextInt(array.length);
            String temp = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = temp;
        }
    }

    private static void displayDeck() {
        wait(400);
        var suits = cards.getDECK();
        System.out.println("Matching^2: A card matching game | By: Group Java Rice");
        System.out.println();

        // Display suit names
        System.out.println("Suits: " + Arrays.toString(suits[4]).replace("[", "").replace("]", " "));
        System.out.println();
        System.out.println("Deck:");
        System.out.println(
                Arrays.toString(suits[0]).replace("[", "").replace("]", " | ") + suits[4][0] + "\n" +
                        Arrays.toString(suits[1]).replace("[", "").replace("]", " | ") + suits[4][1] + "\n" +
                        Arrays.toString(suits[2]).replace("[", "").replace("]", " | ") + suits[4][2] + "\n" +
                        Arrays.toString(suits[3]).replace("[", "").replace("]", " | ") + suits[4][3]
        );
        System.out.println();
        System.out.println("Shuffled deck:");
        shuffleArray(deckList);
        showInRows(deckList, 26);
        System.out.println("Total cards in deck: " + deckList.length);
        System.out.println();
        System.out.println("Hidden Card: " + cards.getHiddenCard());
        System.out.println();
        System.out.println("Initial distribution of cards: ");
        System.out.println("User hand: \n" + Arrays.toString(userHand.getHand()).replace("[", "").replace("]", ""));
        System.out.println("No. of cards: " + userHand.getHand().length);
        System.out.println();

        System.out.println("CPU 1 (" + CPU1_NAME + ") hand: \n" + Arrays.toString(cpuHand.getHand()).replace("[", "").replace("]", ""));
        System.out.println("No. of cards: " + cpuHand.getHand().length);
        System.out.println();

        System.out.println("CPU 2 (" + CPU2_NAME + ") hand: \n" + Arrays.toString(cpuHand1.getHand()).replace("[", "").replace("]", ""));
        System.out.println("No. of cards: " + cpuHand1.getHand().length);
        System.out.println();

        System.out.println("CPU 3 (" + CPU3_NAME + ") hand: \n" + Arrays.toString(cpuHand2.getHand()).replace("[", "").replace("]", ""));
        System.out.println("No. of cards: " + cpuHand2.getHand().length);
        System.out.println();

        System.out.println("Cancelling User hand pairs...");
        userHand.addAll(cards.cancelPairs(userHand.getHand()));
        System.out.println();

        System.out.println("Cancelling CPU 1 hand pairs...");
        cpuHand.addAll(cards.cancelPairs(cpuHand.getHand()));
        System.out.println();

        System.out.println("Cancelling CPU 2 hand pairs...");
        cpuHand1.addAll(cards.cancelPairs(cpuHand1.getHand()));
        System.out.println();

        System.out.println("Cancelling CPU 3 hand pairs...");
        cpuHand2.addAll(cards.cancelPairs(cpuHand2.getHand()));
        System.out.println();

        System.out.println("Cancelled cards: ");
        showInRows(cards.getDiscarded(), 26);
        System.out.println("No. cards: " + cards.getDiscarded().length);
        System.out.println();
        System.out.println(line);
        System.out.println();
    }

    private static boolean isFirstToPick() {
        var coinToss = new CoinFlip();
        // Game intro
        System.out.println("「 GAME START! 」");
        wait(200);

        System.out.println("We will determine the first to pick via coin toss. ");
        System.out.print("Enter your bet, HEAD [0] or TAILS [1]: ");
        coinToss.setUserBet(scanner.next());
        coinToss.setResult();

        // Show the winner of the toss coin
        System.out.println("\nYou bet on " + coinToss.getUserBet());
        anim.coinTossAnim();
        System.out.println("Result of coin toss " + coinToss.getResult());
        System.out.println();
        wait(500);

        if ((coinToss.getResult().equals(coinToss.getUserBet()))) {
            System.out.println("「 You 」 get to pick first!");
            System.out.println();
            return true;
        } else {
            System.out.println("「 CPU 」 get to pick first!");
            System.out.println();
            return false;
        }
    }

    private static void whoGoesFirst(boolean userFirst) {
        if (userFirst) {
            do {
                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // Human's turn (picks from CPU1)
                userTurn(cpuHand);
                pressEnterKeyToContinue();
                System.out.println();

                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // CPU1's turn (picks from CPU2)
                cpuTurn(cpuHand, cpuHand1, CPU1_NAME);
                pressEnterKeyToContinue();
                System.out.println();

                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // CPU2's turn (picks from CPU3)
                cpuTurn(cpuHand1, cpuHand2, CPU2_NAME);
                pressEnterKeyToContinue();
                System.out.println();

                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // CPU3's turn (picks from Human)
                cpuTurn(cpuHand2, userHand, CPU3_NAME);
                pressEnterKeyToContinue();
                System.out.println();
            } while (true);
        } else {
            do {
                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // CPU1's turn (picks from CPU2)
                cpuTurn(cpuHand, cpuHand1, CPU1_NAME);
                pressEnterKeyToContinue();
                System.out.println();

                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // CPU2's turn (picks from CPU3)
                cpuTurn(cpuHand1, cpuHand2, CPU2_NAME);
                pressEnterKeyToContinue();
                System.out.println();

                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // CPU3's turn (picks from Human)
                cpuTurn(cpuHand2, userHand, CPU3_NAME);
                pressEnterKeyToContinue();
                System.out.println();

                // Check if any player has won
                if (userHand.getHand().length == 0 || cpuHand.getHand().length == 0 || cpuHand1.getHand().length == 0 || cpuHand2.getHand().length == 0) {
                    isThereAWinnerYet();
                    break;
                }

                // Human's turn (picks from CPU1)
                userTurn(cpuHand);
                pressEnterKeyToContinue();
                System.out.println();
            } while (true);
        }
    }

    private static void userTurn(Hand targetHand) {
        var cancelled = cards.getTempBin();
        wait(500);
        System.out.println("It's your turn.");
        wait(500);
        System.out.println();
        shuffleArray(targetHand.getHand());
        System.out.println("CPU 1 (" + CPU1_NAME + ") hand:");
        showChoices(targetHand.getHand());
        System.out.println("No. of cards: " + targetHand.getHand().length);
        wait(500);
        System.out.print("\nYour hand: ");
        System.out.println(Arrays.toString(userHand.getHand()).replace("[", "").replace("]", ""));
        System.out.println("No. of cards : " + userHand.getHand().length);
        System.out.println();
        wait(500);
        System.out.println("Please pick a card from CPU 1's hand. ");
        System.out.printf("Enter the index of the card (0 - %s): ", targetHand.getHand().length - 1);
        int pick = scanner.nextInt();
        System.out.println();
        userHand.addCard(targetHand.getHand()[pick]);
        wait(500);
        System.out.println("Drawn card: " + targetHand.getHand()[pick]);
        targetHand.removeCard(pick);
        System.out.println();
        wait(500);
        userHand.addAll(cards.cancelPairs(userHand.getHand()));
        System.out.println();
        System.out.println("Cancelled pairs: ");
        System.out.println((cancelled.length == 0) ? "None" :
                Arrays.toString(cancelled).replace("[", "").replace("]", ""));
        System.out.println();
        System.out.println("Cards left:");
        System.out.print((userHand.getHand().length == 0) ? "None" : Arrays.toString(userHand.getHand()).replace("[", "").replace("]", ""));
        System.out.println();
    }

    private static void cpuTurn(Hand cpuPlayerHand, Hand targetHand, String cpuName) {
        var cancelled = cards.getTempBin();
        shuffleArray(targetHand.getHand());
        int random = random(targetHand.getHand().length - 1, 0);
        System.out.print(cpuName + "'s turn.\r");
        System.out.println();
        System.out.println(cpuName + " is picking...");
        anim.cpuPickAnim();
        cpuPlayerHand.addCard(targetHand.getHand()[random]);
        System.out.println();
        wait(1000);
        System.out.println("Drawn card: " + targetHand.getHand()[random]);
        System.out.println();
        System.out.print(cpuName + " current hand: ");
        System.out.println(Arrays.toString(cpuPlayerHand.getHand()).replace("[", "").replace("]", ""));
        targetHand.removeCard(random);
        System.out.println();
        cpuPlayerHand.addAll(cards.cancelPairs(cpuPlayerHand.getHand()));
        System.out.println();
        System.out.println("Cancelled pairs: ");
        System.out.println((cancelled.length == 0) ? "None" :
                Arrays.toString(cancelled).replace("[", "").replace("]", ""));
        System.out.println();
        System.out.println(cpuName + " cards left:");
        System.out.println((cpuPlayerHand.getHand().length == 0) ? "None" : Arrays.toString(cpuPlayerHand.getHand()).replace("[", "").replace("]", ""));
        System.out.println();
    }

    private static void isThereAWinnerYet() {
        if (userHand.getHand().length == 0) {
            anim.congratsAnim();
            System.out.println();
            System.out.println("We have a winner!!!");
            System.out.println("You won the game!");
            cards.setLastCard(cpuHand.getHand()[0]);
        } else if (cpuHand.getHand().length == 0) {
            anim.congratsAnim();
            System.out.println();
            System.out.println("We have a winner!!!");
            System.out.println(CPU1_NAME + " won the game!");
            cards.setLastCard(userHand.getHand()[0]);
        } else if (cpuHand1.getHand().length == 0) {
            anim.congratsAnim();
            System.out.println();
            System.out.println("We have a winner!!!");
            System.out.println(CPU2_NAME + " won the game!");
            cards.setLastCard(userHand.getHand()[0]);
        } else if (cpuHand2.getHand().length == 0) {
            anim.congratsAnim();
            System.out.println();
            System.out.println("We have a winner!!!");
            System.out.println(CPU3_NAME + " won the game!");
            cards.setLastCard(userHand.getHand()[0]);
        }
    }

    private static void showAllRemoved() {
        System.out.println("All paired cards: ");
        showInRows(cards.getDiscarded(), 20);
        System.out.println("Card count: " + cards.getDiscarded().length);
    }

    public static void compareHiddenAndLastCard() {
        System.out.println("The remaining card: " + cards.getLastCard());
        System.out.println("The hidden card: " + cards.getHiddenCard());
        if (cards.getHiddenCard().charAt(0) == (cards.getLastCard().charAt(0))) {
            System.out.println("The remaining card and the hidden card is a pair.");
        } else {
            System.out.println("They are not a pair and this program failed.");
        }
    }

    public static void showChoices(String[] cardList) {
        int num = 0;
        for (String p : cardList) {
            if (p != null) {
                if (p.contains("10")) {
                    if (num > 9) {
                        System.out.printf("""
                               ┌─────┐
                            %S | %S |
                               └─────┘
                            """, num, p);
                    } else {
                        System.out.printf("""
                               ┌─────┐
                             %S | %S |
                               └─────┘
                            """, num, p);
                    }
                } else {
                    if (num > 9) {
                        System.out.printf("""
                               ┌────┐
                            %S | %S |
                               └────┘
                            """, num, p);
                    } else {
                        System.out.printf("""
                               ┌────┐
                             %S | %S |
                               └────┘
                            """, num, p);
                    }
                }
                num++;
            }
        }
    }

    private static void showInRows(String[] list, int rows) {
        int index = 0;
        int listSize = 0;
        for (String p : list) {
            if (p != null) {
                System.out.printf((index == rows - 1 || listSize == list.length - 1 ? "%S " : "%S, "), p);
                index++;
                listSize++;
                if (index == rows) {
                    System.out.println();
                    index = 0;
                }
            }
        }
        System.out.println();
    }

    public static int random(int max, int min) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void pressEnterKeyToContinue() {
        System.out.println();
        anim.pressAnyKeyAnim();
        Scanner s = new Scanner(System.in);
        s.nextLine();
        System.out.println(line);
    }
}