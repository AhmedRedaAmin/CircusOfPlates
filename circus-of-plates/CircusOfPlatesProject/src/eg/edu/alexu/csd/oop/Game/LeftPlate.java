package eg.edu.alexu.csd.oop.Game;

import javax.swing.JPanel;

public class LeftPlate extends Plate{

    public LeftPlate(String color, int startx, int starty, int shielfWidth, Character[] clown, Game panel, int speed, int movement) {
        super(color, startx, starty, shielfWidth, clown, panel, speed, movement);
    }

    protected void moveIt(Game panel) {
        if (move) {
            this.leftchecker(panel);
            this.rightchecker(panel);
            this.setBounds((xcoordinates < shielfWidth)?xcoordinates+=movement:xcoordinates,(xcoordinates >= shielfWidth)?ycoordinates+=movement:ycoordinates, width, hight);
        } else {
            if (right) {
                //Edit here
                this.setBounds(clown[0].getXCoordinatesRight()-42, ycoordinates, width, hight);
            } else if (left) {
                // Edit here
                this.setBounds(clown[0].getXCoordinatesLeft(), ycoordinates, width, hight);
            }
        }
    }
    
}