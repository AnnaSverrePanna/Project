import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class Testfall {
    @Test
    public void testSolveEmptySudoku() {
        SudokuSolver sudoku = new SudokuModel();
        assertTrue(sudoku.solve());
    }

    @Test
    public void testSolveUnsolvableSudokuDigitsNotValid() {
        SudokuSolver sudoku = new SudokuModel();
        // Testa rad
        sudoku.set(0,0,5);
        sudoku.set(0,7,5);
        assertFalse(sudoku.solve());
        sudoku.clearAll();

        // Testa kolumn
        sudoku.set(0,0,5);
        sudoku.set(7,0,5);
        assertFalse(sudoku.solve());
        sudoku.clearAll();

        // Testa region
        sudoku.set(0,0,5);
        sudoku.set(1,1,5);
        assertFalse(sudoku.solve());
    }

    @Test
    public void testSolveUnsolvableSudokuByRemovingADigit() {
        SudokuSolver sudoku = new SudokuModel();
        int[][] grid = {
                {1, 2, 3, 0, 0, 0, 0, 0, 0},
                {4, 5, 6, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 7, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        sudoku.setGrid(grid);
        assertFalse(sudoku.solve());
        sudoku.clear(2,3);
        assertTrue(sudoku.solve());
    }

    @Test
    public void testClearSudoku() {
        SudokuSolver sudoku = new SudokuModel();
        sudoku.set(0,0, 5);
        sudoku.set(0,4, 5);
        boolean sudokuRes = sudoku.solve();
        assertFalse(sudokuRes);
        sudoku.clearAll();
        assertTrue(sudoku.solve());
    }

    @Test
    public void testSolveSolvableSudoku() {
        SudokuSolver sudoku = new SudokuModel();
        int[][] grid = {
                {0, 0, 8, 0, 0, 9, 0, 6, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {1, 0, 2, 5, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 9, 0},
                {0, 5, 0, 0, 0, 0, 6, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 2, 8},
                {4, 1, 0, 6, 0, 8, 0, 0, 0},
                {8, 6, 0, 0, 3, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0}
        };
        sudoku.setGrid(grid);
        assertTrue(sudoku.solve());
    }

    @Test
    public void testWrongInputData() {
        SudokuSolver sudoku = new SudokuModel();
        assertThrows(IllegalArgumentException.class, () -> sudoku.set(4,4,-1));
        assertThrows(IllegalArgumentException.class, () -> sudoku.set(0,0,0));
        assertThrows(IllegalArgumentException.class, () -> sudoku.set(0,0,10));
    }
}
