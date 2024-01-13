import javax.swing.*;

public class Game {
    private static Game instance = new Game();
    private GameBoard board;
    private GameState currentState;
    private Seed currentPlayer;
    //-----------------------------------------------
    private GameTimer gameTimer;
    private int gameDuration = 30; // domyślny czas gry

    public Game() {
        board = new GameBoard();
        initGame();
    }
    // Publiczna metoda dostępu do instancji
    public static Game getInstance() {
        return instance;
    }
    public void initGame() {
        board.clear();
        currentPlayer = Seed.CROSS;
        currentState = GameState.PLAYING;
    }

    public void updateGameState(Seed theSeed, int row, int col) {
        if (board.hasWon(theSeed, row, col)) {
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {
            currentState = GameState.DRAW;
        }
    }
    // Metody do zmiany aktualnego gracza
    public void changePlayer() {
        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    // Metoda do wykonania ruchu
    public boolean makeMove(int row, int col) {
        if (board.canMove(row, col)) {
            board.setCell(row, col, currentPlayer);
            updateGameState(currentPlayer, row, col);
            changePlayer();
            return true;
        }
        return false;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public Seed getCurrentPlayer() {
        return currentPlayer;
    }


    public void setGameDuration(int seconds) {
        this.gameDuration = seconds;
        if (gameTimer != null) {
            gameTimer.stop();
        }
        gameTimer = new GameTimer(gameDuration, this::endGameDueToTime);
        gameTimer.start();
    }

    private void endGameDueToTime() {
        setCurrentState(GameState.DRAW);

        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Czas minął!");
        });
        // Dodatkowa logika, gdy czas się skończy
    }
}

