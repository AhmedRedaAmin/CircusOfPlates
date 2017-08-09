import javax.swing.JPanel;

public class RightPlate extends Plate{

    public RightPlate(String color, int startx, int starty, int shielfWidth, Character clown, JPanel panel) {
        super(color, startx, starty, shielfWidth, clown, panel);
    }

    @Override
    protected void moveIt(JPanel panel) {
        if(move) {
            this.rightchecker(panel);
            this.leftchecker(panel);
            this.setBounds((xcoordinates >= panel.getWidth()-shielfWidth-20)?xcoordinates-=movement:xcoordinates,(xcoordinates < panel.getWidth()-shielfWidth-20)?ycoordinates+=movement:ycoordinates, width, hight);
        } else {
            if (right) {
                this.setBounds(clown.getXCoordinatesRight()-42, ycoordinates, width, hight);
            } else if (left) {
                this.setBounds(clown.getXCoordinatesLeft(), ycoordinates, width, hight);
            }
        }
    }
}
