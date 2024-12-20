package GUI;

import javax.swing.*;

import Inheritance.SudokuGame;
import SystemAndMain.SudokuSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayFrame extends JFrame {

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnDeleteLast, btnDisplayAll, btnSearchEasy;

    GameFrame gf = null;

    public DisplayFrame(GameFrame gameFrame) {
        gf = gameFrame;

        setTitle("Display/Search/Delete");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null); // Absolute positioning
        setContentPane(contentPane);

        // Text area to display results
        textArea = new JTextArea();
        textArea.setEditable(false); // Read-only
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 440, 200);
        contentPane.add(scrollPane);

        // Button to delete the last played game
        btnDeleteLast = new JButton("Delete Last Played Game");
        btnDeleteLast.setBounds(20, 240, 200, 30);
        contentPane.add(btnDeleteLast);

        // Button to display all boards played
        btnDisplayAll = new JButton("Display All Boards");
        btnDisplayAll.setBounds(240, 240, 200, 30);
        contentPane.add(btnDisplayAll);

        // Button to search for easy games
        btnSearchEasy = new JButton("Search for Easy Games");
        btnSearchEasy.setBounds(130, 281, 200, 30);
        contentPane.add(btnSearchEasy);

        // Back button
        JButton btnBack = new JButton("Go Back to Games");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gf.setVisible(true);
                dispose();
            }
        });
        btnBack.setBounds(304, 331, 174, 23);
        contentPane.add(btnBack);

        // Action for "Delete Last Played Game"
        btnDeleteLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLastGame();
            }
        });

        // Action for "Display All Boards"
        btnDisplayAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllBoards();
            }
        });

        // Action for "Search for Easy Games"
        btnSearchEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEasyGames();
            }
        });
    }

    // Deletes the last played game
    private void deleteLastGame() {
        ArrayList<SudokuGame> games = SudokuSystem.getGames();
        if (!games.isEmpty()) {
            SudokuGame lastGame = games.get(games.size() - 1);
            SudokuSystem.deleteGame(lastGame);
            textArea.setText("Last played game deleted.");
        } else {
            textArea.setText("No games to delete.");
        }
    }

    // Displays all boards that have been played
    private void displayAllBoards() {
        ArrayList<SudokuGame> games = SudokuSystem.getGames();
        if (games.isEmpty()) {
            textArea.setText("No games to display.");
        } else {
            String boards = "";
            int gameCount = 1;
            for (SudokuGame game : games) {
                boards += "Game " + gameCount++ + ":\n" + game.displayBoard() + "\n";
            }
            textArea.setText(boards);
        }
    }
    
    // Searches and displays all games with "Easy" difficulty
    private void searchEasyGames() {
        ArrayList<SudokuGame> easyGames = SudokuSystem.searchGamesByDifficulty("easy");
        if (easyGames.isEmpty()) {
            textArea.setText("No Easy games found.");
        } else {
            String easyBoards = "Easy Games Found:\n";
           ArrayList<SudokuGame> arr=new ArrayList<>();
          arr=SudokuSystem.getGames();
          
         for(SudokuGame g: arr) {
        	 for (SudokuGame game : easyGames) {
        		if( g.equals(game)) {
        			int i=arr.indexOf(g)+1;
        			 easyBoards += "Game " + i + ":\n" + game.displayBoard() + "\n";
        		}
        	 }
         }
         textArea.setText(easyBoards);
           
        }
    }
}
