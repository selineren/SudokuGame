package Inheritance;

import Abstract.SudokuBoard;

//Java Sudoku Game
public abstract class SudokuGame {
    private static int totalGamesPlayed = 0;
    private static int totalWins = 0;
    protected String difficultyLevel;
    protected SudokuBoard board;
  
    
    public SudokuGame(String difficultyLevel) {//constructor method
        this.difficultyLevel = difficultyLevel;
        this.board = new SudokuBoard();//
        totalGamesPlayed++;//increases by 1 each time a game is created
    }

    public static void incrementWins() {
        totalWins++;//Increases by 1 each time a game is finished with valid moves
    }

    public static void displayStats() {
    	//shows how many game is played and there are how many wins 
    	
        System.out.println("Total Games Played: " + totalGamesPlayed);
        System.out.println("Total Wins: " + totalWins);
    }
    
    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    
   
    public boolean playMove(int row, int col, int value) {
        if (board.isValidMove(row, col, value)) {//if move is valid board will be updated
            board.setCell(row, col, value);
            return true;
        }
        return false;
    }

    public abstract void startGame();//abstract method of abstract class

    public String displayBoard() {
        return board.display(); //current version of the board
    }

    public boolean isSolved() {
    	boolean result = board.isBoardFull();
        if(result) {
        	incrementWins();
        }
        return result;
    }

    public boolean hasMovesLeft() {
        return board.hasValidMoves(); //shows is there any valid move left
    }

    public void undoMove() {
        board.undoLastMove();//takes a step back 
    }

    public void resetGame() {
        board.generateBoard(difficultyLevel);
    }

    public void displaySolution() {
        board.solveAndDisplaySolution();
    }


}
