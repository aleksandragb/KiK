public class Main {
    public static void main(String[] args) {
        // Uruchomienie interfejsu użytkownika w bezpiecznym dla wątków sposób
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Tworzenie i wyświetlanie okna gry
                GameWindow gameWindow = new GameWindow();
                gameWindow.setVisible(true);
            }
        });
    }
}

