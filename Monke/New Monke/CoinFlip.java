package Monke;

import java.util.Scanner;

public class CoinFlip {
    Scanner scanner = new Scanner(System.in);
    private String result = " ";
    private String userBet = " ";

    public String getResult() {
        return result;
    }

    public void setResult() {
        if (Main.random(1, 0) == 0) {
            result = "« HEADS »";
        } else {
            result = "« TAILS »";
        }
    }

    public String getUserBet() {
        return userBet;
    }

    public void setUserBet(String userBet) {
        switch (isValid(userBet)) {
            case "0" -> this.userBet = "« HEADS »"; //Obverse
            case "1" -> this.userBet = "« TAILS »"; //Reverse
        }
    }

    private String isValid(String bet) {
        do {
            switch (bet) {
                case "0", "1" -> { //← The options.
                    return bet;
                }
                default -> { //This shows when the user inputs something that is not on the options.
                    System.out.print("Invalid input! Choose only from 0 and 1: ");
                    bet = scanner.next();
                }
            }
        } while (true);
    }
}