import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game implements KeyListener {

    private JFrame frame;
    private int counter = 0;
    private LeftPlate leftPlate;
    private RightPlate rightPlate;
    private Shielf leftShielf;
    private Shielf rightShielf;
    private ScoreBoard score;
    private int scoreboardHeight = 50;
    private Character clown;
    private final int windowWidth = 900;
    private final int windowHeight = 600;
    private JPanel panel;
    private final int windowXcoordinate = 0;
    private final int windowYcoordinate = 0;
    private final int plateYcoordinate = 10;
    private int timeBetweenPlates = 2000;
 // 0 for right arrow , 1 for left arrow , 2 for A, 3 for D
    boolean[] moves = { false, false, false, false };
    String[] colors = {"BlackPlate","RedPlate","GreenPlate","BluePlate","YellowPlate"};
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Game window = new Game();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Game() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(windowXcoordinate, windowYcoordinate, windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        panel = new JPanel();
        panel.setBounds(windowXcoordinate, windowYcoordinate, windowWidth, windowHeight);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        score = new ScoreBoard();
        score.setBounds(0, 0, windowWidth, scoreboardHeight);
        panel.add(score);
        
        clown = new Character(windowWidth, windowHeight);
        clown.setBounds(0, windowHeight-clown.getHight()-40, clown.getWidth(), clown.getHight());
        panel.add(clown);
        
        leftShielf = new Shielf();
        rightShielf = new Shielf();
        addLeftPlate();
        addRightPlate();
        ActionListener platesAdder = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                addLeftPlate();
                addRightPlate();
            }
        };
        Timer timer = new Timer(timeBetweenPlates, platesAdder);
        timer.start();
        
        ActionListener scoreSetter = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                score.setScoreOne(clown.getPoints()+"");
            }
        };
        Timer timer2 = new Timer(50, scoreSetter);
        timer2.start();

        leftShielf.setBounds(0, plateYcoordinate+leftPlate.getHight()+scoreboardHeight, leftShielf.getWidth(), leftShielf.getHight());
        panel.add(leftShielf);
        
        rightShielf.setBounds(windowWidth - rightShielf.getWidth(), plateYcoordinate+rightPlate.getHight()+scoreboardHeight, rightShielf.getWidth(), rightShielf.getHight());
        panel.add(rightShielf);
        frame.addKeyListener(this);
    }
    
    private void addLeftPlate() {
        Random rand = new Random();
        int index = rand.nextInt(5);
        leftPlate = new LeftPlate(colors[index], windowXcoordinate, plateYcoordinate+scoreboardHeight, leftShielf.getWidth(),clown, panel);
        leftPlate.setName(counter+"");
        counter++;
        panel.add(leftPlate);
    }
    
    private void addRightPlate() {
        Random rand = new Random();
        int index = rand.nextInt(5);
        rightPlate = new RightPlate(colors[index], windowWidth, plateYcoordinate+scoreboardHeight, rightShielf.getWidth(), clown, panel);
        rightPlate.setName(counter+"");
        counter++;
        panel.add(rightPlate);
    }
    
    public void keyReleased(KeyEvent prs) {
        if (prs.getKeyCode() == KeyEvent.VK_D)
            moves[3] = false;
        if (prs.getKeyCode() == KeyEvent.VK_A)
            moves[2] = false;
        if (prs.getKeyCode() == KeyEvent.VK_RIGHT)
            moves[0] = false;
        if (prs.getKeyCode() == KeyEvent.VK_LEFT)
            moves[1] = false;
        moveNow();
    }

    public void keyPressed(KeyEvent prs) {
        if (prs.getKeyCode() == KeyEvent.VK_D)
            moves[3] = true;
        if (prs.getKeyCode() == KeyEvent.VK_A)
            moves[2] = true;
        if (prs.getKeyCode() == KeyEvent.VK_RIGHT)
            moves[0] = true;
        if (prs.getKeyCode() == KeyEvent.VK_LEFT)
            moves[1] = true;
        moveNow();
    }

    private void moveNow() {
        for (int i = 0; i < moves.length; i++) {
            if (moves[i]) {
                if (i == 0)
                    clown.move_right();
                else if (i == 1)
                    clown.move_left();
                // else if (i == 2)
                // clown2.move_left();
                // else if (i == 3)
                // clown2.move_right();
            }
        }
    }

    public void keyTyped(KeyEvent arg0) {}
}
