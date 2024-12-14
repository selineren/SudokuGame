package Abstract;
//Java Sudoku Game
import java.util.ArrayList;

import Interface.GameActions;

public class SudokuBoard implements GameActions {//implements the interface and it's signature methods
    private int[][] board;
    private boolean[][] isInitialValue;  //Tracks cells that are pre-filled and cannot be changed.
    private ArrayList<int[]> moveHistory; // Records the player's moves to support the undo feature.

    public SudokuBoard() {
        this.board = new int[9][9];
        this.isInitialValue = new boolean[9][9];  
        this.moveHistory = new ArrayList<>();
    }

    @Override
    public void generateBoard(String difficulty) {
        // Pre-defined boards based on difficulty
        if (difficulty.equalsIgnoreCase("easy")) {
            board = new int[][]{
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
            };

           
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != 0) {
                        isInitialValue[i][j] = true;  //copies board to initial board
                    }
                }
            }
        } else if (difficulty.equalsIgnoreCase("classic")) {
            board = new int[][]{
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}
            };

            
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != 0) {
                        isInitialValue[i][j] = true;  //copies board to initial board
                    }
                }
            }
        }
        moveHistory.clear();  //there is no move yet
    }

    public void setCell(int row, int col, int value) {//updates the board
        if (isInitialValue[row][col]) {
            System.out.println("You cannot change the initial values of the board.");
            return;
        }
        moveHistory.add(new int[]{row, col, board[row][col]});//copies the move into history
        board[row][col] = value;
    }

    public boolean isValidMove(int row, int col, int value) {
        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == value) {
                return false; // Value already in row
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false; // Value already in column
            }
        }

        // Check 3x3 grid
        int gridRow = row / 3 * 3;
        int gridCol = col / 3 * 3;
        for (int i = gridRow; i < gridRow + 3; i++) {
            for (int j = gridCol; j < gridCol + 3; j++) {
                if (board[i][j] == value) {
                    return false; // Value already in 3x3 grid
                }
            }
        }

        return true; // Valid move
    }

    public boolean hasValidMoves() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int value = 1; value <= 9; value++) {
                        if (isValidMove(i, j, value)) {
                            return true; // There is a valid move
                        }
                    }
                }
            }
        }
        return false; // No valid moves left
    }

    public void undoLastMove() {
        if (!moveHistory.isEmpty()) {
            int[] lastMove = moveHistory.remove(moveHistory.size() - 1);
            board[lastMove[0]][lastMove[1]] = lastMove[2];
        }
    }

    public boolean isBoardFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    public String display() {
    	String out = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    out += ". ";
                } else {
                    out += board[i][j] + " ";
                }
                if (j == 2 || j == 5) 
                	out += "| ";
            }
            out+="\n";
            if (i == 2 || i == 5) 
            	out+= "------+-------+------\n";
        }
        return out;
    }

    public void solveAndDisplaySolution() {
        // Simple backtracking solver (just for display purposes)
        if (solve()) {
            System.out.println("Solved Sudoku Board:");
            
        } else {
            System.out.println("No solution exists.");
        }
        System.out.println(display());
    }

    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {  // Empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValidMove(row, col, num)) {
                            board[row][col] = num;
                            if (solve()) {
                                return true;
                            }
                            board[row][col] = 0;  // Backtrack
                        }
                    }
                    return false;  // No valid number can be placed here
                }
            }
        }
        return true;  // Solved!
    }

}
