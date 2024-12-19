import org.junit.Test;

import static org.junit.Assert.*;

public class SolveTest {
    SudokuSolver sudoku = new SudokuModel();

    @Test
    public void testSolveEmptySudoku() {
        boolean sudokuResult = sudoku.solve();
        System.out.println(sudoku.toString());
        assertTrue(sudokuResult);
    }

    @Test
    public void testSolveWithSolvableSudoku() {
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
    public void testSolveWithAlreadySolvedSudoku() {
        int[][] grid = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        sudoku.setGrid(grid);
        boolean sudokuResult = sudoku.solve();
        System.out.println(sudoku.toString());
        assertTrue(sudokuResult);
    }

    @Test
    public void testSolveWithNotSolvableSudoku() {
        int[][] grid = {
                {0, 0, 8, 0, 8, 9, 0, 6, 2},
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
        boolean sudokuResult = sudoku.solve();
        System.out.println(sudoku.toString());
        assertFalse(sudokuResult);
    }

    @Test
    public void testSolveNotChangeSetCell() {
        sudoku.set(0,0, 2);
        sudoku.set(4,5, 6);
        sudoku.set(5,4, 9);
        boolean sudokuResult = sudoku.solve();
        System.out.println(sudoku.toString());
        assertEquals(2, sudoku.get(0,0));
        assertEquals(6, sudoku.get(4,5));
        assertEquals(9, sudoku.get(5,4));
    }
}
