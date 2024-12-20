package GUI;

import javax.swing.*;
import SystemAndMain.SudokuSystem;

public class StatsFrame extends JFrame {

    private JPanel contentPane;
    private JLabel lblTotalGames;
    private JLabel lblWins;
    private JLabel lblLosses;
    private JLabel lblWinLossRatio;

    public StatsFrame(GameFrame gameFrame) {
        // Frame settings
        setTitle("Game Stats");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame without exiting the app
        setBounds(100, 100, 400, 300);

        contentPane = new JPanel();
        contentPane.setLayout(null); // Absolute positioning
        setContentPane(contentPane);

        // Label for "Total Games Played"
        lblTotalGames = new JLabel("Total Games Played: ");
        lblTotalGames.setBounds(30, 30, 300, 30); // X, Y, Width, Height
        contentPane.add(lblTotalGames);

        // Label for "Total Wins"
        lblWins = new JLabel("Total Wins: ");
        lblWins.setBounds(30, 70, 300, 30); // X, Y, Width, Height
        contentPane.add(lblWins);

        // Label for "Total Losses"
        lblLosses = new JLabel("Total Losses: ");
        lblLosses.setBounds(30, 110, 300, 30); // X, Y, Width, Height
        contentPane.add(lblLosses);

        // Label for "Win/Loss Ratio"
        lblWinLossRatio = new JLabel("Win/Loss Ratio: ");
        lblWinLossRatio.setBounds(30, 150, 300, 30); // X, Y, Width, Height
        contentPane.add(lblWinLossRatio);

        // Fetch and display stats
        updateStats();
    }

    public void updateStats() {
        int totalGamesPlayed = SudokuSystem.getTotalGames();
        int totalWins = SudokuSystem.getTotalWins();
        int totalLosses = SudokuSystem.getTotalLosses();
        double winLossRatio = (totalLosses == 0) ? totalWins : (double) totalWins / totalLosses;

        lblTotalGames.setText("Total Games Played: " + totalGamesPlayed);
        lblWins.setText("Total Wins: " + totalWins);
        lblLosses.setText("Total Losses: " + totalLosses);
        lblWinLossRatio.setText("Win/Loss Ratio: " + winLossRatio);
    }

}
