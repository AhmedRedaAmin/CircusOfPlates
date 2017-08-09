import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Character extends JLabel {

    ImageIcon shapeBody = new ImageIcon(getClass().getResource("ClownBody.png"));
    int characterSpeed = 5;
    Hand rightHand;
    Hand leftHand;
    int xcoordinates;
    int ycoordinates;
    int width;
    int hight;
    int windowhight;
    int limit;
    int points = 0;
    int bottomShift = 30;
    
    public Character(int limit, int windowhight) {
        width = shapeBody.getIconWidth();
        hight = shapeBody.getIconHeight();
        this.limit = limit;
        rightHand = new Hand(this);
        leftHand = new Hand(this);
        this.windowhight = windowhight;
        xcoordinates = 0;
        ycoordinates = windowhight-hight-bottomShift;
        putItOn();
    }

    private void putItOn() {
        this.setIcon(shapeBody);
        this.setBounds(xcoordinates, 0, width, hight);
    }
    
    public boolean addInRightHand(Plate rightPlate, JPanel panel) {
        rightPlate.setRight();
        boolean flag = rightHand.addPlate(rightPlate, panel);
        if (flag)
            points++;
        return flag;
    }
    
    public boolean addInLeftHand(Plate leftPlate, JPanel panel) {
        leftPlate.setLeft();
        boolean flag = leftHand.addPlate(leftPlate, panel);
        if (flag)
            points++;
        return flag;
    }
    
    public int getPoints () {
        return points;
    }
    
    public boolean inRightHand(Plate rightPlate) {
        return rightHand.inRangeRight(rightPlate);
    }
    
    public boolean inLeftHand(Plate leftPlate) {
        return leftHand.inRangeLeft(leftPlate);
    }
    
    public int getHight() {
        return hight;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getXCoordinatesLeft() {
        return xcoordinates;
    }
    
    public int getXCoordinatesRight() {
        return xcoordinates + width;
    }
    
    public int getYCoordinatesLeft() {
        return ycoordinates - leftHand.platesHight()-10;
    }
    
    public int getYCoordinatesRight() {
        return ycoordinates - rightHand.platesHight()-10;
    }
    
    public void speedUp() {
        characterSpeed++;
    }
    
    public void move_right() {
        if (xcoordinates < limit - width)
            xcoordinates += characterSpeed;
        moveIt();
    }

    public void move_left() {
        if (xcoordinates > 0)
            xcoordinates -= characterSpeed;
        moveIt();
    }

    private void moveIt() {
        this.setBounds(xcoordinates, ycoordinates, width, hight);
    }
}
