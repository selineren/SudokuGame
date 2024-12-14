package SystemAndMain;
//Java Sudoku Game
//CTIS 221-Object Oriented Programming Fall 2024-2025
//3rd Submission
//Group Members: 
//Selin Eren 22203295 
//Sevval Turkan Gazel 22202058 
//Sila Sevgi Ari 22103435
import java.util.ArrayList;
import java.util.Scanner;

import Inheritance.SudokuClassic;
import Inheritance.SudokuEasy;
import Inheritance.SudokuGame;

public class SudokuMain {
    private static SudokuGame sudokuGame;  
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	 boolean showStatsSelected = false; 
        while (true) {
            System.out.println("1. Play Game"); //starts a new game 
            System.out.println("2. Show Stats"); //shows previous games status: count of total games and count of total wins
            System.out.println("3. Exit"); //exit program
            System.out.println("4. Delete the last played game");
            System.out.println("5. Display all the boards played");
            System.out.println("6. Search for Easy Games");
            System.out.println("7. Show Win/Loss Ratio");
            System.out.print("Enter your choice (1/2/3/4/5/6/7): "); //gets input from user to choice
            
            int choice = scanner.nextInt();  
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                	showStatsSelected = false; 
                    System.out.println("Select difficulty (easy/classic): "); //it takes the difficulty level from the user and decides which child class will run 
                    String difficulty = scanner.nextLine(); 

                    
                    if (difficulty.equalsIgnoreCase("easy")) {
                        sudokuGame = new SudokuEasy(difficulty); //if easy is chosen run SudokuEasy subclass
                    } else if (difficulty.equalsIgnoreCase("classic")) {
                        sudokuGame = new SudokuClassic(difficulty); //if classic is chosen run SudokuClassic subclass
                    } else {
                        System.out.println("Invalid difficulty level. Please choose 'easy' or 'classic'.\n");
                        break; 
                    }
                   
                    if (sudokuGame != null) {
                        sudokuGame.startGame(); 
                    }
                   
                    break;

                case 2:
                	
                	showStatsSelected = true;
                    SudokuGame.displayStats();
                    System.out.println();
                    
                    break;

                case 3:
                   
                    System.out.println("Exiting game...\n");
                    
                    return;
                    
                case 4:
                	
                	if(sudokuGame != null) {
                		SudokuSystem.deleteGame(sudokuGame);
                	} else {
                		System.out.println("You haven't started a game.\n");
                	}
                	
                	break;
                	
                case 5:
                	
                	if(SudokuSystem.displayBoards()== "") {
                		System.out.println("You haven't started any game.\n");
                	} else {
                		System.out.println(SudokuSystem.displayBoards());
                	}
                	
                	break;
                	
                case 6:
                    ArrayList<SudokuGame> easyGames = SudokuSystem.searchGamesByDifficulty("easy");
                    System.out.println("Easy Games Found: " + easyGames.size());

                    if (easyGames.isEmpty()) {
                        System.out.println("No easy games found.\n");
                    } else {
                        for (SudokuGame game : easyGames) {
                            System.out.println("Game: \n" + game.displayBoard());
                        }
                    }
                    break;
                	
                case 7: //calculation method added
                    double winLossRatio = SudokuSystem.calculateWinLossRatio();
                    System.out.println("Win/Loss Ratio: " + winLossRatio);
                    break;
                default:
                  
                    System.out.println("Invalid choice. Please try again.");
            }
            if (showStatsSelected) {
                showStatsSelected = false; 
                continue;
            }
            
            if (sudokuGame != null) {
                System.out.println(sudokuGame.displayBoard());
                
               
                while (!sudokuGame.isSolved()) {
                    System.out.println("Enter row, col, value (or 'undo', 'reset', 'solution', 'exit'):");
                    String moveInput = scanner.nextLine(); // Read the input

                    if (moveInput.equalsIgnoreCase("exit")) {
                    	 if (!sudokuGame.isSolved()) {
                    		 SudokuSystem.addGame(sudokuGame);
                    	 }else {
                    		 SudokuSystem.addGame(sudokuGame);
                    	 }
                        System.out.println("Exiting current game...");
                        break;  
                    } else if (moveInput.equalsIgnoreCase("undo")) {
                        sudokuGame.undoMove();
                        System.out.println("Last move undone.");
                        System.out.println(sudokuGame.displayBoard());
                    } else if (moveInput.equalsIgnoreCase("reset")) {
                        sudokuGame.resetGame();
                        System.out.println("Game reset.");
                        System.out.println(sudokuGame.displayBoard());
                    } else if (moveInput.equalsIgnoreCase("solution")) {
                        sudokuGame.displaySolution();
                    } else {
                       
                        try {
                            String[] inputs = moveInput.split(" ");
                            int row = Integer.parseInt(inputs[0]) - 1;
                            int col = Integer.parseInt(inputs[1]) - 1;
                            int value = Integer.parseInt(inputs[2]);

                            if (sudokuGame.playMove(row, col, value)) {
                                System.out.println("Move accepted.");
                                System.out.println(sudokuGame.displayBoard());
                            } else {
                                System.out.println("Invalid move. Try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter row, col, value correctly.");
                        }
                    }

                   
                    
                }
            }
        }
    }
}
