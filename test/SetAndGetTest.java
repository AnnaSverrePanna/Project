import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SetAndGetTest {
    SudokuSolver sudoku = new SudokuModel();

    @Test
    public void testSetAndGet() {
        sudoku.set(0, 0, 1);
        assertEquals(1, sudoku.get(0, 0));
    }

    @Test
    public void testSetColOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sudoku.set(2, 17, 1));
    }

    @Test
    public void testSetRowOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sudoku.set(17, 2, 1));
    }

    @Test
    public void testSetInvalidDigit() {
        assertThrows(IllegalArgumentException.class, () -> sudoku.set(2, 2, 17));
    }

    @Test
    public void testGetColOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sudoku.get(2, 17));
    }

    @Test
    public void testGetRowOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> sudoku.get(17, 2));
    }
}
