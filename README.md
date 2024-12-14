# Sudoku Game

This is a Java-based Sudoku Game project implemented as part of the CTIS221 - Object-Oriented Programming course. The project demonstrates the principles of object-oriented design, such as inheritance, abstraction, encapsulation, and polymorphism, along with practical Java coding skills.

## Features

- **Playable Sudoku Board:** Allows users to play Sudoku with options for Easy and Classic difficulties.
- **User Interaction:** Users can make moves, undo moves, reset the board, and view the solution.
- **Game Validation:** Validates moves and ensures initial values cannot be changed.
- **Stats Tracking:** Tracks the total games played and the number of wins.
- **Object-Oriented Design:**
  - Inheritance: Classes for `SudokuEasy` and `SudokuClassic` extend the `SudokuGame` class.
  - Interface: Implements the `GameActions` interface for game actions.
  - Encapsulation: Uses private and protected variables for controlled access.
- **Dynamic Gameplay:** The system prevents overwriting initial values and ensures the game is logical.

## Project Structure

The project is divided into several Java classes, each with its specific responsibilities:

- **Abstract Class:**
  - `SudokuGame`: Serves as the base class for all Sudoku games.
- **Inheritance:**
  - `SudokuEasy`: Represents the easy mode of Sudoku.
  - `SudokuClassic`: Represents the classic mode of Sudoku.
- **Interface:**
  - `GameActions`: Defines essential methods for the game, such as `generateBoard()`, `resetGame()`, and `displayBoard()`.
- **System and Main:**
  - `SudokuSystem`: Handles game management, including tracking games and stats.
  - `SudokuMain`: Provides a console-based user interface for interacting with the game.
- **Utilities:**
  - `SudokuBoard`: Manages the board and its state.

## How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/selineren/SudokuGame.git

## Contributors

- **Selin Eren**
- **Şevval Türkan Gazel**
- **Sıla Sevgi Arı**