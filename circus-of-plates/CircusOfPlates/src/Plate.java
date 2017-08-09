import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Plate extends JLabel {

    private String color;
    private ImageIcon plate;
    private int speed = 50;
    protected int movement = 5;
    protected int xcoordinates;
    protected boolean right = false;
    protected boolean left = false;
    protected int ycoordinates;
    protected int hight;
    protected int width;
    protected int shielfWidth;
    protected Character clown;
    protected boolean move = true;
    
    public Plate(String color, int startx, int starty, int shielfWidth,Character clown, final JPanel panel) {
        this.color = color;
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
                moveIt(panel);
            }
        };
        Timer timer = new Timer(speed, actionListener);
        timer.start();
    }
    
    public String getColor() {
        return color;
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
    
    public int getXcoord() {
        return xcoordinates;
    }
    
    public int getYcoord() {
        return ycoordinates;
    }
    
    protected abstract void moveIt(JPanel panel);
    
    public void stopMove() {
        move = false;
    }
    
    public void setLeft() {
        left = true;
    }
    
    public void setRight() {
        right = true;
    }

    protected void leftchecker(JPanel panel) {
        if(ycoordinates > clown.getYCoordinatesLeft() && ycoordinates < clown.getYCoordinatesLeft() + 10) {
            if (clown.inLeftHand(this)) {
                clown.addInLeftHand(this, panel);
            }
        }
    }
    
    protected void rightchecker(JPanel panel) {
        if(ycoordinates > clown.getYCoordinatesRight() && ycoordinates < clown.getYCoordinatesRight() + 10) {
            if (clown.inRightHand(this)) {
                clown.addInRightHand(this, panel);
            }
        }
    }
}
