import java.util.ArrayList;
import java.util.Collections;
 
/**
 * SudokuGenerator
 *
 * This code is written to generate and print a fully solved, valid 9x9 Sudoku board
 *
 * Strategy:
 *   1. Build a valid base row: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 *   2. Shuffle it randomly to get a unique starting row each time it runs
 *   3. Derive all 9 rows by shifting the base row using the classic
 *      Sudoku band-shift pattern:
 *        - Within a band (rows 0-2, 3-5, 6-8): shift by 3 each row
 *        - Between bands: shift by 1 each band.
 *      This guarantees every row, column, and 3x3 box is valid and allows for shifting
 *      around the board while still adhering to the rules of Sudoku
 */
public class SudokuGenerator {
 
    // The 9x9 board stored as a 2D array
    public static int[][] board = new int[9][9];
 
    // This stores the base row as an ArrayList
    public static ArrayList<Integer> baseRow = new ArrayList<>();
 
    public static void main(String[] args) {
        buildBaseRow();
        shuffleBaseRow();
        fillBoard();
 
        System.out.println("=== Generated Sudoku Solution ===\n");
        printBoard();
        System.out.println("\nBoard is valid: " + isBoardValid());
    }
 
    /**
     * Fills baseRow with the numbers 1 through 9
     */
    public static void buildBaseRow() {
        for (int digit = 1; digit <= 9; digit++) {
            baseRow.add(digit);
        }
    }
 
    /**
     * Randomly shuffles the baseRow so each run produces a unique board
     */
    public static void shuffleBaseRow() {
        Collections.shuffle(baseRow);
    }
 
    /**
     * Fills the 9x9 board using the band-shift pattern derived from baseRow
     *
     * For row r:
     *   band      = r / 3          (which group of 3 rows we are in: 0, 1, or 2)
     *   rowInBand = r % 3          (position within that band: 0, 1, or 2)
     *   shiftAmount = band + rowInBand * 3
     *
     * Each cell's value = baseRow[(col + shiftAmount) % 9]
     */
    public static void fillBoard() {
        for (int row = 0; row < 9; row++) {
            int band      = row / 3;
            int rowInBand = row % 3;
            int shiftAmount = band + rowInBand * 3;
 
            for (int col = 0; col < 9; col++) {
                int sourceIndex = (col + shiftAmount) % 9;
                board[row][col] = baseRow.get(sourceIndex);
            }
        }
    }
 
    /**
     * Prints the board with grid lines separating the 3x3 boxes
     */
    public static void printBoard() {
        String horizontalDivider = "+-------+-------+-------+";
 
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0) {
                System.out.println(horizontalDivider);
            }
 
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");
            }
 
            System.out.println("|");
        }
 
        System.out.println(horizontalDivider);
    }
 
    /**
     * Validates the completed board by checking every row, column,
     * and 3x3 box for the numbers 1-9 with no repeats
     *
     * @return true if the board is a valid Sudoku solution
     */
    public static boolean isBoardValid() {
        return rowsAreValid() && columnsAreValid() && boxesAreValid();
    }
 
    /**
     * Checks that each row contains digits 1-9 exactly once
     */
    public static boolean rowsAreValid() {
        for (int row = 0; row < 9; row++) {
            boolean[] seen = new boolean[10]; // index 1-9
            for (int col = 0; col < 9; col++) {
                int value = board[row][col];
                if (seen[value]) return false;
                seen[value] = true;
            }
        }
        return true;
    }
 
    /**
     * Checks that each column contains digits 1-9 exactly once
     */
    public static boolean columnsAreValid() {
        for (int col = 0; col < 9; col++) {
            boolean[] seen = new boolean[10];
            for (int row = 0; row < 9; row++) {
                int value = board[row][col];
                if (seen[value]) return false;
                seen[value] = true;
            }
        }
        return true;
    }
 
    /**
     * Checks that each 3x3 box contains digits 1-9 exactly once
     * boxRow and boxCol identify which box (0, 1, or 2 each)
     */
    public static boolean boxesAreValid() {
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                boolean[] seen = new boolean[10];
 
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        int value = board[boxRow * 3 + r][boxCol * 3 + c];
                        if (seen[value]) return false;
                        seen[value] = true;
                    }
                }
            }
        }
        return true;
    }
}
