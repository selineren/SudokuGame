package SystemAndMain;
//Java Sudoku Game
import java.util.ArrayList;

import Inheritance.SudokuClassic;
import Inheritance.SudokuEasy;
import Inheritance.SudokuGame;

public class SudokuSystem {
	//there is a has-a relationship with SudokuGame class
	//SudokuSystem can have a one or more SudokuGame object
    private static ArrayList<SudokuGame> games = new ArrayList<>();//it creates array to store games that are played
    private static ArrayList<SudokuGame> result = new ArrayList<>();
    
    public static ArrayList<SudokuGame> searchGamesByDifficulty(String difficulty) {  
    for (SudokuGame game : games) {
        if (game.getDifficultyLevel().equalsIgnoreCase(difficulty)) { 
            result.add(game);
        }
    }
    return result; // Returns the list of games matching the difficulty
}



    public static SudokuGame startGame(String difficulty) {
        SudokuGame game;
        if (difficulty.equals("easy")) {
            game = new SudokuEasy(difficulty);
        } else {
            game = new SudokuClassic(difficulty);
        }
        return game;//creates a game and add into array
    }

    public static void deleteGame(SudokuGame game) {
        games.remove(game);//remove game from array 
    }
    
    public static void addGame(SudokuGame game) {
    	games.add(game);
    }
    
    public static String displayBoards() {
    	String out = "";
    	for(SudokuGame board : games) {
    		out += board.displayBoard() + "\n";
    	}
    	return out;
    }
    
    public static double calculateWinLossRatio() {
        int wins = 0;
        int losses = 0;

        for (SudokuGame game : games) {
            if (game.isSolved()) { // Check if the game was won
                wins++;
            } else {
                losses++;
            }
        }

        if (losses == 0) {
            return wins; // If no losses, ratio is the number of wins
        }

        return wins / losses; // Calculate and return the win/loss ratio
    }
    
    
}
