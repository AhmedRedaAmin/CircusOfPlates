package eg.edu.alexu.csd.oop.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Plate extends JLabel {

    private String color;
    private ImageIcon plate;
    private int speed;
    protected int movement;
    protected int xcoordinates;
    protected boolean right = false;
    protected boolean left = false;
    protected int ycoordinates;
    protected int hight;
    protected int width;
    protected int shielfWidth;
    protected Character[] clown;
    protected Game panel;
    protected boolean move = true;
    private Timer timer;
    //private ObjectPoolPlate pooledplates = ObjectPoolPlate.getInstance();
    private LeftPlate Lplate;
    private RightPlate Rplate;

    public Plate(String color, int startx, int starty, int shielfWidth,Character[] clown, final Game panel, int speed, int movement) {
        this.color = color;
        this.speed = speed;
        this.movement = movement;
        plate = new ImageIcon(getClass().getResource(color+".png"));
        this.clown = clown;
        width = plate.getIconWidth();
        hight = plate.getIconHeight();
        xcoordinates = startx;
        ycoordinates = starty;
        this.shielfWidth = shielfWidth;
        setIcon(plate);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (!panel.getPaused())
                    moveIt(panel);
            }
        };
        timer = new Timer(this.speed, actionListener);
        timer.start();
    }

    public String getColor() {
        return color;
    }
    public void setColor(String colour) {
        this.color = colour;
    }
    public int getHight() {
        return hight;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setMovement(int movement) {
        this.movement = movement;
    }
    
    public int getXcoord() {
        return xcoordinates;
    }
    
    public int getYcoord() {
        return ycoordinates;
    }
    
    protected abstract void moveIt(Game panel);
    
    public void stopMove() {
        move = false;
    }
    
    public void setLeft() {
        left = true;
    }
    
    public void setRight() {
        right = true;
    }

    protected void leftchecker(Game panel) {
        for(int i  = 0; i < panel.getNumofClowns(); i++) {
        if(ycoordinates > clown[i].getYCoordinatesLeft() && ycoordinates < clown[i].getYCoordinatesLeft() + 10) {
            if (clown[i].inLeftHand(this)) {
                clown[i].addInLeftHand(this, panel);
            }
        }
        }
        Lplate = (LeftPlate) panel.leftPlate;
        if(Lplate.getYcoord()> 900){
        	panel.pooledPlates.createleftPlate(Lplate);

        }
    }
    
    protected void rightchecker(Game panel) {
        for(int i  = 0; i < panel.getNumofClowns(); i++) {
        if(ycoordinates > clown[i].getYCoordinatesRight() && ycoordinates < clown[i].getYCoordinatesRight() + 10) {
            if (clown[i].inRightHand(this)) {
                clown[i].addInRightHand(this, panel);
            }
        }
        }
        Rplate = (RightPlate) panel.rightPlate;
        if(Rplate.getYcoord()> 900){
        	panel.pooledPlates.createrightPlate(Rplate);
        }
    }
    
    public void pause() {
        timer.stop();
    }
    
    public void start() {
        timer.start();
    }
}