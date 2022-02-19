package com.sideproject.tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe {
    private Scanner userInput;
    private boolean isGameOver = false;
    private Map<String, String> gameValues;
    private int whoseTurn = 0;
    private String winningPlayer = "";
    int playerOneGamesWon = 0;
    int playerTwoGamesWon = 0;

    public TicTacToe() {
        this.userInput = new Scanner(System.in);
        gameValues = new HashMap<>();
    }

    public static void main(String[] args) {
        TicTacToe gameObject = new TicTacToe();
        System.out.println("Let's play Tic-Tac-Toe!");
        System.out.println();
        gameObject.runGames();
        gameObject.userInput.close();

    }

        /* pseudo code
         - give player 1 choice of X or O - set boolean
         - ask for player input where to go
         - test for valid input
         - test input to see if spot already has been taken
         - after successful input, check for victory
                function to test all 8 conditions return boolean
         - after victory test, check for tie(all filled)
                tie check can just check turn > 8
         - ask to play again
         - print series score
         */


    // Loops runSingleGame() in a loop asking if they want to play again
    public void runGames(){
        boolean keepPlaying = true;

        while (keepPlaying) {
            runSingleGame();
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Want to play again? (Y/N): ");
                String playAgainInput = userInput.nextLine();
                if (playAgainInput.equalsIgnoreCase("Y") || playAgainInput.equalsIgnoreCase("N")) {
                    if (playAgainInput.equalsIgnoreCase("N")) {
                        keepPlaying = false;
                    }
                    validInput = true;
                }
            }
        }
    }


    // Runs one game at a time.
    // asks for position till winner is found or board is filled after 9 turns
    public void runSingleGame(){
        gameValues = initializeGame();
        while(!isGameOver) {
            whoseTurn++;
            printBoard(gameValues);
            //System.out.println(gameValues);
            askPosition(gameValues);
            isGameOver = isWon();
            //System.out.println("whoseTurn: " + whoseTurn);
            System.out.println();
            if (whoseTurn >8){
                System.out.println("Tie Game");
                isGameOver = true;
            }
        }
        printBoard(gameValues);
        System.out.println();
        System.out.println("Player " +winningPlayer + " wins!");
        System.out.println();
        if (winningPlayer.equalsIgnoreCase("X")){
            playerOneGamesWon++;
        } else if (winningPlayer.equalsIgnoreCase("O")){
            playerTwoGamesWon++;
        }
        System.out.println("Series score");
        System.out.println("Player One: "+playerOneGamesWon);
        System.out.println("Player Two: "+playerTwoGamesWon);
        System.out.println();

    }


    // Prints board to screen, accepts current Map of values
    public void printBoard(Map<String,String> values){
        System.out.printf("    |   |   %n");
        System.out.printf("  %s | %s | %s %n", values.get("1"), values.get("2"), values.get("3"));
        System.out.printf("----+---+----%n");
        System.out.printf("  %s | %s | %s %n", values.get("4"), values.get("5"), values.get("6"));
        System.out.printf("----+---+----%n");
        System.out.printf("  %s | %s | %s %n", values.get("7"), values.get("8"), values.get("9"));
        System.out.printf("    |   |   %n");
    }

    // Initializes or resets game values.
    // Fills Map with number series 1-9 as a String, position 0 gets a dummy value so positions match key
    public Map<String, String> initializeGame(){
        Map<String, String> gameValues = new HashMap<>();
        gameValues.put("0", "X");
        for (int i = 1; i < 10; i++) {
            gameValues.put(String.valueOf(i),String.valueOf(i));
        }
        isGameOver = false;
        winningPlayer = "";
        whoseTurn = 0;
        return gameValues;
    }

    // asks user what square they want to make their mark in
    public void askPosition(Map<String, String> values){
        System.out.println("****************");
        boolean validEntry = false;
        while (!validEntry) {
            System.out.print("What square do you choose? (1-9): ");
            String userSquare = userInput.nextLine();
            if (values.containsKey(userSquare)) {
                if (!gameValues.get(userSquare).equalsIgnoreCase("X") && !gameValues.get(userSquare).equalsIgnoreCase("O")) {
                    if (whoseTurn % 2 == 0) {
                        values.put(userSquare, "O");
                    } else {
                        values.put(userSquare, "X");
                    }

                    validEntry = true;
                }
            }
        }
    }

    // called to check rows, columns, and diagonals for matching values.
    // Returns boolean true if matching is found and sets winningPlayer field to match winner;
    public boolean isWon() {
        if (isSameCheckThree("1", "2", "3")){
            winningPlayer = gameValues.get("1");
            return true;
        } else if (isSameCheckThree("4", "5", "6")){
            winningPlayer = gameValues.get("4");
            return true;
        } else if (isSameCheckThree("7", "8", "9")) {
            winningPlayer = gameValues.get("7");
            return true;
        } else if (isSameCheckThree("1", "4", "7")) {
            winningPlayer = gameValues.get("1");
            return true;
        } else if (isSameCheckThree("2", "5", "8")) {
            winningPlayer = gameValues.get("2");
            return true;
        } else if (isSameCheckThree("3", "6", "9")) {
            winningPlayer = gameValues.get("3");
            return true;
        } else if (isSameCheckThree("1", "5", "9")) {
            winningPlayer = gameValues.get("1");
            return true;
        } else if (isSameCheckThree("3", "5", "7")) {
            winningPlayer = gameValues.get("3");
            return true;
        }
        return false;
    }

    // sub method to isWon() for clarity.  Takes 3 strings to compare against each other, returns true if matches
    public boolean isSameCheckThree(String a, String b, String c) {
        return gameValues.get(a).equalsIgnoreCase(gameValues.get(b)) && gameValues.get(b).equalsIgnoreCase(gameValues.get(c));
    }
}

