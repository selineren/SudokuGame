package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Inheritance.*;
import SystemAndMain.SudokuSystem;

public class GameFrame extends JFrame {
    private SudokuGame currentGame;
    private JTextField[][] cells;
    private JLabel messageLabel;
    private JComboBox<String> difficultyComboBox;

    DisplayFrame df = new DisplayFrame(this);
    StatsFrame st = new StatsFrame(this);

    public GameFrame() {
        setTitle("Sudoku Game");
        setSize(561, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Top panel for difficulty selection
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Difficulty:"));

        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Easy", "Classic"}));
        topPanel.add(difficultyComboBox);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());
        topPanel.add(startButton);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        JButton btnStats = new JButton("Show Stats");
        btnStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				st.setVisible(true);
				st.updateStats();	
			}
        });
        topPanel.add(btnStats);
        
        JButton btnDisplaySearchDelete = new JButton("Display/Search/Delete");
        btnDisplaySearchDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		df.setVisible(true);
        		dispose();
        	}
        });
        topPanel.add(btnDisplaySearchDelete);

        // Board panel for displaying the Sudoku grid
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        cells = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
            	final int row = i; // Final variable for the current row
                final int col = j;
                
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setEditable(false); // Initially set all cells to non-editable
                cells[i][j].addKeyListener(new CellKeyListener(row, col));
                
                cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!currentGame.getBoardEditable()[row][col]) {
                            messageLabel.setText("This cell cannot be edited.");
                        }
                    }
                });

                
                boardPanel.add(cells[i][j]);
            }
        }
        getContentPane().add(boardPanel, BorderLayout.CENTER);

        // Bottom panel for messages and actions
        JPanel bottomPanel = new JPanel();
        messageLabel = new JLabel("Welcome to Sudoku!");
        bottomPanel.add(messageLabel);

        JButton solutionButton = new JButton("Show Solution");
        solutionButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showSolution();
        	}
        });
        bottomPanel.add(solutionButton);

        JButton resetButton = new JButton("Reset Game");
        resetButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		resetGame();
        	}
        });
        bottomPanel.add(resetButton);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    // Start a new game
    private void startGame() {
        String difficulty = (String) difficultyComboBox.getSelectedItem();
        if ("Easy".equalsIgnoreCase(difficulty)) {
            currentGame = new SudokuEasy("easy");
        } else {
            currentGame = new SudokuClassic("classic");
        }
        currentGame.startGame();
        SudokuSystem.addGame(currentGame); // Add the game to the system
        updateBoard();
        messageLabel.setText("Game started: " + difficulty + " mode.");
    }


    // Update the board based on current game state
    private void updateBoard() {
        int[][] board = currentGame.getBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) {
                    cells[i][j].setText(String.valueOf(board[i][j]));
                    cells[i][j].setEditable(false); // Lock pre-filled cells
                } else {
                    cells[i][j].setText("");
                    cells[i][j].setEditable(true); // Enable only empty cells
                }
            }
        }
    }

    // Show the solution 
    private void showSolution() {
        if (currentGame.solve()) { // Solve the board
            updateBoard(); // Update GUI with solved board
            SudokuSystem.incrementLosses(); // Count this as a loss
            messageLabel.setText("Solution displayed! Counted as a loss.");
        } else {
        	updateBoard();
        	SudokuSystem.incrementLosses();
            messageLabel.setText("No solution exists for the current state! Lost!");
        }
    }

    // Reset the game
    private void resetGame() {
        currentGame.resetGame();
        updateBoard();
        messageLabel.setText("Game reset.");
    }

    // KeyListener for handling cell input
    private class CellKeyListener extends KeyAdapter {
        private int row, col;

        public CellKeyListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            JTextField source = (JTextField) e.getSource();
            String input = source.getText().trim();

            if (input.isEmpty()) {
                currentGame.setCell(row, col, 0);
                messageLabel.setText("Cell cleared. Enter a new value.");
                return;
            }

            try {
                int value = Integer.parseInt(input);
                if (value >= 1 && value <= 9) {
                    if (!currentGame.playMove(row, col, value)) {
                        source.setText("");
                        messageLabel.setText("Invalid move at (" + (row + 1) + ", " + (col + 1) + ")");
                    } else {
                        messageLabel.setText("Move accepted!");
                    }
                } else {
                    source.setText("");
                    messageLabel.setText("Enter a number between 1 and 9.");
                }
            } catch (NumberFormatException ex) {
                source.setText("");
                messageLabel.setText("Invalid. Enter a number between 1 and 9.");
            }

            if (currentGame.isSolved()) {
                messageLabel.setText("Congratulations! You solved the puzzle!");
            }
        }
    }
}
