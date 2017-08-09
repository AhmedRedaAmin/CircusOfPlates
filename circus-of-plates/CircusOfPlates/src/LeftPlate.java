import javax.swing.JPanel;

public class LeftPlate extends Plate{

    public LeftPlate(String color, int startx, int starty, int shielfWidth, Character clown, JPanel panel) {
        super(color, startx, starty, shielfWidth, clown, panel);
    }

    protected void moveIt(JPanel panel) {
        if (move) {
            this.leftchecker(panel);
            this.rightchecker(panel);
            this.setBounds((xcoordinates < shielfWidth)?xcoordinates+=movement:xcoordinates,(xcoordinates >= shielfWidth)?ycoordinates+=movement:ycoordinates, width, hight);
        } else {
            if (right) {
                this.setBounds(clown.getXCoordinatesRight()-42, ycoordinates, width, hight);
            } else if (left) {
                this.setBounds(clown.getXCoordinatesLeft(), ycoordinates, width, hight);
            }
        }
    }
    
}
