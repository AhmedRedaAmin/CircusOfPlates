package eg.edu.alexu.csd.oop.Game;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Play implements KeyListener {

    private JFrame frame;
    private Game game; 
 // 0 for right arrow , 1 for left arrow , 2 for A, 3 for D
    private boolean[] moves = { false, false, false, false };
    private Character[] clown;
    private int width;
    private int hight;
    private boolean paused = false;
    private Button pause;
    private Play a = this;
    private Shots saves;
    private Button save;
    private Button load;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Play window = new Play();
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
    public Play() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        saves = new Shots();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new Game();
        JPanel top = new JPanel(new GridLayout(1, 3));
        pause = new Button("Pause");
        pause.setFocusable(false);
        save = new Button("Save");
        save.setEnabled(false);
        save.setFocusable(false);
        load = new Button("Load");
        load.setEnabled(false);
        load.setFocusable(false);
        pause.setBackground(Color.RED);
        pause.addActionListener(new ButtonListener());
        save.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saves.add(game.saveStateToMemento());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
        load.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                game.getStateFromMemento(saves.get(0));
            }
        });
        
        top.add(pause);
        top.add(save);
        top.add(load);
        clown = game.getClowns();
        width = game.getPanelWidth();
        hight = game.getPanelHight();
        frame.setBounds(0, 0, width, hight+20);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(top,BorderLayout.NORTH);
        frame.getContentPane().add(game,BorderLayout.CENTER);
        frame.addKeyListener(this);
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
                    clown[0].move_right();
                else if (i == 1)
                    clown[0].move_left();
                if (clown[1] != null) {
                    if (i == 2)
                    clown[1].move_left();
                    else if (i == 3)
                    clown[1].move_right();
                }
            }
        }
    }

    public void keyTyped(KeyEvent arg0) {}
    
    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            if(!paused)
            {
                pause.setLabel("Start");
                pause.setBackground(Color.GREEN);
                paused = true;
                game.pause();
                save.setEnabled(true);
                load.setEnabled(true);
                frame.removeKeyListener(a);
            }
            else
            {
                pause.setLabel("Pause");
                pause.setBackground(Color.RED);
                paused = false;
                game.start();
                save.setEnabled(false);
                load.setEnabled(false);
                frame.addKeyListener(a);
            }
        }
    }
}