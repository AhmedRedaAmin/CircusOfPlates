package eg.edu.alexu.csd.oop.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel {

    public Plate leftPlate;
    public Plate rightPlate;
    public ObjectPoolPlate pooledPlates = ObjectPoolPlate.getInstance();
    private Shielf leftShielf;
    private Shielf rightShielf;
    private Scoreboard score;
    private int scoreboardHeight = 50;
    private Character[] clown = new Character[2];
    private final int windowWidth = 900;
    private final int windowHeight = 600;
    private final int windowXcoordinate = 0;
    private final int windowYcoordinate = 0;
    private final int plateYcoordinate = 10;
    private int plateSpeed;
    private int plateMovement;
    private int clownSpeed;
    private int timeLimits;
    private int timeBetweenPlates;
    private int numOfClowns = 1;
    private Timer timer;
    private boolean paused = false;
    private Level level;
    private int secondCounter = 0;
    /**
     * Create the application.
     */
    public Game() {
        level = new LevelOne();
        initializeLevel();
        initialize();
        putLevelThings();
        ActionListener game = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                initializeLevel();
                putLevelThings();
                secondCounter++;
                if(secondCounter%(timeLimits*60) == 0) {
                    level = level.levelUp();
                    secondCounter = 0;
                }
            }
        };
        Timer lev = new Timer(1000, game);
        lev.start();
        lev.setRepeats(true);
    }

    private void putLevelThings() {
        for (int i = 0; i < numOfClowns; i++)
            clown[i].setSpeed(clownSpeed);
    }

    private void initializeLevel() {
        plateSpeed = level.getSpeedOfPlates();
        plateMovement = level.getPlateMovement();
        clownSpeed = level.getSpeedOfClown();
        timeLimits = level.getTimeLimits();
        timeBetweenPlates = level.getTimeBetweenPlates();
        numOfClowns = level.getNumberOfClowns();
        
        if (level.getNumberOfClowns() == 2) {
            Character second = new Character(windowWidth, windowHeight);
            clown[1] = second;
            add(clown[1]);
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setLayout(null);
        putAllNew();
        setAllAdd();
        setAllTiming();
        setAllBounds();
    }

    private void setAllTiming() {
        ActionListener platesAdder = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                addLeftPlate();
                addRightPlate();
            }
        };
        timer = new Timer(timeBetweenPlates, platesAdder);
        timer.start();

        ActionListener scoreSetter = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int res = 0;
                for(int i = 0; i < numOfClowns; i++) {
                    res += clown[i].getPoints();
                }
                score.setScoreOne(res + "");
            }
        };
        Timer timer2 = new Timer(50, scoreSetter);
        timer2.start();
    }

    private void setAllAdd() {
        add(score);
        for(int i  = 0; i < numOfClowns; i++)
            add(clown[i]);
        addLeftPlate();
        addRightPlate();
        add(leftShielf);
        add(rightShielf);
    }

    private void setAllBounds() {
        setBounds(windowXcoordinate, windowYcoordinate, windowWidth, windowHeight);
        score.setBounds(0, 0, windowWidth, scoreboardHeight);
        for(int i  = 0; i < numOfClowns; i++) {
            clown[i].setBounds(0, windowHeight - clown[i].getHight() - 40, clown[i].getWidth(), clown[i].getHight());
        }
        leftShielf.setBounds(0, plateYcoordinate + leftPlate.getHight() + scoreboardHeight, leftShielf.getWidth(),
                leftShielf.getHight());
        rightShielf.setBounds(windowWidth - rightShielf.getWidth(),
                plateYcoordinate + rightPlate.getHight() + scoreboardHeight, rightShielf.getWidth(),
                rightShielf.getHight());
    }

    private void putAllNew() {
        score = new Scoreboard();
        Character clowne = new Character(windowWidth, windowHeight);
        clown[0] = clowne;
        leftShielf = new Shielf();
        rightShielf = new Shielf();
    }

    private void addLeftPlate() {
        leftPlate = pooledPlates.getPlate("left",colorGenerator(),windowXcoordinate, plateYcoordinate + scoreboardHeight,
                leftShielf.getWidth(), clown, this, plateSpeed, plateMovement);
        add(leftPlate);
    }
    private void addRightPlate() {
        rightPlate = pooledPlates.getPlate("right",colorGenerator(),windowWidth, plateYcoordinate + scoreboardHeight,
                rightShielf.getWidth(), clown, this, plateSpeed, plateMovement);
        add(rightPlate);
    }
    private String colorGenerator() {
        String[] colors = { "BlackPlate", "RedPlate", "GreenPlate", "BluePlate", "YellowPlate" };
        Random rand = new Random();
        int index = rand.nextInt(5);
        return colors[index];
    }
    public int getPanelWidth() {
        return windowWidth;
    }

    public int getPanelHight() {
        return windowHeight;
    }

    public Character[] getClowns() {
        return clown;
    }

    public void pause() {
        score.pause();
        timer.stop();
        paused = true;
    }

    public void start() {
        score.start();
        timer.start();
        paused = false;
    }
    
    public boolean getPaused() {
        return paused;
    }
    
    public int getNumofClowns() {
        return numOfClowns;
    }
    
    public SnapShot saveStateToMemento(){
        Character[] clowns = new Character[2];
        Character clown1 = clown[0].clone();
        clowns[0] = clown1;
        return new SnapShot(level, secondCounter, clowns);
     }

     public void getStateFromMemento(SnapShot memento){
        level = memento.getLevel();
        clown = memento.getClown();
        secondCounter = memento.getTime();
        score.setTimer(secondCounter / 60 + ":" + (secondCounter - (secondCounter / 60)*60));
     }
     
}