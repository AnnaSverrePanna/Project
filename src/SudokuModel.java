import java.util.Arrays;

public class SudokuModel implements SudokuSolver {
    private int[][] grid;

    public SudokuModel() {
        grid = new int[9][9];
        clearAll();
    }

    @Override
    public boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int row, int col) {
        // Om man har kollat igenom hela sudokut har man hittat en lösning
        if (row == 9) {
            return true;
        }

        // Om col är 9 ska den istället gå till nästa rad
        if (col == 9) {
            return solve(row + 1, 0);
        }

        // Om den inte är tom ska man kolla om den är valid och isåfall solve nästa ruta
        if (grid[row][col] != 0) {
            if (isValid(row, col)) {
                return solve(row, col + 1);
            }
            return false;
        }

        // Om rutan är tom ska man testa att sätta in 1-9 och kolla om det går att lösa
        for (int i = 1; i <= grid.length; i++) {
            set(row, col, i);
            if (isValid(row, col) && solve(row, col + 1)) {
                return true;
            }
        }

        // Om det inte går att lösa ska rutan tömmas igen
        clear(row, col);
        return false;
    }

    @Override
    public void set(int row, int col, int digit) {
        if (digit < 1 || digit > 9) {
            throw new IllegalArgumentException("Man får bara sätta in siffror mellan 0 och 9");
        }
        grid[row][col] = digit;
    }

    @Override
    public int get(int row, int col) {
        return grid[row][col];
    }

    @Override
    public void clear(int row, int col) {
        grid[row][col] = 0;
    }

    @Override
    public void clearAll() {
        for (int[] row : grid) {
            Arrays.fill(row, 0);
        }
    }

    @Override
    public boolean isValid(int row, int col) {
        int cell = grid[row][col];

        // Kolla om det är en nolla
        if (cell == 0) return true;

        // Kolla rad
        for (int currentCol = 0; currentCol < 9; currentCol++) {
            int currentCell = grid[row][currentCol];
            if (currentCell == cell && currentCol != col) {
                return false;
            }
        }

        // Kolla kolumn
        for (int currentRow = 0; currentRow < 9; currentRow++) {
            int currentCell = grid[currentRow][col];
            if (currentCell == cell && currentRow != row) {
                return false;
            }
        }

        // Kolla region
        int regionRow = row / 3;
        int regionCol = col / 3;

        for (int currentRow = regionRow * 3; currentRow < regionRow * 3 + 3; currentRow++) {
            for (int currentCol = regionCol * 3; currentCol < regionCol * 3 + 3; currentCol++) {
                int currentCell = grid[currentRow][currentCol];
                if (currentCell == cell && currentRow != row && currentCol != col) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean isAllValid() {
        for (int currentRow = 0; currentRow < 9; currentRow++) {
            for (int currentCol = 0; currentCol < 9; currentCol++) {
                if (!isValid(currentRow, currentCol)) return false;
            }
        }
        return true;
    }

    @Override
    public void setGrid(int[][] m) {
        if (m.length != 9) {
            throw new IllegalArgumentException("Fel mängd rader");
        }
        for (int[] row : m) {
            if (row.length != 9) {
                throw new IllegalArgumentException("Fel mängd kolumner");
            }
        }

        grid = m;
    }

    @Override
    public int[][] getGrid() {
        int[][] gridCopy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            gridCopy[i] = Arrays.copyOf(grid[i], 9);
        }
        return gridCopy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int col : row) {
                sb.append(col);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
