package Inheritance;

import Has.SudokuBoard;

//Java Sudoku Game
public abstract class SudokuGame {
    private static int totalGamesPlayed = 0;
    private static int totalWins = 0;
    protected String difficultyLevel;
    protected SudokuBoard board;
    private boolean isLost; // Tracks if the game is marked as lost
    private boolean isSolved;
  
    
    public SudokuGame(String difficultyLevel) {//constructor method
        this.difficultyLevel = difficultyLevel;
        this.board = new SudokuBoard();//
        totalGamesPlayed++;//increases by 1 each time a game is created
        this.isLost = false; // Initialize as not lost
        this.isSolved = false; 
    }

    public static void incrementWins() {
        totalWins++;//Increases by 1 each time a game is finished with valid moves
    }
    
    public static int getTotalWins() {
		return totalWins;
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
    	int[][] theBoard = board.getBoard();
        for (int i = 0; i < theBoard.length; i++) {
            for (int j = 0; j < theBoard[i].length; j++) {
                if (theBoard[i][j] == 0 || !board.isValidMove(i, j, theBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setLoss() {
        this.isLost = true; // Mark the game as lost
    }

    public boolean isLost() {
        return isLost;
    }
    
    public boolean hasMovesLeft() {
        return board.hasValidMoves(); //shows is there any valid move left
    }

    public void resetGame() {
        board.generateBoard(difficultyLevel);
    }

    public void displaySolution() {
        board.solveAndDisplaySolution();
    }

	public int[][] getBoard() {
		return board.getBoard();
	}

	public void setCell(int row, int col, int i) {
		board.setCell(row, col, i);
	}

	public boolean solve() {
		return board.solve();
	}

	public boolean[][] getBoardEditable() {
	    return board.getEditable(); // Delegate to the SudokuBoard class
	}

}
