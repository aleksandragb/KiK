import javax.swing.Timer;

public class GameTimer {
    private Timer timer;
    private int timeLeft;

    public GameTimer(int seconds, Runnable onTimeOut) {
        timeLeft = seconds;
        timer = new Timer(1000, e -> {
            if (timeLeft > 0) {
                timeLeft--;
            } else {
                timer.stop();
                onTimeOut.run();
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
