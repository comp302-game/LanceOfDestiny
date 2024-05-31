package tr.edu.ku.View.GameView;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import tr.edu.ku.GameArea.GameArea;

public class CountdownWindow {
    
    private JFrame countdownFrame;
    private JLabel countdownLabel;
    private Timer countdownTimer;
    private int countdownSeconds = 3; // Countdown duration
    private GameArea gameArea;

    public CountdownWindow(GameArea gamearea) {
    
        countdownFrame = new JFrame("Countdown");
        countdownLabel = new JLabel("", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Serif", Font.BOLD, 48));
        
        countdownFrame.add(countdownLabel);
        countdownFrame.setSize(300, 200);
        countdownFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent closing
        countdownFrame.setLocationRelativeTo(null); // Center the countdown window
        countdownFrame.setAlwaysOnTop(true); // Ensure the countdown window is on top
        countdownFrame.setVisible(true);

        this.gameArea = gamearea;
    }

    public void startCountdown() {
        countdownLabel.setText(String.valueOf(countdownSeconds));
        
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownSeconds--;
                if (countdownSeconds > 0) {
                    countdownLabel.setText(String.valueOf(countdownSeconds));
                } else {
                    countdownTimer.stop();
                    countdownFrame.dispose();
                    gameArea.setGameState();
                }
            }
        });
        
        countdownTimer.start();
    }
}
