import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private Game game;
    private JButton[][] buttons;
    //----------------------------------
    private JMenuBar menuBar;
    private JMenuItem tenSeconds, thirtySeconds, oneMinute;

    public GameWindow() {
        game = Game.getInstance();
        buttons = new JButton[3][3];
        initUI();
    }

    private void initUI() {
        setTitle("Kółko i Krzyżyk");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].addActionListener(new ButtonListener(row, col));
                add(buttons[row][col]);
            }
        }
        //--------------------------------------------
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Czas Gry");
        menuBar.add(menu);

        tenSeconds = new JMenuItem("10 Sekund");
        thirtySeconds = new JMenuItem("30 Sekund");
        oneMinute = new JMenuItem("1 Minuta");

        tenSeconds.addActionListener(e -> setGameTime(10));
        thirtySeconds.addActionListener(e -> setGameTime(30));
        oneMinute.addActionListener(e -> setGameTime(60));

        menu.add(tenSeconds);
        menu.add(thirtySeconds);
        menu.add(oneMinute);

        setJMenuBar(menuBar);

    }
//---------------------------------------------------
    private void setGameTime(int seconds) {
        game.setGameDuration(seconds);
    }
    //-----------------------------------------------


    private class ButtonListener implements ActionListener {
        private int row, col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (game.getCurrentState() == GameState.PLAYING && game.makeMove(row, col)) {
                buttons[row][col].setText(game.getCurrentPlayer() == Seed.CROSS ? "X" : "O");
                if (game.getCurrentState() == GameState.CROSS_WON) {
                    JOptionPane.showMessageDialog(null, "Kółko wygrywa!");
                } else if (game.getCurrentState() == GameState.NOUGHT_WON) {
                    JOptionPane.showMessageDialog(null, "Krzyżyk wygrywa!");
                } else if (game.getCurrentState() == GameState.DRAW) {
                    JOptionPane.showMessageDialog(null, "Remis!");
                }
            }
        }
    }


}
