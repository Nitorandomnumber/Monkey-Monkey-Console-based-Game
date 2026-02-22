package javaapplication2;

import org.fusesource.jansi.AnsiConsole;

public class Goblin {
    public static void main(String[] args) throws InterruptedException {
        AnsiConsole.systemInstall();

        String[][] frames = {
            {
                                            "                                 ██████    ██████  ████████   ██         ██   ████    ██ ",
                                            "                                ██        ██    ██ ██      ██ ██         ██   ██ ██   ██ ",
                                            "                                ██ ████   ██ ██ ██ ██   ████  ██         ██   ██   ██ ██ ",
                                            "                                ██    ██  ██    ██ ██      ██ ██         ██   ██    ████ ",
                                            "                                 ██████     ████   ████████   ████████   ██   ██     ███ "
                        },
            {
                            "                        ██████  ███████  ████████  ████████ ██████   ██████  ██    ██ ███████ ██████  ",
                            "                        ██   ██ ██       ██           ██    ██   ██ ██    ██ ████████ ██      ██   ██ ",
                            "                        ██   ██ █████    ████████     ██    ██████  ██    ██    ██    █████   ██████  ",
                            "                        ██   ██ ██             ██     ██    ██   ██ ██    ██    ██    ██      ██   ██ ",
                            "                        ██████  ███████  ████████     ██    ██   ██  ██████    ████   ███████ ██   ██ "
            },
            {
                                                            "                                        ██████  ██████   ██████  ",
                                                            "                                        ██   ██ ██   ██ ██       ",
                                                            "                                        ██████  ██████  ██   ███ ",
                                                            "                                        ██   ██ ██      ██    ██ ",
                                                            "                                        ██   ██ ██       ██████  "
                                                        }
        };

        for (String[] frame : frames) {
            clearConsole();
            dissolveFrame(frame, "\u001B[32m");
        }

             clearConsole();
        System.out.println("\u001B[32m");
        System.out.println("                         ██████      ██████   ██████    ██        ██████  ████    ██");
        System.out.println("                        ██          ██    ██  ██    ██  ██          ██    ██ ██   ██");
        System.out.println("                        ██     ███ ██      ██ ██ ████   ██          ██    ██  ██  ██");
        System.out.println("                        ██       ██ ██    ██  ██    ██  ██          ██    ██   ██ ██");
        System.out.println("                         ████████    ██████   ██████    ████████  ██████  ██    ████ ");
        System.out.println();
        System.out.println("           ██████  ███████ ████████ ██████████  ████████      █████     ██    ██ ████████   ██████   ");
        System.out.println("           ██   ██ ██      ██           ██      ██      ██   ██    ██    ██  ██  ██         ██    ██ ");
        System.out.println("           ██   ██ █████   ████████     ██      ████████    ██      ██     ██    ██████     ██████   "); 
        System.out.println("           ██   ██ ██            ██     ██      ██      ██   ██    ██      ██    ██         ██   ██  ");
        System.out.println("           ██████  ███████ ████████    ████     ██      ██    ██████       ██    ████████   ██    ██ ");
        System.out.println();
        System.out.println("                                              ██████  ██████   ██████  ");
        System.out.println("                                              ██   ██ ██   ██ ██       ");
        System.out.println("                                              ██████  ██████  ██   ███ ");
        System.out.println("                                              ██   ██ ██      ██    ██ ");
        System.out.println("                                              ██   ██ ██       ██████  ");
        System.out.println("\u001B[0m"); 


        AnsiConsole.systemUninstall();
    }

    public static void dissolveFrame(String[] text, String color) throws InterruptedException {
        int maxLength = 0;
        for (String line : text) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }

        for (int step = 0; step <= maxLength; step++) {
            clearConsole();
            for (String line : text) {
                int length = Math.min(step, line.length());
                System.out.println(color + line.substring(0, length) + "\u001B[0m");
            }
            Thread.sleep(30);
        }
        Thread.sleep(500);
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}