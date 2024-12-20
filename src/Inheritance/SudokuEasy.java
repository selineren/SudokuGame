package Inheritance;
//Java Sudoku Game
public class SudokuEasy extends SudokuGame {

    public SudokuEasy(String difficultyLevel) {
        super(difficultyLevel);//calls the constructor method of the superclass
    }

    @Override
    public void startGame() { //abstract class overriding 
        board.generateBoard(difficultyLevel);//Generates the board according to difficulty
    }
}
