package Has;
//Java Sudoku Game
import java.util.ArrayList;

import Interface.GameActions;

public class SudokuBoard implements GameActions { // Implements the interface and its signature methods
    private int[][] board;
    private boolean[][] isInitialValue;  // Tracks cells that are pre-filled and cannot be changed
    private ArrayList<int[]> moveHistory; // Records the player's moves to support the undo feature
    private boolean[][] isEditable; // Tracks cells that are editable

    public SudokuBoard() {
        this.board = new int[9][9];
        this.isInitialValue = new boolean[9][9];  
        this.moveHistory = new ArrayList<>();
        this.isEditable = new boolean[9][9];
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
        }
        markEditableCells(); // Update isEditable
        moveHistory.clear();  // Clear the move history at the beginning of a new game
    }

    public void markEditableCells() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    isEditable[i][j] = true; // Editable if the cell is empty
                } else {
                    isEditable[i][j] = false; // Not editable if pre-filled
                }
            }
        }
    }

    public void setCell(int row, int col, int value) { // Updates the board
        if (!isEditable[row][col]) { // Check if the cell is editable
            System.out.println("You cannot change the initial values of the board.");
            return;
        }
        moveHistory.add(new int[]{row, col, board[row][col]}); // Add move to history
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

    public boolean isBoardFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    public String display() {
        String boardString = ""; // Use a simple String to accumulate the result
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    boardString += ". "; // Empty cells represented as "."
                } else {
                    boardString += board[i][j] + " ";
                }
                if (j == 2 || j == 5) {
                    boardString += "| "; // Vertical grid lines
                }
            }
            boardString += "\n";
            if (i == 2 || i == 5) {
                boardString += "------+-------+------\n"; // Horizontal grid lines
            }
        }
        return boardString;
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
                if (board[row][col] == 0) { // Empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValidMove(row, col, num)) {
                            board[row][col] = num; // Place the number
                            if (solve()) {
                                return true; // Continue solving
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number can be placed here
                }
            }
        }
        return true; // Solved
    }


    public int[][] getBoard() {
        return board;
    }

    public boolean[][] getEditable() {
        return isEditable;
    }
}
