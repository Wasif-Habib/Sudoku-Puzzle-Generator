# Sudoku-Puzzle-Generator
AP CSA With Experience Project
Wasif Habib

**SudokuGenerator**
A Java console program that generates and prints a fully solved, valid 9x9 Sudoku board.
**How to Run**

Download SudokuGenerator.java
Compile: javac SudokuGenerator.java
Run: java SudokuGenerator

**How the Board is Generated**
A base row of the numbers 1–9 is randomly shuffled, then each of the nine rows is derived from it using a band-shift pattern. Rows within the same band shift by 3; rows in different bands shift by 1. This guarantees every row, column, and 3x3 box contains each number exactly once so no backtracking needed.
Files

SudokuGenerator.java — main source file
