import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearTest {
    SudokuSolver sudoku = new SudokuModel();

    @Test
    public void testClear() {
        sudoku.set(0, 0, 1);
        sudoku.clear(0, 0);
        assertEquals(0, sudoku.get(0, 0));
    }

    @Test
    public void testClearRowOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sudoku.clear(17, 2));
    }

    @Test
    public void testClearColOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sudoku.clear(2, 17));
    }

    @Test
    public void testClearAll() {
        sudoku.set(0, 0, 1);
        sudoku.set(0, 1, 2);
        sudoku.set(0, 4, 5);
        sudoku.clearAll();
        assertEquals(0, sudoku.get(0, 0));
        assertEquals(0, sudoku.get(0, 1));
        assertEquals(0, sudoku.get(0, 4));
    }
}
