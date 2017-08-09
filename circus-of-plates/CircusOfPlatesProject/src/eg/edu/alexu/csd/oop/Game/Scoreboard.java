package eg.edu.alexu.csd.oop.Game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Scoreboard extends JPanel {

    JLabel playerOneName;
    JLabel timer;
    JLabel playerOneScore;
    static JLabel timing;
    String oneScore = "0";
    String time = "0:00";
    static int minuits = 0;
    static int seconds = 0;
    private Timer tim;

    public Scoreboard() {
        setLayout(new GridLayout(2, 3));
        initializeLables();
        add(playerOneName);
        add(timer);
        add(playerOneScore);
        add(timing);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (seconds < 10)
                    Scoreboard.setTimer(minuits + ":0" + seconds);
                else
                    Scoreboard.setTimer(minuits + ":" + seconds);
                if (seconds < 59)
                    seconds++;
                else {
                    minuits++;
                    seconds = 0;
                }
            }
        };
        tim = new Timer(1000, actionListener);
        tim.start();
        if (minuits == 5)
            tim.stop();
    }

    private void initializeLables() {
        playerOneName = new JLabel("Player Points");
        playerOneName.setVerticalAlignment(JLabel.CENTER);
        playerOneName.setHorizontalAlignment(JLabel.CENTER);
        playerOneName.setFont(new Font(playerOneName.getName(), Font.PLAIN, 20));
        timer = new JLabel("Timer");
        timer.setVerticalAlignment(JLabel.CENTER);
        timer.setHorizontalAlignment(JLabel.CENTER);
        timer.setFont(new Font(timer.getName(), Font.PLAIN, 20));
        playerOneScore = new JLabel(oneScore);
        playerOneScore.setVerticalAlignment(JLabel.CENTER);
        playerOneScore.setHorizontalAlignment(JLabel.CENTER);
        playerOneScore.setFont(new Font(playerOneScore.getName(), Font.PLAIN, 20));
        timing = new JLabel(time);
        timing.setVerticalAlignment(JLabel.CENTER);
        timing.setHorizontalAlignment(JLabel.CENTER);
        timing.setFont(new Font(timing.getName(), Font.PLAIN, 20));
    }

    public void setScoreOne(String score) {
        playerOneScore.setText(score);
    }

    public String getScoreOne() {
        return playerOneScore.getText();
    }

    public static void setTimer(String time) {
        timing.setText(time);
    }

    public String getTimer() {
        return timing.getText();
    }
    
    public void pause() {
        tim.stop();
    }
    
    public void start() {
        tim.start();
    }
}