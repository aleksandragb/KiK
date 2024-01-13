public class GameBoard {
    private Seed[][] cells;
    private int rows, cols;

    public GameBoard() {
        this(new Builder(3, 3));
    }

    private GameBoard(Builder builder) {
        this.rows = builder.rows;
        this.cols = builder.cols;
        cells = new Seed[rows][cols];
        clear();
    }

    public static class Builder {
        private int rows;
        private int cols;

        public Builder(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
        }

    }

    public void clear() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                cells[row][col] = Seed.EMPTY;
            }
        }
    }

    public boolean isDraw() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (cells[row][col] == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWon(Seed theSeed, int row, int col) {
        // Sprawdzanie wierszy
        boolean winRows = true;
        for (int i = 0; i < cols; i++) {
            if (cells[row][i] != theSeed) {
                winRows = false;
                break;
            }
        }

        // Sprawdzanie kolumn
        boolean winCols = true;
        for (int i = 0; i < rows; i++) {
            if (cells[i][col] != theSeed) {
                winCols = false;
                break;
            }
        }

        // Sprawdzanie przekątnych
        boolean winDiag1 = true;
        boolean winDiag2 = true;
        for (int i = 0; i < rows; i++) {
            if (cells[i][i] != theSeed) {
                winDiag1 = false;
            }
            if (cells[i][rows - i - 1] != theSeed) {
                winDiag2 = false;
            }
        }

        return winRows || winCols || winDiag1 || winDiag2;
    }

    public boolean canMove(int row, int col) {
        return cells[row][col] == Seed.EMPTY;
    }

    public void setCell(int row, int col, Seed seed) {
        cells[row][col] = seed;
    }

}
