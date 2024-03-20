package efs.task.syntax;

import java.util.Scanner;

import static efs.task.syntax.UsefulConstants.*;

public class GuessNumberGame {

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private final int numberToGuess;
    private final int maxAttempts;
    private int usedAttempts = 0;
    private int M = 0;
    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        try {
            int M = Integer.parseInt(argument);
            if(M < 1 || M > MAX_UPPER_BOUND){
                System.out.println(WRONG_ARGUMENT);
                throw new IllegalArgumentException(WRONG_ARGUMENT);
            }

            System.out.println("<1," + M + ">");
            numberToGuess = (int) (Math.random() * M) + 1;
            maxAttempts = (int) (Math.log(M) / Math.log(2)) + 1;

        } catch (NumberFormatException ex){
            System.out.println(WRONG_ARGUMENT);
            throw new IllegalArgumentException(WRONG_ARGUMENT);
        }

    }

    private void showProgressBar(){
        System.out.print("[");
        System.out.print("*".repeat(usedAttempts));
        System.out.print(".".repeat(maxAttempts - usedAttempts));
        System.out.println("]");
    }

    public void play() {
        //TODO: Implement the method that executes the game session
        Scanner scanner = new Scanner(System.in);
        String txt;
        int guess;

        while(usedAttempts < maxAttempts){
            usedAttempts++;
            showProgressBar();
            System.out.println(GIVE_ME);
            txt = scanner.nextLine();

            try{
                guess = Integer.parseInt(txt);
            } catch (NumberFormatException ex){
                System.out.println(NOT_A_NUMBER);
                continue;
            }

            if (guess > numberToGuess){
                System.out.println(TO_MUCH);
            } else if (guess < numberToGuess) {
                System.out.println(TO_LESS);
            } else {
                System.out.println(YES);
                System.out.println(CONGRATULATIONS);
                return;
            }
        }

        System.out.println(UNFORTUNATELY);

    }
}
