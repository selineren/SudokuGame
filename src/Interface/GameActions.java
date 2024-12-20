package Interface;
//Java Sudoku Game
public interface GameActions {
    public void generateBoard(String difficulty);
    public void setCell(int row, int col, int value);
    public boolean isValidMove(int row, int col, int value);
    public boolean isBoardFull();
    public boolean hasValidMoves();
    public String display();
}
